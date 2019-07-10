package org.finra.interview.controller;

import lombok.extern.log4j.Log4j;
import org.finra.interview.exceptions.CandidateNotFoundException;
import org.finra.interview.exceptions.QuestionAlreadyAssignedException;
import org.finra.interview.models.Candidate;
import org.finra.interview.models.Interview;
import org.finra.interview.models.Question;
import org.finra.interview.services.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j
@CrossOrigin
@RestController
@RequestMapping("/api/candidates")
public class CandidateController {

    private final CandidateService candidateService;

    @Autowired
    public CandidateController(CandidateService candidateService){
        this.candidateService = candidateService;
    }

    @GetMapping
    public Iterable<Candidate> findAll(){
        return candidateService.list();
    }

    @GetMapping("/{id}")
    public Candidate findById(@PathVariable Long id) throws CandidateNotFoundException{
        return candidateService.findById(id);
    }

    @PutMapping("/add-interview/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void createInterviewForCandidateById(@RequestBody Interview interview, @PathVariable Long id)
            throws CandidateNotFoundException{
        System.out.println(interview);


        //Retrieve request candidate
        Candidate candidate =
                candidateService.findById(id);

        //Create new Interview instance
//        Interview interview = new Interview();

        interview.setStatus("new");

        //Add to candidate
        candidate.addInterview(interview);

        //Set status to pending
        candidate.setStatus("pending");
        System.out.println(candidate.getInterviews().get(0).getCandidate().getFirstname());

        //Persist candidate
        candidateService.save(candidate);

    }

    @PutMapping("/{id}")
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



    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCandidateById(@PathVariable Long id) throws CandidateNotFoundException{
        candidateService.removeCandidateById(id);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Candidate create(@RequestBody Candidate candidate){
        return candidateService.save(candidate);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Candidate update(@RequestBody Candidate candidate) throws CandidateNotFoundException{
        return candidateService.update(candidate);
    }

}
