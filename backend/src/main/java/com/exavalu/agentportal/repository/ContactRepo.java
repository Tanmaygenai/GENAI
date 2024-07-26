package com.exavalu.agentportal.repository;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.exavalu.agentportal.model.db.ContactUsDetails;

public interface ContactRepo extends CrudRepository<ContactUsDetails, Integer>{

	List<ContactUsDetails> findByStatus(String string);
	
	@Modifying
	@Transactional
    @Query("UPDATE ContactUsDetails c " +
           "SET c.closedBy = :closedBy, " +
           "c.closedAt = :closedAt, " +
           "c.status = :status " +
           "WHERE c.caseId = :caseId")
    int updateContactDetails(@Param("caseId") String caseId,
                              @Param("closedBy") String closedBy,
                              @Param("closedAt") String closedAt,
                              @Param("status") String status);
	

}
