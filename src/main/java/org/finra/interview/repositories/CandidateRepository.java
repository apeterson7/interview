package org.finra.interview.repositories;

import org.finra.interview.models.Candidate;
import org.springframework.data.repository.CrudRepository;


public interface CandidateRepository extends CrudRepository<Candidate, Long> {

}
