package com.exavalu.agentportal.service;

//import com.exavalu.agentportal.model.PolicyDetails;
import com.exavalu.agentportal.model.QuoteList;
import com.exavalu.agentportal.model.DRCExcessQuickIndication.PolicyPremiumInfo;
import com.exavalu.agentportal.model.DRCExcessQuickIndication.QuickIndication;
import com.exavalu.agentportal.model.db.Quote;
import com.exavalu.agentportal.model.DocumentContainer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PolicyService {
	QuoteList getPolicyFromDB(String id, String username);

	List<Quote> getPolicyFileDetails(String username, String quoteNumber);

	DocumentContainer getPolicyFile(String fileId);

	String createPolicy(QuickIndication quickIndicationDetails, String username);
}
