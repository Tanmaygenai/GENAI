package com.exavalu.agentportal.service;

import com.exavalu.agentportal.model.ClaimDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClaimDBService {

    List<ClaimDetails> getClaimDetailsFromDB(String id, String userName);
}
