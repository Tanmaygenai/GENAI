package com.exavalu.agentportal.model;

public class BasicPolicy {
	
private String id;
private String policyNumber; 
private String statusCd;
private String carrierCd;
	  public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPolicyNumber() {
		return policyNumber;
	}
	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}
	public String getStatusCd() {
		return statusCd;
	}
	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}
	
    public String getCarrierCd() {
		return carrierCd;
	}
	public void setCarrierCd(String carrierCd) {
		this.carrierCd = carrierCd;
	}
	
	 

}
