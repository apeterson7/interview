package org.finra.interview.controller;

import lombok.extern.log4j.Log4j;
import org.finra.interview.models.Candidate;
import org.finra.interview.models.Response;
import org.finra.interview.repositories.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j
@RestController
@CrossOrigin
@RequestMapping("/api/responses")
public class ResponseController {

    @Autowired
    private ResponseRepository responseRepository;

    @GetMapping
    @CrossOrigin
    public Iterable<Response> findAll(){

        return responseRepository.findAll();
    }

}
