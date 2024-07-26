package com.exavalu.agentportal.service;

import com.exavalu.agentportal.model.Login;
import com.exavalu.agentportal.model.db.LoginUser;
import com.exavalu.agentportal.model.db.Quote;
import com.exavalu.agentportal.model.db.User;
import com.exavalu.agentportal.repository.LoginUserRepo;
import com.exavalu.agentportal.repository.UserRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import javax.persistence.EntityManager;
import java.sql.Date;
import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {
	private static final Logger logger = LogManager.getLogger(LoginServiceImpl.class);
	@Value("${authUsername}")
	String authUsername;
	@Value("${authPassword}")
	String authPassword;
	@Autowired
	private RestTemplate restTemplate;
	@Value("${clientId}")
	String clientId;
	@Value("${clientPassword}")
	String clientPassword;

	String str1 = "userName";
	@Autowired
	private EntityManager entityManager;

	@Override
	public String getRoleFromDB(Login loginDetails) {
		logger.debug("Entering LoginServiceImpl getRoleFromDB method, with loginDetails = " + loginDetails.toString());
		String userName = loginDetails.getUserName();
		String password = loginDetails.getPassword();
		String role = "";

		Session currentSession = entityManager.unwrap(Session.class);
		Query<String> query = currentSession
				.createQuery("select userRole from User where user_name= :userName and password= :password");
		query.setParameter(str1, userName);
		query.setParameter("password", password);
		List<String> result = query.list();
		if (result.size() >= 1) {
			role = result.get(0).toString();
		}
		return role;
	}

	@Autowired
	UserRepo repo;

	@Override
	public String createNewUser(User userDetails) {
		logger.debug("Entering LoginServiceImpl createNewUser method, with userDetails = " + userDetails.toString());
		String response = "";
		String userName = userDetails.getUserName();
		String password = userDetails.getPassword();
		Session currentSession = entityManager.unwrap(Session.class);
		Query query = currentSession
				.createQuery("select userName from User where user_name= :userName and password= :password");
		query.setParameter(str1, userName);
		query.setParameter("password", password);
		List<String> result = query.list();
		if (result.size() == 0) {
			long now = System.currentTimeMillis();
			Date sqlDate = new Date(now);
			userDetails.setCreationDate(sqlDate);
			repo.save(userDetails);
			response = "User created successfully";
		} else {
			response = "User already exist";
		}
		return response;
	}

	@Autowired
	UserRepo repo1;

	@Override
	public String updateUser(User userDetails) {
		logger.debug("Entering LoginServiceImpl updateUser method, with userDetails = " + userDetails.toString());
		String response = "";
		String password = userDetails.getPassword();
		String userName = userDetails.getUserName();
		repo1.updateUser(password, userName);
		return response;
	}

	@Autowired
	UserRepo repo2;

	@Override
	public String deleteUser(User userDetails) {
		logger.debug("Entering LoginServiceImpl deleteUser method, with userDetails = " + userDetails.toString());
		String response = "";
		String userName = userDetails.getUserName();
		repo1.deleteUser(userName);
		return response;
	}

	@Autowired
	UserRepo repo3;

	@Override
	public List<User> getUser(User userDetails) {
		logger.debug("Entering LoginServiceImpl getUser method, with userDetails = " + userDetails.toString());
		List<User> response = null;
		Session currentSession = entityManager.unwrap(Session.class);
		String userName = userDetails.getUserName();
		List<User> result = null;

		if (userName.isEmpty()) {
			Query<User> query = currentSession.createQuery("SELECT id,creationDate,userName,userRole from User");
			result = query.list();
		} else {
			Query<User> query = currentSession
					.createQuery("SELECT id,creationDate,userName,userRole from User WHERE userName = :userName");
			query.setParameter(str1, userName);
			result = query.list();
		}

		if (!result.isEmpty()) {

			response = result;
		} else {
			response = null;
		}
		return response;
	}

	@Autowired
	LoginUserRepo repo4;

	@Override
	public String insertLoginUserIntoDb(LoginUser loginUserDetails) {
		logger.debug("Entering LoginServiceImpl insertLoginUserIntoDb method, with loginUserDetails = "
				+ loginUserDetails.toString());
		String response = "";
		String userName = loginUserDetails.getUserName();
		String lastUsed = loginUserDetails.getLastUsed();
		Session currentSession = entityManager.unwrap(Session.class);
		Query query = currentSession.createQuery("select userName from LoginUser where user_name= :userName");
		query.setParameter("userName", userName);
		List result = query.list();
		if (result.size() == 0) {
			repo4.save(loginUserDetails);
			response = "User created successfully";
		} else {
			repo4.updateUser(lastUsed, userName);
			return response;
		}
		return response;
	}

	@Autowired
	LoginUserRepo repo5;

	public List<LoginUser> getLoginUser() {
		logger.debug("Entering LoginServiceImpl getLoginUser method");
		List<LoginUser> response = null;
		Session currentSession = entityManager.unwrap(Session.class);

		Query<LoginUser> query = currentSession.createQuery("from LoginUser");
		response = query.list();
		return response;
	}

}
