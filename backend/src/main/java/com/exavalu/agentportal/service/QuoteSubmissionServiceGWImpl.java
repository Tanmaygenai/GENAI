package com.exavalu.agentportal.service;

import java.time.LocalDate;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.exavalu.agentportal.db.DatabaseDAOImpl;
import com.exavalu.agentportal.model.DRCExcessQuickIndication.QuickIndication;
import com.exavalu.agentportal.model.db.Quote;
import com.exavalu.agentportal.repository.QuoteRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Qualifier("gw")
public class QuoteSubmissionServiceGWImpl implements QuoteSubmissionService{

	private static final Logger logger = LogManager.getLogger(QuoteSubmissionServiceGWImpl.class);
	@Autowired
	private QuoteRepository repository;

	@Autowired
	private DatabaseDAOImpl quoteDAO;
	@Override
	public String submitQuote(QuickIndication quickIndicationDetails) {


		logger.debug("Entering QuoteSubmissionServiceGWImpl submitQuote method, with quickIndicationDetails = "
				+ quickIndicationDetails.toString());
		String quoteNumber = "";
		LocalDate submissionDate = LocalDate.now();

		List<Quote> quotelist = repository.findAll(Sort.by(Sort.Direction.DESC, "id"));
		Quote quote = quotelist.get(0);
		quoteNumber = quickIndicationDetails.getApplication().getPolicyPremiumInfo().getLocator();
		ObjectMapper Obj = new ObjectMapper();
		String jsonStr = null;
		try {
			jsonStr = Obj.writeValueAsString(quickIndicationDetails);
		} catch (JsonProcessingException e) {
			logger.error("Inside QuoteSubmissionServiceGWImpl submitQuote method: {}", e.getMessage());
		}

		try {

			submissionDate = LocalDate.now();
		} catch (Exception exception) {
			logger.error("Inside QuoteSubmissionServiceCarrierImpl submitQuote method: {}", exception.getMessage());
			exception.getMessage();
		}

		quoteDAO.updateSubmissionStatus(quickIndicationDetails.getApplication().getExternalId(), quoteNumber, "Yes",
				submissionDate.toString());
		return quoteNumber;
	}

}
