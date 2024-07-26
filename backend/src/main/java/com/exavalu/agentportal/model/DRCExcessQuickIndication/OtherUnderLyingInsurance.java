
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
    "insuranceType",
    "classCd",
    "underlyingPremium",
    "limits"
})
@Generated("jsonschema2pojo")
public class OtherUnderLyingInsurance {

    @JsonProperty("insuranceType")
    private String insuranceType;
    @JsonProperty("classCd")
    private String classCd;
    @JsonProperty("underlyingPremium")
    private String underlyingPremium;
    @JsonProperty("limits")
    private Limits__1 limits;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("insuranceType")
    public String getInsuranceType() {
        return insuranceType;
    }

    @JsonProperty("insuranceType")
    public void setInsuranceType(String insuranceType) {
        this.insuranceType = insuranceType;
    }

    public OtherUnderLyingInsurance withInsuranceType(String insuranceType) {
        this.insuranceType = insuranceType;
        return this;
    }

    @JsonProperty("classCd")
    public String getClassCd() {
        return classCd;
    }

    @JsonProperty("classCd")
    public void setClassCd(String classCd) {
        this.classCd = classCd;
    }

    public OtherUnderLyingInsurance withClassCd(String classCd) {
        this.classCd = classCd;
        return this;
    }

    @JsonProperty("underlyingPremium")
    public String getUnderlyingPremium() {
        return underlyingPremium;
    }

    @JsonProperty("underlyingPremium")
    public void setUnderlyingPremium(String underlyingPremium) {
        this.underlyingPremium = underlyingPremium;
    }

    public OtherUnderLyingInsurance withUnderlyingPremium(String underlyingPremium) {
        this.underlyingPremium = underlyingPremium;
        return this;
    }

    @JsonProperty("limits")
    public Limits__1 getLimits() {
        return limits;
    }

    @JsonProperty("limits")
    public void setLimits(Limits__1 limits) {
        this.limits = limits;
    }

    public OtherUnderLyingInsurance withLimits(Limits__1 limits) {
        this.limits = limits;
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

    public OtherUnderLyingInsurance withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(OtherUnderLyingInsurance.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("insuranceType");
        sb.append('=');
        sb.append(((this.insuranceType == null)?"<null>":this.insuranceType));
        sb.append(',');
        sb.append("classCd");
        sb.append('=');
        sb.append(((this.classCd == null)?"<null>":this.classCd));
        sb.append(',');
        sb.append("underlyingPremium");
        sb.append('=');
        sb.append(((this.underlyingPremium == null)?"<null>":this.underlyingPremium));
        sb.append(',');
        sb.append("limits");
        sb.append('=');
        sb.append(((this.limits == null)?"<null>":this.limits));
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
        result = ((result* 31)+((this.classCd == null)? 0 :this.classCd.hashCode()));
        result = ((result* 31)+((this.insuranceType == null)? 0 :this.insuranceType.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.underlyingPremium == null)? 0 :this.underlyingPremium.hashCode()));
        result = ((result* 31)+((this.limits == null)? 0 :this.limits.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof OtherUnderLyingInsurance) == false) {
            return false;
        }
        OtherUnderLyingInsurance rhs = ((OtherUnderLyingInsurance) other);
        return ((((((this.classCd == rhs.classCd)||((this.classCd!= null)&&this.classCd.equals(rhs.classCd)))&&((this.insuranceType == rhs.insuranceType)||((this.insuranceType!= null)&&this.insuranceType.equals(rhs.insuranceType))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.underlyingPremium == rhs.underlyingPremium)||((this.underlyingPremium!= null)&&this.underlyingPremium.equals(rhs.underlyingPremium))))&&((this.limits == rhs.limits)||((this.limits!= null)&&this.limits.equals(rhs.limits))));
    }

}
