package org.finra.interview.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import lombok.extern.log4j.Log4j;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


//Hibernate
@Entity
@Table(name = "INTERVIEW")
@DynamicUpdate                  //This updates only changed fields when compared to what is in cache (facilitates update() in interviewService)
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
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "INTERVIEW_ID", unique = true, nullable = false)
    private UUID interview_id;

//    private User interviewer;

    @Column(nullable = false, name="STATUS")
    private int status;
    /**
     *
     * 1 - New (Response Objects are created, Interview is sent to candidate)
     * 2 - Under Review (Candidate has provided responses, Cannot view interview)
     * 3 - Finalized (User has provided feedback, candidate has been hired, rejected or released)
     *
     */

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

    @OneToMany(
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<Response> responses = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "CREATED_TS")
    private LocalDateTime created_ts;

    @UpdateTimestamp
    @Column(name = "REVIEWED_TS")
    private LocalDateTime updated_ts;
}
