package com.exavalu.agentportal.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.exavalu.agentportal.model.db.AgentFeedback;
import com.exavalu.agentportal.model.db.AgentSuggestion;
import com.exavalu.agentportal.service.AgentSuggestionService;
import com.exavalu.agentportal.util.CryptoUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
@RestController
@RequestMapping("${apiPrefix}")
public class AgentSuggestionController {

	private static final Logger logger = LogManager.getLogger(AgentSuggestionController.class);
	@Value("${portalMode}")
    String portalMode;
	@Autowired
	@Qualifier("db")
	private AgentSuggestionService agentSuggestionDBService;
	
	@Autowired
	@Qualifier("carrier")
	private AgentSuggestionService agentSuggestionCarrierService;
	
	@Autowired
	private CryptoUtil cryptoUtil;
    Gson gson = new GsonBuilder().create();
    
    @PostMapping("${securedString}" + "/agentSuggestion")
	 public String submitSuggestion(@RequestBody String encryptedSuggestion,
	            @RequestHeader(value = "username") String username) {
		 logger.info("Entering AgentSuggestionController submitSuggestion method");
		 String response = "";
		 AgentSuggestion agentSuggestion=null;
		 String decryptedSuggestion = cryptoUtil.decryptData(encryptedSuggestion);
		 agentSuggestion = gson.fromJson(decryptedSuggestion, AgentSuggestion.class);
	        if (portalMode.equalsIgnoreCase("DB")) {
	            response = agentSuggestionDBService.submitSuggestion(agentSuggestion);
	            logger.info("Exiting AgentSuggestionController submitSuggestion method");
	        } else {
	        	response = agentSuggestionCarrierService.submitSuggestion(agentSuggestion);
	        	logger.info("Exiting AgentSuggestionController submitSuggestion method");
	        }
					return response;
		 
	 }
    
    @GetMapping("${securedString}" + "/getSuggestion")
    public ResponseEntity<List<AgentSuggestion>> getAgentSuggestion( ) {
        logger.info("Entering AgentSuggestionController getAgentSuggestion method");
        List<AgentSuggestion> agentSuggestion = null;
        if (portalMode.equalsIgnoreCase("DB")) {
        	agentSuggestion = agentSuggestionDBService.getAgentSuggestion();
        } else {
            // Customised implementation if portal mode switched to API
        }
        if (agentSuggestion == null) {
            logger.info("Exiting AgentSuggestionController getAgentSuggestion method");
            return new ResponseEntity<List<AgentSuggestion>>(HttpStatus.NOT_FOUND);
        } else {
            logger.info("Exiting AgentSuggestionController getAgentSuggestion method");
            return new ResponseEntity<List<AgentSuggestion>>(agentSuggestion, HttpStatus.OK);
        }

    }
}
