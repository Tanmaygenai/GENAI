package com.exavalu.agentportal.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.exavalu.agentportal.model.ProducerManagement;

@Repository
public interface ProducerManagementService {
	List<ProducerManagement> getProducerInfo(String producerType, String producerCode);

	String createNewProducer(ProducerManagement newProducer);
}
