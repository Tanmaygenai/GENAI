package com.exavalu.agentportal.controller;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.exavalu.agentportal.model.UserStats;
import com.exavalu.agentportal.service.StatisticsService;

@RestController
@RequestMapping("${apiPrefix}")
public class StatisticsController {

	private static final Logger logger = LogManager.getLogger(StatisticsController.class);

	@Value("${portalMode}")
	String portalMode;

	@Autowired
	@Qualifier("db")
	private StatisticsService statisticsDBService;

	@Autowired
	@Qualifier("carrier")
	private StatisticsService statisticsCarrierService;

	@GetMapping("${securedString}" + "/userStats")
	public List<UserStats> monthlySubmissionReport(@RequestHeader(value = "username") String username) {
		logger.info("Entering StatisticsController monthlySubmissionReport method");

		List<UserStats> userStatsList = null;

		if (portalMode.equalsIgnoreCase("DB")) {
			userStatsList = statisticsDBService.getUserStats();
		} else {
			userStatsList = statisticsCarrierService.getUserStats();
		}
		logger.info("Exiting StatisticsController monthlySubmissionReport method");
		return userStatsList;
	}
}
