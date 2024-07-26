
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
    "idRef",
    "actionCd",
    "previousValue",
    "newValue",
    "field",
    "status",
    "parentIdRef"
})
@Generated("jsonschema2pojo")
public class ModInfo {

    @JsonProperty("id")
    private String id;
    @JsonProperty("idRef")
    private String idRef;
    @JsonProperty("actionCd")
    private String actionCd;
    @JsonProperty("previousValue")
    private String previousValue;
    @JsonProperty("newValue")
    private String newValue;
    @JsonProperty("field")
    private String field;
    @JsonProperty("status")
    private String status;
    @JsonProperty("parentIdRef")
    private String parentIdRef;
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

    public ModInfo withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("idRef")
    public String getIdRef() {
        return idRef;
    }

    @JsonProperty("idRef")
    public void setIdRef(String idRef) {
        this.idRef = idRef;
    }

    public ModInfo withIdRef(String idRef) {
        this.idRef = idRef;
        return this;
    }

    @JsonProperty("actionCd")
    public String getActionCd() {
        return actionCd;
    }

    @JsonProperty("actionCd")
    public void setActionCd(String actionCd) {
        this.actionCd = actionCd;
    }

    public ModInfo withActionCd(String actionCd) {
        this.actionCd = actionCd;
        return this;
    }

    @JsonProperty("previousValue")
    public String getPreviousValue() {
        return previousValue;
    }

    @JsonProperty("previousValue")
    public void setPreviousValue(String previousValue) {
        this.previousValue = previousValue;
    }

    public ModInfo withPreviousValue(String previousValue) {
        this.previousValue = previousValue;
        return this;
    }

    @JsonProperty("newValue")
    public String getNewValue() {
        return newValue;
    }

    @JsonProperty("newValue")
    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public ModInfo withNewValue(String newValue) {
        this.newValue = newValue;
        return this;
    }

    @JsonProperty("field")
    public String getField() {
        return field;
    }

    @JsonProperty("field")
    public void setField(String field) {
        this.field = field;
    }

    public ModInfo withField(String field) {
        this.field = field;
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

    public ModInfo withStatus(String status) {
        this.status = status;
        return this;
    }

    @JsonProperty("parentIdRef")
    public String getParentIdRef() {
        return parentIdRef;
    }

    @JsonProperty("parentIdRef")
    public void setParentIdRef(String parentIdRef) {
        this.parentIdRef = parentIdRef;
    }

    public ModInfo withParentIdRef(String parentIdRef) {
        this.parentIdRef = parentIdRef;
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

    public ModInfo withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ModInfo.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("idRef");
        sb.append('=');
        sb.append(((this.idRef == null)?"<null>":this.idRef));
        sb.append(',');
        sb.append("actionCd");
        sb.append('=');
        sb.append(((this.actionCd == null)?"<null>":this.actionCd));
        sb.append(',');
        sb.append("previousValue");
        sb.append('=');
        sb.append(((this.previousValue == null)?"<null>":this.previousValue));
        sb.append(',');
        sb.append("newValue");
        sb.append('=');
        sb.append(((this.newValue == null)?"<null>":this.newValue));
        sb.append(',');
        sb.append("field");
        sb.append('=');
        sb.append(((this.field == null)?"<null>":this.field));
        sb.append(',');
        sb.append("status");
        sb.append('=');
        sb.append(((this.status == null)?"<null>":this.status));
        sb.append(',');
        sb.append("parentIdRef");
        sb.append('=');
        sb.append(((this.parentIdRef == null)?"<null>":this.parentIdRef));
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
        result = ((result* 31)+((this.newValue == null)? 0 :this.newValue.hashCode()));
        result = ((result* 31)+((this.field == null)? 0 :this.field.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.parentIdRef == null)? 0 :this.parentIdRef.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.previousValue == null)? 0 :this.previousValue.hashCode()));
        result = ((result* 31)+((this.idRef == null)? 0 :this.idRef.hashCode()));
        result = ((result* 31)+((this.actionCd == null)? 0 :this.actionCd.hashCode()));
        result = ((result* 31)+((this.status == null)? 0 :this.status.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ModInfo) == false) {
            return false;
        }
        ModInfo rhs = ((ModInfo) other);
        return ((((((((((this.newValue == rhs.newValue)||((this.newValue!= null)&&this.newValue.equals(rhs.newValue)))&&((this.field == rhs.field)||((this.field!= null)&&this.field.equals(rhs.field))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.parentIdRef == rhs.parentIdRef)||((this.parentIdRef!= null)&&this.parentIdRef.equals(rhs.parentIdRef))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.previousValue == rhs.previousValue)||((this.previousValue!= null)&&this.previousValue.equals(rhs.previousValue))))&&((this.idRef == rhs.idRef)||((this.idRef!= null)&&this.idRef.equals(rhs.idRef))))&&((this.actionCd == rhs.actionCd)||((this.actionCd!= null)&&this.actionCd.equals(rhs.actionCd))))&&((this.status == rhs.status)||((this.status!= null)&&this.status.equals(rhs.status))));
    }

}
