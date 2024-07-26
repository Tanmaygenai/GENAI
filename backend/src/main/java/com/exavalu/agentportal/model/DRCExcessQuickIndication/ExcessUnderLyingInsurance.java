
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
    "underlyingPremium",
    "liabilityLimit"
})
@Generated("jsonschema2pojo")
public class ExcessUnderLyingInsurance {

    @JsonProperty("insuranceType")
    private String insuranceType;
    @JsonProperty("underlyingPremium")
    private String underlyingPremium;
    @JsonProperty("liabilityLimit")
    private String liabilityLimit;
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

    public ExcessUnderLyingInsurance withInsuranceType(String insuranceType) {
        this.insuranceType = insuranceType;
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

    public ExcessUnderLyingInsurance withUnderlyingPremium(String underlyingPremium) {
        this.underlyingPremium = underlyingPremium;
        return this;
    }

    @JsonProperty("liabilityLimit")
    public String getLiabilityLimit() {
        return liabilityLimit;
    }

    @JsonProperty("liabilityLimit")
    public void setLiabilityLimit(String liabilityLimit) {
        this.liabilityLimit = liabilityLimit;
    }

    public ExcessUnderLyingInsurance withLiabilityLimit(String liabilityLimit) {
        this.liabilityLimit = liabilityLimit;
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

    public ExcessUnderLyingInsurance withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ExcessUnderLyingInsurance.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("insuranceType");
        sb.append('=');
        sb.append(((this.insuranceType == null)?"<null>":this.insuranceType));
        sb.append(',');
        sb.append("underlyingPremium");
        sb.append('=');
        sb.append(((this.underlyingPremium == null)?"<null>":this.underlyingPremium));
        sb.append(',');
        sb.append("liabilityLimit");
        sb.append('=');
        sb.append(((this.liabilityLimit == null)?"<null>":this.liabilityLimit));
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
        result = ((result* 31)+((this.insuranceType == null)? 0 :this.insuranceType.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.underlyingPremium == null)? 0 :this.underlyingPremium.hashCode()));
        result = ((result* 31)+((this.liabilityLimit == null)? 0 :this.liabilityLimit.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ExcessUnderLyingInsurance) == false) {
            return false;
        }
        ExcessUnderLyingInsurance rhs = ((ExcessUnderLyingInsurance) other);
        return (((((this.insuranceType == rhs.insuranceType)||((this.insuranceType!= null)&&this.insuranceType.equals(rhs.insuranceType)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.underlyingPremium == rhs.underlyingPremium)||((this.underlyingPremium!= null)&&this.underlyingPremium.equals(rhs.underlyingPremium))))&&((this.liabilityLimit == rhs.liabilityLimit)||((this.liabilityLimit!= null)&&this.liabilityLimit.equals(rhs.liabilityLimit))));
    }

}
