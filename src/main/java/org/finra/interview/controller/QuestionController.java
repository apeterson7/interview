package org.finra.interview.controller;

import lombok.extern.slf4j.Slf4j;
import org.finra.interview.models.Question;
import org.finra.interview.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping
    public Iterable<Question> findAll(){
        return questionRepository.findAll();
    }

    @GetMapping("/{id}")
    public Question findOne(@PathVariable Long id){
        return questionRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Question create(@RequestBody Question question){
        return questionRepository.save(question);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        questionRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        questionRepository.deleteById(id);
    }

}
