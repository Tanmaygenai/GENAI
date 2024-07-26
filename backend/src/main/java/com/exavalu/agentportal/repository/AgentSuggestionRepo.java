package com.exavalu.agentportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exavalu.agentportal.model.db.AgentSuggestion;



public interface AgentSuggestionRepo extends JpaRepository<AgentSuggestion,String>{

}
