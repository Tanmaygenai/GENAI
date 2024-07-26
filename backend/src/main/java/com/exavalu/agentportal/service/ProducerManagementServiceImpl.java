package com.exavalu.agentportal.service;

import java.util.List;
import javax.persistence.EntityManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.exavalu.agentportal.model.ProducerManagement;
import com.exavalu.agentportal.repository.ProducerManagementRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Qualifier("db")
public class ProducerManagementServiceImpl implements ProducerManagementService {
	private static final Logger logger = LogManager.getLogger(ProducerManagementServiceImpl.class);

	@Autowired
	private ProducerManagementRepo repo;

	@Autowired
	private EntityManager entityManager;

	@Override
	public List<ProducerManagement> getProducerInfo(String producerType, String producerCode) {
		logger.debug("Entering ProducerManagementServiceImpl getProducerInfo method, with producerType = "
				+ producerType + " , " + "producerCode = " + producerCode);
		List<ProducerManagement> response = null;
		Session currentSession = entityManager.unwrap(Session.class);
		List<ProducerManagement> result = null;
		if (producerType.startsWith("producer_code")) {
			Query<ProducerManagement> query = currentSession
					.createQuery("from ProducerManagement where producer_code LIKE CONCAT('%',:producer_code,'%')");
			query.setParameter("producer_code", producerCode);
			result = query.list();
			if (result.size() >= 1) {
				response = result;
			}
		} else if (producerType.startsWith("name")) {
			Query<ProducerManagement> query = currentSession.createQuery("from ProducerManagement where name LIKE CONCAT('%',:name,'%')");
			query.setParameter("name", producerCode);
			result = query.list();
			if (result.size() >= 1) {
				response = result;
			}
		} else if (producerType.startsWith("email")) {
			Query<ProducerManagement> query = currentSession.createQuery("from ProducerManagement where email LIKE CONCAT('%',:email,'%')");
			query.setParameter("email", producerCode);
			result = query.list();
			if (result.size() >= 1) {
				response = result;
			}
		} else {
			// If any other condition needs to be returned when none of the option matches
		}
		return response;
	}

	@Override
	public String createNewProducer(ProducerManagement newProducer) {
		logger.debug("Entering ProducerManagementServiceImpl createNewProducer method, with ProducerManagement = "
				+ newProducer.toString());
		String response = "";
		String email = newProducer.getEmail();
		String producer_code = newProducer.getProducer_code();
		String agency_code = newProducer.getAgency_code();
		String group_code = newProducer.getGroup_code();
		String name = newProducer.getName();
		ObjectMapper mapper = new ObjectMapper();
		int count = 0, res = 0;
		List<ProducerManagement> allProducer = repo.findAll();
		for (int i = 0; i < allProducer.size(); i++) {
			try {
				if (mapper.writeValueAsString(allProducer.get(i)).contains(newProducer.getEmail())) {
					count++;
				}
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (count > 0) {
			response = "Producer already exist";
		} else {
			res = repo.insertProducer(producer_code, agency_code, group_code, name, email);
		}
		if (res > 0) {
			response = "Producer created successfully";
		}else {
			response = "Duplicate values exist for the product code or agency code or group code!";
		}
		return response;
	}

}
