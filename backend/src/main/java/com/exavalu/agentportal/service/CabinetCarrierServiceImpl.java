package com.exavalu.agentportal.service;

import com.exavalu.agentportal.controller.EligibilityCheckController;
import com.exavalu.agentportal.model.ContentManagement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

import java.util.Collections;
import java.util.List;

@Service
@Qualifier("carrier")
public class CabinetCarrierServiceImpl implements CabinetService {
	private static final Logger logger = LogManager.getLogger(CabinetCarrierServiceImpl.class);
	@Autowired
	private EntityManager entityManager;

	public List<String> getCabinet(String username) {
		logger.debug("Entering CabinetCarrierServiceImpl getCabinet method, with username = " + username);
		return Collections.emptyList();
	}

	@Override
	public List<ContentManagement> getFileDetails(String username, String classification) {
		logger.debug("Entering CabinetCarrierServiceImpl getFileDetails method, with username = " + username + " , "
				+ "classification = " + classification);
		return Collections.emptyList();
	}

	@Override
	public ContentManagement getFile(String fileId) {
		logger.debug("Entering CabinetCarrierServiceImpl getFile method, with fileId = " + fileId);
		return null;
	}

	@Override
	public List<ContentManagement> getAllFileDetails(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
