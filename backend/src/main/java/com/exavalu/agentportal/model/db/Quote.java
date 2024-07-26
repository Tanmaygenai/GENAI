package com.exavalu.agentportal.model.db;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import com.exavalu.agentportal.config.DatabaseEncryptDecrypt;
import com.exavalu.agentportal.config.Encrypt;

@Entity(name = "QuoteEntity")
@Table(name = "quote")

@SqlResultSetMapping(name = "updateResult", columns = { @ColumnResult(name = "count") })
@NamedNativeQueries({
		@NamedNativeQuery(name = "updateSubmissionStatus", query = "UPDATE quote SET submission_status = ? , quote_number = ?, submission_date = ? WHERE indication_id = ?", resultSetMapping = "updateResult"),
		@NamedNativeQuery(name = "getDashboardDataCount", query = "select effective_date,count(case indication_status when 'Yes' then 1 else null end) as indStatus, count(case submission_status when 'Yes' then 1 else null end) as subStatus, count(case submission_status when 'No' then 1 else null end) as pendingStatus from quote where effective_date between ? and ? group by effective_date", resultSetMapping = "updateResult") })

public class Quote implements Serializable {

	private static final long serialVersionUID = 1L;

	public Quote() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "indication_id")
	private String indicationId;
	@Column(name = "user_name")
	private String userName;
	@Column(name = "effective_date")
	private String effectiveDate;
	@Column(name = "class_code")
	private String classCode;
	@Column(name = "state_cd")
	private String stateCd;
	@Column(name = "liability_limit")
	private String liabilityLimit;
	@Column(name = "indication_status")
	private String indicationStatus;
	@Column(name = "submission_status")
	private String submissionStatus;
	@Column(name = "quote_number")
	private String quoteNumber;
	@Column(name = "accounting_month")
	private String accountingMonth;
	@Column(name = "premium")
	private String premium;
	@Column(name = "bus_address")
	private String busAddress;
	@Column(name = "bus_city")
	private String busCity;
	@Column(name = "bus_state")
	private String busState;
	@Column(name = "bus_postal_code")
	private String busPostalCode;
	@Column(name = "indication_date")
	private String indicationDate;
	@Column(name = "last_update_date")
	private String lastUpdateDate;
	@Column(name = "indication_data")
	@Convert(converter = Encrypt.class)
	private String indicationData;
	@Column(name = "product")
	private String product;
	@Column(name = "insured_name")
	private String insuredName;
	@Column(name = "expiration_date")
	private String expirationDate;
	@Column(name = "status")
	private int status;

	public String getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(String submissionDate) {
		this.submissionDate = submissionDate;
	}

	private String submissionDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIndicationId() {
		return indicationId;
	}

	public void setIndicationId(String indicationId) {
		this.indicationId = indicationId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getStateCd() {
		return stateCd;
	}

	public void setStateCd(String stateCd) {
		this.stateCd = stateCd;
	}

	public String getLiabilityLimit() {
		return liabilityLimit;
	}

	public void setLiabilityLimit(String liabilityLimit) {
		this.liabilityLimit = liabilityLimit;
	}

	public String getIndicationStatus() {
		return indicationStatus;
	}

	public void setIndicationStatus(String indicationStatus) {
		this.indicationStatus = indicationStatus;
	}

	public String getSubmissionStatus() {
		return submissionStatus;
	}

	public void setSubmissionStatus(String submissionStatus) {
		this.submissionStatus = submissionStatus;
	}

	public String getQuoteNumber() {
		return quoteNumber;
	}

	public void setQuoteNumber(String quoteNumber) {
		this.quoteNumber = quoteNumber;
	}

	public String getAccountingMonth() {
		return accountingMonth;
	}

	public void setAccountingMonth(String accountingMonth) {
		this.accountingMonth = accountingMonth;
	}

	public String getPremium() {
		return premium;
	}

	public void setPremium(String premium) {
		this.premium = premium;
	}

	public String getBusAddress() {
		return busAddress;
	}

	public void setBusAddress(String busAddress) {
		this.busAddress = busAddress;
	}

	public String getBusCity() {
		return busCity;
	}

	public void setBusCity(String busCity) {
		this.busCity = busCity;
	}

	public String getBusState() {
		return busState;
	}

	public void setBusState(String busState) {
		this.busState = busState;
	}

	public String getBusPostalCode() {
		return busPostalCode;
	}

	public void setBusPostalCode(String busPostalCode) {
		this.busPostalCode = busPostalCode;
	}

	public String getIndicationDate() {
		return indicationDate;
	}

	public void setIndicationDate(String indicationDate) {
		this.indicationDate = indicationDate;
	}

	public String getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getIndicationData() {
		return indicationData;
	}

	public void setIndicationData(String indicationData) {
		this.indicationData = indicationData;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getInsuredName() {
		return insuredName;
	}

	public void setInsuredName(String insuredName) {
		this.insuredName = insuredName;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Quote [id=" + id + ", indicationId=" + indicationId + ", userName=" + userName + ", effectiveDate="
				+ effectiveDate + ", classCode=" + classCode + ", stateCd=" + stateCd + ", liabilityLimit="
				+ liabilityLimit + ", indicationStatus=" + indicationStatus + ", submissionStatus=" + submissionStatus
				+ ", quoteNumber=" + quoteNumber + ", accountingMonth=" + accountingMonth + ", premium=" + premium
				+ ", busAddress=" + busAddress + ", busCity=" + busCity + ", busState=" + busState + ", busPostalCode="
				+ busPostalCode + ", indicationDate=" + indicationDate + ", lastUpdateDate=" + lastUpdateDate
				+ ",expirationDate=" + expirationDate + ",insuredName=" + insuredName + ", product=" + product
				+ ", indicationData=" + indicationData + ",submissionDate=" + submissionDate + ", status="
				+ status + "]";
	}

	public Quote(String indicationId, String userName, String effectiveDate, String classCode, String stateCd,
			String liabilityLimit, String indicationStatus, String submissionStatus, String quoteNumber,
			String accountingMonth, String premium, String busAddress, String busCity, String busState,
			String busPostalCode, String product, String indicationDate, String lastUpdateDate,
			String indicationData, String insuredName, String expirationDate, String submissionDate, int status) {
		super();
		this.indicationId = indicationId;
		this.userName = userName;
		this.effectiveDate = effectiveDate;
		this.classCode = classCode;
		this.stateCd = stateCd;
		this.liabilityLimit = liabilityLimit;
		this.indicationStatus = indicationStatus;
		this.submissionStatus = submissionStatus;
		this.quoteNumber = quoteNumber;
		this.accountingMonth = accountingMonth;
		this.premium = premium;
		this.busAddress = busAddress;
		this.busCity = busCity;
		this.busState = busState;
		this.busPostalCode = busPostalCode;
		this.indicationDate = indicationDate;
		this.lastUpdateDate = lastUpdateDate;
		this.indicationData = indicationData;
		this.product = product;
		this.insuredName = insuredName;
		this.expirationDate = expirationDate;
		this.submissionDate = submissionDate;
		this.status = status;
	}

}