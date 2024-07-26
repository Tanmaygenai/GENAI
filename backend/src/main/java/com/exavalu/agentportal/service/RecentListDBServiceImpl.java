package com.exavalu.agentportal.service;

import com.exavalu.agentportal.model.RecentList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class RecentListDBServiceImpl implements RecentListDBService {
	private static final Logger logger = LogManager.getLogger(RecentListDBServiceImpl.class);
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<RecentList> getItems() {
		logger.debug("Entering RecentListDBServiceImpl getItems method");
		Session currentSession = entityManager.unwrap(Session.class);
		List<RecentList> list = currentSession.createQuery("from RecentList").list();
//		for (RecentList view : list) {
//			logger.info("Current Owner ::: " + view.getCurrentOwner());
//			logger.info("Status ::: " + view.getStatus());
//		}
		return list;
	}

}
