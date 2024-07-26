package com.exavalu.agentportal.service;

import com.exavalu.agentportal.model.DashboardChart;
import com.exavalu.agentportal.model.QuoteList;
import com.exavalu.agentportal.model.db.Quote;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

@Service
@Qualifier("carrier")
public class DashboardCarrierServiceImpl implements DashboardService {
	@Autowired
	private RestTemplate restTemplate;
	private static final Logger logger = LogManager.getLogger(DashboardCarrierServiceImpl.class);
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Quote> monthlySubmissionReport(String userName,String roleType) {
		logger.debug("Entering DashboardCarrierServiceImpl monthlySubmissionReport method, with userName = " + userName);
		return Collections.emptyList();
	}

	@Override
	public List<Quote> monthlyPremiumReport(String userName,String roleType) {
		logger.debug("Entering DashboardCarrierServiceImpl monthlyPremiumReport method, with userName = " + userName);
		return Collections.emptyList();
	}

	@Override
	public List<Quote> totalPremiumReport(String userName,String roleType) {
		logger.debug("Entering DashboardCarrierServiceImpl totalPremiumReport method, with userName = " + userName);
		return Collections.emptyList();
	}
	
	@Override
	public DashboardChart getData(String username) {
		logger.debug("Entering DashboardCarrierServiceImpl getData method");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<QuoteList> getQuote(String userName) {
		logger.debug("Entering DashboardCarrierServiceImpl getQuote method");
		// TODO Auto-generated method stub
		return null;
	}
}
