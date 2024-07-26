package com.exavalu.agentportal.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.exavalu.agentportal.model.DRCExcessQuickIndication.QuickIndication;

@Service
@Qualifier("carrier")
public class QuoteSubmissionServiceCarrierImpl implements QuoteSubmissionService {
	private static final Logger logger = LogManager.getLogger(QuoteSubmissionServiceCarrierImpl.class);

	@Override
	public String submitQuote(QuickIndication quickIndicationDetails) {
		// TODO Auto-generated method stub
		logger.debug("Entering QuoteSubmissionServiceCarrierImpl submitQuote method, with quickIndicationDetails = "
				+ quickIndicationDetails.toString());
		return null;
	}

}
