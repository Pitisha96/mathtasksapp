package by.itransition.mathtasksapp.controllers;

import by.itransition.mathtasksapp.services.TagService;
import by.itransition.mathtasksapp.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String greeting(Model model){
        model.addAttribute("lastPublished",taskService.getLast5Published());
        model.addAttribute("topRating",taskService.getFirst5Rating());
        model.addAttribute("lastTags",tagService.getLast12Tags());
        return "greeting";
    }
}
