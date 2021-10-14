package by.itransition.mathtasksapp.controllers;

import by.itransition.mathtasksapp.models.*;
import by.itransition.mathtasksapp.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String search(@RequestParam("search") String searchLine,Model model){
        model.addAttribute("tasks",taskService.searchTasks(searchLine));
        System.out.println(searchLine);
        return "search_results";
    }

    @GetMapping("/form_task/{task}")
    public String showForm(Model model,@AuthenticationPrincipal OAuth2User principal,
                           @PathVariable String task){
        model.addAttribute("themes",themeService.getAll());
        model.addAttribute("username",principal.getAttributes().get("username"));
        System.out.println(task);
        /*model.addAttribute("action",task==null?"post":"put");
        if(task!=null)
            model.addAttribute("task",taskService.getById(task));*/
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
                return "redirect:../form_task/"+id;
            model.addAttribute("solved",user.getSolvedTasks().contains(task));
            model.addAttribute("username",principal.getAttributes().get("username"));
        }
        model.addAttribute("task",task);
        model.addAttribute("images",imageService.getAllByTask(task));
        return "task";
    }
}
