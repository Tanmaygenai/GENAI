package com.exavalu.agentportal.service;

import com.exavalu.agentportal.db.DatabaseDAO;
import com.exavalu.agentportal.model.ReportDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class QuoteSubmissionPremiumReportServiceImpl implements QuoteSubmissionPremiumReportService {
	private static final Logger logger = LogManager.getLogger(QuoteSubmissionPremiumReportServiceImpl.class);
	@Value("${quoteSubmissionPremiumReportURL}")
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

	public List<ReportDetails> getQuoteSubmissionPremiumDetails(String username, String startDate, String endDate,
			String product,String roleType) {
		logger.debug(
				"Entering QuoteSubmissionPremiumReportServiceImpl getQuoteSubmissionPremiumDetails method, with username = "
						+ username + " , " + "startDate = " + startDate + " , " + "endDate = " + endDate + " , "
						+ "product = " + product);
		String jsonString = "";
		List<ReportDetails> list = new ArrayList<>();

		List<Object[]> details = quoteDAO.getQuoteSubmissionPremiumDetails(startDate, endDate, product, username,roleType);

		for (Object[] array : details) {
			ReportDetails reportDetails = new ReportDetails();
			int count = 0;
			for (Object field : array) {
				if (count == 0) {
					reportDetails.setQuoteNumber(field.toString());
				} else if (count == 1) {
					reportDetails.setProduct(field.toString());
				} else if (count == 2) {
					reportDetails.setPremium(field.toString());
				} else if (count == 3) {
					reportDetails.setEffectiveDate(field.toString());
				} else if (count == 4) {
					reportDetails.setSubmissionDate(field.toString());
				} else if (count == 5) {
					reportDetails.setAccountingMonth(field.toString());
				}
				count++;
			}
			list.add(reportDetails);
		}

		return list;

	}
}
