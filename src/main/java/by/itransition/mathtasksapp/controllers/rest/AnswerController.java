package by.itransition.mathtasksapp.controllers.rest;

import by.itransition.mathtasksapp.models.Task;
import by.itransition.mathtasksapp.services.AnswerService;
import by.itransition.mathtasksapp.services.TaskService;
import by.itransition.mathtasksapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnswerController {
    private final AnswerService answerService;
    private final TaskService taskService;
    private final UserService userService;

    @Autowired
    public AnswerController(AnswerService answerService, TaskService taskService, UserService userService) {
        this.answerService = answerService;
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping("/checkAnswer/{task}")
    public boolean checkAnswer(@PathVariable("task") Long task, @RequestParam("answer") String answer,
                               @AuthenticationPrincipal OAuth2User principal){
        Task currentTask=taskService.getById(task);
        if(answerService.containsAnswer(currentTask,answer)){
            userService.addSolved((Long)principal.getAttributes().get("id"),currentTask);
            return true;
        }
        return false;
    }
}
