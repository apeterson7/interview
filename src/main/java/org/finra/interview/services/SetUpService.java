package org.finra.interview.services;

import org.finra.interview.models.Candidate;
import org.finra.interview.models.Question;
import org.finra.interview.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Arrays;

@Service
public class SetUpService {

    @Autowired
    CandidateRepository candidateRepository;

    @Autowired
    InterviewService interviewService;

    public void setUp(){
        Candidate candidate1 = Candidate.builder()
                .username("candidate1")
                .password("baudrillard")
                .firstname("Alex")
                .lastname("Peterson")
                .email("test@test.test")
                .notes("Decent...")
                .status("new")
                .build();

        Candidate candidate2 = Candidate.builder()
                .username("candidate2")
                .password("roygbiv")
                .firstname("Test")
                .lastname("Test")
                .email("test@test.test")
                .notes("Excellent!!")
                .status("new")
                .build();

        Candidate candidate3 = Candidate.builder()
                .username("candidate3")
                .password("roygbiv")
                .firstname("Person1")
                .lastname("Test")
                .email("test@test.test")
                .notes("In progress")
                .status("new")
                .build();

        Candidate candidate4 = Candidate.builder()
                .username("candidate4")
                .password("roygbiv")
                .firstname("Person2")
                .lastname("Test")
                .email("test@test.test")
                .notes("In progress")
                .status("new")
                .build();

        Candidate candidate5 = Candidate.builder()
                .username("candidate5")
                .password("roygbiv")
                .firstname("Person3")
                .lastname("Test")
                .email("test@test.test")
                .notes("In progress")
                .status("new")
                .build();

        Candidate candidate6 = Candidate.builder()
                .username("candidate6")
                .password("roygbiv")
                .firstname("Person4")
                .lastname("Test")
                .email("test@test.test")
                .notes("In progress")
                .status("new")
                .build();

        Question q1 = Question.builder().name("question1").level("senior").type("single_answer").text("text1").answer("answer1").score(-1).build();
        Question q2 = Question.builder().name("question2").level("mid").type("single_answer").text("text2").answer("answer3").score(-1).build();
        Question q3 = Question.builder().name("question3").level("junior").type("single_answer").text("text3").answer("answer3").score(-1).build();

        candidate1.setQuestions(Arrays.asList(q1,q2));
        candidate2.setQuestions(Arrays.asList(q2,q3));

//        Response response1 = Response.builder().Question(q1).candidate(candidate1).build();
//        Response response2 = Response.builder().Question(q2).candidate(candidate1).build();
//        Response response3 = Response.builder().Question(q2).candidate(candidate2).build();
//        Response response4 = Response.builder().question(q3).candidate(candidate2).build();



//        candidate1.setResponses(Arrays.asList(response1, response2));
//        candidate2.setResponses(Arrays.asList(response3, response4));

//        Interviewer interviewer = Interviewer.builder().email("test@test.test").firstname("alex").lastname("peterson").username("interviewer1").password("pword").build();

//        interviewer.addCandidate(candidate1);
//        interviewer.addCandidate(candidate2);

//        interviewerRepository.save(interviewer);import lombok.

        candidateRepository.saveAll(Arrays.asList(candidate1, candidate2, candidate3, candidate4, candidate5, candidate6));


//        responseRepository.saveAll(Arrays.asList(response1,response2,response3,response4));



        // fetch all categories
//        for (Candidate candidate : candidateRepository.findAll()) {
//            System.out.println((candidate.toString()));
//        }
    }
}
