package com.exavalu.agentportal.service;

import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.exavalu.agentportal.model.ProducerManagement;

@Service
@Qualifier("carrier")
public class ProducerManagementCarrierImpl implements ProducerManagementService {
	private static final Logger logger = LogManager.getLogger(ProducerManagementCarrierImpl.class);

	@Override
	public List<ProducerManagement> getProducerInfo(String producerType, String producerCode) {
		logger.debug("Entering ProducerManagementCarrierImpl getProducerInfo method, with producerType = "
				+ producerType + " , " + "producerCode = " + producerCode);
		// TODO Auto-generated method stub
		return Collections.emptyList();
	}

	@Override
	public String createNewProducer(ProducerManagement newProducer) {
		logger.debug("Entering ProducerManagementCarrierImpl createNewProducer method, with ProducerManagement = "
				+ newProducer.toString());
		// TODO Auto-generated method stub
		return null;
	}

}
