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
public class Question {

    @Id
    @GeneratedValue(generator = "answer_generator")
    @SequenceGenerator(
            name = "answer_generator",
            sequenceName = "answer_sequence",
            initialValue = 1000
    )
    @Column(name = "ID", unique = true, nullable = false)
    private long id;

    @Column(nullable = false, unique = true, name="NAME")
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
