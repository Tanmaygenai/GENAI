package com.exavalu.agentportal.repository;

import com.exavalu.agentportal.model.DocumentContainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface PolicyFileRepo extends JpaRepository<DocumentContainer, String> {
}