package org.finra.interview.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class QuestionResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //Whose response
    private Candidate candidate;

    //question to which it responds
    private Question question;


}
