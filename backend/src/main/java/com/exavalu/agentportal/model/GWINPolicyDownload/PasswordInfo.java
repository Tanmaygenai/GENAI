
package com.exavalu.agentportal.model.GWINPolicyDownload;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    "passwordTypeCd",
    "passwordLastChangeDt",
    "passwordRequirementTemplateId",
    "passwordHistory"
})
@Generated("jsonschema2pojo")
public class PasswordInfo {

    @JsonProperty("id")
    private String id;
    @JsonProperty("passwordTypeCd")
    private String passwordTypeCd;
    @JsonProperty("passwordLastChangeDt")
    private String passwordLastChangeDt;
    @JsonProperty("passwordRequirementTemplateId")
    private String passwordRequirementTemplateId;
    @JsonProperty("passwordHistory")
    private List<PasswordHistory> passwordHistory = new ArrayList<PasswordHistory>();
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

    public PasswordInfo withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("passwordTypeCd")
    public String getPasswordTypeCd() {
        return passwordTypeCd;
    }

    @JsonProperty("passwordTypeCd")
    public void setPasswordTypeCd(String passwordTypeCd) {
        this.passwordTypeCd = passwordTypeCd;
    }

    public PasswordInfo withPasswordTypeCd(String passwordTypeCd) {
        this.passwordTypeCd = passwordTypeCd;
        return this;
    }

    @JsonProperty("passwordLastChangeDt")
    public String getPasswordLastChangeDt() {
        return passwordLastChangeDt;
    }

    @JsonProperty("passwordLastChangeDt")
    public void setPasswordLastChangeDt(String passwordLastChangeDt) {
        this.passwordLastChangeDt = passwordLastChangeDt;
    }

    public PasswordInfo withPasswordLastChangeDt(String passwordLastChangeDt) {
        this.passwordLastChangeDt = passwordLastChangeDt;
        return this;
    }

    @JsonProperty("passwordRequirementTemplateId")
    public String getPasswordRequirementTemplateId() {
        return passwordRequirementTemplateId;
    }

    @JsonProperty("passwordRequirementTemplateId")
    public void setPasswordRequirementTemplateId(String passwordRequirementTemplateId) {
        this.passwordRequirementTemplateId = passwordRequirementTemplateId;
    }

    public PasswordInfo withPasswordRequirementTemplateId(String passwordRequirementTemplateId) {
        this.passwordRequirementTemplateId = passwordRequirementTemplateId;
        return this;
    }

    @JsonProperty("passwordHistory")
    public List<PasswordHistory> getPasswordHistory() {
        return passwordHistory;
    }

    @JsonProperty("passwordHistory")
    public void setPasswordHistory(List<PasswordHistory> passwordHistory) {
        this.passwordHistory = passwordHistory;
    }

    public PasswordInfo withPasswordHistory(List<PasswordHistory> passwordHistory) {
        this.passwordHistory = passwordHistory;
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

    public PasswordInfo withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(PasswordInfo.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("passwordTypeCd");
        sb.append('=');
        sb.append(((this.passwordTypeCd == null)?"<null>":this.passwordTypeCd));
        sb.append(',');
        sb.append("passwordLastChangeDt");
        sb.append('=');
        sb.append(((this.passwordLastChangeDt == null)?"<null>":this.passwordLastChangeDt));
        sb.append(',');
        sb.append("passwordRequirementTemplateId");
        sb.append('=');
        sb.append(((this.passwordRequirementTemplateId == null)?"<null>":this.passwordRequirementTemplateId));
        sb.append(',');
        sb.append("passwordHistory");
        sb.append('=');
        sb.append(((this.passwordHistory == null)?"<null>":this.passwordHistory));
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
        result = ((result* 31)+((this.passwordTypeCd == null)? 0 :this.passwordTypeCd.hashCode()));
        result = ((result* 31)+((this.passwordLastChangeDt == null)? 0 :this.passwordLastChangeDt.hashCode()));
        result = ((result* 31)+((this.passwordHistory == null)? 0 :this.passwordHistory.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.passwordRequirementTemplateId == null)? 0 :this.passwordRequirementTemplateId.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PasswordInfo) == false) {
            return false;
        }
        PasswordInfo rhs = ((PasswordInfo) other);
        return (((((((this.passwordTypeCd == rhs.passwordTypeCd)||((this.passwordTypeCd!= null)&&this.passwordTypeCd.equals(rhs.passwordTypeCd)))&&((this.passwordLastChangeDt == rhs.passwordLastChangeDt)||((this.passwordLastChangeDt!= null)&&this.passwordLastChangeDt.equals(rhs.passwordLastChangeDt))))&&((this.passwordHistory == rhs.passwordHistory)||((this.passwordHistory!= null)&&this.passwordHistory.equals(rhs.passwordHistory))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.passwordRequirementTemplateId == rhs.passwordRequirementTemplateId)||((this.passwordRequirementTemplateId!= null)&&this.passwordRequirementTemplateId.equals(rhs.passwordRequirementTemplateId))));
    }

}
