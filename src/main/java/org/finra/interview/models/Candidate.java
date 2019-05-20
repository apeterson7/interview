package org.finra.interview.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Log4j
@Entity
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
    @Column(name = "ID", unique = true, nullable = false)
    private long id;

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
    //new, ready, pending-review, hired, rejected

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "post")
    private Set<Question> comments = new HashSet<>();


    //question ids that have been assigned
//    List<Question> questionList;
//
//    @OneToMany
//    Question[] questionList;

//    List<QuestionResponse> responseList;



}
