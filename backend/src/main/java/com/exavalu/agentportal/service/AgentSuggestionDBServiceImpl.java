package com.exavalu.agentportal.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.exavalu.agentportal.model.db.AgentFeedback;
import com.exavalu.agentportal.model.db.AgentSuggestion;
import com.exavalu.agentportal.repository.AgentSuggestionRepo;

@Service
@Qualifier("db")
public class AgentSuggestionDBServiceImpl implements AgentSuggestionService{
	private static final Logger logger = LogManager.getLogger(AgentSuggestionDBServiceImpl.class);
	@Autowired
	private EntityManager entityManager;
	@Autowired
	private AgentSuggestionRepo repo;
	@Override
	public String submitSuggestion(AgentSuggestion agentSuggestion) {
		String response="";
		String suggestionDate = String.valueOf(java.time.LocalDate.now());
		agentSuggestion.setSuggestionDate(suggestionDate);
		
		try {
			repo.save(agentSuggestion);
			response="Success";
		}catch(Exception ex) {
			response="Some error";
		}
		return response;
	}
	@Override
	public List<AgentSuggestion> getAgentSuggestion() {
		logger.debug("Entering AgentSuggestionDBServiceImpl getAgentSuggestion method");
		Session currentSession = entityManager.unwrap(Session.class);
		Query query = currentSession.createQuery(
				"select suggestion,userName,suggestionDate from AgentSuggestion");
		List<AgentSuggestion> list = new ArrayList<>();
		List<Object[]> results = query.getResultList();
		for (Object[] result : results) {
			AgentSuggestion agentSuggestion = new AgentSuggestion();
			
			agentSuggestion.setSuggestion((String) result[0]);
			agentSuggestion.setUserName((String) result[1]);
			agentSuggestion.setSuggestionDate((String) result[2]);
			list.add(agentSuggestion);
		}
		return list;
	}

}
