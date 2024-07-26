package com.exavalu.agentportal.service;

import com.exavalu.agentportal.error.FileNotFoundException;
import com.exavalu.agentportal.model.ContentManagement;
import com.exavalu.agentportal.repository.FileRepo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Qualifier("db")
public class CabinetDBServiceImpl implements CabinetService {
	private static final Logger logger = LogManager.getLogger(CabinetDBServiceImpl.class);
	@Autowired
	private EntityManager entityManager;
	@Autowired
	private FileRepo fileRepo;

	public List<String> getCabinet(String username) {
		logger.debug("Entering CabinetDBServiceImpl getCabinet method, with username = " + username);
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<String> query = builder.createQuery(String.class);
		Root<ContentManagement> classificationDetails = query.from(ContentManagement.class);
		query.select(classificationDetails.get("classification")).distinct(true);
		List<String> classificationList = entityManager.createQuery(query).getResultList();
		return classificationList;
	}

	@Override
	public List<ContentManagement> getFileDetails(String username, String classification) {
		logger.debug("Entering CabinetDBServiceImpl getFileDetails method, with username = " + username + " , "
				+ "classification = " + classification);

		List<ContentManagement> list1 = new ArrayList<>();
		Session currentSession = entityManager.unwrap(Session.class);
		Query query = currentSession.createQuery(
				"select name,description,id from ContentManagement where classification = :classification_name");
		query.setParameter("classification_name", classification);

		List<ContentManagement> list = new ArrayList<>();
		List<Object[]> results = query.getResultList();
		for (Object[] result : results) {
			ContentManagement contentManagement = new ContentManagement();
			contentManagement.setName((String) result[0]);
			contentManagement.setDescription((String) result[1]);
			contentManagement.setId((String) result[2]);
			list.add(contentManagement);
		}
//        Iterator <ContentManagement> itr = list.iterator();
//        while (itr.hasNext()) {
//        	logger.info("inside while >>>>>>>>>>>>>>>>>>>>>");
//             	//ContentManagement row = itr.next();
//                 ContentManagement cabinateFileDetails = new ContentManagement();
//                 cabinateFileDetails.setName((String) itr[0]);
//                 cabinateFileDetails.setDescription((String) row.getDescription());
//                 cabinateFileDetails.setId((String) row.getId());
//                 list1.add(cabinateFileDetails);
//
//        }
		return list;
	}

	@Override
	public ContentManagement getFile(String fileId) {
		logger.debug("Entering CabinetDBServiceImpl getFile method, with fileId = " + fileId);
		return fileRepo.findById(fileId)
				.orElseThrow(() -> new FileNotFoundException("File you requested not found with id " + fileId));
	}

	@Override
	public List<ContentManagement> getAllFileDetails(String username) {
		logger.debug("Entering CabinetDBServiceImpl getAllFileDetails method, with username = " + username );

		List<ContentManagement> list1 = new ArrayList<>();
		Session currentSession = entityManager.unwrap(Session.class);
		Query query = currentSession.createQuery(
				"select name,description,id from ContentManagement");
		

		List<ContentManagement> list = new ArrayList<>();
		List<Object[]> results = query.getResultList();
		for (Object[] result : results) {
			ContentManagement contentManagement = new ContentManagement();
			contentManagement.setName((String) result[0]);
			contentManagement.setDescription((String) result[1]);
			contentManagement.setId((String) result[2]);
			list.add(contentManagement);
		}
//        Iterator <ContentManagement> itr = list.iterator();
//        while (itr.hasNext()) {
//        	logger.info("inside while >>>>>>>>>>>>>>>>>>>>>");
//             	//ContentManagement row = itr.next();
//                 ContentManagement cabinateFileDetails = new ContentManagement();
//                 cabinateFileDetails.setName((String) itr[0]);
//                 cabinateFileDetails.setDescription((String) row.getDescription());
//                 cabinateFileDetails.setId((String) row.getId());
//                 list1.add(cabinateFileDetails);
//
//        }
		return list;
	}

}
