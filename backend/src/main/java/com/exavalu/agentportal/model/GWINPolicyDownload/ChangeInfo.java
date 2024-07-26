
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
    "userId",
    "addDt",
    "addTm",
    "status",
    "modInfo",
    "changeType"
})
@Generated("jsonschema2pojo")
public class ChangeInfo {

    @JsonProperty("id")
    private String id;
    @JsonProperty("userId")
    private String userId;
    @JsonProperty("addDt")
    private String addDt;
    @JsonProperty("addTm")
    private String addTm;
    @JsonProperty("status")
    private String status;
    @JsonProperty("modInfo")
    private List<ModInfo> modInfo = new ArrayList<ModInfo>();
    @JsonProperty("changeType")
    private String changeType;
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

    public ChangeInfo withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("userId")
    public String getUserId() {
        return userId;
    }

    @JsonProperty("userId")
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ChangeInfo withUserId(String userId) {
        this.userId = userId;
        return this;
    }

    @JsonProperty("addDt")
    public String getAddDt() {
        return addDt;
    }

    @JsonProperty("addDt")
    public void setAddDt(String addDt) {
        this.addDt = addDt;
    }

    public ChangeInfo withAddDt(String addDt) {
        this.addDt = addDt;
        return this;
    }

    @JsonProperty("addTm")
    public String getAddTm() {
        return addTm;
    }

    @JsonProperty("addTm")
    public void setAddTm(String addTm) {
        this.addTm = addTm;
    }

    public ChangeInfo withAddTm(String addTm) {
        this.addTm = addTm;
        return this;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    public ChangeInfo withStatus(String status) {
        this.status = status;
        return this;
    }

    @JsonProperty("modInfo")
    public List<ModInfo> getModInfo() {
        return modInfo;
    }

    @JsonProperty("modInfo")
    public void setModInfo(List<ModInfo> modInfo) {
        this.modInfo = modInfo;
    }

    public ChangeInfo withModInfo(List<ModInfo> modInfo) {
        this.modInfo = modInfo;
        return this;
    }

    @JsonProperty("changeType")
    public String getChangeType() {
        return changeType;
    }

    @JsonProperty("changeType")
    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    public ChangeInfo withChangeType(String changeType) {
        this.changeType = changeType;
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

    public ChangeInfo withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ChangeInfo.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("userId");
        sb.append('=');
        sb.append(((this.userId == null)?"<null>":this.userId));
        sb.append(',');
        sb.append("addDt");
        sb.append('=');
        sb.append(((this.addDt == null)?"<null>":this.addDt));
        sb.append(',');
        sb.append("addTm");
        sb.append('=');
        sb.append(((this.addTm == null)?"<null>":this.addTm));
        sb.append(',');
        sb.append("status");
        sb.append('=');
        sb.append(((this.status == null)?"<null>":this.status));
        sb.append(',');
        sb.append("modInfo");
        sb.append('=');
        sb.append(((this.modInfo == null)?"<null>":this.modInfo));
        sb.append(',');
        sb.append("changeType");
        sb.append('=');
        sb.append(((this.changeType == null)?"<null>":this.changeType));
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
        result = ((result* 31)+((this.addDt == null)? 0 :this.addDt.hashCode()));
        result = ((result* 31)+((this.modInfo == null)? 0 :this.modInfo.hashCode()));
        result = ((result* 31)+((this.changeType == null)? 0 :this.changeType.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.userId == null)? 0 :this.userId.hashCode()));
        result = ((result* 31)+((this.addTm == null)? 0 :this.addTm.hashCode()));
        result = ((result* 31)+((this.status == null)? 0 :this.status.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ChangeInfo) == false) {
            return false;
        }
        ChangeInfo rhs = ((ChangeInfo) other);
        return (((((((((this.addDt == rhs.addDt)||((this.addDt!= null)&&this.addDt.equals(rhs.addDt)))&&((this.modInfo == rhs.modInfo)||((this.modInfo!= null)&&this.modInfo.equals(rhs.modInfo))))&&((this.changeType == rhs.changeType)||((this.changeType!= null)&&this.changeType.equals(rhs.changeType))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.userId == rhs.userId)||((this.userId!= null)&&this.userId.equals(rhs.userId))))&&((this.addTm == rhs.addTm)||((this.addTm!= null)&&this.addTm.equals(rhs.addTm))))&&((this.status == rhs.status)||((this.status!= null)&&this.status.equals(rhs.status))));
    }

}
