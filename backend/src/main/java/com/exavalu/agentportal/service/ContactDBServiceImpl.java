package com.exavalu.agentportal.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import com.exavalu.agentportal.model.db.ContactUsDetails;
import com.exavalu.agentportal.repository.ContactRepo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.aspectj.weaver.loadtime.Agent;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@EnableScheduling
@Service
public class ContactDBServiceImpl implements ContactDBService {
	private static final Logger logger = LogManager.getLogger(ContactDBServiceImpl.class);
	@Value("${clientId}")
	String clientId;
	@Value("${clientPassword}")
	String clientPassword;
	@Value("${spring.mail.username}")
	private String mailUsername;

	@Autowired
	private EntityManager entityManager;

	@Autowired
	ContactRepo crepo;

	@Autowired
	JavaMailSenderImpl emailService;
	
	@Override
	public List<ContactUsDetails> getContactUsDetails(String userName, String role) {
	    Session currentSession = entityManager.unwrap(Session.class);
	    List<ContactUsDetails> contactUsDetails = null;

	    if (role.contains("admin")) {
	        // admin role
	        String filePath = "../frontend/src/dummydata/UW-Agent_Mapping.xlsx";//provide src->dummydata path.
	        Map<String, List<String>> mappings = new HashMap<>();

	        try (Workbook workbook = new XSSFWorkbook(new FileInputStream(filePath))) {
	            Sheet sheet = workbook.getSheetAt(0); // Assuming the mappings are in the first sheet

	            // leaving the first row and starting to execute from the second row
	            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
	                Row row = sheet.getRow(rowIndex);
	                Cell adminCell = row.getCell(0);
	                Cell agentsCell = row.getCell(1);

	                String admin = adminCell.getStringCellValue();
	                String agentsCellValue = agentsCell.getStringCellValue();

	                if (mappings.containsKey(admin)) {
	                    mappings.get(admin).add(agentsCellValue);
	                } else {
	                    List<String> agentList = new ArrayList<>();
	                    agentList.add(agentsCellValue);
	                    mappings.put(admin, agentList);
	                }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        if (mappings.containsKey(userName)) {
	            List<String> agents = mappings.get(userName);

	            if (!agents.isEmpty()) {
	                Query query = currentSession.createQuery("FROM ContactUsDetails WHERE userName IN (:agents) ORDER BY caseId DESC");
	                query.setParameterList("agents", agents);
	                String queryString = query.getQueryString();
	                contactUsDetails = query.list();
	            }
	        } else {
	            System.out.println("No mapping found for " + userName);
	        }
	    } else {
	        // Fetch cases for the logged-in agent
	        Query query = currentSession.createQuery("FROM ContactUsDetails WHERE userName in (:userName) ORDER BY caseId DESC");
	        query.setParameter("userName", userName);
	        String queryString = query.getQueryString();
	        contactUsDetails = query.list();
	    }
	    return contactUsDetails;

	}
	
	@Scheduled(cron = "0 0 0 * * *") // Runs at midnight every day
    public void markEntriesAsClosed() {
        List<ContactUsDetails> openEntries = crepo.findByStatus("Open");
        List<ContactUsDetails> entries = new ArrayList<>();
        LocalDate today = LocalDate.now();
        LocalDate thresholdDate = today.minusDays(10);
	     ZoneId systemZone = ZoneId.systemDefault();
        for (ContactUsDetails entry : openEntries) {
            if (entry.getDescription() != null) {
                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    List<Map<String, Object>> descriptionArray = objectMapper.readValue(entry.getDescription(), new TypeReference<List<Map<String, Object>>>() {});

                    if (!descriptionArray.isEmpty()) {
                        String createdAt = (String) descriptionArray.get(descriptionArray.size()-1).get("createdAt");
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
                        ZonedDateTime createdAtZonedDateTime = ZonedDateTime.parse(createdAt, formatter.withZone(systemZone));
                        LocalDateTime createdAtDateTime = createdAtZonedDateTime.toLocalDateTime();                 
                        if (createdAtDateTime.toLocalDate().isBefore(thresholdDate)) {
                            entry.setStatus("Close");
                            entry.setClosedAt(LocalDateTime.now().toString());
                            entries.add(entry);
                        }
                    }
                } catch (Exception e) {
                    // Handle the exception if JSON parsing fails
                    e.printStackTrace();
                }
            }
        }
        if(!entries.isEmpty()) {
        	  crepo.saveAll(entries);
        } else {
        	System.out.print("No ticket to close");
        }
	};

