package org.finra.interview.models;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

//hibernate
@Entity
@Table(name = "RESPONSE")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "response_id")
//lombok
@Log4j
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Response {

    @Id
    @GeneratedValue(generator = "response_generator")
    @SequenceGenerator(
            name = "response_generator",
            sequenceName = "response_sequence",
            initialValue = 1000
    )
    private long response_id;

    @ManyToOne(
        cascade = CascadeType.ALL
    )
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

    @Column(name = "RESPONSE")
    private String response;
}
