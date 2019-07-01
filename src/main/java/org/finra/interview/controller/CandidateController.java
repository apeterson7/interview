package org.finra.interview.controller;

import lombok.extern.log4j.Log4j;
import org.finra.interview.exceptions.CandidateNotFoundException;
import org.finra.interview.exceptions.QuestionAlreadyAssignedException;
import org.finra.interview.models.Candidate;
import org.finra.interview.models.Question;
import org.finra.interview.services.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/candidates")
public class CandidateController {

    private final CandidateService candidateService;

    @Autowired
    public CandidateController(CandidateService candidateService){
        this.candidateService = candidateService;
    }

    @GetMapping
    @CrossOrigin
    public Iterable<Candidate> findAll(){
        return candidateService.list();
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Candidate findById(@PathVariable Long id) throws CandidateNotFoundException{
        return candidateService.findById(id);
    }

    @PutMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    @ResponseStatus(HttpStatus.OK)
    public void addQuestionsToCandidateById(@RequestBody List<Question> questions, @PathVariable Long id)
            throws CandidateNotFoundException
    {
        candidateService.addQuestionsToCandidateById(questions, id);
    }


    @DeleteMapping("/{id}")
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    public void deleteCandidateById(@PathVariable Long id) throws CandidateNotFoundException{
        candidateService.removeCandidateById(id);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Candidate create(@RequestBody Candidate candidate){
        return candidateService.save(candidate);
    }

}
