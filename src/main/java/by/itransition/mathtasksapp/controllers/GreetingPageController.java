package by.itransition.mathtasksapp.controllers;

import by.itransition.mathtasksapp.services.TagService;
import by.itransition.mathtasksapp.services.TaskService;
import by.itransition.mathtasksapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GreetingPageController {
    private final TaskService taskService;
    private final TagService tagService;

    @Autowired
    public GreetingPageController(TaskService taskService, TagService tagService) {
        this.taskService = taskService;
        this.tagService = tagService;
    }

    @GetMapping("/")
    public String greeting(@AuthenticationPrincipal OAuth2User principal, Model model){
        model.addAttribute("lastPublished",taskService.getLastPublished(5));
        model.addAttribute("topRating",taskService.getFirstRating(5));
        model.addAttribute("lastTags",tagService.getLastTags(12));
        if(principal!=null)
            model.addAttribute("username", principal.getAttributes().get("username"));
        return "greeting";
    }
}
