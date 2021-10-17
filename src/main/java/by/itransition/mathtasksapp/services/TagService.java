package by.itransition.mathtasksapp.services;

import by.itransition.mathtasksapp.dto.TagDto;
import by.itransition.mathtasksapp.models.Tag;

import java.util.List;

public interface TagService {
    Tag save(Tag tag);
    List<Tag> getLastTags(int count);
    List<TagDto> getFirstDto(int limit);
    List<TagDto> getAllByNameContains(String name);
    List<Tag> saveTagsByTagString(String tagString);
    Tag findByName(String name);
}
