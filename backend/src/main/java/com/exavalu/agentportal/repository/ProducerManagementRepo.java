package com.exavalu.agentportal.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.exavalu.agentportal.model.ProducerManagement;

public interface ProducerManagementRepo extends JpaRepository<ProducerManagement, Integer> {
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("INSERT INTO ProducerManagement(producer_code, agency_code, group_code, name, email) " +
			"SELECT DISTINCT :producer_code, :agency_code, :group_code, :name, :email " +
            "FROM ProducerManagement p " +
            "WHERE p.producer_code <> :producer_code " +
            "   AND p.agency_code <> :agency_code " +
            "   AND p.group_code <> :group_code " +
            "   AND p.email <> :email")
	int insertProducer(String producer_code, String agency_code, String group_code, String name, String email);
}
