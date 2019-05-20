package org.finra.interview.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "RESPONSE")
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int question_id;

    private int candidate_id;

    private String answer;

}
