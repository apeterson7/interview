package org.finra.interview.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import lombok.extern.log4j.Log4j;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//hibernate
@Entity
@Table(name = "CANDIDATE")
@DynamicUpdate
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "candidate_id")
//lombok
@Log4j
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

    @Column(nullable = false, name="STATUS")
    private String status;
    //new, interviewing, review, hired, rejected

    @ManyToMany(
            cascade = CascadeType.ALL
    )
    @JoinTable(name = "CANDIDATE_QUESTION",
            joinColumns = { @JoinColumn(name = "CANDIDATE_ID") },
            inverseJoinColumns = { @JoinColumn(name = "QUESTION_ID") })
    private List<Question> questions = new ArrayList<>();

//    @JsonManagedReference
    @Setter(AccessLevel.NONE)
    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "candidate"
    )
    private List<Interview> interviews = new ArrayList<>();


    /**
     * Safe methods to protect consistency
     *
     * @param interview
     */
    public void addInterview(Interview interview){
        if(interviews.contains(interview))
            return;

        interviews.add(interview);

        interview.setCandidate(this);

    }

    public void removeInterview(Interview interview){
        if(!interviews.contains(interview))
            return;

        interviews.remove(interview);

        interview.setCandidate(null);
    }


    @CreationTimestamp
    @Column(name = "CREATED_TS")
    private LocalDateTime created_ts;

    @UpdateTimestamp
    @Column(name = "REVIEWED_TS")
    private LocalDateTime updated_ts;

    //question ids that have been assigned
//    List<Question> questionList;
//
//    @OneToMany
//    Question[] questionList;

//    List<Response> responseList;

}
