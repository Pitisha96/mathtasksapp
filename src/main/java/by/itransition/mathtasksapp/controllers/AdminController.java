package by.itransition.mathtasksapp.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Secured("ADMIN")
public class AdminController {
    @GetMapping("/admin")
    public String info(){
        return "admin";
    }
}
