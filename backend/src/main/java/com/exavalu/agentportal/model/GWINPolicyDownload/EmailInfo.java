
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
    "emailTypeCd",
    "emailAddr",
    "preferredInd"
})
@Generated("jsonschema2pojo")
public class EmailInfo {

    @JsonProperty("id")
    private String id;
    @JsonProperty("emailTypeCd")
    private String emailTypeCd;
    @JsonProperty("emailAddr")
    private String emailAddr;
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

    public EmailInfo withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("emailTypeCd")
    public String getEmailTypeCd() {
        return emailTypeCd;
    }

    @JsonProperty("emailTypeCd")
    public void setEmailTypeCd(String emailTypeCd) {
        this.emailTypeCd = emailTypeCd;
    }

    public EmailInfo withEmailTypeCd(String emailTypeCd) {
        this.emailTypeCd = emailTypeCd;
        return this;
    }

    @JsonProperty("emailAddr")
    public String getEmailAddr() {
        return emailAddr;
    }

    @JsonProperty("emailAddr")
    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
    }

    public EmailInfo withEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
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

    public EmailInfo withPreferredInd(Boolean preferredInd) {
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

    public EmailInfo withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(EmailInfo.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("emailTypeCd");
        sb.append('=');
        sb.append(((this.emailTypeCd == null)?"<null>":this.emailTypeCd));
        sb.append(',');
        sb.append("emailAddr");
        sb.append('=');
        sb.append(((this.emailAddr == null)?"<null>":this.emailAddr));
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
        result = ((result* 31)+((this.emailTypeCd == null)? 0 :this.emailTypeCd.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.emailAddr == null)? 0 :this.emailAddr.hashCode()));
        result = ((result* 31)+((this.preferredInd == null)? 0 :this.preferredInd.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof EmailInfo) == false) {
            return false;
        }
        EmailInfo rhs = ((EmailInfo) other);
        return ((((((this.emailTypeCd == rhs.emailTypeCd)||((this.emailTypeCd!= null)&&this.emailTypeCd.equals(rhs.emailTypeCd)))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.emailAddr == rhs.emailAddr)||((this.emailAddr!= null)&&this.emailAddr.equals(rhs.emailAddr))))&&((this.preferredInd == rhs.preferredInd)||((this.preferredInd!= null)&&this.preferredInd.equals(rhs.preferredInd))));
    }

}
