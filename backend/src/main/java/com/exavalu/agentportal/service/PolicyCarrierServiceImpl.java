package com.exavalu.agentportal.service;

import com.exavalu.agentportal.model.DocumentContainer;
import com.exavalu.agentportal.model.PolicyDetails;
import com.exavalu.agentportal.model.QuoteList;
import com.exavalu.agentportal.model.DRCExcessQuickIndication.PolicyPremiumInfo;
import com.exavalu.agentportal.model.DRCExcessQuickIndication.QuickIndication;
import com.exavalu.agentportal.model.db.Quote;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

@Service
@Qualifier("carrier")
public class PolicyCarrierServiceImpl implements PolicyService {
	private static final Logger logger = LogManager.getLogger(PolicyCarrierServiceImpl.class);
	@Autowired
	private EntityManager entityManager;

	@Override
	public QuoteList getPolicyFromDB(String id, String username) {
		logger.debug("Entering PolicyCarrierServiceImpl getPolicyFromDB method, with username = " + username + " , "
				+ "id = " + id);
		return null;
	}

	@Override
	public List<Quote> getPolicyFileDetails(String username, String policyNumber) {
		logger.debug("Entering PolicyCarrierServiceImpl getPolicyFileDetails method, with username = " + username
				+ " , " + "policyNumber = " + policyNumber);
		return Collections.emptyList();
	}

	@Override
	public DocumentContainer getPolicyFile(String fileId) {
		logger.debug("Entering PolicyCarrierServiceImpl getPolicyFile method, with fileId = " + fileId);
		return null;
	}

	@Override
	public String createPolicy(QuickIndication quickIndicationDetails, String username) {
		// TODO Auto-generated method stub
		return null;
	}
}
