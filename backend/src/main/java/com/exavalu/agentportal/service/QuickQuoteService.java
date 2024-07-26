package com.exavalu.agentportal.service;

import com.exavalu.agentportal.model.DRCExcessQuickIndication.PolicyPremiumInfo;
import com.exavalu.agentportal.model.DRCExcessQuickIndication.QuickIndication;
import com.exavalu.agentportal.model.db.Quote;

public interface QuickQuoteService {

	PolicyPremiumInfo getQuickQuotePremium(QuickIndication quickIndicationDetails, String username);

	Quote getQuickQuotePremiumSaveForLater(String decryptedQuickIndicationDetails, String username, String insuredName, String state, String effectiveDt, String product,int tempId);
}
