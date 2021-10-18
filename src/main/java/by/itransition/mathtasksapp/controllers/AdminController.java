package by.itransition.mathtasksapp.controllers;

import by.itransition.mathtasksapp.services.TaskService;
import by.itransition.mathtasksapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String info(Model model, @AuthenticationPrincipal OAuth2User principal){
        model.addAttribute("users",userService.getAllByRoleId(1L));
        model.addAttribute("username",principal.getAttributes().get("username"));
        model.addAttribute("userService",userService);
        return "admin";
    }
}
