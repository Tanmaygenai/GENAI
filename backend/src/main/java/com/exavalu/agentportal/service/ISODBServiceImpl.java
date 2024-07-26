package com.exavalu.agentportal.service;

import com.exavalu.agentportal.controller.ISOController;
import com.exavalu.agentportal.model.FireLine;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
@Qualifier("db")
public class ISODBServiceImpl implements ISOService {
	static Logger logger = LogManager.getLogger(ISODBServiceImpl.class);

	@Override
	public String getISOData(FireLine fireLineDetails) {
		logger.debug(
				"Entering ISODBServiceImpl getISOData method, with fireLineDetails = " + fireLineDetails.toString());
		List<String> postcodeList = Arrays.asList("95678", "92110");
		String fireLineMessage = "Potential Hazard - As per our Location Intelligence reprort for entered Address";
		if (postcodeList.contains(fireLineDetails.getPrimaryBusinessAddress().getPostcode())) {
			return fireLineMessage;
		} else
			return "";
	}

}
