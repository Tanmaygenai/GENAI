package com.exavalu.agentportal.model;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.exavalu.agentportal.config.Encrypt;

import java.sql.Date;

@Entity
@Table(name="policydetails")
public class PolicyDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="policy_number")
    private String policyNumber;
    @Column(name="created_date")
    private String createdDate;
    @Column(name="premium")
    private String premium;
	@Column(name="carrier_mode")
    private String carrierMode;
    @Column(name="creation_status")
    private String creationStatus;
	@Column(name="username")
    private String userName;
    @Column(name="policy_data")
    private String policyData;
	@Column(name="quote_number")
    private String quoteNumber;
	@Column(name="insured_name")
    private String insuredName;
	@Column(name="product")
    private String product;
	@Column(name="state")
    private String state;
	@Column(name="indication_data")
	@Convert(converter = Encrypt.class)
    private String indicationData;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
    public String getQuoteNumber() {
		return quoteNumber;
	}

	public void setQuoteNumber(String quoteNumber) {
		this.quoteNumber = quoteNumber;
	}
  	
    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPremium() {
		return premium;
	}

	public void setPremium(String premium) {
		this.premium = premium;
	}

	public String getCarrierMode() {
		return carrierMode;
	}

	public void setCarrierMode(String carrierMode) {
		this.carrierMode = carrierMode;
	}

	public String getCreationStatus() {
		return creationStatus;
	}

	public void setCreationStatus(String creationStatus) {
		this.creationStatus = creationStatus;
	}
    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }
    
	public String getPolicyData() {
		return policyData;
	}

	public void setPolicyData(String policyData) {
		this.policyData = policyData;
	}
	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getInsuredName() {
		return insuredName;
	}

	public void setInsuredName(String insuredName) {
		this.insuredName = insuredName;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	 public String getIndicationData() {
			return indicationData;
	}

	public void setIndicationData(String indicationData) {
		this.indicationData = indicationData;
	}

}
