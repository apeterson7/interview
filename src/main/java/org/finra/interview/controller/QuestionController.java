package org.finra.interview.controller;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.finra.interview.exceptions.QuestionNotFoundException;
import org.finra.interview.models.Question;
import org.finra.interview.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.apache.log4j.Logger;


@Log4j

@RestController
@RequestMapping("/api/questions")
public class QuestionController {


//    static Logger log = Logger.getLogger(QuestionController.class);

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping
    public Iterable<Question> findAll(){

//        log.info("test");

        return questionRepository.findAll();
    }

    @GetMapping("/{id}")
    public Question findOne(@PathVariable Long id) throws QuestionNotFoundException{
        return questionRepository.findById(id)
                .orElseThrow(() -> new QuestionNotFoundException("Question "+id+" does not exit."));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Question create(@RequestBody Question question){
        return questionRepository.save(question);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) throws QuestionNotFoundException{
        questionRepository.findById(id)
                .orElseThrow(() -> new QuestionNotFoundException("Question "+id+" does not exit."));
        questionRepository.deleteById(id);
    }

}
