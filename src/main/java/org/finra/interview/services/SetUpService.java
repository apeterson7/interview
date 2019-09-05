package org.finra.interview.services;

import org.finra.interview.models.Candidate;
import org.finra.interview.models.Question;
import org.finra.interview.models.User;
import org.finra.interview.repositories.CandidateRepository;
import org.finra.interview.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Service
public class SetUpService {

    @Autowired
    CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @Autowired
    UserRepository userRepository;

//    @EventListener(ApplicationReadyEvent.class)
    public void setUp(){
        Candidate candidate1 = Candidate.builder()
                .username("Sean")
                .password("baudrillard")
                .firstname("Alex")
                .lastname("Peterson")
                .email("test@test.test")
                .notes("Decent...")
                .tags(new HashSet<>(Arrays.asList("junior")))
                .status(1)
                .build();

        Candidate candidate2 = Candidate.builder()
                .username("Connery")
                .password("roygbiv")
                .firstname("Sean")
                .lastname("Connery")
                .email("test@test.test")
                .notes("Excellent!!")
                .tags(new HashSet<>(Arrays.asList("senior","lead","aws")))
                .status(1)
                .build();

        Candidate candidate3 = Candidate.builder()
                .username("candidate3")
                .password("roygbiv")
                .firstname("Lorrie")
                .lastname("Moore")
                .email("test@test.test")
                .notes("In progress")
                .tags(new HashSet<>(Arrays.asList("windows","legacy")))
                .status(1)
                .build();

        Candidate candidate4 = Candidate.builder()
                .username("candidate4")
                .password("roygbiv")
                .firstname("Junot")
                .lastname("Diaz")
                .email("test@test.test")
                .notes("In progress")
                .tags(new HashSet<>(Arrays.asList("architect","java","node.js")))
                .status(1)
                .build();

        Candidate candidate5 = Candidate.builder()
                .username("candidate5")
                .password("roygbiv")
                .firstname("Phillip")
                .lastname("Roth")
                .email("test@test.test")
                .notes("In progress")
                .tags(new HashSet<>(Arrays.asList("senior","devops")))
                .status(1)
                .build();

        Candidate candidate6 = Candidate.builder()
                .username("candidate6")
                .password("roygbiv")
                .firstname("Maggie")
                .lastname("Nelson")
                .email("test@test.test")
                .notes("In progress")
                .tags(new HashSet<>(Arrays.asList("sdet","qa","selenium")))
                .status(1)
                .build();

        Question q1 = Question.builder().name("Favorite Color").level("senior").text("What is your favorite color?").answer("Maroon").build();
        Question q2 = Question.builder().name("City of Birth").level("mid").text("In which city were you born?").answer("Boston").build();
        Question q3 = Question.builder().name("Year Born").level("junior").text("What year were you born?").answer("'94").build();

        candidate1.setQuestions(Arrays.asList(q1,q2));
        candidate2.setQuestions(Arrays.asList(q2,q3));

        candidateRepository.saveAll(Arrays.asList(candidate1, candidate2, candidate3, candidate4, candidate5, candidate6));


        // Create users
        User dan = new User("dan",passwordEncoder.encode("dan123"),"USER","");
        User admin = new User("admin",passwordEncoder.encode("admin123"),"ADMIN","ACCESS_TEST1,ACCESS_TEST2");
        User manager = new User("manager",passwordEncoder.encode("manager123"),"MANAGER","ACCESS_TEST1");

        List<User> users = Arrays.asList(dan,admin,manager);

        // Save to db
        userRepository.saveAll(users);
    }
}
