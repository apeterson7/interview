package org.finra.interview.repositories;

import org.finra.interview.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface QuestionRepository extends CrudRepository<Question, Long> {

}
