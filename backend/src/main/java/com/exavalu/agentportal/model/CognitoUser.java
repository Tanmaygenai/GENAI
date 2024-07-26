package com.exavalu.agentportal.model;

public class CognitoUser {
	private String userName;
	private String password;
	private String email;
	private String phone;
	private String userStatus;
	private String groupCd;
	private String agencyCd;
	private String role;
	private String organization;
	private String status;
	public CognitoUser (String userName, String email, String phone,String userStatus,String groupCd,
			String agencyCd,String role,String organization, String status ) {
		this.userName = userName;
		this.email = email;
		this.phone = phone;
		this.userStatus = userStatus;
		this.groupCd = groupCd;
		this.agencyCd = agencyCd;
		this.role = role;
		this.organization = organization;
		this.status = status;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public String getGroupCd() {
		return groupCd;
	}
	public void setGroupCd(String groupCd) {
		this.groupCd = groupCd;
	}
	public String getAgencyCd() {
		return agencyCd;
	}
	public void setAgencyCd(String agencyCd) {
		this.agencyCd = agencyCd;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	  public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public CognitoUser() {
	       super();
	        // TODO Auto-generated constructor stub
	    }
	@Override
    public String toString() {
        return this.userName + ", email " + this.email + " phone" + this.phone +", status "+ this.userStatus + ", groupCd "+ this.groupCd
        		+", agencyCd " + this.agencyCd + ", role " +this.role + ", organization " + this.organization + ", status " + this.status ;
    }
}
