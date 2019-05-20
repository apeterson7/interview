package org.finra.interview.controller;

import lombok.extern.log4j.Log4j;
import org.finra.interview.models.Candidate;
import org.finra.interview.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j
@RestController
@CrossOrigin
@RequestMapping("/api/candidates")
public class CandidateController {

    @Autowired
    private CandidateRepository candidateRepository;

    @GetMapping
    @CrossOrigin
    public Iterable<Candidate> findAll(){

        return candidateRepository.findAll();
    }
}
