package com.exavalu.agentportal.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.exavalu.agentportal.carrier.socotra.SocotraBusinessController;
import com.exavalu.agentportal.model.DRCExcessQuickIndication.PolicyPremiumInfo;
import com.exavalu.agentportal.model.DRCExcessQuickIndication.QuickIndication;
import com.exavalu.agentportal.util.CryptoUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
@Qualifier("soc")
public class EndorsementServiceSocotraImpl implements EndorsementService {

	@Value("${socotraEndPoint}")
	String socotraEndPoint;
	
	@Value("${socotraUsername}")
	String socotraUsername;
	
	@Value("${socotraPassword}")
	String socotraPassword;
	
	@Value("${socotraHostname}")
	String socotraHostname;
	
	@Autowired
	private CryptoUtil cryptoUtil;
	Gson gson = new GsonBuilder().create();
	
	private static final Logger logger = LogManager.getLogger(EndorsementServiceSocotraImpl.class);
	private final ObjectMapper objectMapper;

    public EndorsementServiceSocotraImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

	@Override
	public PolicyPremiumInfo createEndorsementDetails(String policyLocator, QuickIndication quickIndicationDetails) {
		logger.debug("Entering QuickQuoteServiceCarrierImpl endorsementDetails method, with username = " + policyLocator
				+ " , " + "quickIndicationDetails = " + quickIndicationDetails.toString());
		SocotraBusinessController service = new SocotraBusinessController();
		String socotraUsernameValue = cryptoUtil.decryptResult(socotraUsername);
		String socotraPasswordValue = cryptoUtil.decryptResult(socotraPassword);
		String jsonResponse = service.createEndorsementWorkFlow(socotraEndPoint, socotraUsernameValue, socotraPasswordValue, socotraHostname, quickIndicationDetails, policyLocator);
        try {
        	PolicyPremiumInfo response = objectMapper.readValue(jsonResponse, PolicyPremiumInfo.class);
        	response.setWorkFlow("SOC");
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}

}
