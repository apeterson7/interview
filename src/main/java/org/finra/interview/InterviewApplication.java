package org.finra.interview;


import org.finra.interview.models.Candidate;
import org.finra.interview.models.Question;
import org.finra.interview.repositories.CandidateRepository;
import org.finra.interview.services.SetUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@EnableJpaRepositories()
@EntityScan("org.finra.interview.models")
//@RestController
//@CrossOrigin
@SpringBootApplication
public class InterviewApplication {


//	@RequestMapping("/resource")
//	public Map<String,Object> home() {
//		Map<String,Object> model = new HashMap<String,Object>();
//		model.put("id", UUID.randomUUID().toString());
//		model.put("content", "Hello World");
//		return model;
//	}

	public static void main(String[] args) {
		SpringApplication.run(InterviewApplication.class, args);

	}

}
