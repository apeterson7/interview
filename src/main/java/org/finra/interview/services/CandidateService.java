package org.finra.interview.services;

import lombok.extern.log4j.Log4j;
import org.finra.interview.models.Question;
import org.finra.interview.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j
@Service
public class CandidateService {

    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> list(){
        return (List) questionRepository.findAll();
    }

    //get Questions

    //get responses

    //update responses (all or none)

    //get status

    //set status

}
