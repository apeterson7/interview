package org.finra.interview.models;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, unique = true, name="NAME")
    private String name;

    @Column(nullable = false, name="TYPE")
    private String type;

    @Column(nullable = false, name="LEVEL")
    private String level;

    @Column(nullable = false, name="TEXT")
    private String text;

    @Column(nullable = false, name="ANSWER")
    private String answer;

//    @Column(nullable = true)
//    private List<String> options;


}
