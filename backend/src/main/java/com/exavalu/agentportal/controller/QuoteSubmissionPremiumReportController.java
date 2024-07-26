package com.exavalu.agentportal.controller;

import com.exavalu.agentportal.model.ReportDetails;
import com.exavalu.agentportal.service.QuoteSubmissionPremiumReportService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("${apiPrefix}")
public class QuoteSubmissionPremiumReportController {
	private static final Logger logger = LogManager.getLogger(QuoteSubmissionPremiumReportController.class);
	@Autowired
	private QuoteSubmissionPremiumReportService reportService;

	public QuoteSubmissionPremiumReportController() {
		/*
		 * 
		 */
	}

	@GetMapping("${securedString}" + "/quoteSubmissionPremiumReport")
	public ResponseEntity<List<ReportDetails>> getQuoteSubmissionPremiumDetails(
			@RequestHeader(value = "username") String username,
			@RequestParam(value = "startDate", required = false) String startDate,
			@RequestParam(value = "endDate", required = false) String endDate,
			@RequestParam(value = "productType", required = false) String productType,
			@RequestParam(value= "roleType") String roleType) {
		logger.info("Entering QuoteSubmissionPremiumReportController getQuoteSubmissionPremiumDetails method");
		List<ReportDetails> details = reportService.getQuoteSubmissionPremiumDetails(username, startDate, endDate,
				productType,roleType);
		if (details == null) {
			logger.info("Exiting QuoteSubmissionPremiumReportController getQuoteSubmissionPremiumDetails method");
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		} else {
			logger.info("Exiting QuoteSubmissionPremiumReportController getQuoteSubmissionPremiumDetails method");
			return new ResponseEntity(details, HttpStatus.OK);
		}
	}
}
