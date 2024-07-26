package com.exavalu.agentportal.service;

import com.exavalu.agentportal.model.FNOL;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
@Qualifier("carrier")
public class FNOLServiceCarrierImpl implements FNOLService {

	private static final Logger logger = LogManager.getLogger(FNOLServiceCarrierImpl.class);

	@Override
	public String generateLossNoticeNumber(FNOL fnolDetails, String username,String decryptedFnolDetails) {
		// TODO Auto-generated method stub
		logger.debug("Entering FNOLServiceCarrierImpl generateLossNoticeNumber method, with username = " + username
				+ " , " + " fnolDetails =" + fnolDetails);

		return "";
	}

	@Override
	public Long getLNCount(String username, String role) {
		logger.debug("Entering FNOLServiceCarrierImpl getLNCount method, with username = " + username + " , " + "role = "
				+ role);
		return null;
	}
}
