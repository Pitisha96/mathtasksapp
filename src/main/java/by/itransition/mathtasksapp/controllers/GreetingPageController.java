package by.itransition.mathtasksapp.controllers;

import by.itransition.mathtasksapp.models.User;
import by.itransition.mathtasksapp.repositories.RoleRepository;
import by.itransition.mathtasksapp.repositories.UserRepository;
import by.itransition.mathtasksapp.services.RoleService;
import by.itransition.mathtasksapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GreetingPageController {
    private final UserService userService;

    @Autowired
    public GreetingPageController(UserService userService) {
        this.userService=userService;
    }

    @GetMapping("/")
    public String greeting(@AuthenticationPrincipal OAuth2User principal,Model model){
        return "greeting";
    }
}
