
package com.exavalu.agentportal.model.DRCExcessQuickIndication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "externalId", "policyPremiumInfo", "policyInfo", "insuredInfo", "uwQuestions", "locations",
		"insuranceLine", "drivers", "additionalInterests", "commercialPolicyInfo", "commlUnderLyingInsurance",
		"underlyingPolicies"})
@Generated("jsonschema2pojo")
public class Application {
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private int id;
	@JsonProperty("externalId")
	private String externalId;
	@JsonProperty("policyPremiumInfo")
	private PolicyPremiumInfo policyPremiumInfo;
	@JsonProperty("policyInfo")
	private PolicyInfo policyInfo;
	@JsonProperty("insuredInfo")
	private InsuredInfo insuredInfo;
	@JsonProperty("uwQuestions")
	private List<UwQuestion> uwQuestions = new ArrayList<UwQuestion>();
	@JsonProperty("locations")
	private List<Location> locations = new ArrayList<Location>();
	@JsonProperty("insuranceLine")
	private List<InsuranceLine> insuranceLine = new ArrayList<InsuranceLine>();
	@JsonProperty("drivers")
	private List<Driver> drivers = new ArrayList<Driver>();
	@JsonProperty("additionalInterests")
	private List<AdditionalInterest> additionalInterests = new ArrayList<AdditionalInterest>();
	@JsonProperty("commercialPolicyInfo")
	private CommercialPolicyInfo commercialPolicyInfo;
	@JsonProperty("underlyingPolicies")
	private List<UnderlyingInsurance> underlyingPolicies = new ArrayList<UnderlyingInsurance>();
	@JsonProperty("commlUnderLyingInsurance")
	private CommlUnderLyingInsurance commlUnderLyingInsurance;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("externalId")
	public String getExternalId() {
		return externalId;
	}

	@JsonProperty("externalId")
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public Application withExternalId(String externalId) {
		this.externalId = externalId;
		return this;
	}

	@JsonProperty("policyPremiumInfo")
	public PolicyPremiumInfo getPolicyPremiumInfo() {
		return policyPremiumInfo;
	}

	@JsonProperty("policyPremiumInfo")
	public void setPolicyPremiumInfo(PolicyPremiumInfo policyPremiumInfo) {
		this.policyPremiumInfo = policyPremiumInfo;
	}

	public Application withPolicyPremiumInfo(PolicyPremiumInfo policyPremiumInfo) {
		this.policyPremiumInfo = policyPremiumInfo;
		return this;
	}

	@JsonProperty("policyInfo")
	public PolicyInfo getPolicyInfo() {
		return policyInfo;
	}

	@JsonProperty("policyInfo")
	public void setPolicyInfo(PolicyInfo policyInfo) {
		this.policyInfo = policyInfo;
	}

	public Application withPolicyInfo(PolicyInfo policyInfo) {
		this.policyInfo = policyInfo;
		return this;
	}

	@JsonProperty("insuredInfo")
	public InsuredInfo getInsuredInfo() {
		return insuredInfo;
	}

	@JsonProperty("insuredInfo")
	public void setInsuredInfo(InsuredInfo insuredInfo) {
		this.insuredInfo = insuredInfo;
	}

	public Application withInsuredInfo(InsuredInfo insuredInfo) {
		this.insuredInfo = insuredInfo;
		return this;
	}

	@JsonProperty("uwQuestions")
	public List<UwQuestion> getUwQuestions() {
		return uwQuestions;
	}

	@JsonProperty("uwQuestions")
	public void setUwQuestions(List<UwQuestion> uwQuestions) {
		this.uwQuestions = uwQuestions;
	}

	public Application withUwQuestions(List<UwQuestion> uwQuestions) {
		this.uwQuestions = uwQuestions;
		return this;
	}

	@JsonProperty("locations")
	public List<Location> getLocations() {
		return locations;
	}

	@JsonProperty("locations")
	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

	public Application withLocations(List<Location> locations) {
		this.locations = locations;
		return this;
	}

	@JsonProperty("insuranceLine")
	public List<InsuranceLine> getInsuranceLine() {
		return insuranceLine;
	}

	@JsonProperty("insuranceLine")
	public void setInsuranceLine(List<InsuranceLine> insuranceLine) {
		this.insuranceLine = insuranceLine;
	}

	public Application withInsuranceLine(List<InsuranceLine> insuranceLine) {
		this.insuranceLine = insuranceLine;
		return this;
	}

	@JsonProperty("drivers")
	public List<Driver> getDrivers() {
		return drivers;
	}

