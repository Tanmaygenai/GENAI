package com.exavalu.agentportal.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.exavalu.agentportal.error.FileStorageException;
import com.exavalu.agentportal.model.ContentManagement;
import com.exavalu.agentportal.model.db.AgentFeedback;
import com.exavalu.agentportal.repository.AgentFeedbackRepo;

@Service
@Qualifier("db")
public class AgentFeedbackDBServiceImpl implements AgentFeedbackService{
	private static final Logger logger = LogManager.getLogger(AgentFeedbackDBServiceImpl.class);
	@Autowired
	private EntityManager entityManager;
	@Autowired
	private AgentFeedbackRepo repo;
	@Override
	public String submitFeedback(AgentFeedback agentFeedback)  {
		
		String response="";
		String feedbackDate = String.valueOf(java.time.LocalDate.now());
		
		agentFeedback.setFeedbackDate(feedbackDate);
		
		try {
			repo.save(agentFeedback);
			response="Success";
		}catch(Exception ex) {
			response="Some error";
		}
		
		return response;
	}
	@Override
	public List<AgentFeedback> getFeedback() {
		logger.debug("Entering AgentFeedbackDBServiceImpl getFeedback method");
		
	
		Session currentSession = entityManager.unwrap(Session.class);
		Query query = currentSession.createQuery(
				"select description,feedback,userName,feedbackDate from AgentFeedback");
		List<AgentFeedback> list = new ArrayList<>();
		List<Object[]> results = query.getResultList();
		for (Object[] result : results) {
			AgentFeedback agentFeedback = new AgentFeedback();
			agentFeedback.setDescription((String) result[0]);
			agentFeedback.setFeedback((String) result[1]);
			agentFeedback.setUserName((String) result[2]);
			agentFeedback.setFeedbackDate((String) result[3]);
			list.add(agentFeedback);
		}
		return list;
	}

}
