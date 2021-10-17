package by.itransition.mathtasksapp.services.Impl;

import by.itransition.mathtasksapp.models.Rating;
import by.itransition.mathtasksapp.models.Task;
import by.itransition.mathtasksapp.repositories.RatingRepository;
import by.itransition.mathtasksapp.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;

    @Autowired
    public RatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    public Double getRatingByTask(Task task) {
        Double rating = ratingRepository.getAvgRatingByTaskId(task.getId());
        return rating==null?0d:rating;
    }

    @Override
    public Rating save(Rating rating) {
        return ratingRepository.save(rating);
    }
}
