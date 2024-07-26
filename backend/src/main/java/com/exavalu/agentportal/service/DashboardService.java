package com.exavalu.agentportal.service;

import com.exavalu.agentportal.model.DashboardChart;
import com.exavalu.agentportal.model.QuoteList;
import com.exavalu.agentportal.model.db.Quote;

import java.util.List;

public interface DashboardService {
    List<Quote> monthlySubmissionReport(String userName,String roleType);
    List<Quote> monthlyPremiumReport(String userName,String roleType);
    List<Quote> totalPremiumReport(String userName,String roleType);
    public DashboardChart getData(String username);
    List<QuoteList> getQuote(String userName);
}
