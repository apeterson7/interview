package org.finra.interview;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.finra.interview.models.Question;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

import static org.testng.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {InterviewApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class LiveTest {
    private static final String API_ROOT
            = "http://localhost:8081/api/books";

    private Question createRandomQuestion(){
        Question question = Question.builder()
                .level(new String[]{"junior", "mid", "senior"}[new Random().nextInt(3)])
                .name(new String[]{"question1", "question2", "question3"}[new Random().nextInt(3)])
                .text(new String[]{"text1", "text2", "text3"}[new Random().nextInt(3)])
                .type(new String[]{"single", "multi", "other"}[new Random().nextInt(3)])
                .answer(new String[]{"answer1", "answer2", "answer3"}[new Random().nextInt(3)])
                .build();
        return question;
    }

    private String createQuestionUri(Question question){
        Response response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(question)
                .post(API_ROOT);
        return API_ROOT+"/"+ response.jsonPath().get("id");
    }

    @Test
    public void whenGetAllBooks_thenOK(){
        Response response = RestAssured.get(API_ROOT);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }

}
