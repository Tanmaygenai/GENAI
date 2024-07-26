
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
    "userRoleAttributes",
    "startDt",
    "endDt",
    "authorityRoleIdRef"
})
@Generated("jsonschema2pojo")
public class Role {

    @JsonProperty("id")
    private String id;
    @JsonProperty("userRoleAttributes")
    private List<UserRoleAttribute> userRoleAttributes = new ArrayList<UserRoleAttribute>();
    @JsonProperty("startDt")
    private String startDt;
    @JsonProperty("endDt")
    private String endDt;
    @JsonProperty("authorityRoleIdRef")
    private String authorityRoleIdRef;
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

    public Role withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("userRoleAttributes")
    public List<UserRoleAttribute> getUserRoleAttributes() {
        return userRoleAttributes;
    }

    @JsonProperty("userRoleAttributes")
    public void setUserRoleAttributes(List<UserRoleAttribute> userRoleAttributes) {
        this.userRoleAttributes = userRoleAttributes;
    }

    public Role withUserRoleAttributes(List<UserRoleAttribute> userRoleAttributes) {
        this.userRoleAttributes = userRoleAttributes;
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

    public Role withStartDt(String startDt) {
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

    public Role withEndDt(String endDt) {
        this.endDt = endDt;
        return this;
    }

    @JsonProperty("authorityRoleIdRef")
    public String getAuthorityRoleIdRef() {
        return authorityRoleIdRef;
    }

    @JsonProperty("authorityRoleIdRef")
    public void setAuthorityRoleIdRef(String authorityRoleIdRef) {
        this.authorityRoleIdRef = authorityRoleIdRef;
    }

    public Role withAuthorityRoleIdRef(String authorityRoleIdRef) {
        this.authorityRoleIdRef = authorityRoleIdRef;
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

    public Role withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Role.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("userRoleAttributes");
        sb.append('=');
        sb.append(((this.userRoleAttributes == null)?"<null>":this.userRoleAttributes));
        sb.append(',');
        sb.append("startDt");
        sb.append('=');
        sb.append(((this.startDt == null)?"<null>":this.startDt));
        sb.append(',');
        sb.append("endDt");
        sb.append('=');
        sb.append(((this.endDt == null)?"<null>":this.endDt));
        sb.append(',');
        sb.append("authorityRoleIdRef");
        sb.append('=');
        sb.append(((this.authorityRoleIdRef == null)?"<null>":this.authorityRoleIdRef));
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
        result = ((result* 31)+((this.authorityRoleIdRef == null)? 0 :this.authorityRoleIdRef.hashCode()));
        result = ((result* 31)+((this.startDt == null)? 0 :this.startDt.hashCode()));
        result = ((result* 31)+((this.endDt == null)? 0 :this.endDt.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.userRoleAttributes == null)? 0 :this.userRoleAttributes.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Role) == false) {
            return false;
        }
        Role rhs = ((Role) other);
        return (((((((this.authorityRoleIdRef == rhs.authorityRoleIdRef)||((this.authorityRoleIdRef!= null)&&this.authorityRoleIdRef.equals(rhs.authorityRoleIdRef)))&&((this.startDt == rhs.startDt)||((this.startDt!= null)&&this.startDt.equals(rhs.startDt))))&&((this.endDt == rhs.endDt)||((this.endDt!= null)&&this.endDt.equals(rhs.endDt))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.userRoleAttributes == rhs.userRoleAttributes)||((this.userRoleAttributes!= null)&&this.userRoleAttributes.equals(rhs.userRoleAttributes))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}
