package by.itransition.mathtasksapp.services;

import by.itransition.mathtasksapp.models.Tag;

import java.util.List;

public interface TagService {
    Tag save(Tag tag);
    List<Tag> getLast12Tags();
}
