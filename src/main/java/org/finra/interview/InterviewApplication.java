package org.finra.interview;


import org.finra.interview.models.Candidate;
import org.finra.interview.models.Question;
import org.finra.interview.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Arrays;


@EnableJpaRepositories()
@EntityScan("org.finra.interview.models")
@SpringBootApplication
public class InterviewApplication {

	@Autowired
	CandidateRepository candidateRepository;

	public static void main(String[] args) {
		SpringApplication.run(InterviewApplication.class, args);

	}

}
