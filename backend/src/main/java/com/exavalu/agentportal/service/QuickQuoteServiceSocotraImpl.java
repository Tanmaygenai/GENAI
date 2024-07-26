package com.exavalu.agentportal.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.exavalu.agentportal.carrier.guidewire.GuidewireBusinessController;
import com.exavalu.agentportal.carrier.socotra.SocotraBusinessController;
import com.exavalu.agentportal.model.PolicyDetails;
import com.exavalu.agentportal.model.DRCExcessQuickIndication.Addr;
import com.exavalu.agentportal.model.DRCExcessQuickIndication.InsuranceLine;
import com.exavalu.agentportal.model.DRCExcessQuickIndication.InsuredInfo;
import com.exavalu.agentportal.model.DRCExcessQuickIndication.Location;
import com.exavalu.agentportal.model.DRCExcessQuickIndication.PolicyPremiumInfo;
import com.exavalu.agentportal.model.DRCExcessQuickIndication.QuickIndication;
import com.exavalu.agentportal.model.db.Quote;
import com.exavalu.agentportal.repository.PolicyDetailsRepo;
import com.exavalu.agentportal.util.CryptoUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
@Qualifier("soc")
public class QuickQuoteServiceSocotraImpl implements QuickQuoteService {
	@Autowired
	PolicyDetailsRepo policyDetailsRepo;

	@Value("${socotraEndPoint}")
	String socotraEndPoint;
	
	@Value("${socotraUsername}")
	String socotraUsername;
	
	@Value("${socotraPassword}")
	String socotraPassword;
	
	@Value("${socotraHostname}")
	String socotraHostname;
	
	@Value("${quoteFormat}")
	String quoteFormat;
	
	@Autowired
	private CryptoUtil cryptoUtil;
	Gson gson = new GsonBuilder().create();
	
	private static final Logger logger = LogManager.getLogger(QuickQuoteServiceSocotraImpl.class);
	private final ObjectMapper objectMapper;

    public QuickQuoteServiceSocotraImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

	@Override
	public PolicyPremiumInfo getQuickQuotePremium(QuickIndication quickIndicationDetails, String username) {
		logger.debug("Entering QuickQuoteServiceCarrierImpl getQuickQuotePremium method, with username = " + username
				+ " , " + "quickIndicationDetails = " + quickIndicationDetails.toString());
		SocotraBusinessController service = new SocotraBusinessController();
		String socotraUsernameValue = cryptoUtil.decryptResult(socotraUsername);
		String socotraPasswordValue = cryptoUtil.decryptResult(socotraPassword);
		String jsonResponse = service.createPolicyWorkFlow(socotraEndPoint, socotraUsernameValue, socotraPasswordValue, socotraHostname, quickIndicationDetails);
		PolicyDetails policyDetails = new PolicyDetails();
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDate = currentDate.format(formatter);
		String product = "";
		String state = "";
		String commercialName = "";
		InsuredInfo insuredInfo = quickIndicationDetails.getApplication() != null ? quickIndicationDetails.getApplication().getInsuredInfo() : null;
		if(insuredInfo!=null) {
			commercialName = insuredInfo.getCommercialName();
	    }

		if (!quickIndicationDetails.getApplication().getPolicyInfo().getPolicyType().equals("")) {
			product = quickIndicationDetails.getApplication().getPolicyInfo().getPolicyType();
		}
		if(product!=null) {
			if (product.equalsIgnoreCase("Commercial Excess Liability")
					|| product.equalsIgnoreCase("Commercial Property")) {
				for (Addr addr : quickIndicationDetails.getApplication().getInsuredInfo().getAddr()) {
					state = addr.getState();
				}
			} else {
				for (Location location : quickIndicationDetails.getApplication().getLocations()) {
					state = location.getAddr().getState();
				}
			}
		}
		ObjectMapper Obj = new ObjectMapper();
		String formatStr = null;
		if (quoteFormat.equalsIgnoreCase("json")) {
			try {
				formatStr = Obj.writeValueAsString(quickIndicationDetails);
			} catch (JsonProcessingException e) {
				logger.error("Inside QuickQuoteServiceDBImpl getQuickQuotePremium method: {}", e.getMessage());
				e.printStackTrace();
			}
		} 
        try {
        	PolicyPremiumInfo response = objectMapper.readValue(jsonResponse, PolicyPremiumInfo.class);
        	response.setWorkFlow("SOC");
        	policyDetails.setPolicyNumber(response.getLocator());
     	    policyDetails.setPremium(response.getFullTermAmount().toString());
     	    policyDetails.setCreationStatus("Yes");
     	    policyDetails.setCarrierMode("SOC");
     	    policyDetails.setUserName(username);
     	    policyDetails.setCreatedDate(formattedDate);
     	    policyDetails.setPolicyData(response.getPolicyData());
     	    policyDetails.setInsuredName(commercialName);
    	    policyDetails.setProduct(product);
    	    policyDetails.setState(state);
    	    policyDetails.setIndicationData(formatStr);
     	    policyDetailsRepo.save(policyDetails);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}

	@Override
	public Quote getQuickQuotePremiumSaveForLater(String decryptedQuickIndicationDetails, String username, String insuredName, String state, String effectiveDt, String product,int tempId) {
		// TODO Auto-generated method stub
		return null;
	}


}
