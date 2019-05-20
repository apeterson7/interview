package org.finra.interview;

import org.finra.interview.models.Candidate;
import org.finra.interview.models.Question;
import org.finra.interview.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import sun.tools.jar.CommandLine;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
\
@EnableJpaRepositories()
@EntityScan("org.finra.interview.models")
@SpringBootApplication
public class InterviewApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(InterviewApplication.class, args);
	}

	@Autowired
	private CandidateRepository candidateRepository;

	@Override
	@Transactional
	public void run(String... strings) throws Exception {
		// save a couple of categories
		final Candidate candidate1 = new Candidate();
		candidate1.setUserName("hello");

		Set<Question> questions = new HashSet<>();
		questions.add(new Question("Book A1", categoryA));
		questions.add(new Question("Book A2", categoryA));
		questions.add(new Question("Book A3", categoryA));
		candidate1.setQuestions(questions);


		Set<Question> questions2 = new HashSet<>();
		questions2.add(new Question());
		questions2.add(new Question());
		questions2.add(new Question());
		candidate2.setQuestions(questions2);

		candidateRepository.saveAll(Arrays.asList(candidate1, candidate2));

		// fetch all categories
		for (Candidate candidate : candidateRepository.findAll()) {
			System.out.println((candidate.toString()));
		}
	}
}
