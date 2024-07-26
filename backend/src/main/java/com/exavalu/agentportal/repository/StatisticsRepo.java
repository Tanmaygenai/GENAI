package com.exavalu.agentportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.exavalu.agentportal.model.UserStats;

public interface StatisticsRepo extends JpaRepository<UserStats,Integer>{

}