	@JsonProperty("drivers")
	public void setDrivers(List<Driver> drivers) {
		this.drivers = drivers;
	}

	public Application withDrivers(List<Driver> drivers) {
		this.drivers = drivers;
		return this;
	}

	@JsonProperty("additionalInterests")
	public List<AdditionalInterest> getAdditionalInterests() {
		return additionalInterests;
	}

	@JsonProperty("additionalInterests")
	public void setAdditionalInterests(List<AdditionalInterest> additionalInterests) {
		this.additionalInterests = additionalInterests;
	}

	public Application withAdditionalInterests(List<AdditionalInterest> additionalInterests) {
		this.additionalInterests = additionalInterests;
		return this;
	}

	@JsonProperty("commercialPolicyInfo")
	public CommercialPolicyInfo getCommercialPolicyInfo() {
		return commercialPolicyInfo;
	}

	@JsonProperty("commercialPolicyInfo")
	public void setCommercialPolicyInfo(CommercialPolicyInfo commercialPolicyInfo) {
		this.commercialPolicyInfo = commercialPolicyInfo;
	}

	public Application withCommercialPolicyInfo(CommercialPolicyInfo commercialPolicyInfo) {
		this.commercialPolicyInfo = commercialPolicyInfo;
		return this;
	}

	@JsonProperty("commlUnderLyingInsurance")
	public CommlUnderLyingInsurance getCommlUnderLyingInsurance() {
		return commlUnderLyingInsurance;
	}

	@JsonProperty("commlUnderLyingInsurance")
	public void setCommlUnderLyingInsurance(CommlUnderLyingInsurance commlUnderLyingInsurance) {
		this.commlUnderLyingInsurance = commlUnderLyingInsurance;
	}

	public Application withCommlUnderLyingInsurance(CommlUnderLyingInsurance commlUnderLyingInsurance) {
		this.commlUnderLyingInsurance = commlUnderLyingInsurance;
		return this;
	}
	
	@JsonProperty("underlyingPolicies")
	public List<UnderlyingInsurance> getUnderlyingPolicies() {
		return underlyingPolicies;
	}

	@JsonProperty("underlyingPolicies")
	public void setUnderlyingPolicies(List<UnderlyingInsurance> underlyingPolicies) {
		this.underlyingPolicies = underlyingPolicies;
	}

