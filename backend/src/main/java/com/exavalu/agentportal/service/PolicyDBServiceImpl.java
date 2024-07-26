package com.exavalu.agentportal.service;

import com.exavalu.agentportal.error.FileNotFoundException;
import com.exavalu.agentportal.model.PolicyDetails;
import com.exavalu.agentportal.model.QuoteList;
import com.exavalu.agentportal.model.DRCExcessQuickIndication.PolicyPremiumInfo;
import com.exavalu.agentportal.model.DRCExcessQuickIndication.QuickIndication;
import com.exavalu.agentportal.model.db.Quote;
import com.exavalu.agentportal.model.DocumentContainer;
import com.exavalu.agentportal.repository.PolicyFileRepo;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
@Qualifier("db")
public class PolicyDBServiceImpl implements PolicyService {
	private static final Logger logger = LogManager.getLogger(PolicyDBServiceImpl.class);
	@Autowired
	private EntityManager entityManager;
	@Autowired
	private PolicyFileRepo policyFileRepo;

	@Override
	public QuoteList getPolicyFromDB(String id, String username) {
		logger.debug("Entering PolicyDBServiceImpl getPolicyFromDB method, with username = " + username + " , "
				+ "id = " + id);
		QuoteList quoteObj=null;
		Session currentSession = entityManager.unwrap(Session.class);
		if (id.contains("QT"))
		{
			
			quoteObj =currentSession.get(QuoteList.class,Long.parseLong(id.replaceAll("^\"QT-0+", "").replaceAll("\"", "")));
		}
		else {
			quoteObj =currentSession.get(QuoteList.class,Long.parseLong(id.replace("\"", "")));
		}
		return quoteObj;
	}

	@Override
	public List<Quote> getPolicyFileDetails(String username, String quoteNumber) {
		logger.debug("Entering PolicyDBServiceImpl getPolicyFileDetails method, with username = " + username + " , "
				+ "policyNumber = " + quoteNumber);

		List<Quote> list1 = new ArrayList<>();
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Object[]> query = currentSession.createQuery("select insuredName,quoteNumber,busAddress,effectiveDate,expirationDate from QuoteEntity where quote_number = :quoteNumber");
		query.setParameter("quoteNumber", quoteNumber.replace("\"", ""));
		List<Object[]> list = query.list();
		for (Object[] row : list) {
			Quote fileDetails = new Quote();
			fileDetails.setInsuredName((String) row[0]);
			fileDetails.setQuoteNumber((String) row[1]);
			fileDetails.setBusAddress((String) row[2]);
			fileDetails.setEffectiveDate((String) row[3]);
			fileDetails.setExpirationDate((String) row[4]);
			list1.add(fileDetails);
}
		return list1;
	}

	@Override
	public DocumentContainer getPolicyFile(String fileId) {
		logger.debug("Entering PolicyDBServiceImpl getPolicyFile method, with fileId = " + fileId);
		return policyFileRepo.findById(fileId)
				.orElseThrow(() -> new FileNotFoundException("File you requested not found with id " + fileId));
	}

	@Override
	public String createPolicy(QuickIndication quickIndicationDetails, String username) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
