package by.itransition.mathtasksapp.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Secured("USER")
public class UserController {
    @GetMapping("/user")
    public String info(){
        return "user";
    }
}
