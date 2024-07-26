package com.exavalu.agentportal.service;

import com.exavalu.agentportal.model.DRCExcessQuickIndication.ExcessQuickIndication;
import com.exavalu.agentportal.model.DRCExcessQuickIndication.PolicyFees;
import com.exavalu.agentportal.model.DRCExcessQuickIndication.PolicyPremiumInfo;
import com.exavalu.agentportal.util.CryptoUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class ExcessQuickIndicationServiceImpl implements ExcessQuickIndicationService {
	@Autowired
	private CryptoUtil cryptoUtil;
	Gson gson = new GsonBuilder().create();
	@Value("${excessQuickIndicationURL}")
	String excessQuickIndicationURL;
	@Value("${clientId}")
	String clientId;
	@Value("${clientPassword}")
	String clientPassword;
	@Autowired
	private RestTemplate restTemplate;
	private static final Logger logger = LogManager.getLogger(ExcessQuickIndicationServiceImpl.class);

	@Override
	public PolicyPremiumInfo sendExcessQuickIndicationData(ExcessQuickIndication excessQuickIndicationDetails,
			String username) {
		logger.debug(
				"Entering ExcessQuickIndicationServiceImpl sendExcessQuickIndicationData method, with ExcessQuickIndication = "
						+ excessQuickIndicationDetails.toString() + " , " + "username = " + username);
		PolicyPremiumInfo responseData = null;
		// String authStr = username+":"+password;
		String authStr = username;
		String base64Creds = Base64.getEncoder().encodeToString(authStr.getBytes());
		String clientIdValue = cryptoUtil.decryptResult(clientId);
		String clientPasswordValue = cryptoUtil.decryptResult(clientPassword);
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("Authorization", "Basic " + base64Creds);
		requestHeaders.add("client_id", clientIdValue);
		requestHeaders.add("client_secret", clientPasswordValue);
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<ExcessQuickIndication> requestEntity = new HttpEntity<>(excessQuickIndicationDetails,
				requestHeaders);
		ResponseEntity<PolicyPremiumInfo> response = null;
//For Testing purpose
		PolicyPremiumInfo policyPremiumInfoObject = new PolicyPremiumInfo();
		policyPremiumInfoObject.setFullTermAmount(13477);
		policyPremiumInfoObject.setTriaPremium(250);
		PolicyFees policyFeesObject = new PolicyFees();
		policyFeesObject.setPolicy("13727");
		policyFeesObject.setPuc("75");
		policyFeesObject.setFraud("0");
		policyFeesObject.setDmv("75");
		policyFeesObject.setCargo("75");
		policyFeesObject.setDot("75");
		policyPremiumInfoObject.setPolicyFees(policyFeesObject);
//End for testing purpose
		try {
			response = restTemplate.exchange(excessQuickIndicationURL, HttpMethod.POST, requestEntity,
					PolicyPremiumInfo.class);
		} catch (Exception e) {
			logger.error("Inside ExcessQuickIndicationServiceImpl sendExcessQuickIndicationData method: {}", e.getMessage());
			return policyPremiumInfoObject;
		}
		if ((response.getStatusCode() == HttpStatus.OK) || (response.getStatusCode() == HttpStatus.CREATED)) {
			responseData = response.getBody();
			return responseData;
		}
		return responseData;
	}
}
