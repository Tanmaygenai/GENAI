package com.exavalu.agentportal.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.json.XML;
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
@Qualifier("db")
public class QuoteSubmissionServiceDBImpl implements QuoteSubmissionService {

	private static final Logger logger = LogManager.getLogger(QuoteSubmissionServiceDBImpl.class);
	@Autowired
	private QuoteRepository repository;

	@Autowired
	private DatabaseDAOImpl quoteDAO;

	@Override
	public String submitQuote(QuickIndication quickIndicationDetails) {
		logger.debug("Entering QuoteSubmissionServiceCarrierImpl submitQuote method, with quickIndicationDetails = "
				+ quickIndicationDetails.toString());
		String quoteNumber = "";
		LocalDate submissionDate = LocalDate.now();

		List<Quote> quotelist = repository.findAll(Sort.by(Sort.Direction.DESC, "id"));
		Quote quote = quotelist.get(0);
		int sequenceNumber = quote.getId();
		ObjectMapper Obj = new ObjectMapper();
		String jsonStr = null;
		try {
			jsonStr = Obj.writeValueAsString(quickIndicationDetails);
		} catch (JsonProcessingException e) {
			logger.error("Inside QuoteSubmissionServiceCarrierImpl submitQuote method: {}", e.getMessage());
		}

		quoteNumber = "QT-00000" + sequenceNumber;

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
