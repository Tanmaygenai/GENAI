package com.exavalu.agentportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exavalu.agentportal.model.AppConfig;

@Repository
public interface AppConfigRepo extends JpaRepository<AppConfig,String>{

	AppConfig findByKeytype(String keytype);
}
