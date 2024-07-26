
package com.exavalu.agentportal.model.GWINPolicyDownload;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "phoneTypeCd",
    "phoneNumber",
    "preferredInd",
    "phoneName"
})
@Generated("jsonschema2pojo")
public class PhoneInfo__1 {

    @JsonProperty("id")
    private String id;
    @JsonProperty("phoneTypeCd")
    private String phoneTypeCd;
    @JsonProperty("phoneNumber")
    private String phoneNumber;
    @JsonProperty("preferredInd")
    private Boolean preferredInd;
    @JsonProperty("phoneName")
    private String phoneName;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public PhoneInfo__1 withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("phoneTypeCd")
    public String getPhoneTypeCd() {
        return phoneTypeCd;
    }

    @JsonProperty("phoneTypeCd")
    public void setPhoneTypeCd(String phoneTypeCd) {
        this.phoneTypeCd = phoneTypeCd;
    }

    public PhoneInfo__1 withPhoneTypeCd(String phoneTypeCd) {
        this.phoneTypeCd = phoneTypeCd;
        return this;
    }

    @JsonProperty("phoneNumber")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @JsonProperty("phoneNumber")
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public PhoneInfo__1 withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    @JsonProperty("preferredInd")
    public Boolean getPreferredInd() {
        return preferredInd;
    }

    @JsonProperty("preferredInd")
    public void setPreferredInd(Boolean preferredInd) {
        this.preferredInd = preferredInd;
    }

    public PhoneInfo__1 withPreferredInd(Boolean preferredInd) {
        this.preferredInd = preferredInd;
        return this;
    }

    @JsonProperty("phoneName")
    public String getPhoneName() {
        return phoneName;
    }

    @JsonProperty("phoneName")
    public void setPhoneName(String phoneName) {
        this.phoneName = phoneName;
    }

    public PhoneInfo__1 withPhoneName(String phoneName) {
        this.phoneName = phoneName;
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

    public PhoneInfo__1 withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(PhoneInfo__1 .class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("phoneTypeCd");
        sb.append('=');
        sb.append(((this.phoneTypeCd == null)?"<null>":this.phoneTypeCd));
        sb.append(',');
        sb.append("phoneNumber");
        sb.append('=');
        sb.append(((this.phoneNumber == null)?"<null>":this.phoneNumber));
        sb.append(',');
        sb.append("preferredInd");
        sb.append('=');
        sb.append(((this.preferredInd == null)?"<null>":this.preferredInd));
        sb.append(',');
        sb.append("phoneName");
        sb.append('=');
        sb.append(((this.phoneName == null)?"<null>":this.phoneName));
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
        result = ((result* 31)+((this.phoneTypeCd == null)? 0 :this.phoneTypeCd.hashCode()));
        result = ((result* 31)+((this.phoneNumber == null)? 0 :this.phoneNumber.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.phoneName == null)? 0 :this.phoneName.hashCode()));
        result = ((result* 31)+((this.preferredInd == null)? 0 :this.preferredInd.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PhoneInfo__1) == false) {
            return false;
        }
        PhoneInfo__1 rhs = ((PhoneInfo__1) other);
        return (((((((this.phoneTypeCd == rhs.phoneTypeCd)||((this.phoneTypeCd!= null)&&this.phoneTypeCd.equals(rhs.phoneTypeCd)))&&((this.phoneNumber == rhs.phoneNumber)||((this.phoneNumber!= null)&&this.phoneNumber.equals(rhs.phoneNumber))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.phoneName == rhs.phoneName)||((this.phoneName!= null)&&this.phoneName.equals(rhs.phoneName))))&&((this.preferredInd == rhs.preferredInd)||((this.preferredInd!= null)&&this.preferredInd.equals(rhs.preferredInd))));
    }

}
