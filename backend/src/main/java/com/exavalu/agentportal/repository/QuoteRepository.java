package com.exavalu.agentportal.repository;

import com.exavalu.agentportal.model.db.Quote;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {
	@Modifying(clearAutomatically = true)
	@Query("update QuoteEntity set submissionStatus = :submissionStatus,quoteNumber = :quoteNumber,submissionDate = :submissionDate WHERE indicationId = :indicationId")
	int updateQuoteSubmissionData(String submissionStatus, String quoteNumber, String submissionDate,
			String indicationId);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update QuoteEntity set status = :status WHERE indicationId = :indicationId")
	int updateStatus(int status, String indicationId);

	@Transactional
	@Modifying(clearAutomatically = true)
	List<Quote> findByQuoteNumber(String quote_number);

	@Transactional
	@Modifying(clearAutomatically = true)
	List<Quote> findById(int id);
	
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update QuoteEntity set  indicationData = :indicationData WHERE id = :id")
	void updateData(String indicationData,int id);
}