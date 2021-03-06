package by.itransition.mathtasksapp.services.Impl;

import by.itransition.mathtasksapp.dto.TagDto;
import by.itransition.mathtasksapp.mappers.TagMapper;
import by.itransition.mathtasksapp.models.Tag;
import by.itransition.mathtasksapp.models.Task;
import by.itransition.mathtasksapp.repositories.TagRepository;
import by.itransition.mathtasksapp.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public Tag save(Tag tag) {
        Optional<Tag> tagFromDB = tagRepository.findByName(tag.getName());
        if(tagFromDB.isPresent()){
            return tagFromDB.get();
        }
        return tagRepository.save(tag);
    }

    @Override
    public List<Tag> getLastTags(int limit) {
        return tagRepository.findLastTagsByLimit(limit);
    }

    @Override
    public List<TagDto> getFirstDto(int limit) {
        return tagRepository.findLastTagsByLimit(limit).stream()
                .map(TagMapper.INSTANCE::tagToTagDto).collect(Collectors.toList());
    }

    @Override
    public List<TagDto> getAllByNameContains(String name) {
        return tagRepository.findAllByNameContaining(name).stream()
                .map(TagMapper.INSTANCE::tagToTagDto).collect(Collectors.toList());
    }

    @Override
    public List<Tag> saveTagsByTagString(String stringTags) {
        List<Tag> tags = new LinkedList<>();
        if(!stringTags.isEmpty()){
            for (String tagName : stringTags.split(" ")) {
                if(!tagName.equals(" ")){
                    tags.add(save(new Tag(tagName)));
                }
            }
        }
        return tags;
    }

    @Override
    public Tag findByName(String name) {
        return tagRepository.findByName(name).orElse(new Tag());
    }
}
