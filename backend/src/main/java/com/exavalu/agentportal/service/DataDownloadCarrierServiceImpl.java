package com.exavalu.agentportal.service;

import java.util.Collections;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.exavalu.agentportal.model.db.Quote;

@Service
@Qualifier("carrier")
public class DataDownloadCarrierServiceImpl implements DataDownloadService {
	private static final Logger logger = LogManager.getLogger(DataDownloadCarrierServiceImpl.class);

	@Override
	public List<Quote> getDataDownloadDetails(String systemId) {
		logger.debug(
				"Entering DataDownloadCarrierServiceImpl getDataDownloadDetails method, with systemId = " + systemId);
		// TODO Auto-generated method stub
		return null;
	}

}
