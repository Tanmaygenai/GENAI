package com.exavalu.agentportal.controller;

import com.exavalu.agentportal.model.ClaimDetails;
import com.exavalu.agentportal.service.ClaimDBService;
import com.exavalu.agentportal.util.CryptoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

@RestController
@RequestMapping("${apiPrefix}")
public class ClaimsController {
	private static final Logger logger = LogManager.getLogger(ClaimsController.class);

	public ClaimsController() {
		/*
		 * 
		 */
	}

	@Autowired
	ClaimDBService claimDBService;
	@Autowired
	private CryptoUtil cryptoUtil;

	@GetMapping("${securedString}" + "/claimDB/{id}")
	public ResponseEntity<List<ClaimDetails>> getClaimsFromDB(@PathVariable("id") String id,
			@RequestHeader(value = "username") String username) {
		logger.info("Entering ClaimsController getClaimsFromDB method");
		id = cryptoUtil.decryptData(id);
		List<ClaimDetails> claimDetails = claimDBService.getClaimDetailsFromDB(id, username);
//		logger.info("claim details .... %s", claimDetails);
		if (claimDetails == null) {
			logger.info("Exiting ClaimsController getClaimsFromDB method");
			return new ResponseEntity<List<ClaimDetails>>(HttpStatus.NOT_FOUND);
		} else {
			logger.info("Exiting ClaimsController getClaimsFromDB method");
			return new ResponseEntity<List<ClaimDetails>>(claimDetails, HttpStatus.OK);
		}
	}

}
