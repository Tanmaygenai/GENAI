package com.exavalu.agentportal.service;

import com.exavalu.agentportal.model.QuoteList;
import com.exavalu.agentportal.model.db.LossNotice;
import com.exavalu.agentportal.model.db.Quote;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpenWorkService {

    List<QuoteList> getItems(String userName, String role);
    String submitOpenWork(String appNumber,String username);
    String deleteOpenWork(String appNumber,String username);
	List<LossNotice> getlossNoticeItems(String username, String role);
}
