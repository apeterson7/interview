package org.finra.interview.repositories;

import org.finra.interview.models.Interview;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface InterviewRepository extends CrudRepository<Interview, UUID> {

//    @Modifying
////    @Modifying(flushAutomatically = true, clearAutomatically = true)
//    @Query("update Interview i set i.status = :status where i.interview_id = :id")
//    void updateStatus(@Param("id") UUID id, @Param("status") Integer status);

//    @Modifying(flushAutomatically = true, clearAutomatically = true)
//    @Query(value = "update Interview i set i.status = ? where i.interview_id = ?",
//            nativeQuery = true)
//    void updateStatus(UUID id, Integer status);
}