package org.finra.interview.services;

import lombok.extern.log4j.Log4j;
import org.finra.interview.exceptions.CandidateNotFoundException;
import org.finra.interview.exceptions.QuestionAlreadyAssigneException;
import org.finra.interview.exceptions.QuestionNotFoundException;
import org.finra.interview.models.Candidate;
import org.finra.interview.models.Question;
import org.finra.interview.repositories.CandidateRepository;
import org.finra.interview.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j
@Service
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    public Iterable<Candidate> list(){
        return candidateRepository.findAll();
    }

    public void addQuestionById(Question question, Long id) throws CandidateNotFoundException, QuestionAlreadyAssigneException{
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new CandidateNotFoundException("Question "+id+" does not exit."));
        List<Question> questions = candidate.getQuestions();
        if(questions.contains(question)){
            throw new QuestionAlreadyAssigneException("Candidate "+candidate.getCandidate_id()+" is already assigned question_id "+question.getQuestion_id());
        }else{
            questions.add(question);
            candidate.setQuestions(questions);
        }
        candidateRepository.save(candidate);
    }
    //get Questions

    //get responses

    //update responses (all or none)

    //get status

    //set status

}
