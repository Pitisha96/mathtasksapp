package by.itransition.mathtasksapp.services;

import by.itransition.mathtasksapp.models.Rating;
import by.itransition.mathtasksapp.models.Task;

public interface RatingService {
    Double getRatingByTask(Task task);
    Rating save(Rating rating);
}
