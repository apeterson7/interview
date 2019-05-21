package org.finra.interview.controller;

import lombok.extern.log4j.Log4j;
import org.finra.interview.exceptions.CandidateNotFoundException;
import org.finra.interview.exceptions.QuestionAlreadyAssigneException;
import org.finra.interview.models.Candidate;
import org.finra.interview.models.Question;
import org.finra.interview.repositories.CandidateRepository;
import org.finra.interview.services.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j
@RestController
@CrossOrigin
@RequestMapping("/api/candidates")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @GetMapping
    @CrossOrigin
    public Iterable<Candidate> findAll(){

        return candidateService.list();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void addQuestionById(@RequestBody Question question, @PathVariable Long id)
            throws CandidateNotFoundException, QuestionAlreadyAssigneException
    {
        candidateService.addQuestionById(question, id);
    }
}
