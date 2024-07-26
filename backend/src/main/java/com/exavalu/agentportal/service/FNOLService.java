package com.exavalu.agentportal.service;

import com.exavalu.agentportal.model.FNOL;
import org.springframework.stereotype.Repository;

@Repository
public interface FNOLService {
    String generateLossNoticeNumber(FNOL fnolDetails, String username, String decryptedFnolDetails);
    Long getLNCount(String username,String role);
}
