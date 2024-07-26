package com.exavalu.agentportal.repository;

import com.exavalu.agentportal.model.db.LossNotice;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FNOLRepo extends JpaRepository<LossNotice,Integer> {
    long countByUserName(String userName);
}
