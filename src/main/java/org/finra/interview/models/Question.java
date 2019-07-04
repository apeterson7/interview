package org.finra.interview.models;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Log4j
@Entity
@Table(name = "QUESTION")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Question {

    @Id
    @GeneratedValue(generator = "answer_generator")
    @SequenceGenerator(
            name = "answer_generator",
            sequenceName = "answer_sequence",
            initialValue = 1000
    )
    @Column(name = "QUESTION_ID", unique = true, nullable = false)
    private long question_id;

    @Column(nullable = false, name="NAME")
    private String name;

    @Column(nullable = false, name="TYPE")
    private String type;

    @Column(nullable = false, name="LEVEL")
    private String level;

    @Column(nullable = false, name="TEXT", length=10485760)
    private String text;

    @Column(name="ANSWER",length=10485760)
    private String answer;

    @Column(name = "SCORE")
    private Integer score;

//    @Column(nullable = true)
//    private List<String> options;
    
}
