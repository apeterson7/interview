package org.finra.interview.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Log4j
@Entity
@Table(name = "CANDIDATE")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Candidate {
    @Id
    @GeneratedValue(generator = "candidate_generator")
    @SequenceGenerator(
            name = "candidate_generator",
            sequenceName = "candidate_sequence",
            initialValue = 1000
    )
    @Column(name = "CANDIDATE_ID", unique = true, nullable = false)
    private long candidate_id;

    @Column(nullable = false, unique = true, name="USERNAME")
    private String username;

    @Column(nullable = false, name="PASSWORD")
    private String password;

    @Column(nullable = false, name="EMAIL")
    private String email;

    @Column(nullable = false, name="FIRSTNAME")
    private String firstname;

    @Column(nullable = false,  name="LASTNAME")
    private String lastname;

    @Column(name="NOTES")
    private String notes;

    @Column(name="STATUS")
    private String status;
    //new, interviewing, review, hired, rejected

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "CANDIDATE_QUESTION",
            joinColumns = { @JoinColumn(name = "CANDIDATE_ID") },
            inverseJoinColumns = { @JoinColumn(name = "QUESTION_ID") })
    private List<Question> questions = new ArrayList<>();
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "interviewer_id")
    private Interviewer interviewer;


    //question ids that have been assigned
//    List<Question> questionList;
//
//    @OneToMany
//    Question[] questionList;

//    List<Response> responseList;

}
