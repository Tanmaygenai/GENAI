package com.exavalu.agentportal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.exavalu.agentportal.db.DatabaseDAO;
import com.exavalu.agentportal.db.DatabaseDAOImpl;
import com.exavalu.agentportal.model.ReportDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class QuickIndicationReportServiceImpl implements QuickIndicationReportService {
	private static final Logger logger = LogManager.getLogger(QuickIndicationReportServiceImpl.class);
	@Value("${quickIndicationDetailsReportURL}")
	String reportURL;
	@Value("${version}")
	String version;
	@Value("${clientId}")
	String clientId;
	@Value("${clientPassword}")
	String clientPassword;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private DatabaseDAO quoteDAO;

	public List<ReportDetails> getQuickIndicationDetails(String username, String startDate, String endDate,String product,
			String userRole) {
		logger.debug("Entering QuickIndicationReportServiceImpl getQuickIndicationDetails method, with username = "
				+ username + " , " + "startDate = " + startDate + " , " + "endDate = " + endDate + " , " + "product = "
				+ product);
		String jsonString = "";
		List<ReportDetails> list = new ArrayList<>();

		List<Object[]> details = quoteDAO.getQuickIndicationDetails(startDate, endDate, product, username,userRole);

		for (Object[] array : details) {
			ReportDetails reportDetails = new ReportDetails();
			int count = 0;
			for (Object field : array) {
				if (count == 0) {
					reportDetails.setQuoteNumber(field.toString());
				} else if (count == 1) {
					reportDetails.setInsuredName(field.toString());
				} else if (count == 2) {
					reportDetails.setStateCd(field.toString());
				} else if (count == 3) {
					reportDetails.setProduct(field.toString());
				} else if (count == 4) {
					reportDetails.setPremium(field.toString());
				} else if (count == 5) {
					reportDetails.setEffectiveDate(field.toString());
				} else if (count == 6) {
					reportDetails.setClassCode(field.toString());
				} else if (count == 7) {
					reportDetails.setLiabilityLimit(field.toString());
				} else if (count == 8) {

					reportDetails.setSubmissionDate(field.toString());
				} else if (count == 9) {
					reportDetails.setIndicationDate(field.toString());
				}
				count++;
			}
			list.add(reportDetails);
		}

		return list;

	}
}
