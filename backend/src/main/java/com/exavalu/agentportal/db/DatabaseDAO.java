package com.exavalu.agentportal.db;

import java.util.List;

public interface DatabaseDAO {

	public boolean updateSubmissionStatus(String indicationId, String quoteNumber,String submissionStatus,String submissionDate);
	public List<Object[]> getQuickIndicationDetails(String startDt, String endDt, String product,String userName,String userRole);
	public List<Object[]> getLossNoticeReports(String startDt, String endDt,String userName,String userRole);
	public List<Object[]> getQuoteSubmissionPremiumDetails(String startDt, String endDt, String product,String userName,String userRole);
	public List<Object> getUserRole(String userName);
}