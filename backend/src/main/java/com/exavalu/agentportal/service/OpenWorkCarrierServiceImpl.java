package com.exavalu.agentportal.service;

import com.exavalu.agentportal.model.QuoteList;
import com.exavalu.agentportal.model.db.LossNotice;
import com.exavalu.agentportal.model.db.Quote;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
@Qualifier("carrier")
public class OpenWorkCarrierServiceImpl implements OpenWorkService {
	private static final Logger logger = LogManager.getLogger(OpenWorkCarrierServiceImpl.class);

	@Override
	public List<QuoteList> getItems(String userName, String role) {
		// TODO Auto-generated method stub
		logger.debug("Entering OpenWorkCarrierServiceImpl getItems method, with userName = " + userName + " , "
				+ "role = " + role);
		return Collections.emptyList();
	}

	@Override
	public String submitOpenWork(String appNumber, String username) {
		logger.debug("Entering OpenWorkCarrierServiceImpl submitOpenWork method, with username = " + username + " , "
				+ "appNumber = " + appNumber);
		return null;
	}

	@Override
	public String deleteOpenWork(String appNumber, String username) {
		logger.debug("Entering OpenWorkCarrierServiceImpl deleteOpenWork method, with username = " + username + " , "
				+ "appNumber = " + appNumber);
		return null;
	}

	@Override
	public List<LossNotice> getlossNoticeItems(String username, String role) {
		logger.debug("Entering OpenWorkCarrierServiceImpl getlossNoticeItems method, with userName = " + username + " , "
				+ "role = " + role);
		return Collections.emptyList();
	}
}
