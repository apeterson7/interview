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
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Interviewer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String username;

    private String password;

    private String firstname;

    private String lastname;

    private String email;
//
//    @OneToMany
//    private Candidate[] candidateList;
}
