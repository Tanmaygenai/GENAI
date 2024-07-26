package com.exavalu.agentportal.service;

import com.exavalu.agentportal.model.RecentList;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecentListDBService {

    List<RecentList> getItems();
}
