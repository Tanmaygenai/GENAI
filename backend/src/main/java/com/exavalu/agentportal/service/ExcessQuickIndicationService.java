package com.exavalu.agentportal.service;

import com.exavalu.agentportal.model.DRCExcessQuickIndication.ExcessQuickIndication;
import com.exavalu.agentportal.model.DRCExcessQuickIndication.PolicyPremiumInfo;

public interface ExcessQuickIndicationService {

    PolicyPremiumInfo sendExcessQuickIndicationData(ExcessQuickIndication excessQuickIndicationDetails, String username);
}
