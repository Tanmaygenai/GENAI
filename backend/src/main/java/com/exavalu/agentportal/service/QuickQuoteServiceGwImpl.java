package com.exavalu.agentportal.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.exavalu.agentportal.carrier.guidewire.GuidewireBusinessController;
import com.exavalu.agentportal.db.DatabaseDAOImpl;
import com.exavalu.agentportal.model.DRCExcessQuickIndication.Location;
import com.exavalu.agentportal.model.DRCExcessQuickIndication.PolicyPremiumInfo;
import com.exavalu.agentportal.model.DRCExcessQuickIndication.QuickIndication;
import com.exavalu.agentportal.model.db.Quote;
import com.exavalu.agentportal.repository.QuoteRepository;
import com.exavalu.agentportal.util.CryptoUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
@Qualifier("gw")
public class QuickQuoteServiceGwImpl implements QuickQuoteService {

	@Value("${accountCreateGWURL}")
	String accountCreateGWURL;

	@Value("${newSubmissionGWURL}")
	String newSubmissionGWURL;

	@Value("${guidewire.auth}")
	String auth;
	
	@Autowired
	private DatabaseDAOImpl quoteDAO;
	@Autowired
	private CryptoUtil cryptoUtil;
	Gson gson = new GsonBuilder().create();
	
	@Autowired
	private QuoteRepository repository;
	
	@Value("${quoteFormat}")
	String quoteFormat;

	private static final Logger logger = LogManager.getLogger(QuickQuoteServiceGwImpl.class);

	@Override
	public PolicyPremiumInfo getQuickQuotePremium(QuickIndication quickIndicationDetails, String username) {
		logger.info("Entering QuickQuoteServiceGwImpl getQuickQuotePremium method");

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
				logger.error("Inside QuickQuoteServiceGwImpl getQuickQuotePremium method: {}", e.getMessage());
				e.printStackTrace();
			}
		} 
		
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
		
		for (Location location : quickIndicationDetails.getApplication().getLocations()) {
			address = location.getAddr().getAddr1();
			city = location.getAddr().getCity();
			state = location.getAddr().getState();
			zipCode = location.getAddr().getPostalCode();
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
		
		GuidewireBusinessController serviceGW = new GuidewireBusinessController();
		String authValue = cryptoUtil.decryptResult(auth);
		String premuimDetails = serviceGW.getQuickQuotePremium(quickIndicationDetails, accountCreateGWURL,
				newSubmissionGWURL, authValue);
		PolicyPremiumInfo policyPremiumInfoObject = new PolicyPremiumInfo();
		JSONObject jsonObject = new JSONObject(premuimDetails);
		String premium = jsonObject.getString("fullTermAmount");
		String quoteNumber= jsonObject.getString("transactionIdGW");
		LocalDate submissionDate = LocalDate.now();
		Quote quote = new Quote(quickIndicationDetails.getApplication().getExternalId(), username,
				quickIndicationDetails.getApplication().getPolicyInfo().getEffectiveDt(), "",
				quickIndicationDetails.getApplication().getPolicyInfo().getControllingState(), liabilityLimit, "Yes",
				"No", quoteNumber, currentMonth.toString(), String.valueOf(premium), address, city, state, zipCode, product,
				currentDate.toString(), "", formatStr, insureName, expirationDate, "", status);
		if (existingRecord) {
			quote.setId(quickIndicationDetails.getApplication().getId());
			repository.save(quote);
		} else
			repository.save(quote);
		
		
		premuimDetails = premium.replace(".00 usd", "");
		policyPremiumInfoObject.setFullTermAmount((int) Double.parseDouble(premuimDetails));
		policyPremiumInfoObject.setWorkFlow("GW");
		policyPremiumInfoObject.setLocator(jsonObject.getString("transactionIdGW"));

		quoteDAO.updateSubmissionStatus(quickIndicationDetails.getApplication().getExternalId(), quoteNumber, "Yes",
				submissionDate.toString());
		return policyPremiumInfoObject;
	}

	@Override
	public Quote getQuickQuotePremiumSaveForLater(String decryptedQuickIndicationDetails, String username, String insuredName, String state, String effectiveDt, String product,int tempId) {
		// TODO Auto-generated method stub
		return null;
	}


}