	@Override
	public String closeCaseByUser(String userName, ContactUsDetails contactUsDetails ) {
		Session currentSession = entityManager.unwrap(Session.class);
		//ContactUsDetails contactUsDetails = null;
		String result = "";
		if(contactUsDetails.getCaseId() != null && !contactUsDetails.getCaseId().isEmpty()) {
			contactUsDetails.setClosedBy(userName);
			int rowsUpdated = crepo.updateContactDetails(contactUsDetails.getCaseId(), userName,  contactUsDetails.getClosedAt(), contactUsDetails.getStatus());
			if(rowsUpdated> 0) {
				result="Ticket closed successfully!";
			}
			else {
				result="Some error!";
			}
		} else {
			result = "Case Id is empty";
		}
		return result;	
	}
	
    @Override
	public List<ContactUsDetails> getContactUdDetailsByCaseId(String userName, String caseId) {
		Session currentSession = entityManager.unwrap(Session.class);
		List<ContactUsDetails> contactUsDetails = null;
		Query query = (Query) currentSession.createQuery("from ContactUsDetails Where caseId=:caseId");
		query.setParameter("caseId", caseId);
		contactUsDetails = query.list();
		return contactUsDetails;	
	}

    @Override
	public String caseReopen(String userName, ContactUsDetails contactUsDetails ) {
		Session currentSession = entityManager.unwrap(Session.class);
		//ContactUsDetails contactUsDetails = null;
		String result = "";
		if(contactUsDetails.getCaseId() != null && !contactUsDetails.getCaseId().isEmpty()) {
			contactUsDetails.setClosedBy(userName);
			int rowsUpdated = crepo.updateContactDetails(contactUsDetails.getCaseId(), null, contactUsDetails.getClosedAt(), contactUsDetails.getStatus());
			if(rowsUpdated> 0) {
				result="Ticket reopened successfully!";
			}
			else {
				result="Some error!";
			}
		} else {
			result = "Case Id is empty";
		}
		return result;
	}
    
	@Override
	public String sendDetails(ContactUsDetails contactUsDetails) {
		logger.debug("Entering ContactDBServiceImpl sendDetails method, with ContactUsDetails = "
				+ contactUsDetails.toString());
		// TODO Auto-generated method stub
		String response="";
		String name = contactUsDetails.getName();
		String userName = contactUsDetails.getUserName();
		String agencyName = contactUsDetails.getAgencyName();
		String email = contactUsDetails.getEmail();
		String phoneNumber = contactUsDetails.getPhoneNumber();
		String message = contactUsDetails.getMessage();
		String description = contactUsDetails.getDescription();
		String severity = contactUsDetails.getSeverity();
		String category = contactUsDetails.getCategory();
		String createdDate = contactUsDetails.getCreatedDate();
		String status = contactUsDetails.getStatus();
		String caseId =  contactUsDetails.getCaseId();
		Session currentSession = entityManager.unwrap(Session.class);
		List<ContactUsDetails> contactUsDetailsList = null;
		contactUsDetailsList = currentSession.createQuery("from ContactUsDetails").list();
		if(caseId == null || caseId.isEmpty()) {
			int listSize = contactUsDetailsList.size();
			if (listSize > 0) {
				int maxCaseId = 1000000;
				for (ContactUsDetails contactUsDetailsL : contactUsDetailsList) {
					if(contactUsDetailsL.getCaseId() != null) {
					    if (Integer.parseInt(contactUsDetailsL.getCaseId()) > maxCaseId) {
					        maxCaseId = Integer.parseInt(contactUsDetailsL.getCaseId());
					    }
					}
				}

			    int newCaseId = maxCaseId + 1;
			    caseId = Integer.toString(newCaseId);
			} else {
			    caseId = "1000001";
			}
			contactUsDetails.setCaseId(caseId);
		}
		crepo.save(contactUsDetails);
	    	try {
				emailService.sendSimpleEmail(mailUsername, "Contact Us Details",
						"User contact details:" + "Name: " + name + "," + "User Name: " + userName + "Agency name: " + agencyName + "," + "Email: "
								+ email + "," + "Phone number:" + phoneNumber + "," + "Message: " + message + "," + "Description: " + description + ","
								+ "Severity:" + severity + "," + "Case Id:" + caseId + "," + "Category:" + category + "," + "Created Date:" + createdDate + ","
								+ "Status: " + status);
			response = caseId;
			} catch (MailException mailException) {
				logger.error("Inside ContactDBServiceImpl sendDetails method: {}", mailException.getMessage());
			response = "Some error!";
			}
		return response;
	}

}
