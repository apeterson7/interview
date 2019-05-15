package org.finra.interview.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Log4j
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String username;

    private String password;

    private String email;

    private String firstname;

    private String lastname;

    private String notes;

    //question ids that have been assigned
    List<Question> questionList;

    List<QuestionResponse> responseList;

    String status;
    //new, ready, pending-review, hired, rejected

}
