package com.exavalu.agentportal.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.exavalu.agentportal.model.LossNoticeList;
import com.exavalu.agentportal.service.LossNoticeService;

@RestController
@RequestMapping("${apiPrefix}")
public class LossNoticeController {
	private static final Logger logger = LogManager.getLogger(LossNoticeController.class);
	@Autowired
	private LossNoticeService lossNoticeService;
	@Value("${portalMode}")
	String portalMode;

	@GetMapping("${securedString}" + "/lossReports")
	public ResponseEntity<LossNoticeList> getLossNoticeReports(@RequestHeader(value = "username") String username,
			@RequestParam(value = "startDate", required = false) String startDate,
			@RequestParam(value = "endDate", required = false) String endDate,
			@RequestParam(value= "roleType") String roleType, HttpServletRequest req,
			HttpSession session) {
		logger.info("Entering LossNoticeController getLossNoticeReports method");
		List<LossNoticeList> details = lossNoticeService.getLossNoticeReports(username, startDate, endDate,roleType);
		if (details == null) {
			logger.info("Exiting LossNoticeController getLossNoticeReports method");
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		} else {
			logger.info("Exiting LossNoticeController getLossNoticeReports method");
			return new ResponseEntity(details, HttpStatus.OK);
		}
	}

}
