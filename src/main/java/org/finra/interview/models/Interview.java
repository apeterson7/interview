package org.finra.interview.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import lombok.extern.log4j.Log4j;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;


//Hibernate
@Entity
@Table(name = "INTERVIEW")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "interview_id")
//Lombok
@Log4j
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Interview {

    @Id
//    @GeneratedValue(generator = "interview_generator")
//    @SequenceGenerator(
//            name = "interview_generator",
//            sequenceName = "interview_sequence",
//            initialValue = 1000
//    )
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "INTERVIEW_ID", unique = true, nullable = false)
    private UUID interview_id;

//    private User interviewer;

    @Column(nullable = false, name="STATUS")
    private String status;

//    @JsonBackReference
    @Setter(AccessLevel.NONE)
    @ManyToOne(
//            fetch = FetchType.LAZY
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "CANDIDATE_ID")
    private Candidate candidate;

    /**
     *
     * Set new candidate.  This method keeps relationship consistency:
     * * this candidate is removed from the previous interview
     * * this candidate is added to the next interview
     *
     */

    public void setCandidate(Candidate candidate){
        if(sameAsFormer(candidate))
            return;

        //set new candidate
        Candidate oldCandidate = this.candidate;
        this.candidate = candidate;

        //remove from old owner
        if(oldCandidate != null){
            oldCandidate.removeInterview(this);
        }

        //add to new owner
        if(candidate != null){
            candidate.addInterview(this);
        }

    }

    private boolean sameAsFormer(Candidate newCandidate){
        return this.candidate==null? newCandidate == null :
            this.candidate.equals(newCandidate);
    }

    @CreationTimestamp
    @Column(name = "CREATED_TS")
    private LocalDateTime created_ts;

    @UpdateTimestamp
    @Column(name = "REVIEWED_TS")
    private LocalDateTime updated_ts;
}
