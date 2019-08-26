package org.finra.interview.services;

import lombok.extern.log4j.Log4j;
import org.finra.interview.exceptions.CandidateNotFoundException;
import org.finra.interview.exceptions.QuestionAlreadyAssignedException;
import org.finra.interview.exceptions.QuestionNotFoundException;
import org.finra.interview.models.Candidate;
import org.finra.interview.models.Question;
import org.finra.interview.repositories.CandidateRepository;
import org.finra.interview.repositories.QuestionRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import java.math.BigInteger;
import java.util.List;
import java.util.Timer;
import java.util.stream.Collector;

import static java.util.stream.Collectors.toList;

@Log4j
@Service
public class CandidateService {

    private final CandidateRepository candidateRepository;
    private final S3Service s3Service;


    private SessionFactory sessionFactory;

    @Autowired
    public CandidateService(CandidateRepository candidateRepository, S3Service s3Service){
        this.candidateRepository = candidateRepository;
        this.s3Service = s3Service;
    }

    public Iterable<Candidate> list(){
        return candidateRepository.findAll();
    }

    public Candidate findById(Long id) throws CandidateNotFoundException{
        return candidateRepository.findById(id).orElseThrow(
                () -> new CandidateNotFoundException("Question "+id+" does not exit.")
        );
    }

//    @Transactional
    //Saves all child questions and interviews
    public Candidate save(Candidate candidate) {

        return candidateRepository.save(candidate);
    }

    /**
     *  Updates a select set of fields [status, firstname, lastname, email, notes]
     *  Other fields are not persisted
     *
     */

    public Candidate update(Candidate updatedCandidate) throws CandidateNotFoundException{
        Long id = updatedCandidate.getCandidate_id();

        log.info("updating candidate " + id);
        System.out.println("updating candidate " + id);

        Candidate currentCandidate = candidateRepository.findById(id)
                .orElseThrow(() -> new CandidateNotFoundException("Question "+id+" does not exit."));

        currentCandidate.setStatus(updatedCandidate.getStatus());
        currentCandidate.setFirstname(updatedCandidate.getFirstname());
        currentCandidate.setLastname(updatedCandidate.getLastname());
        currentCandidate.setEmail(updatedCandidate.getEmail());
        currentCandidate.setNotes(updatedCandidate.getNotes());

        return candidateRepository.save(currentCandidate);
    }

    public void updateStatus(Long id, Integer status) throws CandidateNotFoundException{

        Candidate candidate = this.findById(id);
        candidate.setStatus(status);
        this.save(candidate);

//        StopWatch stopWatch = new StopWatch();
//
//        stopWatch.start();
//        candidateRepository.updateStatus(id,status);
//        stopWatch.stop();
//
//        stopWatch.getTotalTimeMillis();
//        log.info("time " + stopWatch.getTotalTimeMillis());

//        return this.findById(id);

    }

//    @Transactional
    public void addQuestionsToCandidateById(List<Question> questions, Long id) throws CandidateNotFoundException {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new CandidateNotFoundException("Question "+id+" does not exit."));
        candidate.setQuestions(questions);
        candidateRepository.save(candidate);
    }

////    @Transactional
//    public void removeCandidateById(Long id) throws CandidateNotFoundException{
//        candidateRepository.findById(id)
//                .orElseThrow(() -> new CandidateNotFoundException("Question "+id+" does not exit."));
//        candidateRepository.deleteById(id);
//        s3Service.deleteFileFromS3(id.toString());
//    }

    public List<Candidate> getCandidatesForTag(String tag){

        //Convert from BigInteger value to Long
        List<Long> longIds = candidateRepository.getCandidatesForTag(tag)
                .stream().map(id -> id.longValue()).collect(toList());

        return (List) candidateRepository.findAllById(longIds);

    }

    public List<String> getTags(){
        return candidateRepository.getTags();
    }

    public List<Candidate> getCandidatesForTag(List<String> tags){

        //Convert from BigInteger value to Long
        List<Long> longIds = candidateRepository.getCandidatesForTag(tags)
                .stream().map(id -> id.longValue()).collect(toList());

        return (List) candidateRepository.findAllById(longIds);

    }


}
