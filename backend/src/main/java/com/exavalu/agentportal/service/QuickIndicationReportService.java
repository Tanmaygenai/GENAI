package com.exavalu.agentportal.service;

import com.exavalu.agentportal.model.ReportDetails;

import java.util.List;

public interface QuickIndicationReportService {
	List<ReportDetails> getQuickIndicationDetails(String username, String startDate, String endDate, String product,String userRole);
}
