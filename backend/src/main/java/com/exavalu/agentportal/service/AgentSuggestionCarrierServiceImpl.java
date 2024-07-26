package com.exavalu.agentportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.exavalu.agentportal.model.db.AgentSuggestion;

@Service
@Qualifier("carrier")
public class AgentSuggestionCarrierServiceImpl implements AgentSuggestionService{

	@Override
	public String submitSuggestion(AgentSuggestion agentSuggestion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AgentSuggestion> getAgentSuggestion() {
		// TODO Auto-generated method stub
		return null;
	}

}
