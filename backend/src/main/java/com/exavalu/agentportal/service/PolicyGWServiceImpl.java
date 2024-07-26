package com.exavalu.agentportal.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.exavalu.agentportal.carrier.guidewire.GuidewireBusinessController;
import com.exavalu.agentportal.model.DocumentContainer;
import com.exavalu.agentportal.model.PolicyDetails;
import com.exavalu.agentportal.model.QuoteList;
import com.exavalu.agentportal.model.DRCExcessQuickIndication.Addr;
import com.exavalu.agentportal.model.DRCExcessQuickIndication.InsuredInfo;
import com.exavalu.agentportal.model.DRCExcessQuickIndication.Location;
import com.exavalu.agentportal.model.DRCExcessQuickIndication.PolicyPremiumInfo;
import com.exavalu.agentportal.model.DRCExcessQuickIndication.QuickIndication;
import com.exavalu.agentportal.model.db.Quote;
import com.exavalu.agentportal.repository.PolicyDetailsRepo;
import com.exavalu.agentportal.repository.QuoteRepository;
import com.exavalu.agentportal.util.CryptoUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
@Qualifier("gw")
public class PolicyGWServiceImpl implements PolicyService {
	@Autowired
	PolicyDetailsRepo policyDetailsRepo;

	@Value("${accountCreateGWURL}")
	String accountCreateGWURL;

	@Value("${issueSubmissionGWURL}")
	String issueSubmissionGWURL;

	@Value("${guidewire.auth}")
	String auth;
	
	@Value("${quoteFormat}")
	String quoteFormat;
	@Autowired
	private CryptoUtil cryptoUtil;
	Gson gson = new GsonBuilder().create();

	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private QuoteRepository repository;

	private static final Logger logger = LogManager.getLogger(QuickQuoteServiceGwImpl.class);

	@Override
	public QuoteList getPolicyFromDB(String id, String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Quote> getPolicyFileDetails(String username, String quoteNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DocumentContainer getPolicyFile(String fileId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createPolicy(QuickIndication quickIndicationDetails, String username) {
		// TODO Auto-generated method stub
		
		GuidewireBusinessController serviceGW = new GuidewireBusinessController();
		String authValue = cryptoUtil.decryptResult(auth);
		String policyNumber = serviceGW.createPolicy(quickIndicationDetails.getApplication().getPolicyPremiumInfo().getLocator(), issueSubmissionGWURL, authValue);
    	if (policyNumber!=null) {
    		PolicyDetails policyDetails = new PolicyDetails();
    		LocalDate currentDate = LocalDate.now();
    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    		String formattedDate = currentDate.format(formatter);
    		
    		ObjectMapper Obj = new ObjectMapper();
    		String formatStr = null;
    		String product = "";
    		String state = "";
    		String commercialName = "";
    		if (quoteFormat.equalsIgnoreCase("json")) {
    			try {
    				formatStr = Obj.writeValueAsString(quickIndicationDetails);
    			} catch (JsonProcessingException e) {
    				logger.error("Inside QuickQuoteServiceDBImpl getQuickQuotePremium method: {}", e.getMessage());
    				e.printStackTrace();
    			}
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
    		InsuredInfo insuredInfo = quickIndicationDetails.getApplication() != null ? quickIndicationDetails.getApplication().getInsuredInfo() : null;
    		if(insuredInfo!=null) {
    			commercialName = insuredInfo.getCommercialName();
    	    }
    		policyDetails.setPolicyNumber(policyNumber);
     	    policyDetails.setPremium(quickIndicationDetails.getApplication().getPolicyPremiumInfo().getFullTermAmount().toString());
     	    policyDetails.setCreationStatus("Yes");
     	    policyDetails.setCarrierMode("GW");
     	    policyDetails.setUserName(username);
     	    policyDetails.setCreatedDate(formattedDate);
     	    policyDetails.setQuoteNumber(quickIndicationDetails.getApplication().getPolicyPremiumInfo().getLocator());
     	    policyDetails.setPolicyData(quickIndicationDetails.toString());
     	    policyDetails.setProduct(quickIndicationDetails.getApplication().getPolicyInfo().getPolicyType());
    	    policyDetails.setState(state);
    	    policyDetails.setIndicationData(formatStr);
    	    policyDetails.setInsuredName(commercialName);
     	    policyDetailsRepo.save(policyDetails);
     	    
    	}
		
		logger.info("Exiting PolicyGWServiceImpl createPolicy with policyNumber : " + policyNumber);
		return policyNumber;
	}
}
