package com.exavalu.agentportal.service;

import com.exavalu.agentportal.model.Login;
import com.exavalu.agentportal.model.db.LoginUser;
import com.exavalu.agentportal.model.db.User;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface LoginService {
    String getRoleFromDB(Login loginDetails);
    String createNewUser(User loginDetails);
    String updateUser(User loginDetails1);
    String deleteUser(User loginDetails2);
    String insertLoginUserIntoDb(LoginUser loginUserDetails);
    List<User> getUser(User userDetails3);
    List<LoginUser> getLoginUser();
}
