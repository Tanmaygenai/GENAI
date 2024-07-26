
package com.exavalu.agentportal.model.DRCExcessQuickIndication;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "driverNumber",
    "givenName",
    "surname",
    "birthDt",
    "age",
    "licenseState",
    "licenseNumber",
    "licenseType",
    "yearsOfUSExperience",
    "excludedFlag",
    "totalPoints"
})
@Generated("jsonschema2pojo")
public class Driver {

    @JsonProperty("driverNumber")
    private Integer driverNumber;
    @JsonProperty("givenName")
    private String givenName;
    @JsonProperty("surname")
    private String surname;
    @JsonProperty("birthDt")
    private String birthDt;
    @JsonProperty("age")
    private Integer age;
    @JsonProperty("licenseState")
    private String licenseState;
    @JsonProperty("licenseType")
    private String licenseType;
    @JsonProperty("licenseNumber")
    private String licenseNumber;
	@JsonProperty("yearsOfUSExperience")
    private String yearsOfUSExperience;
    @JsonProperty("excludedFlag")
    private Boolean excludedFlag;
    @JsonProperty("totalPoints")
    private Integer totalPoints;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("driverNumber")
    public Integer getDriverNumber() {
        return driverNumber;
    }

    @JsonProperty("driverNumber")
    public void setDriverNumber(Integer driverNumber) {
        this.driverNumber = driverNumber;
    }

    public Driver withDriverNumber(Integer driverNumber) {
        this.driverNumber = driverNumber;
        return this;
    }

    @JsonProperty("givenName")
    public String getGivenName() {
        return givenName;
    }

    @JsonProperty("givenName")
    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public Driver withGivenName(String givenName) {
        this.givenName = givenName;
        return this;
    }

    @JsonProperty("surname")
    public String getSurname() {
        return surname;
    }

    @JsonProperty("surname")
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Driver withSurname(String surname) {
        this.surname = surname;
        return this;
    }

    @JsonProperty("birthDt")
    public String getBirthDt() {
        return birthDt;
    }

    @JsonProperty("birthDt")
    public void setBirthDt(String birthDt) {
        this.birthDt = birthDt;
    }

    public Driver withBirthDt(String birthDt) {
        this.birthDt = birthDt;
        return this;
    }

    @JsonProperty("age")
    public Integer getAge() {
        return age;
    }

    @JsonProperty("age")
    public void setAge(Integer age) {
        this.age = age;
    }

    public Driver withAge(Integer age) {
        this.age = age;
        return this;
    }

    @JsonProperty("licenseState")
    public String getLicenseState() {
        return licenseState;
    }

    @JsonProperty("licenseState")
    public void setLicenseState(String licenseState) {
        this.licenseState = licenseState;
    }

    public Driver withLicenseState(String licenseState) {
        this.licenseState = licenseState;
        return this;
    }

    @JsonProperty("licenseType")
    public String getLicenseType() {
        return licenseType;
    }

