package org.finra.interview.controller;

import lombok.extern.log4j.Log4j;
import org.finra.interview.models.Question;
import org.finra.interview.models.Response;
import org.finra.interview.repositories.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/responses")
public class ResponseController {

    @Autowired
    ResponseRepository responseRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<Response> saveAll(@RequestBody List<Response> responses){ return (List<Response>) responseRepository.saveAll(responses); }


}
