package by.itransition.mathtasksapp.services;

import by.itransition.mathtasksapp.dto.AnswerDto;
import by.itransition.mathtasksapp.models.Answer;
import by.itransition.mathtasksapp.models.Task;

import java.util.List;

public interface AnswerService {
    Answer save(Answer answer);
    List<Answer> saveAllByAnswerStrings(String[] answers, Task task);
    List<Answer> updateAllByAnswerStrings(String[] answers,Task task);
    List<AnswerDto> getAllAnswerDtoByTask(Task task);
    boolean containsAnswer(Task task,String answer);
    List<Answer> getAllByTaskId(Long id);
}
