package com.exavalu.agentportal.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.exavalu.agentportal.model.db.AgentSuggestion;

@Repository
public interface AgentSuggestionService {
	String submitSuggestion(AgentSuggestion agentSuggestion);
	List<AgentSuggestion> getAgentSuggestion();
}
