package by.itransition.mathtasksapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GreetingPageController {
    @GetMapping("/")
    public String greeting(){
        return "greeting";
    }
}
