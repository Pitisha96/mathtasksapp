package by.itransition.mathtasksapp.controllers;

import by.itransition.mathtasksapp.models.*;
import by.itransition.mathtasksapp.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Controller
public class TaskController {
    private final ThemeService themeService;
    private final TagService tagService;
    private final TaskService taskService;
    private final ImageService imageService;
    private final AnswerService answerService;
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public TaskController(ThemeService themeService,
                          TagService tagService, TaskService taskService,
                          ImageService imageService, AnswerService answerService, UserService userService, RoleService roleService) {
        this.themeService = themeService;
        this.tagService = tagService;
        this.taskService = taskService;
        this.imageService = imageService;
        this.answerService = answerService;
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/search")
    public String search(@RequestParam("search") String searchLine,
                         Model model,@AuthenticationPrincipal OAuth2User principal){
        model.addAttribute("tasks",taskService.searchTasks(searchLine));
        if(principal!=null)
            model.addAttribute("username",principal.getAttributes().get("username"));
        return "search_results";
    }

    @GetMapping("/tag_task")
    public String tags(@RequestParam("search") String tagName,
                       Model model,@AuthenticationPrincipal OAuth2User principal){
        model.addAttribute("tasks",taskService.findAllByTagName(tagName));
        if(principal!=null)
            model.addAttribute("username",principal.getAttributes().get("username"));
        return "search_results";
    }

    @GetMapping("/add/task")
    public String showForm(Model model,@AuthenticationPrincipal OAuth2User principal){
        model.addAttribute("themes",themeService.getAll());
        model.addAttribute("username",principal.getAttributes().get("username"));
        model.addAttribute("action","create");
        return "form_task";
    }

    @GetMapping("/update/task/{id}")
    public String showForm(Model model,@AuthenticationPrincipal OAuth2User principal,
                           @PathVariable Long id){
        model.addAttribute("themes",themeService.getAll());
        model.addAttribute("username",principal.getAttributes().get("username"));
        model.addAttribute("action","update");
        model.addAttribute("task",taskService.getById(id));
        model.addAttribute("tags",taskService.getAllTagsByTaskId(id));
        model.addAttribute("answers",taskService.getAllAnswersByTaskId(id));
        model.addAttribute("taskImages",taskService.getAllImagesByTaskId(id));
        return "form_task";
    }

    @PostMapping("/task")
    public String add(@AuthenticationPrincipal OAuth2User principal,
                      @RequestParam("theme") Long themeId,
                      @RequestParam("name") String name,
                      @RequestParam("content") String content,
                      @RequestParam(value = "tags",required = false) String stringTags,
                      @RequestParam(value = "file1",required = false)MultipartFile file1,
                      @RequestParam(value = "file2",required = false)MultipartFile file2,
                      @RequestParam(value = "file3",required = false)MultipartFile file3,
                      @RequestParam("answer1") String answer1,
                      @RequestParam(value = "answer2",required = false)String answer2,
                      @RequestParam(value = "answer3",required = false)String answer3){
        Task task = taskService.save(
                new Task(name, content, new Date(), new Theme(themeId),
                        tagService.saveTagsByTagString(stringTags),
                        new User((Long) principal.getAttributes().get("id"))
                        ));
        task.setImages(imageService
                .saveImagesByMultipartFileArray(
                        new MultipartFile[]{file1,file2,file3},task));
        task.setAnswers(answerService
                .saveAllByAnswerStrings(
                        new String[]{answer1,answer2,answer3},task));
        taskService.save(task);
        return "redirect:/user";
    }

    @GetMapping("/task/{id}")
    public String get(@PathVariable("id") Long id,Model model,@AuthenticationPrincipal OAuth2User principal){
        Task task = taskService.getById(id);
        if(principal!=null){
            User user = userService.getById((Long) principal.getAttributes().get("id"));
            if(taskService.findAllByOwner(user).contains(task)||
                    user.getRoles().contains(roleService.getRoleById(2)))
                return "redirect:../update/task/"+id;
            model.addAttribute("solved",user.getSolvedTasks().contains(task));
            model.addAttribute("username",principal.getAttributes().get("username"));
            model.addAttribute("voted",user.getVotedTasks().contains(task));
        }
        model.addAttribute("task",task);
        model.addAttribute("images",imageService.getAllByTaskId(task.getId()));
        return "task";
    }

    @PostMapping("/task/{id}")
    public String update(@PathVariable Long id,
                         @AuthenticationPrincipal OAuth2User principal,
                         @RequestParam("theme") Long themeId,
                         @RequestParam("name") String name,
                         @RequestParam("content") String content,
                         @RequestParam(value = "tags",required = false) String stringTags,
                         @RequestParam(value = "file1",required = false)MultipartFile file1,
                         @RequestParam(value = "file2",required = false)MultipartFile file2,
                         @RequestParam(value = "file3",required = false)MultipartFile file3,
                         @RequestParam("answer1") String answer1,
                         @RequestParam(value = "answer2",required = false)String answer2,
                         @RequestParam(value = "answer3",required = false)String answer3){
        Task task = taskService.getById(id);
        task.setName(name);
        task.setContent(content);
        task.setTheme(new Theme(themeId));
        List<Tag> tags = task.getTags();//?
        tags.addAll(tagService.saveTagsByTagString(stringTags));
        task.setTags(tags);

        task.setImages(imageService
                .updateImagesByMultipartFileArray(
                        new MultipartFile[]{file1,file2,file3},task));
        task.setAnswers(answerService
                .updateAllByAnswerStrings(
                        new String[]{answer1,answer2,answer3},task));
        taskService.save(task);
        return "redirect:/user";
    }
}
