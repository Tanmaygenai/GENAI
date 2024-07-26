package com.exavalu.agentportal.service;

import com.exavalu.agentportal.model.DRCExcessQuickIndication.PolicyPremiumInfo;
import com.exavalu.agentportal.model.DRCExcessQuickIndication.QuickIndication;

public interface EndorsementService {

	PolicyPremiumInfo createEndorsementDetails(String policyLocator, QuickIndication quickIndicationDetails);
}
