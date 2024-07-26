package com.exavalu.agentportal.service;

import com.exavalu.agentportal.db.DatabaseDAOImpl;
import com.exavalu.agentportal.model.Login;
import com.exavalu.agentportal.model.LossNoticeList;
import com.exavalu.agentportal.model.RecentList;
import com.exavalu.agentportal.model.DRCExcessQuickIndication.QuickIndication;
import com.exavalu.agentportal.model.db.LossNotice;
import com.exavalu.agentportal.model.db.Quote;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.exavalu.agentportal.model.QuoteList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import com.exavalu.agentportal.repository.QuoteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
@Qualifier("db")
public class OpenWorkDBServiceImpl implements OpenWorkService {
	private static final Logger logger = LogManager.getLogger(OpenWorkDBServiceImpl.class);
	@Autowired
	private EntityManager entityManager;
	@Autowired
	private LoginService loginService;
	@Autowired
	private QuoteRepository repository;

	@Autowired
	private DatabaseDAOImpl quoteDAO;

	@Override
	public List<QuoteList> getItems(String userName, String role) {
		logger.debug("Entering OpenWorkDBServiceImpl getItems method, with userName = " + userName + " , " + "role = "
				+ role);
//        Login loginDetails = new Login();
//        loginDetails.setUserName(userName);
//        loginDetails.setPassword(password);
//        List<Quote> response = null;
//        List result = null;
		Session currentSession = entityManager.unwrap(Session.class);
		List<QuoteList> quotes;
//        String role = "";
//        role = loginService.getRoleFromDB(loginDetails);
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Quote> quote = builder.createQuery(Quote.class);
		Root<Quote> root = quote.from(Quote.class);
		if (role.contains("admin")) {
			quote.orderBy(builder.desc(root.get("id")));
			quotes = currentSession.createQuery("from QuoteList q WHERE q.quoteNumber NOT IN (SELECT q.quoteNumber from QuoteList q join PolicyDetails p on q.quoteNumber=p.quoteNumber) AND q.status!=2").list();
		} else { 
			quote.where(builder.equal(root.get("userName"), userName)).orderBy(builder.desc(root.get("id")));
			Query query = currentSession.createQuery("from QuoteList q WHERE q.quoteNumber NOT IN (SELECT q.quoteNumber from QuoteList q join PolicyDetails p on q.quoteNumber=p.quoteNumber) AND q.status!=2 AND q.userName=:userName");
			query.setParameter("userName", userName);
			quotes = query.list();
		}
		
		return quotes;
	}

	public String submitOpenWork(String appNumber, String username) {
		logger.debug("Entering OpenWorkDBServiceImpl submitOpenWork method, with username = " + username + " , "
				+ "appNumber = " + appNumber);
		String quoteNumber = "";
		LocalDate submissionDate = LocalDate.now();

		try {

			submissionDate = LocalDate.now();
		} catch (Exception exception) {
			exception.getMessage();
		}
		quoteNumber = "QT-00000" + appNumber;

		Session currentSession = entityManager.unwrap(Session.class);
		List<QuoteList> quotes = currentSession.createQuery("from QuoteList Where id =" + appNumber).list();
		quoteDAO.updateSubmissionStatus(quotes.get(0).getIndicationId(), quoteNumber, "Yes", submissionDate.toString());
		return quoteNumber;

	}

	public String deleteOpenWork(String appNumber, String username) {
		logger.debug("Entering OpenWorkDBServiceImpl deleteOpenWork method, with username = " + username + " , "
				+ "appNumber = " + appNumber);
		String deleteSuc = "";
		Session currentSession = entityManager.unwrap(Session.class);
		List<QuoteList> quotes = currentSession.createQuery("from QuoteList Where id =" + appNumber).list();
		int deleteQuote = repository.updateStatus(0, quotes.get(0).getIndicationId());
		return deleteSuc;
	}

	
	@Override
	public List<LossNotice> getlossNoticeItems(String username, String role) {
		   logger.debug("Entering OpenWorkDBServiceImpl getLossNoticeItems method, with userName = " + username + " , " + "role = "
		            + role);

		    Session currentSession = entityManager.unwrap(Session.class);
		    List<LossNotice> lossNotices;
		    
		    if (role.contains("admin")) {
		        lossNotices = currentSession.createQuery("from LossNotice").list();
		    } else {
		        Query query = currentSession.createQuery("from LossNotice where userName=:userName");
		        query.setParameter("userName", username);
		        lossNotices = query.list();
		    }
		    return lossNotices;
	}

}
