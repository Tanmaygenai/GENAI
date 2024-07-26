package com.exavalu.agentportal.service;

import com.exavalu.agentportal.model.PolicyDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpenWorkSocService {

    List<PolicyDetails> getItems(String userName, String role);
}