    @JsonProperty("licenseType")
    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType;
    }

    public Driver withLicenseType(String licenseType) {
        this.licenseType = licenseType;
        return this;
    }

    @JsonProperty("licenseNumber")
    public String getLicenseNumber() {
		return licenseNumber;
	}
    
    @JsonProperty("licenseNumber")
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
	
    public Driver withLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
        return this;
    }
    
    @JsonProperty("yearsOfUSExperience")
    public String getYearsOfUSExperience() {
        return yearsOfUSExperience;
    }

    @JsonProperty("yearsOfUSExperience")
    public void setYearsOfUSExperience(String yearsOfUSExperience) {
        this.yearsOfUSExperience = yearsOfUSExperience;
    }

    public Driver withYearsOfUSExperience(String yearsOfUSExperience) {
        this.yearsOfUSExperience = yearsOfUSExperience;
        return this;
    }

    @JsonProperty("excludedFlag")
    public Boolean getExcludedFlag() {
        return excludedFlag;
    }

    @JsonProperty("excludedFlag")
    public void setExcludedFlag(Boolean excludedFlag) {
        this.excludedFlag = excludedFlag;
    }

    public Driver withExcludedFlag(Boolean excludedFlag) {
        this.excludedFlag = excludedFlag;
        return this;
    }

    @JsonProperty("totalPoints")
    public Integer getTotalPoints() {
        return totalPoints;
    }

    @JsonProperty("totalPoints")
    public void setTotalPoints(Integer totalPoints) {
        this.totalPoints = totalPoints;
    }

    public Driver withTotalPoints(Integer totalPoints) {
        this.totalPoints = totalPoints;
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

    public Driver withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Driver.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("driverNumber");
        sb.append('=');
        sb.append(((this.driverNumber == null)?"<null>":this.driverNumber));
        sb.append(',');
        sb.append("givenName");
        sb.append('=');
        sb.append(((this.givenName == null)?"<null>":this.givenName));
        sb.append(',');
        sb.append("surname");
        sb.append('=');
        sb.append(((this.surname == null)?"<null>":this.surname));
        sb.append(',');
        sb.append("birthDt");
        sb.append('=');
        sb.append(((this.birthDt == null)?"<null>":this.birthDt));
        sb.append(',');
        sb.append("age");
        sb.append('=');
        sb.append(((this.age == null)?"<null>":this.age));
        sb.append(',');
        sb.append("licenseState");
        sb.append('=');
        sb.append(((this.licenseState == null)?"<null>":this.licenseState));
        sb.append(',');
        sb.append("licenseType");
        sb.append('=');
        sb.append(((this.licenseType == null)?"<null>":this.licenseType));
        sb.append(',');
        sb.append("licenseNumber");
        sb.append('=');
        sb.append(((this.licenseNumber == null)?"<null>":this.licenseNumber));
        sb.append(',');
        sb.append("yearsOfUSExperience");
        sb.append('=');
        sb.append(((this.yearsOfUSExperience == null)?"<null>":this.yearsOfUSExperience));
        sb.append(',');
        sb.append("excludedFlag");
        sb.append('=');
        sb.append(((this.excludedFlag == null)?"<null>":this.excludedFlag));
        sb.append(',');
        sb.append("totalPoints");
        sb.append('=');
        sb.append(((this.totalPoints == null)?"<null>":this.totalPoints));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.driverNumber == null)? 0 :this.driverNumber.hashCode()));
        result = ((result* 31)+((this.licenseType == null)? 0 :this.licenseType.hashCode()));
        result = ((result* 31)+((this.excludedFlag == null)? 0 :this.excludedFlag.hashCode()));
        result = ((result* 31)+((this.birthDt == null)? 0 :this.birthDt.hashCode()));
        result = ((result* 31)+((this.licenseState == null)? 0 :this.licenseState.hashCode()));
        result = ((result* 31)+((this.licenseNumber == null)? 0 :this.licenseNumber.hashCode()));
        result = ((result* 31)+((this.surname == null)? 0 :this.surname.hashCode()));
        result = ((result* 31)+((this.givenName == null)? 0 :this.givenName.hashCode()));
        result = ((result* 31)+((this.totalPoints == null)? 0 :this.totalPoints.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.yearsOfUSExperience == null)? 0 :this.yearsOfUSExperience.hashCode()));
        result = ((result* 31)+((this.age == null)? 0 :this.age.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Driver) == false) {
            return false;
        }
        Driver rhs = ((Driver) other);
        return ((((((((((((this.driverNumber == rhs.driverNumber)||((this.driverNumber!= null)&&this.driverNumber.equals(rhs.driverNumber)))&&((this.licenseType == rhs.licenseType)||((this.licenseType!= null)&&this.licenseType.equals(rhs.licenseType))))&&((this.excludedFlag == rhs.excludedFlag)||((this.excludedFlag!= null)&&this.excludedFlag.equals(rhs.excludedFlag))))&&((this.birthDt == rhs.birthDt)||((this.birthDt!= null)&&this.birthDt.equals(rhs.birthDt))))&&((this.licenseState == rhs.licenseState)||((this.licenseState!= null)&&this.licenseState.equals(rhs.licenseState))))&&((this.licenseNumber == rhs.licenseNumber)||((this.licenseNumber!= null)&&this.licenseNumber.equals(rhs.licenseNumber)))&&((this.licenseType == rhs.licenseType)||((this.licenseType!= null)&&this.licenseType.equals(rhs.licenseType))))&&((this.excludedFlag == rhs.excludedFlag)||((this.excludedFlag!= null)&&this.excludedFlag.equals(rhs.excludedFlag))))&&((this.birthDt == rhs.birthDt)||((this.birthDt!= null)&&this.birthDt.equals(rhs.birthDt)))&&((this.surname == rhs.surname)||((this.surname!= null)&&this.surname.equals(rhs.surname))))&&((this.givenName == rhs.givenName)||((this.givenName!= null)&&this.givenName.equals(rhs.givenName))))&&((this.totalPoints == rhs.totalPoints)||((this.totalPoints!= null)&&this.totalPoints.equals(rhs.totalPoints))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.yearsOfUSExperience == rhs.yearsOfUSExperience)||((this.yearsOfUSExperience!= null)&&this.yearsOfUSExperience.equals(rhs.yearsOfUSExperience)))&&((this.age == rhs.age)||((this.age!= null)&&this.age.equals(rhs.age)));
    }

}
