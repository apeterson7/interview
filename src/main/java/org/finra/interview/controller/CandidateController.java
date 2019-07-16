package org.finra.interview.controller;

import lombok.extern.log4j.Log4j;
import org.finra.interview.exceptions.CandidateNotFoundException;
import org.finra.interview.exceptions.InterviewNotFoundException;
import org.finra.interview.exceptions.QuestionAlreadyAssignedException;
import org.finra.interview.models.Candidate;
import org.finra.interview.models.Interview;
import org.finra.interview.models.Question;
import org.finra.interview.models.Response;
import org.finra.interview.services.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Log4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class CandidateController {

    private final CandidateService candidateService;

    @Autowired
    public CandidateController(CandidateService candidateService){
        this.candidateService = candidateService;
    }

    @GetMapping("/candidates")
    public Iterable<Candidate> findAll(){
        return candidateService.list();
    }

    @GetMapping("/candidate/{id}")
    public Candidate findById(@PathVariable Long id) throws CandidateNotFoundException{
        return candidateService.findById(id);
    }

    @PutMapping("/candidate/add-interview/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void createInterviewForCandidateById(@RequestBody Interview interview, @PathVariable Long id)
            throws CandidateNotFoundException{
//        System.out.println(interview);

        //Retrieve request candidate
        Candidate candidate =
                candidateService.findById(id);

        //Set Up Interview with status 1 'New'
        interview.setStatus(1);

        //Add new empty response objects for each question
        List<Response> responses = new ArrayList<>();
        candidate.getQuestions().forEach(question ->
        {
            Response response = Response.builder()
                    .question(question)
                    .response("")
                    .build();

            responses.add(response);
        });

        interview.setResponses(responses);

        //Remove questions from candidate
        candidate.setQuestions(new ArrayList<>());

        //Add to candidate
        candidate.addInterview(interview);

        //Set status to status 2 'Interview In Progress'
        candidate.setStatus(2);

        //Persist candidate
        candidateService.save(candidate);

    }

    @PutMapping("/candidate/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void addQuestionsToCandidateById(@RequestBody List<Question> questions, @PathVariable Long id)
            throws CandidateNotFoundException
    {
        candidateService.addQuestionsToCandidateById(questions, id);
    }

//    @PutMapping("/{id}/update")
//    @CrossOrigin(origins = "http://localhost:4200")
//    @ResponseStatus(HttpStatus.OK)
//    public Candidate addQuestionsToCandidateById(@RequestBody Candidate candidate)
//    {
//        return candidateService.save(candidate);
//    }

    @DeleteMapping("/candidate/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCandidateById(@PathVariable Long id) throws CandidateNotFoundException{
        candidateService.removeCandidateById(id);

    }

    @PostMapping("/candidates")
    @ResponseStatus(HttpStatus.CREATED)
    public Candidate create(@RequestBody Candidate candidate){
        return candidateService.save(candidate);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Candidate update(@RequestBody Candidate candidate) throws CandidateNotFoundException{
        log.info("PUT update candidate");
        System.out.println("PUT update candidate");
        return candidateService.update(candidate);
    }

    @PutMapping("/candidate/{id}/status/{status}")
    @ResponseStatus(HttpStatus.OK)
    public void updateStatus(@PathVariable Long id, @PathVariable Integer status) throws CandidateNotFoundException {
        log.info("HERE CANDIDATE UPDATE "+id+" STATUS "+status);

        candidateService.updateStatus(id,status);

    }

}
