package org.finra.interview.services;

import lombok.extern.log4j.Log4j;
import org.finra.interview.exceptions.InterviewNotFoundException;
import org.finra.interview.exceptions.QuestionNotFoundException;
import org.finra.interview.models.Candidate;
import org.finra.interview.models.Interview;
import org.finra.interview.repositories.InterviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Log4j
@Service
public class InterviewService {

    InterviewRepository interviewRepository;

    @Autowired
    public InterviewService(InterviewRepository interviewRepository){
        this.interviewRepository = interviewRepository;
    }

    public Iterable<Interview> list(){
        return interviewRepository.findAll();
    }

    public Interview findById(UUID id) throws InterviewNotFoundException{
        return interviewRepository.findById(id).orElseThrow(() -> new InterviewNotFoundException("Interview "+id+" does not exit."));
    }

    public Interview save(Interview interview){

        return interviewRepository.save(interview);
    }
}
