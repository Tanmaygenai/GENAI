package com.exavalu.agentportal.service;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.exavalu.agentportal.db.DatabaseDAO;
import com.exavalu.agentportal.model.LossNoticeList;
import com.exavalu.agentportal.model.ReportDetails;

@Service
@Qualifier("db")

public class LossNoticeDBServiceImpl implements LossNoticeService {
	private static final Logger logger = LogManager.getLogger(LossNoticeDBServiceImpl.class);
	@Autowired
	private DatabaseDAO lossDAO;

	@Override
	public List<LossNoticeList> getLossNoticeReports(String userName, String startDate, String endDate,String roleType) {
		logger.debug("Entering LossNoticeDBServiceImpl getLossNoticeReports method, with userName = " + userName + " , "
				+ "startDate = " + startDate + " , " + "endDate = " + endDate);
		List<LossNoticeList> list = new ArrayList<>();
		List<Object[]> details = lossDAO.getLossNoticeReports(startDate, endDate, userName,roleType);

		for (Object[] array : details) {
			LossNoticeList lossNoticeList = new LossNoticeList();
			int count = 0;
			for (Object field : array) {
				if (count == 0) {
					lossNoticeList.setReportedDate(field.toString());
				} else if (count == 1) {
					if (field == null) {
						lossNoticeList.setLossNoticeNumber("".toString());
					} else {
						lossNoticeList.setLossNoticeNumber(field.toString());
					}
				} else if (count == 2) {
					lossNoticeList.setLossnoticeData(field.toString());
				} else if (count == 3) {
					lossNoticeList.setUserName(field.toString());
				}
				count++;
			}
			list.add(lossNoticeList);
		}
		return list;
	}

}
