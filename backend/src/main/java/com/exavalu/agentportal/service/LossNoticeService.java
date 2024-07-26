package com.exavalu.agentportal.service;

import java.util.List;

import com.exavalu.agentportal.model.LossNoticeList;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LossNoticeService {
	
	 List<LossNoticeList> getLossNoticeReports(String username, String startDate, String endDate,String roleType);
}
