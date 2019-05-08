package org.finra.interview;

import org.finra.interview.models.Question;
import org.finra.interview.services.QuestionService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InterviewApplicationTests {

	@Autowired
	private QuestionService questionService;

	@Test
	public void contextLoads() {
	}



	@Test
	public void whenApplicationStarts_thenHibernateCreatesInitialRecords() {
		List<Question> questions = questionService.list();

		Assert.assertEquals(questions.size(), 3);
		System.out.println(questions.get(1));
	}
}
