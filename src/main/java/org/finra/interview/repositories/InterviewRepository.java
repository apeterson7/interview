package org.finra.interview.repositories;

import org.finra.interview.models.Interview;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface InterviewRepository extends CrudRepository<Interview, UUID> {

}