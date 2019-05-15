package org.finra.interview.services;

import lombok.extern.log4j.Log4j;
import org.finra.interview.models.Question;
import org.finra.interview.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j
@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> list(){
        return (List) questionRepository.findAll();
    }

    //post question

    //get questions by filter

}
