
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
    "combinedSingleLimit",
    "underlyingPremium",
    "vehicles"
})
@Generated("jsonschema2pojo")
public class AutoUnderLyingPolicy {

    @JsonProperty("insuranceType")
    private String insuranceType;
    @JsonProperty("combinedSingleLimit")
    private String combinedSingleLimit;
    @JsonProperty("underlyingPremium")
    private String underlyingPremium;
    @JsonProperty("vehicles")
    private Vehicles vehicles;
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

    public AutoUnderLyingPolicy withInsuranceType(String insuranceType) {
        this.insuranceType = insuranceType;
        return this;
    }

    @JsonProperty("combinedSingleLimit")
    public String getCombinedSingleLimit() {
        return combinedSingleLimit;
    }

    @JsonProperty("combinedSingleLimit")
    public void setCombinedSingleLimit(String combinedSingleLimit) {
        this.combinedSingleLimit = combinedSingleLimit;
    }

    public AutoUnderLyingPolicy withCombinedSingleLimit(String combinedSingleLimit) {
        this.combinedSingleLimit = combinedSingleLimit;
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

    public AutoUnderLyingPolicy withUnderlyingPremium(String underlyingPremium) {
        this.underlyingPremium = underlyingPremium;
        return this;
    }
    @JsonProperty("vehicles")
    public Vehicles getVehicles() {
        return vehicles;
    }

    @JsonProperty("vehicles")
    public void setVehicles(Vehicles vehicles) {
        this.vehicles = vehicles;
    }

    public AutoUnderLyingPolicy withVehicles(Vehicles vehicles) {
        this.vehicles = vehicles;
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

    public AutoUnderLyingPolicy withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(AutoUnderLyingPolicy.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("insuranceType");
        sb.append('=');
        sb.append(((this.insuranceType == null)?"<null>":this.insuranceType));
        sb.append(',');
        sb.append("combinedSingleLimit");
        sb.append('=');
        sb.append(((this.combinedSingleLimit == null)?"<null>":this.combinedSingleLimit));
        sb.append(',');
        sb.append("underlyingPremium");
        sb.append('=');
        sb.append(((this.underlyingPremium == null)?"<null>":this.underlyingPremium));
        sb.append(',');
        sb.append("vehicles");
        sb.append('=');
        sb.append(((this.vehicles == null)?"<null>":this.vehicles));
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
        result = ((result* 31)+((this.vehicles == null)? 0 :this.vehicles.hashCode()));
        result = ((result* 31)+((this.insuranceType == null)? 0 :this.insuranceType.hashCode()));
        result = ((result* 31)+((this.underlyingPremium == null)? 0 :this.underlyingPremium.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.combinedSingleLimit == null)? 0 :this.combinedSingleLimit.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof AutoUnderLyingPolicy) == false) {
            return false;
        }
        AutoUnderLyingPolicy rhs = ((AutoUnderLyingPolicy) other);
        return (((((this.vehicles == rhs.vehicles)||((this.vehicles!= null)&&this.vehicles.equals(rhs.vehicles)))&&
        		((this.insuranceType == rhs.insuranceType)||((this.insuranceType!= null)&&this.insuranceType.equals(rhs.insuranceType))))&&
        		((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&
        		((this.underlyingPremium == rhs.underlyingPremium)||((this.underlyingPremium!= null)&&this.underlyingPremium.equals(rhs.underlyingPremium))))&&
        		((this.combinedSingleLimit == rhs.combinedSingleLimit)||((this.combinedSingleLimit!= null)&&this.combinedSingleLimit.equals(rhs.combinedSingleLimit)));
    }

}
