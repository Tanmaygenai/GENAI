package com.exavalu.agentportal.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.exavalu.agentportal.model.DRCExcessQuickIndication.PolicyPremiumInfo;
import com.exavalu.agentportal.model.DRCExcessQuickIndication.QuickIndication;
import com.exavalu.agentportal.model.db.Quote;

@Service
@Qualifier("carrier")
public class QuickQuoteServiceCarrierImpl implements QuickQuoteService {

	private static final Logger logger = LogManager.getLogger(QuickQuoteServiceCarrierImpl.class);

	@Override
	public PolicyPremiumInfo getQuickQuotePremium(QuickIndication quickIndicationDetails, String username) {
		logger.debug("Entering QuickQuoteServiceCarrierImpl getQuickQuotePremium method, with username = " + username
				+ " , " + "quickIndicationDetails = " + quickIndicationDetails.toString());
		return null;
	}

	@Override
	public Quote getQuickQuotePremiumSaveForLater(String decryptedQuickIndicationDetails, String username, String insuredName, String state, String effectiveDt, String product,int tempId) {
		// TODO Auto-generated method stub
		return null;
	}

}
