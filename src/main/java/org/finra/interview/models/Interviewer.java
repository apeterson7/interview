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
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Interviewer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "INTERVIEWER_ID")
    private long interviewer_id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "FIRSTNAME")
    private String firstname;

    @Column(name = "LASTNAME")
    private String lastname;

    @Column(name = "EMAIL")
    private String email;
//
    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "interviewer"
    )
    private List<Candidate> candidates = new ArrayList<>();

    public void addCandidate(Candidate candidate){
        this.candidates.add(candidate);
        candidate.setInterviewer(this);
    }

    public void removeCandidate(Candidate candidate){
        this.candidates.remove(candidate);
        candidate.setInterviewer(null);
    }
}
