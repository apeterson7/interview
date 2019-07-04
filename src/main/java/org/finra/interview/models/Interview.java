package org.finra.interview.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

import javax.persistence.*;
import java.util.List;


@Log4j
@Entity
@Table(name = "CANDIDATE")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Interview {

    @Id
    @GeneratedValue(generator = "interview_generator")
    @SequenceGenerator(
            name = "interview_generator",
            sequenceName = "interview_sequence",
            initialValue = 1000
    )
    @Column(name = "INTERVIEW_ID", unique = true, nullable = false)
    private String interview_id;


    private Candidate candidate;

    private User interviewer;

    @Column(nullable = false, name="PASSWORD")
    private String status;

    @ManyToMany
    private List<Question> questions;

    @OneToMany
    private List<Response> responses;
}
