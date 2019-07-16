package org.finra.interview.repositories;

import org.finra.interview.models.Question;
import org.finra.interview.models.Response;
import org.springframework.data.repository.CrudRepository;

public interface ResponseRepository  extends CrudRepository<Response, Long> {

}