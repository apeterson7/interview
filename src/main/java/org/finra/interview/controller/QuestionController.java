package org.finra.interview.controller;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.finra.interview.exceptions.QuestionNotFoundException;
import org.finra.interview.models.Question;
import org.finra.interview.repositories.QuestionRepository;
import org.finra.interview.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.apache.log4j.Logger;

import javax.validation.Valid;
import java.util.List;


@Log4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class QuestionController {

//    static Logger log = Logger.getLogger(QuestionController.class);

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService){
        this.questionService = questionService;
    }

    @GetMapping("questions")
    @CrossOrigin
    public Iterable<Question> findAll(){ return questionService.list(); }

    //changed
    @GetMapping("question/{id}")
    public Question findById(@PathVariable Long id) throws QuestionNotFoundException{ return questionService.findById(id); }

    //changed
    @PostMapping("/question")
    @ResponseStatus(HttpStatus.CREATED)
    public Question create(@RequestBody Question question){
        System.out.println(question.toString());
        return questionService.save(question);
    }

    @PostMapping("/questions")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Question> saveAll(@RequestBody List<Question> questions){ return questionService.saveAll(questions); }

    //changed
    @DeleteMapping("question/{id}")
    public void delete(@PathVariable Long id) throws QuestionNotFoundException{ questionService.deleteById(id); }


}
