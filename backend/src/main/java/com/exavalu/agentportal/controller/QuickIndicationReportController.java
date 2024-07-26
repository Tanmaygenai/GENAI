package com.exavalu.agentportal.controller;

import com.exavalu.agentportal.model.ReportDetails;
import com.exavalu.agentportal.service.QuickIndicationReportService;
import com.mysql.cj.PreparedQuery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("${apiPrefix}")
public class QuickIndicationReportController {
	private static final Logger logger = LogManager.getLogger(QuickIndicationReportController.class);
	@Autowired
	private QuickIndicationReportService reportService;

	public QuickIndicationReportController() {
		/*
		 * 
		 */
	}

	@GetMapping("${securedString}" + "/quickIndicationReport")
	public ResponseEntity<List<ReportDetails>> getQuickIndicationDetails(
			@RequestHeader(value = "username") String username,
			@RequestParam(value = "startDate", required = false) String startDate,
			@RequestParam(value = "endDate", required = false) String endDate,
			@RequestParam(value = "productType", required = false) String productType,
			@RequestParam(value="roleType") String roleType) {
		logger.info("Entering QuickIndicationReportController getQuickIndicationDetails method");
		List<ReportDetails> details = reportService.getQuickIndicationDetails(username,startDate,endDate,
				productType,roleType);
		if (details == null) {
			logger.info("Exiting QuickIndicationReportController getQuickIndicationDetails method");
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		} else {
			logger.info("Exiting QuickIndicationReportController getQuickIndicationDetails method");
			return new ResponseEntity(details, HttpStatus.OK);
		}
	}
}
