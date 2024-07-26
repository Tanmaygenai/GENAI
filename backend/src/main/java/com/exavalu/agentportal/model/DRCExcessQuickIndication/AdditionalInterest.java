
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
@JsonPropertyOrder({
    "interestType",
    "interestName",
    "addr",
    "interestDetails"
})
@Generated("jsonschema2pojo")
public class AdditionalInterest {

    @JsonProperty("interestType")
    private String interestType;
    @JsonProperty("interestName")
    private String interestName;
    @JsonProperty("addr")
    private Addr__3 addr;
    @JsonProperty("interestDetails")
    private List<InterestDetail> interestDetails = new ArrayList<InterestDetail>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("interestType")
    public String getInterestType() {
        return interestType;
    }

    @JsonProperty("interestType")
    public void setInterestType(String interestType) {
        this.interestType = interestType;
    }

    public AdditionalInterest withInterestType(String interestType) {
        this.interestType = interestType;
        return this;
    }

    @JsonProperty("interestName")
    public String getInterestName() {
        return interestName;
    }

    @JsonProperty("interestName")
    public void setInterestName(String interestName) {
        this.interestName = interestName;
    }

    public AdditionalInterest withInterestName(String interestName) {
        this.interestName = interestName;
        return this;
    }

    @JsonProperty("addr")
    public Addr__3 getAddr() {
        return addr;
    }

    @JsonProperty("addr")
    public void setAddr(Addr__3 addr) {
        this.addr = addr;
    }

    public AdditionalInterest withAddr(Addr__3 addr) {
        this.addr = addr;
        return this;
    }

    @JsonProperty("interestDetails")
    public List<InterestDetail> getInterestDetails() {
        return interestDetails;
    }

    @JsonProperty("interestDetails")
    public void setInterestDetails(List<InterestDetail> interestDetails) {
        this.interestDetails = interestDetails;
    }

    public AdditionalInterest withInterestDetails(List<InterestDetail> interestDetails) {
        this.interestDetails = interestDetails;
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

    public AdditionalInterest withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(AdditionalInterest.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("interestType");
        sb.append('=');
        sb.append(((this.interestType == null)?"<null>":this.interestType));
        sb.append(',');
        sb.append("interestName");
        sb.append('=');
        sb.append(((this.interestName == null)?"<null>":this.interestName));
        sb.append(',');
        sb.append("addr");
        sb.append('=');
        sb.append(((this.addr == null)?"<null>":this.addr));
        sb.append(',');
        sb.append("interestDetails");
        sb.append('=');
        sb.append(((this.interestDetails == null)?"<null>":this.interestDetails));
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
        result = ((result* 31)+((this.interestType == null)? 0 :this.interestType.hashCode()));
        result = ((result* 31)+((this.interestDetails == null)? 0 :this.interestDetails.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.addr == null)? 0 :this.addr.hashCode()));
        result = ((result* 31)+((this.interestName == null)? 0 :this.interestName.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof AdditionalInterest) == false) {
            return false;
        }
        AdditionalInterest rhs = ((AdditionalInterest) other);
        return ((((((this.interestType == rhs.interestType)||((this.interestType!= null)&&this.interestType.equals(rhs.interestType)))&&((this.interestDetails == rhs.interestDetails)||((this.interestDetails!= null)&&this.interestDetails.equals(rhs.interestDetails))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.addr == rhs.addr)||((this.addr!= null)&&this.addr.equals(rhs.addr))))&&((this.interestName == rhs.interestName)||((this.interestName!= null)&&this.interestName.equals(rhs.interestName))));
    }

}
