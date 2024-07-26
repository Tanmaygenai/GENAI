package com.exavalu.agentportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.exavalu.agentportal.model.db.AgentFeedback;


@Service
@Qualifier("carrier")
public class AgentFeedbackCarrierServiceImpl implements AgentFeedbackService{

	@Override
	public String submitFeedback(AgentFeedback agentFeedback) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AgentFeedback> getFeedback() {
		// TODO Auto-generated method stub
		return null;
	}

}
