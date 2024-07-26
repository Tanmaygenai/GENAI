
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
    "value",
    "startDt",
    "endDt",
    "authorityAttributeIdRef"
})
@Generated("jsonschema2pojo")
public class UserRoleAttribute {

    @JsonProperty("id")
    private String id;
    @JsonProperty("value")
    private String value;
    @JsonProperty("startDt")
    private String startDt;
    @JsonProperty("endDt")
    private String endDt;
    @JsonProperty("authorityAttributeIdRef")
    private String authorityAttributeIdRef;
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

    public UserRoleAttribute withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("value")
    public String getValue() {
        return value;
    }

    @JsonProperty("value")
    public void setValue(String value) {
        this.value = value;
    }

    public UserRoleAttribute withValue(String value) {
        this.value = value;
        return this;
    }

    @JsonProperty("startDt")
    public String getStartDt() {
        return startDt;
    }

    @JsonProperty("startDt")
    public void setStartDt(String startDt) {
        this.startDt = startDt;
    }

    public UserRoleAttribute withStartDt(String startDt) {
        this.startDt = startDt;
        return this;
    }

    @JsonProperty("endDt")
    public String getEndDt() {
        return endDt;
    }

    @JsonProperty("endDt")
    public void setEndDt(String endDt) {
        this.endDt = endDt;
    }

    public UserRoleAttribute withEndDt(String endDt) {
        this.endDt = endDt;
        return this;
    }

    @JsonProperty("authorityAttributeIdRef")
    public String getAuthorityAttributeIdRef() {
        return authorityAttributeIdRef;
    }

    @JsonProperty("authorityAttributeIdRef")
    public void setAuthorityAttributeIdRef(String authorityAttributeIdRef) {
        this.authorityAttributeIdRef = authorityAttributeIdRef;
    }

    public UserRoleAttribute withAuthorityAttributeIdRef(String authorityAttributeIdRef) {
        this.authorityAttributeIdRef = authorityAttributeIdRef;
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

    public UserRoleAttribute withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(UserRoleAttribute.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("value");
        sb.append('=');
        sb.append(((this.value == null)?"<null>":this.value));
        sb.append(',');
        sb.append("startDt");
        sb.append('=');
        sb.append(((this.startDt == null)?"<null>":this.startDt));
        sb.append(',');
        sb.append("endDt");
        sb.append('=');
        sb.append(((this.endDt == null)?"<null>":this.endDt));
        sb.append(',');
        sb.append("authorityAttributeIdRef");
        sb.append('=');
        sb.append(((this.authorityAttributeIdRef == null)?"<null>":this.authorityAttributeIdRef));
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
        result = ((result* 31)+((this.authorityAttributeIdRef == null)? 0 :this.authorityAttributeIdRef.hashCode()));
        result = ((result* 31)+((this.startDt == null)? 0 :this.startDt.hashCode()));
        result = ((result* 31)+((this.endDt == null)? 0 :this.endDt.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.value == null)? 0 :this.value.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof UserRoleAttribute) == false) {
            return false;
        }
        UserRoleAttribute rhs = ((UserRoleAttribute) other);
        return (((((((this.authorityAttributeIdRef == rhs.authorityAttributeIdRef)||((this.authorityAttributeIdRef!= null)&&this.authorityAttributeIdRef.equals(rhs.authorityAttributeIdRef)))&&((this.startDt == rhs.startDt)||((this.startDt!= null)&&this.startDt.equals(rhs.startDt))))&&((this.endDt == rhs.endDt)||((this.endDt!= null)&&this.endDt.equals(rhs.endDt))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.value == rhs.value)||((this.value!= null)&&this.value.equals(rhs.value))));
    }

}
