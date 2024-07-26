package com.exavalu.agentportal.service;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.exavalu.agentportal.model.db.AgentFeedback;

@Repository
public interface AgentFeedbackService {

	String submitFeedback(AgentFeedback agentFeedback);
	List<AgentFeedback> getFeedback();
}
