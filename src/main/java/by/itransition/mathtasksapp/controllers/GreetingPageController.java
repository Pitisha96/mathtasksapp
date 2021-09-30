package by.itransition.mathtasksapp.controllers;

import by.itransition.mathtasksapp.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GreetingPageController {
    private final TaskService taskService;

    @Autowired
    public GreetingPageController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String greeting(Model model){
        model.addAttribute("lastPublished",taskService.getLastPublished());
        return "greeting";
    }
}
