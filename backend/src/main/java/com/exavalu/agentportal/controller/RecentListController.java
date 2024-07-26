package com.exavalu.agentportal.controller;

import com.exavalu.agentportal.model.RecentList;
import com.exavalu.agentportal.service.RecentListDBService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("${apiPrefix}")
public class RecentListController {
	private static final Logger logger = LogManager.getLogger(RecentListController.class);

	public RecentListController() {
		/*
		 * 
		 */
	}

	@Autowired
	private RecentListDBService recentListDBService;

	@GetMapping("${securedString}" + "/recentlyViewedDB")
	public ResponseEntity<List<RecentList>> getRecentListDB() {
		logger.info("Entering RecentListController getRecentListDB method");
		List<RecentList> details = recentListDBService.getItems();
		if (details == null) {
			logger.info("Entering RecentListController getRecentListDB method");
			return new ResponseEntity<List<RecentList>>(HttpStatus.NOT_FOUND);
		} else {
			logger.info("Entering RecentListController getRecentListDB method");
			return new ResponseEntity<List<RecentList>>(details, HttpStatus.OK);
		}
	}
}
