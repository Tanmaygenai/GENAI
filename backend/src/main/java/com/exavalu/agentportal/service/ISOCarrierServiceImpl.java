package com.exavalu.agentportal.service;

import com.exavalu.agentportal.model.FireLine;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("carrier")
public class ISOCarrierServiceImpl implements ISOService {
	private static final Logger logger = LogManager.getLogger(ISOCarrierServiceImpl.class);

	@Override
	public String getISOData(FireLine fireLineDetails) {
		logger.debug("Entering ISOCarrierServiceImpl getISOData method, with fireLineDetails = "
				+ fireLineDetails.toString());
		return null;
	}
}
