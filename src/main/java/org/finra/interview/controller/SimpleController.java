package org.finra.interview.controller;

import org.finra.interview.models.Candidate;
import org.finra.interview.models.Interviewer;
import org.finra.interview.models.Question;
import org.finra.interview.repositories.CandidateRepository;
import org.finra.interview.repositories.InterviewerRepository;
import org.finra.interview.services.SetUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/")
public class SimpleController {
    @Value("${spring.application.name}")
    String appName;

    @Autowired
    SetUpService setUpService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddl;

    @GetMapping()
    public String homePage(Model model) {
        model.addAttribute("appName", appName);

        if(ddl.equals("create")){
            setUpService.setUp();
        }

        return "home";
    }

//    @GetMapping("setup")
//    public List<Interviewer> setup() {
//
//        setUpService.setUp();
//
////        return (List) interviewerRepository.findAll();
//
//    }
}
