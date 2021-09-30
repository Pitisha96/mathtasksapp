package by.itransition.mathtasksapp.services.Impl;

import by.itransition.mathtasksapp.models.Tag;
import by.itransition.mathtasksapp.repositories.TagRepository;
import by.itransition.mathtasksapp.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public List<Tag> getLast12Tags() {
        return tagRepository.findLast12Tags();
    }
}
