package by.itransition.mathtasksapp.services.Impl;

import by.itransition.mathtasksapp.dto.AnswerDto;
import by.itransition.mathtasksapp.mappers.AnswerMapper;
import by.itransition.mathtasksapp.models.Answer;
import by.itransition.mathtasksapp.models.Task;
import by.itransition.mathtasksapp.repositories.AnswerRepository;
import by.itransition.mathtasksapp.services.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AnswerServiceImpl implements AnswerService {
    private final AnswerRepository answerRepository;

    @Autowired
    public AnswerServiceImpl(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Override
    public Answer save(Answer answer) {
        return answerRepository.save(answer);
    }

    @Override
    public List<Answer> saveAllByAnswerStrings(String[] answers, Task task) {
        List<Answer> result =new LinkedList<>();
        for(String answer:answers){
            if(answer!=null&&!answer.isEmpty())
                result.add(save(new Answer(answer,task)));
        }
        return result;
    }

    @Override
    public List<AnswerDto> getAllAnswerDtoByTask(Task task) {
        return answerRepository.findAllByTask(task).stream()
                .map(AnswerMapper.INSTANCE::answerToAnswerDto).collect(Collectors.toList());
    }

    @Override
    public boolean containsAnswer(Task task, String answer) {
        return getAllAnswerDtoByTask(task).stream()
                .map(AnswerDto::getContent).collect(Collectors.toList()).contains(answer);
    }

    @Override
    public List<Answer> getAllByTaskId(Long id) {
        return answerRepository.findAllByTask(new Task(id));
    }
}
