package org.finra.interview.repositories;

import org.finra.interview.exceptions.CandidateNotFoundException;
import org.finra.interview.models.Candidate;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;


public interface CandidateRepository extends CrudRepository<Candidate, Long> {

//    @Modifying
////    @Modifying(flushAutomatically = true, clearAutomatically = true)
//    @Query("update Candidate c set c.status = :status where c.candidate_id = :id")
//    void updateStatus(@Param("id") Long id, @Param("status") Integer status);


//    @Query("select c.status from Candidate c where c.candidate_id = :id")
//    Candidate test(@Param("id") Long id);


    @Query(value = "select candidate_id from candidate_tags ct where ct.tags = ?1",
            nativeQuery = true)
    List<BigInteger> getCandidatesForTag(String tag);

    @Query(value = "select candidate_id from candidate_tags ct where ct.tags in (:tags)",
                     nativeQuery = true)
    List<BigInteger> getCandidatesForTag(@Param("tags") List<String> tags);


    @Query(value = "select distinct tags from candidate_tags",
            nativeQuery = true)
    List<String> getTags();

//    Iterable<Candidate> findAllById(Collection<Long> ids);

}
