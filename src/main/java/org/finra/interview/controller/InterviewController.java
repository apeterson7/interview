package org.finra.interview.controller;

import lombok.extern.log4j.Log4j;
import org.finra.interview.exceptions.InterviewNotFoundException;
import org.finra.interview.exceptions.QuestionNotFoundException;
import org.finra.interview.models.Candidate;
import org.finra.interview.models.Interview;
import org.finra.interview.models.Question;
import org.finra.interview.services.EmailService;
import org.finra.interview.services.InterviewService;
import org.finra.interview.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Log4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class InterviewController {

    private final InterviewService interviewService;
    private final EmailService emailService;


    @Autowired
    public InterviewController(InterviewService interviewService,
                               EmailService emailService){
        this.interviewService = interviewService;
        this.emailService = emailService;
    }

    @GetMapping("/interviews")
    @CrossOrigin
    public Iterable<Interview> findAll(){ return interviewService.list(); }

    //changed
    @GetMapping("/interview/{id}")
    public Interview findById(@PathVariable UUID id) throws InterviewNotFoundException { return interviewService.findById(id); }


    @PutMapping("/interview")
    @ResponseStatus(HttpStatus.CREATED)
    public Interview save(@RequestBody Interview interview){

        return interviewService.save(interview);

    }

}
