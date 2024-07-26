
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
    "descOfOperations",
    "businessEstDt",
    "yearsExperience",
    "fullTimeEmployees",
    "primaryGLClassCd",
    "primaryOccupation",
    "secondaryOccupation"
})
@Generated("jsonschema2pojo")
public class CommercialPolicyInfo {

    @JsonProperty("descOfOperations")
    private String descOfOperations;
    @JsonProperty("businessEstDt")
    private String businessEstDt;
    @JsonProperty("yearsExperience")
    private Integer yearsExperience;
    @JsonProperty("fullTimeEmployees")
    private Integer fullTimeEmployees;
    @JsonProperty("primaryGLClassCd")
    private String primaryGLClassCd;
    @JsonProperty("primaryOccupation")
    private String primaryOccupation;
    @JsonProperty("secondaryOccupation")
    private String secondaryOccupation;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("descOfOperations")
    public String getDescOfOperations() {
        return descOfOperations;
    }

    @JsonProperty("descOfOperations")
    public void setDescOfOperations(String descOfOperations) {
        this.descOfOperations = descOfOperations;
    }

    public CommercialPolicyInfo withDescOfOperations(String descOfOperations) {
        this.descOfOperations = descOfOperations;
        return this;
    }

    @JsonProperty("businessEstDt")
    public String getBusinessEstDt() {
        return businessEstDt;
    }

    @JsonProperty("businessEstDt")
    public void setBusinessEstDt(String businessEstDt) {
        this.businessEstDt = businessEstDt;
    }

    public CommercialPolicyInfo withBusinessEstDt(String businessEstDt) {
        this.businessEstDt = businessEstDt;
        return this;
    }

    @JsonProperty("yearsExperience")
    public Integer getYearsExperience() {
        return yearsExperience;
    }

    @JsonProperty("yearsExperience")
    public void setYearsExperience(Integer yearsExperience) {
        this.yearsExperience = yearsExperience;
    }

    public CommercialPolicyInfo withYearsExperience(Integer yearsExperience) {
        this.yearsExperience = yearsExperience;
        return this;
    }

    @JsonProperty("fullTimeEmployees")
    public Integer getFullTimeEmployees() {
        return fullTimeEmployees;
    }

    @JsonProperty("fullTimeEmployees")
    public void setFullTimeEmployees(Integer fullTimeEmployees) {
        this.fullTimeEmployees = fullTimeEmployees;
    }

    public CommercialPolicyInfo withFullTimeEmployees(Integer fullTimeEmployees) {
        this.fullTimeEmployees = fullTimeEmployees;
        return this;
    }

    @JsonProperty("primaryGLClassCd")
    public String getPrimaryGLClassCd() {
        return primaryGLClassCd;
    }

    @JsonProperty("primaryGLClassCd")
    public void setPrimaryGLClassCd(String primaryGLClassCd) {
        this.primaryGLClassCd = primaryGLClassCd;
    }

    public CommercialPolicyInfo withPrimaryGLClassCd(String primaryGLClassCd) {
        this.primaryGLClassCd = primaryGLClassCd;
        return this;
    }

    @JsonProperty("primaryOccupation")
    public String getPrimaryOccupation() {
        return primaryOccupation;
    }

    @JsonProperty("primaryOccupation")
    public void setPrimaryOccupation(String primaryOccupation) {
        this.primaryOccupation = primaryOccupation;
    }

    public CommercialPolicyInfo withPrimaryOccupation(String primaryOccupation) {
        this.primaryOccupation = primaryOccupation;
        return this;
    }

    @JsonProperty("secondaryOccupation")
    public String getSecondaryOccupation() {
        return secondaryOccupation;
    }

    @JsonProperty("secondaryOccupation")
    public void setSecondaryOccupation(String secondaryOccupation) {
        this.secondaryOccupation = secondaryOccupation;
    }

    public CommercialPolicyInfo withSecondaryOccupation(String secondaryOccupation) {
        this.secondaryOccupation = secondaryOccupation;
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

    public CommercialPolicyInfo withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(CommercialPolicyInfo.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("descOfOperations");
        sb.append('=');
        sb.append(((this.descOfOperations == null)?"<null>":this.descOfOperations));
        sb.append(',');
        sb.append("businessEstDt");
        sb.append('=');
        sb.append(((this.businessEstDt == null)?"<null>":this.businessEstDt));
        sb.append(',');
        sb.append("yearsExperience");
        sb.append('=');
        sb.append(((this.yearsExperience == null)?"<null>":this.yearsExperience));
        sb.append(',');
        sb.append("fullTimeEmployees");
        sb.append('=');
        sb.append(((this.fullTimeEmployees == null)?"<null>":this.fullTimeEmployees));
        sb.append(',');
        sb.append("primaryGLClassCd");
        sb.append('=');
        sb.append(((this.primaryGLClassCd == null)?"<null>":this.primaryGLClassCd));
        sb.append(',');
        sb.append("primaryOccupation");
        sb.append('=');
        sb.append(((this.primaryOccupation == null)?"<null>":this.primaryOccupation));
        sb.append(',');
        sb.append("secondaryOccupation");
        sb.append('=');
        sb.append(((this.secondaryOccupation == null)?"<null>":this.secondaryOccupation));
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
        result = ((result* 31)+((this.primaryGLClassCd == null)? 0 :this.primaryGLClassCd.hashCode()));
        result = ((result* 31)+((this.fullTimeEmployees == null)? 0 :this.fullTimeEmployees.hashCode()));
        result = ((result* 31)+((this.businessEstDt == null)? 0 :this.businessEstDt.hashCode()));
        result = ((result* 31)+((this.secondaryOccupation == null)? 0 :this.secondaryOccupation.hashCode()));
        result = ((result* 31)+((this.primaryOccupation == null)? 0 :this.primaryOccupation.hashCode()));
        result = ((result* 31)+((this.yearsExperience == null)? 0 :this.yearsExperience.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.descOfOperations == null)? 0 :this.descOfOperations.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CommercialPolicyInfo) == false) {
            return false;
        }
        CommercialPolicyInfo rhs = ((CommercialPolicyInfo) other);
        return (((((((((this.primaryGLClassCd == rhs.primaryGLClassCd)||((this.primaryGLClassCd!= null)&&this.primaryGLClassCd.equals(rhs.primaryGLClassCd)))&&((this.fullTimeEmployees == rhs.fullTimeEmployees)||((this.fullTimeEmployees!= null)&&this.fullTimeEmployees.equals(rhs.fullTimeEmployees))))&&((this.businessEstDt == rhs.businessEstDt)||((this.businessEstDt!= null)&&this.businessEstDt.equals(rhs.businessEstDt))))&&((this.secondaryOccupation == rhs.secondaryOccupation)||((this.secondaryOccupation!= null)&&this.secondaryOccupation.equals(rhs.secondaryOccupation))))&&((this.primaryOccupation == rhs.primaryOccupation)||((this.primaryOccupation!= null)&&this.primaryOccupation.equals(rhs.primaryOccupation))))&&((this.yearsExperience == rhs.yearsExperience)||((this.yearsExperience!= null)&&this.yearsExperience.equals(rhs.yearsExperience))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.descOfOperations == rhs.descOfOperations)||((this.descOfOperations!= null)&&this.descOfOperations.equals(rhs.descOfOperations))));
    }

}
