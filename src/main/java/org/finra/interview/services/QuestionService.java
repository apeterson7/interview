package org.finra.interview.services;

import lombok.extern.log4j.Log4j;
import org.finra.interview.exceptions.QuestionNotFoundException;
import org.finra.interview.models.Question;
import org.finra.interview.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository){
        this.questionRepository = questionRepository;
    }

    public List<Question> list(){
        return (List) questionRepository.findAll();
    }

    public Question findById(Long id) throws QuestionNotFoundException{
        return questionRepository.findById(id)
                .orElseThrow(() -> new QuestionNotFoundException("Question "+id+" does not exit."));
    }

    public void deleteById(Long id) throws QuestionNotFoundException{
        this.findById(id);
        questionRepository.deleteById(id);
    }

    public Question save(Question question){
        return questionRepository.save(question);
    }

    //get questions by filter

}