	public Application withUnderlyingPolicies(List<UnderlyingInsurance> underlyingPolicies) {
		this.underlyingPolicies = underlyingPolicies;
		return this;
	}
	
	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public Application withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(Application.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this)))
				.append('[');
		sb.append("externalId");
		sb.append('=');
		sb.append(((this.externalId == null) ? "<null>" : this.externalId));
		sb.append(',');
		sb.append("policyPremiumInfo");
		sb.append('=');
		sb.append(((this.policyPremiumInfo == null) ? "<null>" : this.policyPremiumInfo));
		sb.append(',');
		sb.append("policyInfo");
		sb.append('=');
		sb.append(((this.policyInfo == null) ? "<null>" : this.policyInfo));
		sb.append(',');
		sb.append("insuredInfo");
		sb.append('=');
		sb.append(((this.insuredInfo == null) ? "<null>" : this.insuredInfo));
		sb.append(',');
		sb.append("uwQuestions");
		sb.append('=');
		sb.append(((this.uwQuestions == null) ? "<null>" : this.uwQuestions));
		sb.append(',');
		sb.append("locations");
		sb.append('=');
		sb.append(((this.locations == null) ? "<null>" : this.locations));
		sb.append(',');
		sb.append("insuranceLine");
		sb.append('=');
		sb.append(((this.insuranceLine == null) ? "<null>" : this.insuranceLine));
		sb.append(',');
		sb.append("drivers");
		sb.append('=');
		sb.append(((this.drivers == null) ? "<null>" : this.drivers));
		sb.append(',');
		sb.append("additionalInterests");
		sb.append('=');
		sb.append(((this.additionalInterests == null) ? "<null>" : this.additionalInterests));
		sb.append(',');
		sb.append("commercialPolicyInfo");
		sb.append('=');
		sb.append(((this.commercialPolicyInfo == null) ? "<null>" : this.commercialPolicyInfo));
		sb.append(',');
		sb.append("underlyingPolicies");
		sb.append('=');
		sb.append(((this.underlyingPolicies == null) ? "<null>" : this.underlyingPolicies));
		sb.append(',');
		sb.append("commlUnderLyingInsurance");
		sb.append('=');
		sb.append(((this.commlUnderLyingInsurance == null) ? "<null>" : this.commlUnderLyingInsurance));
		sb.append(',');
		sb.append("additionalProperties");
		sb.append('=');
		sb.append(((this.additionalProperties == null) ? "<null>" : this.additionalProperties));
		sb.append(',');
		if (sb.charAt((sb.length() - 1)) == ',') {
			sb.setCharAt((sb.length() - 1), ']');
		} else {
			sb.append(']');
		}
		return sb.toString();
	}

	@Override
	public int hashCode() {
		int result = 1;
		result = ((result * 31) + ((this.externalId == null) ? 0 : this.externalId.hashCode()));
		result = ((result * 31) + ((this.uwQuestions == null) ? 0 : this.uwQuestions.hashCode()));
		result = ((result * 31) + ((this.policyPremiumInfo == null) ? 0 : this.policyPremiumInfo.hashCode()));
		result = ((result * 31) + ((this.additionalInterests == null) ? 0 : this.additionalInterests.hashCode()));
		result = ((result * 31) + ((this.commlUnderLyingInsurance == null) ? 0 : this.commlUnderLyingInsurance.hashCode()));
		result = ((result * 31) + ((this.underlyingPolicies == null) ? 0 : this.underlyingPolicies.hashCode()));
		result = ((result * 31) + ((this.insuranceLine == null) ? 0 : this.insuranceLine.hashCode()));
		result = ((result * 31) + ((this.commercialPolicyInfo == null) ? 0 : this.commercialPolicyInfo.hashCode()));
		result = ((result * 31) + ((this.policyInfo == null) ? 0 : this.policyInfo.hashCode()));
		result = ((result * 31) + ((this.locations == null) ? 0 : this.locations.hashCode()));
		result = ((result * 31) + ((this.additionalProperties == null) ? 0 : this.additionalProperties.hashCode()));
		result = ((result * 31) + ((this.drivers == null) ? 0 : this.drivers.hashCode()));
		result = ((result * 31) + ((this.insuredInfo == null) ? 0 : this.insuredInfo.hashCode()));
		return result;
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof Application) == false) {
			return false;
		}
		Application rhs = ((Application) other);
		return (((((((((((((this.externalId == rhs.externalId)
				|| ((this.externalId != null) && this.externalId.equals(rhs.externalId)))
				&& ((this.uwQuestions == rhs.uwQuestions)
						|| ((this.uwQuestions != null) && this.uwQuestions.equals(rhs.uwQuestions))))
				&& ((this.policyPremiumInfo == rhs.policyPremiumInfo)
						|| ((this.policyPremiumInfo != null) && this.policyPremiumInfo.equals(rhs.policyPremiumInfo))))
				&& ((this.additionalInterests == rhs.additionalInterests) || ((this.additionalInterests != null)
						&& this.additionalInterests.equals(rhs.additionalInterests))))
				&& ((this.commlUnderLyingInsurance == rhs.commlUnderLyingInsurance)
						|| ((this.commlUnderLyingInsurance != null)
								&& this.commlUnderLyingInsurance.equals(rhs.commlUnderLyingInsurance))))
				&& ((this.underlyingPolicies == rhs.underlyingPolicies)
						|| ((this.underlyingPolicies != null)
								&& this.underlyingPolicies.equals(rhs.underlyingPolicies)))
				&& ((this.insuranceLine == rhs.insuranceLine)
						|| ((this.insuranceLine != null) && this.insuranceLine.equals(rhs.insuranceLine))))
				&& ((this.commercialPolicyInfo == rhs.commercialPolicyInfo) || ((this.commercialPolicyInfo != null)
						&& this.commercialPolicyInfo.equals(rhs.commercialPolicyInfo))))
				&& ((this.policyInfo == rhs.policyInfo)
						|| ((this.policyInfo != null) && this.policyInfo.equals(rhs.policyInfo))))
				&& ((this.locations == rhs.locations)
						|| ((this.locations != null) && this.locations.equals(rhs.locations))))
				&& ((this.additionalProperties == rhs.additionalProperties) || ((this.additionalProperties != null)
						&& this.additionalProperties.equals(rhs.additionalProperties))))
				&& ((this.drivers == rhs.drivers) || ((this.drivers != null) && this.drivers.equals(rhs.drivers))))
				&& ((this.insuredInfo == rhs.insuredInfo)
						|| ((this.insuredInfo != null) && this.insuredInfo.equals(rhs.insuredInfo))));
	}

}
