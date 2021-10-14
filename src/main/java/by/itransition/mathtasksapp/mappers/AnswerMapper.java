package by.itransition.mathtasksapp.mappers;

import by.itransition.mathtasksapp.dto.AnswerDto;
import by.itransition.mathtasksapp.models.Answer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AnswerMapper {
    AnswerMapper INSTANCE= Mappers.getMapper(AnswerMapper.class);
    AnswerDto answerToAnswerDto(Answer answer);
}
