package com.exavalu.agentportal.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import org.hibernate.Query;
import org.hibernate.Session;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.exavalu.agentportal.config.DatabaseEncryptDecrypt;
import com.exavalu.agentportal.model.db.Quote;
import com.exavalu.agentportal.service.DashboardDBServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.exavalu.agentportal.repository.QuoteRepository;

@Service
@Qualifier("db")
public class DataDownloadServiceImpl implements DataDownloadService {
	@Value("${quoteFormat}")
	String quoteFormat;
	@Autowired
	private RestTemplate restTemplate;
	private static final Logger logger = LogManager.getLogger(DashboardDBServiceImpl.class);
	@Autowired
	private EntityManager entityManager;

	@Autowired
	QuoteRepository repository;

	@Autowired
	DatabaseEncryptDecrypt databaseEncryptDecrypt;

	@Override
	public List<Quote> getDataDownloadDetails(String systemId) {
		logger.debug("Entering DataDownloadServiceImpl getDataDownloadDetails method, with systemId = ", systemId);
		Session currentSession = entityManager.unwrap(Session.class);
		List<Quote> quoteList = null;
		if (systemId.contains("QT")) {
			//query.append("Select indication_data_json FROM quote where quote_number='").append(systemId).append("'");
			Query<Quote> query = currentSession.createQuery(
					"select indicationData from QuoteEntity where quote_number = :quoteNumber");
			query.setParameter("quoteNumber", systemId.replace("\"", ""));
			quoteList = query.list();
		} else {
			//query.append("Select indication_data_json FROM quote where id=").append(systemId);
			Query<Quote> query = currentSession.createQuery(
					"select indicationData from QuoteEntity where id = :id");
			query.setParameter("id", Integer.parseInt(systemId.replace("\"", "")));
			quoteList = query.list();
		}
		return quoteList;
	}

}
