package com.exavalu.agentportal.service;

import com.exavalu.agentportal.model.DRCExcessQuickIndication.QuickIndication;

public interface QuoteSubmissionService {

	String submitQuote(QuickIndication quickIndicationDetails);
}
