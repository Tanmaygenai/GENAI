package com.exavalu.agentportal.repository;

import com.exavalu.agentportal.model.db.LoginUser;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface LoginUserRepo extends JpaRepository<LoginUser,Integer> {
	
	@Modifying(clearAutomatically = true)
    @Query("update LoginUser set last_used=:lastUsed where user_name= :userName")
	@Transactional
    public void updateUser(String lastUsed, String userName);
	
	@Modifying(clearAutomatically = true)
    @Query("delete from LoginUser where user_name= :userName")
	@Transactional
    public void deleteUser(String userName);
	
}