
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
    "preferredInd"
})
@Generated("jsonschema2pojo")
public class PhoneInfo {

    @JsonProperty("id")
    private String id;
    @JsonProperty("phoneTypeCd")
    private String phoneTypeCd;
    @JsonProperty("preferredInd")
    private Boolean preferredInd;
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

    public PhoneInfo withId(String id) {
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

    public PhoneInfo withPhoneTypeCd(String phoneTypeCd) {
        this.phoneTypeCd = phoneTypeCd;
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

    public PhoneInfo withPreferredInd(Boolean preferredInd) {
        this.preferredInd = preferredInd;
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

    public PhoneInfo withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(PhoneInfo.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("phoneTypeCd");
        sb.append('=');
        sb.append(((this.phoneTypeCd == null)?"<null>":this.phoneTypeCd));
        sb.append(',');
        sb.append("preferredInd");
        sb.append('=');
        sb.append(((this.preferredInd == null)?"<null>":this.preferredInd));
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
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.preferredInd == null)? 0 :this.preferredInd.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PhoneInfo) == false) {
            return false;
        }
        PhoneInfo rhs = ((PhoneInfo) other);
        return (((((this.phoneTypeCd == rhs.phoneTypeCd)||((this.phoneTypeCd!= null)&&this.phoneTypeCd.equals(rhs.phoneTypeCd)))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.preferredInd == rhs.preferredInd)||((this.preferredInd!= null)&&this.preferredInd.equals(rhs.preferredInd))));
    }

}
