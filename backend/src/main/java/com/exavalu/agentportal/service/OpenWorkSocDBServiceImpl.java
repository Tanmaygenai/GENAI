package com.exavalu.agentportal.service;

import com.exavalu.agentportal.db.DatabaseDAOImpl;
import com.exavalu.agentportal.model.Login;
import com.exavalu.agentportal.model.PolicyDetails;
import com.exavalu.agentportal.model.RecentList;
import com.exavalu.agentportal.model.DRCExcessQuickIndication.QuickIndication;
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
public class OpenWorkSocDBServiceImpl implements OpenWorkSocService {
	private static final Logger logger = LogManager.getLogger(OpenWorkSocDBServiceImpl.class);
	@Autowired
	private EntityManager entityManager;
	@Autowired
	private LoginService loginService;

	@Override
	public List<PolicyDetails> getItems(String userName, String role) {
		logger.debug("Entering OpenWorkDBServiceImpl getItems method, with userName = " + userName + " , " + "role = "
				+ role);

		Session currentSession = entityManager.unwrap(Session.class);
		List<PolicyDetails> policies;
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<PolicyDetails> policy = builder.createQuery(PolicyDetails.class);
		Root<PolicyDetails> root = policy.from(PolicyDetails.class);
		if (role.contains("admin")) {
			policy.orderBy(builder.desc(root.get("id")));
			policies = currentSession.createQuery("from PolicyDetails ").list();
		} else {
			policy.where(builder.equal(root.get("userName"), userName)).orderBy(builder.desc(root.get("id")));
			Query query = currentSession.createQuery("from PolicyDetails Where userName=:userName and carrierMode ='SOC'");
			query.setParameter("userName", userName);
			policies = query.list();
		}
		return policies;
	}

}
