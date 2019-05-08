package org.finra.interview.services;

import org.finra.interview.models.Question;
import org.finra.interview.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> list(){
        return questionRepository.findAll();
    }


}
