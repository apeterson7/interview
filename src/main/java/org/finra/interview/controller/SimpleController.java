package org.finra.interview.controller;

import org.finra.interview.services.EmailService;
import org.finra.interview.services.SetUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class SimpleController {
    @Value("${spring.application.name}")
    String appName;

    @Autowired
    SetUpService setUpService;

    @Autowired
    EmailService emailService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddl;

    @GetMapping()
    public String homePage(Model model) {
        model.addAttribute("appName", appName);

//        if(ddl.equals("create")){
//            setUpService.setUp();
//        }

//        emailService.sendEmail("alexcpeterson7@gmail.com","test","test");

        setUpService.setUp();


        return "home";
    }


}
