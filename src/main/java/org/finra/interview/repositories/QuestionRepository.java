package org.finra.interview.repositories;

import org.finra.interview.models.Question;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface QuestionRepository extends CrudRepository<Question, Long> {

}