package com.exavalu.agentportal.repository;

import com.exavalu.agentportal.model.db.User;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepo extends JpaRepository<User,Integer> {
	
	@Modifying(clearAutomatically = true)
    @Query("update User set password=:password where userName= :userName")
	@Transactional
    public void updateUser(String password, String userName);
	
	@Modifying(clearAutomatically = true)
    @Query("delete from User where userName= :userName")
	@Transactional
    public void deleteUser(String userName);
	
}