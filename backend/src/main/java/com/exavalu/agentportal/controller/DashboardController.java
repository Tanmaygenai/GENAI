package com.exavalu.agentportal.controller;

import com.exavalu.agentportal.model.DashboardChart;
import com.exavalu.agentportal.model.QuoteList;
import com.exavalu.agentportal.model.db.Quote;
import com.exavalu.agentportal.service.DashboardService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${apiPrefix}")
public class DashboardController {
	private static final Logger logger = LogManager.getLogger(DashboardController.class);

	@Value("${portalMode}")
	String portalMode;

	@Autowired
	@Qualifier("db")
	private DashboardService dashboardDBService;
 
	@Autowired
	@Qualifier("carrier")
	private DashboardService dashboardCarrierService;

	@GetMapping("${securedString}" + "/monthlyReport")
	public ResponseEntity<List<Quote>> monthlySubmissionReport(@RequestHeader(value = "username") String username, @RequestParam(value= "roleType") String roleType) {
		logger.info("Entering DashboardController monthlySubmissionReport method");
		List<Quote> report = null;
		if (portalMode.equalsIgnoreCase("DB")) {
			report = dashboardDBService.monthlySubmissionReport(username,roleType);
		} else {
			report = dashboardCarrierService.monthlySubmissionReport(username,roleType);
		}

		if (report == null) {
			logger.info("Exiting DashboardController monthlySubmissionReport method");
			return new ResponseEntity<List<Quote>>(HttpStatus.NOT_FOUND);
		} else {
			logger.info("Exiting DashboardController monthlySubmissionReport method");
			return new ResponseEntity<List<Quote>>(report, HttpStatus.OK);
		}

	}

	@GetMapping("${securedString}" + "/premiumReport")
	public ResponseEntity<List<Quote>> premiumReport(@RequestHeader(value = "username") String username, @RequestParam(value="roleType") String roleType ) {
		logger.info("Entering DashboardController premiumReport method");
		List<Quote> report = null;
		if (portalMode.equalsIgnoreCase("DB")) {
			report = dashboardDBService.monthlyPremiumReport(username,roleType);
		} else {
			report = dashboardCarrierService.monthlyPremiumReport(username,roleType);
		}

		if (report == null) {
			logger.info("Exiting DashboardController premiumReport method");
			return new ResponseEntity<List<Quote>>(HttpStatus.NOT_FOUND);
		} else {
			logger.info("Exiting DashboardController premiumReport method");
			return new ResponseEntity<List<Quote>>(report, HttpStatus.OK);
		}

	}

	@GetMapping("${securedString}" + "/totalPremiumReport")
	public ResponseEntity<List<Quote>> totalPremiumReport(@RequestHeader(value = "username") String username, @RequestParam(value="roleType") String roleType ) {
		logger.debug("Entering DashboardDBServiceImpl totalPremiumReport method");
		List<Quote> report = null;
		if (portalMode.equalsIgnoreCase("DB")) {
			report = dashboardDBService.totalPremiumReport(username,roleType);
		} else {
			report = dashboardCarrierService.totalPremiumReport(username,roleType);
		}
		if (report == null) {
			logger.info("Exiting DashboardController premiumReport method");
			return new ResponseEntity<List<Quote>>(HttpStatus.NOT_FOUND);
		} else {
			logger.info("Exiting DashboardController premiumReport method");
			return new ResponseEntity<List<Quote>>(report, HttpStatus.OK);
		}
	}

	@PostMapping("${securedString}" + "/chartDashboard")
	public DashboardChart getChartDataForDashboard(@RequestHeader(value = "username") String username) {
		logger.info("Entering DashboardController getChartDataForDashboard method");
		DashboardChart dashboardDetails = null;
		if (portalMode.equalsIgnoreCase("DB")) {
			dashboardDetails = dashboardDBService.getData(username);
		} else {
			dashboardDetails = dashboardCarrierService.getData(username);
		}
		logger.info("Exiting DashboardController getChartDataForDashboard method");
		return dashboardDetails;
	}
	
	@PostMapping("${securedString}" + "/getQuoteReport")
	public ResponseEntity<List<QuoteList>> getQuoteReport(@RequestHeader(value = "username") String username) {
		logger.info("Entering DashBoardController getQuoteReport Method");
		List<QuoteList> getQuoteReport = null;
		if (portalMode.equalsIgnoreCase("DB")) {
			getQuoteReport = dashboardDBService.getQuote(username);
		} else {
			getQuoteReport = dashboardCarrierService.getQuote(username);
		}

		if (getQuoteReport == null) {
			logger.info("Exiting DashBoardController getQuoteReport method");
			return new ResponseEntity<List<QuoteList>>(HttpStatus.NOT_FOUND);
		} else {
			logger.info("Exiting DashBoardController getQuoteReport method");
			return new ResponseEntity<List<QuoteList>>(getQuoteReport, HttpStatus.OK);
		}

	}


}
