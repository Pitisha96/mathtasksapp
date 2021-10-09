package by.itransition.mathtasksapp.controllers.rest;

import by.itransition.mathtasksapp.dto.TagDto;
import by.itransition.mathtasksapp.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TagController {
    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/tags")
    public List<TagDto> get(@RequestParam(value = "limit",required = false) Integer limit,
                            @RequestParam(value = "name",required = false) String name){
        return name==null?tagService.getFirstDto(limit):tagService.getAllByNameContains(name);
    }
}
