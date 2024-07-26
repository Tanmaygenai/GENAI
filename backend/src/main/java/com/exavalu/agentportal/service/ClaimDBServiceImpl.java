package com.exavalu.agentportal.service;

import com.exavalu.agentportal.model.ClaimDetails;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import java.util.List;

@Service
public class ClaimDBServiceImpl implements ClaimDBService {

	private static final Logger logger = LogManager.getLogger(ClaimDBServiceImpl.class);
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<ClaimDetails> getClaimDetailsFromDB(String id, String userName) {
		logger.debug("Entering ClaimDBServiceImpl getClaimDetailsFromDB method, with id = " + id + " , " + "userName = "
				+ userName);
		Session currentSession = entityManager.unwrap(Session.class);
		Query<ClaimDetails> query = currentSession.createQuery("from ClaimDetails where policy_number= :id");
		query.setParameter("id", id);
		return query.list();
	}
}
