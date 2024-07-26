package com.exavalu.agentportal.model;

public class Policy {
private String systemId;
private String id;
private String updateCount;
private String updateUser;
private String customerRef;
private BasicPolicy basicPolicy;

public BasicPolicy getBasicPolicy() {
	return basicPolicy;
}
public void setBasicPolicy(BasicPolicy basicPolicy) {
	this.basicPolicy = basicPolicy;
}
public String getSystemId() {
	return systemId;
}
public void setSystemId(String systemId) {
	this.systemId = systemId;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getUpdateCount() {
	return updateCount;
}
public void setUpdateCount(String updateCount) {
	this.updateCount = updateCount;
}
public String getUpdateUser() {
	return updateUser;
}
public void setUpdateUser(String updateUser) {
	this.updateUser = updateUser;
}
public String getCustomerRef() {
	return customerRef;
}
public void setCustomerRef(String customerRef) {
	this.customerRef = customerRef;
}
@Override
public String toString() {
	return "Policy [systemId=" + systemId + ", id=" + id + ", updateCount=" + updateCount + ", updateUser=" + updateUser
			+ ", customerRef=" + customerRef + ", basicPolicy ID=" + basicPolicy.getId() + ","
	+ "basicPolicy policyNumber="+basicPolicy.getPolicyNumber()+",carrierCd="+basicPolicy.getCarrierCd()+"]";
}





}
