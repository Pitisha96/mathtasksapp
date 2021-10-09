package by.itransition.mathtasksapp.mappers;

import by.itransition.mathtasksapp.dto.TagDto;
import by.itransition.mathtasksapp.models.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TagMapper {
    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);
    TagDto tagToTagDto(Tag tag);
}
