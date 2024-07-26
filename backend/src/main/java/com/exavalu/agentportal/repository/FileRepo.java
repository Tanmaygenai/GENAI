package com.exavalu.agentportal.repository;

import com.exavalu.agentportal.model.ContentManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface FileRepo extends JpaRepository<ContentManagement, String> {
}