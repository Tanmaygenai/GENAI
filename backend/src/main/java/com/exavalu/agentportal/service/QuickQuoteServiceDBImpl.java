package com.exavalu.agentportal.service;

import com.exavalu.agentportal.carrier.guidewire.GuidewireBusinessController;
import com.exavalu.agentportal.model.DRCExcessQuickIndication.*;
import com.exavalu.agentportal.model.db.Quote;
import com.exavalu.agentportal.repository.QuoteRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.Logger;

@Service
@Qualifier("db")
public class QuickQuoteServiceDBImpl implements QuickQuoteService {
	private static final Logger logger = LogManager.getLogger(QuickQuoteServiceDBImpl.class);
	@Value("${quoteFormat}")
	String quoteFormat;

	@Autowired
	private QuoteRepository repository;

	@Override
	public PolicyPremiumInfo getQuickQuotePremium(QuickIndication quickIndicationDetails, String username) {
		logger.debug("Entering QuickQuoteServiceDBImpl getQuickQuotePremium method, with username = " + username + " , "
				+ "quickIndicationDetails = " + quickIndicationDetails.toString());

		boolean existingRecord = true;
		if (quickIndicationDetails.getApplication().getId() == 0) {
			existingRecord = false;
			List<Quote> quoteList = repository.findAll(Sort.by(Sort.Direction.DESC, "id"));
			if (!quoteList.isEmpty()) {
				Quote quote1 = quoteList.get(0);
				quickIndicationDetails.getApplication().setId(quote1.getId() + 1); // setting it to thenext quote id
				quickIndicationDetails.getApplication().setId(1);
			} else {
				quickIndicationDetails.getApplication().setId(1); // setting it to thenext quote id
				quickIndicationDetails.getApplication().setId(1);
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
//		else {
//			GuidewireBusinessController serviceGW = new GuidewireBusinessController();
//			String premuimDetails = serviceGW.getQuickQuotePremium(quickIndicationDetails);
//			PolicyPremiumInfo policyPremiumInfoObject = new PolicyPremiumInfo();
//			policyPremiumInfoObject.setFullTermAmount(Integer.parseInt(premuimDetails));
//
//			return policyPremiumInfoObject;
//		}
		// SecureRandom random = new SecureRandom();
		// int premium = (int) (random.nextInt() * 250 + 1250);
		int premium = (int) (Math.random() * 250 + 1250);

		LocalDate currentDate = LocalDate.now();
		Month currentMonth = currentDate.getMonth();
		String product = "";
		String address = "";
		String city = "";
		String state = "";
		String zipCode = "";
		String liabilityLimit = "";

		if (!quickIndicationDetails.getApplication().getPolicyInfo().getPolicyType().equals("")) {
			product = quickIndicationDetails.getApplication().getPolicyInfo().getPolicyType();
		}

		if (product.equalsIgnoreCase("Commercial Excess Liability")
				|| product.equalsIgnoreCase("Commercial Property")) {
			for (Addr addr : quickIndicationDetails.getApplication().getInsuredInfo().getAddr()) {
				address = addr.getAddr1();
				city = addr.getCity();
				state = addr.getState();
				zipCode = addr.getPostalCode();
			}
		} else {
			for (Location location : quickIndicationDetails.getApplication().getLocations()) {
				address = location.getAddr().getAddr1();
				city = location.getAddr().getCity();
				state = location.getAddr().getState();
				zipCode = location.getAddr().getPostalCode();
			}
		}
		if (product.equalsIgnoreCase("Commercial Excess Liability")) {
			liabilityLimit = quickIndicationDetails.getApplication().getPolicyPremiumInfo().getLiabilityLimit();
		}
		String effDate = quickIndicationDetails.getApplication().getPolicyInfo().getEffectiveDt();
		String expirationDate = "";
		try {
			Date date = new SimpleDateFormat("MM-dd-yyyy").parse(effDate);
			LocalDateTime expDate = LocalDateTime.from(date.toInstant()).plusYears(1);
			expirationDate = expDate.toString();
		} catch (Exception exception) {
			exception.getMessage();
		}
		String insureName = quickIndicationDetails.getApplication().getInsuredInfo().getCommercialName();
		int status = 1;
		Quote quote = new Quote(quickIndicationDetails.getApplication().getExternalId(), username,
				quickIndicationDetails.getApplication().getPolicyInfo().getEffectiveDt(), "",
				quickIndicationDetails.getApplication().getPolicyInfo().getControllingState(), liabilityLimit, "Yes",
				"No", "", currentMonth.toString(), String.valueOf(premium), address, city, state, zipCode, product,
				currentDate.toString(), "", formatStr, insureName, expirationDate, "", status);
		if (existingRecord) {
			quote.setId(quickIndicationDetails.getApplication().getId());
			repository.save(quote);
		} else
			repository.save(quote);

		PolicyPremiumInfo policyPremiumInfoObject = new PolicyPremiumInfo();
		policyPremiumInfoObject.setFullTermAmount(premium);
		policyPremiumInfoObject.setWorkFlow("DB");
		return policyPremiumInfoObject;
	}

	@Override
	public Quote getQuickQuotePremiumSaveForLater(String decryptedQuickIndicationDetails, String username,  String insuredName, String state, String effectiveDt, String product,int tempId) {
		logger.debug("Entering QuickQuoteServiceDBImpl getQuickQuotePremiumSaveForLater method, with username = " + username + " , "
				+ "quickIndicationDetails = " + decryptedQuickIndicationDetails.toString());

		boolean existingRecord = true;
		boolean recordForSaveLater = true;
		String ExternalId= null;
		int Id= 0;
		
		ObjectMapper Obj = new ObjectMapper();
		try {
			JsonNode jsonNode = Obj.readTree(decryptedQuickIndicationDetails);
			Id=jsonNode.get("application").get("id").asInt();
			 ExternalId=jsonNode.get("application").get("externalId").asText();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (Id == 0 && tempId ==0) {
			existingRecord = false;
			List<Quote> quoteList = repository.findAll(Sort.by(Sort.Direction.DESC, "id"));
			if (!quoteList.isEmpty()) {
				Quote quote1 = quoteList.get(0);
				Id=(quote1.getId() + 1); // setting it to thenext quote id
				tempId=Id;
			} else {
				Id=(1); // setting it to thenext quote id
				
			}
		}

		LocalDate currentDate = LocalDate.now();
		Month currentMonth = currentDate.getMonth();
		String Product = "";
		String address = "";
		String city = "";
		String State = "";
		String zipCode = "";
		String liabilityLimit = "";
		int status = 0;
		Quote quote = new Quote(ExternalId, username,
				effectiveDt, "",
				state, liabilityLimit, "Yes",
				"No", "", currentMonth.toString(), "", address, city, state, zipCode, product,
				currentDate.toString(), "", "", insuredName, "", "", status);
				quote.setIndicationData(decryptedQuickIndicationDetails);
		
		if (existingRecord && tempId!=0) {
			quote.setId(tempId);
			repository.updateData(decryptedQuickIndicationDetails,quote.getId());
		} else
			repository.save(quote);
		Quote quoteobj = new Quote();
		quoteobj.setId(quote.getId());
		quoteobj.setIndicationData(decryptedQuickIndicationDetails);
		return quoteobj;
	}

}
