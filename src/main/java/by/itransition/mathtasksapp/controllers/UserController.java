package by.itransition.mathtasksapp.controllers;

import by.itransition.mathtasksapp.models.User;
import by.itransition.mathtasksapp.services.TaskService;
import by.itransition.mathtasksapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    private final UserService userService;
    private final TaskService taskService;

    @Autowired
    public UserController(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }

    @GetMapping("/user")
    public String info(Model model, @AuthenticationPrincipal OAuth2User principal){
        User user = userService.getById((Long) principal.getAttributes().get("id"));
        model.addAttribute("countTasks",userService.countPublishedTasks(user));
        model.addAttribute("countSolved",userService.countSolved(user));
        model.addAttribute("tasks",taskService.findAllByOwner(user));
        model.addAttribute("username",user.getUsername());
        return "user";
    }
}
