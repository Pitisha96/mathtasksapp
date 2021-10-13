package by.itransition.mathtasksapp.services.Impl;

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
            if(answer!=null)
                result.add(save(new Answer(answer,task)));
        }
        return result;
    }
}
