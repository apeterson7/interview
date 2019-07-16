package org.finra.interview.services;

import lombok.extern.log4j.Log4j;
import org.finra.interview.exceptions.CandidateNotFoundException;
import org.finra.interview.exceptions.InterviewNotFoundException;
import org.finra.interview.exceptions.QuestionNotFoundException;
import org.finra.interview.models.Candidate;
import org.finra.interview.models.Interview;
import org.finra.interview.repositories.InterviewRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import java.util.List;
import java.util.UUID;

@Log4j
@Service
public class InterviewService {

    InterviewRepository interviewRepository;

    private SessionFactory sessionFactory;


    @Autowired
    public InterviewService(InterviewRepository interviewRepository){
        this.interviewRepository = interviewRepository;
    }

    public Iterable<Interview> list(){
        List<Interview> interviews = (List) interviewRepository.findAll();


        return interviews;

    }

    public Interview findById(UUID id) throws InterviewNotFoundException{
        return interviewRepository.findById(id).orElseThrow(() -> new InterviewNotFoundException("Interview "+id+" does not exit."));
    }


//    @Transactional
    public Interview save(Interview interview){
        return interviewRepository.save(interview);
    }

    public Interview update(Interview updatedInterview) throws InterviewNotFoundException{
        UUID id = updatedInterview.getInterview_id();
        Interview currentInterview = interviewRepository.findById(id)
                .orElseThrow(() -> new InterviewNotFoundException("Interview "+id+" does not exist"));

        currentInterview.setStatus(updatedInterview.getStatus());
//        currentInterview.setCandidate(updatedInterview.getCandidate());

        return interviewRepository.save(currentInterview);
    }


//    @Transactional
    public Interview updateStatus(UUID id, Integer status) throws InterviewNotFoundException{

        System.out.println(1);
        Interview interview = this.findById(id);

        System.out.println(2);
        interview.setStatus(3);

        System.out.println(3);
        interview.getCandidate().setStatus(status);

        System.out.println(4);
        Interview interview1 = this.save(interview);

        System.out.println(5);
        return interview1;

//        StopWatch stopWatch = new StopWatch();
//
//        stopWatch.start();
//        interviewRepository.updateStatus(id,status);
//        stopWatch.stop();
//
//        stopWatch.getTotalTimeMillis();
//        log.info("time " + stopWatch.getTotalTimeMillis());
//
//
//        return this.findById(id);

    }


}
