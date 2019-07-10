package org.finra.interview.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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

//    @JsonBackReference
    @ManyToOne(
//            fetch = FetchType.LAZY
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "CANDIDATE_ID")
    private Candidate candidate;

//    private User interviewer;

    @Column(nullable = false, name="STATUS")
    private String status;

    @CreationTimestamp
    @Column(name = "CREATED_TS")
    private LocalDateTime created_ts;

    @UpdateTimestamp
    @Column(name = "REVIEWED_TS")
    private LocalDateTime updated_ts;

}
