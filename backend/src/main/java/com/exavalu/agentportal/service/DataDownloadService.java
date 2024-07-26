package com.exavalu.agentportal.service;
import java.util.List;

import com.exavalu.agentportal.model.db.Quote;

public interface DataDownloadService {
	List<Quote> getDataDownloadDetails(String systemId);
}
