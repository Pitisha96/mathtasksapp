package by.itransition.mathtasksapp.controllers.rest;

import by.itransition.mathtasksapp.models.Rating;
import by.itransition.mathtasksapp.models.Task;
import by.itransition.mathtasksapp.services.TaskService;
import by.itransition.mathtasksapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController
public class RatingController {
    private final UserService userService;
    private final TaskService taskService;


    @Autowired
    public RatingController(UserService userService, TaskService taskService) {

        this.userService = userService;
        this.taskService = taskService;
    }

    @GetMapping("rating/{id}")
    public Double get(@PathVariable Long id){
        return taskService.getRatingByTask(new Task(id));
    }

    @PostMapping("rating/{id}")
    public Boolean vote(@AuthenticationPrincipal OAuth2User principal,
                        @PathVariable("id") Long id,
                        @RequestParam("rating") Double rating){
        userService.addVoted((Long) principal.getAttributes().get("id"),
                taskService.addRatingByTask(new Rating(rating),
                        taskService.getById(id)));
        return true;
    }
}
