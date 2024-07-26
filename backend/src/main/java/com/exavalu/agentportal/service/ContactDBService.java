package com.exavalu.agentportal.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.exavalu.agentportal.model.db.ContactUsDetails;

@Repository
public interface ContactDBService {
	List<ContactUsDetails> getContactUsDetails(String userName, String role);
	List<ContactUsDetails> getContactUdDetailsByCaseId(String userName, String caseId);
	public String closeCaseByUser(String userName, ContactUsDetails contactUsDetails);
	public String caseReopen(String userName, ContactUsDetails contactUsDetails);
	String sendDetails(ContactUsDetails contactUsDetails);
}
