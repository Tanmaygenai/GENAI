package com.exavalu.agentportal.service;

import com.exavalu.agentportal.model.ReportDetails;

import java.util.List;

public interface QuoteSubmissionPremiumReportService {
	List<ReportDetails> getQuoteSubmissionPremiumDetails(String username, String startDate, String endDate, String product,String roleType);
}
