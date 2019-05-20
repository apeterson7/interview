package org.finra.interview.repositories;

import org.finra.interview.models.Candidate;
import org.finra.interview.models.Interviewer;
import org.springframework.data.repository.CrudRepository;

public interface InterviewerRepository extends CrudRepository<Interviewer, Long> {

}
