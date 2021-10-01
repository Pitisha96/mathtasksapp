package by.itransition.mathtasksapp.controllers;

import by.itransition.mathtasksapp.models.Tag;
import by.itransition.mathtasksapp.models.Task;
import by.itransition.mathtasksapp.models.Theme;
import by.itransition.mathtasksapp.models.User;
import by.itransition.mathtasksapp.services.TagService;
import by.itransition.mathtasksapp.services.TaskService;
import by.itransition.mathtasksapp.services.ThemeService;
import by.itransition.mathtasksapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Controller
public class TaskController {
    private final UserService userService;
    private final ThemeService themeService;
    private final TagService tagService;
    private final TaskService taskService;

    @Autowired
    public TaskController(UserService userService, ThemeService themeService, TagService tagService, TaskService taskService) {
        this.userService = userService;
        this.themeService = themeService;
        this.tagService = tagService;
        this.taskService = taskService;
    }

    @GetMapping("/form_task")
    public String showForm(Model model){
        model.addAttribute("themes",themeService.getAll());
        return "form_task";
    }

    @PostMapping("/task")
    public String add(@AuthenticationPrincipal OAuth2User principal,
                      @RequestParam("theme") Long themeId,
                      @RequestParam(value = "name",required = false) String name,
                      @RequestParam(value = "content",required = false) String content,
                      @RequestParam(value = "tags",required = false) String stringTags){
        Set<Tag> tags = new HashSet<>();
        for (String tagName : stringTags.split(" ")) {
            tags.add(tagService.save(new Tag(tagName)));
        }
        taskService.save(new Task(name,content,new Date(),new Theme(themeId),tags,
                new User((Long) principal.getAttributes().get("id"))));
        return "redirect:/user";
    }

    @GetMapping("/task/{id}")
    public String get(@PathVariable("id") Long id,Model model){
        model.addAttribute(taskService.getById(id));
        return "task";
    }
}
