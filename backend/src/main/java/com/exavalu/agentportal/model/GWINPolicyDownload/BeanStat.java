
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
    "beanAddUserIdRef",
    "beanAddDt",
    "beanAddTm",
    "beanUpdateUserIdRef",
    "beanUpdateDt",
    "beanUpdateTm"
})
@Generated("jsonschema2pojo")
public class BeanStat {

    @JsonProperty("id")
    private String id;
    @JsonProperty("beanAddUserIdRef")
    private String beanAddUserIdRef;
    @JsonProperty("beanAddDt")
    private String beanAddDt;
    @JsonProperty("beanAddTm")
    private String beanAddTm;
    @JsonProperty("beanUpdateUserIdRef")
    private String beanUpdateUserIdRef;
    @JsonProperty("beanUpdateDt")
    private String beanUpdateDt;
    @JsonProperty("beanUpdateTm")
    private String beanUpdateTm;
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

    public BeanStat withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("beanAddUserIdRef")
    public String getBeanAddUserIdRef() {
        return beanAddUserIdRef;
    }

    @JsonProperty("beanAddUserIdRef")
    public void setBeanAddUserIdRef(String beanAddUserIdRef) {
        this.beanAddUserIdRef = beanAddUserIdRef;
    }

    public BeanStat withBeanAddUserIdRef(String beanAddUserIdRef) {
        this.beanAddUserIdRef = beanAddUserIdRef;
        return this;
    }

    @JsonProperty("beanAddDt")
    public String getBeanAddDt() {
        return beanAddDt;
    }

    @JsonProperty("beanAddDt")
    public void setBeanAddDt(String beanAddDt) {
        this.beanAddDt = beanAddDt;
    }

    public BeanStat withBeanAddDt(String beanAddDt) {
        this.beanAddDt = beanAddDt;
        return this;
    }

    @JsonProperty("beanAddTm")
    public String getBeanAddTm() {
        return beanAddTm;
    }

    @JsonProperty("beanAddTm")
    public void setBeanAddTm(String beanAddTm) {
        this.beanAddTm = beanAddTm;
    }

    public BeanStat withBeanAddTm(String beanAddTm) {
        this.beanAddTm = beanAddTm;
        return this;
    }

    @JsonProperty("beanUpdateUserIdRef")
    public String getBeanUpdateUserIdRef() {
        return beanUpdateUserIdRef;
    }

    @JsonProperty("beanUpdateUserIdRef")
    public void setBeanUpdateUserIdRef(String beanUpdateUserIdRef) {
        this.beanUpdateUserIdRef = beanUpdateUserIdRef;
    }

    public BeanStat withBeanUpdateUserIdRef(String beanUpdateUserIdRef) {
        this.beanUpdateUserIdRef = beanUpdateUserIdRef;
        return this;
    }

    @JsonProperty("beanUpdateDt")
    public String getBeanUpdateDt() {
        return beanUpdateDt;
    }

    @JsonProperty("beanUpdateDt")
    public void setBeanUpdateDt(String beanUpdateDt) {
        this.beanUpdateDt = beanUpdateDt;
    }

    public BeanStat withBeanUpdateDt(String beanUpdateDt) {
        this.beanUpdateDt = beanUpdateDt;
        return this;
    }

    @JsonProperty("beanUpdateTm")
    public String getBeanUpdateTm() {
        return beanUpdateTm;
    }

    @JsonProperty("beanUpdateTm")
    public void setBeanUpdateTm(String beanUpdateTm) {
        this.beanUpdateTm = beanUpdateTm;
    }

    public BeanStat withBeanUpdateTm(String beanUpdateTm) {
        this.beanUpdateTm = beanUpdateTm;
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

    public BeanStat withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(BeanStat.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("beanAddUserIdRef");
        sb.append('=');
        sb.append(((this.beanAddUserIdRef == null)?"<null>":this.beanAddUserIdRef));
        sb.append(',');
        sb.append("beanAddDt");
        sb.append('=');
        sb.append(((this.beanAddDt == null)?"<null>":this.beanAddDt));
        sb.append(',');
        sb.append("beanAddTm");
        sb.append('=');
        sb.append(((this.beanAddTm == null)?"<null>":this.beanAddTm));
        sb.append(',');
        sb.append("beanUpdateUserIdRef");
        sb.append('=');
        sb.append(((this.beanUpdateUserIdRef == null)?"<null>":this.beanUpdateUserIdRef));
        sb.append(',');
        sb.append("beanUpdateDt");
        sb.append('=');
        sb.append(((this.beanUpdateDt == null)?"<null>":this.beanUpdateDt));
        sb.append(',');
        sb.append("beanUpdateTm");
        sb.append('=');
        sb.append(((this.beanUpdateTm == null)?"<null>":this.beanUpdateTm));
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
        result = ((result* 31)+((this.beanUpdateTm == null)? 0 :this.beanUpdateTm.hashCode()));
        result = ((result* 31)+((this.beanAddTm == null)? 0 :this.beanAddTm.hashCode()));
        result = ((result* 31)+((this.beanUpdateUserIdRef == null)? 0 :this.beanUpdateUserIdRef.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.beanAddUserIdRef == null)? 0 :this.beanAddUserIdRef.hashCode()));
        result = ((result* 31)+((this.beanAddDt == null)? 0 :this.beanAddDt.hashCode()));
        result = ((result* 31)+((this.beanUpdateDt == null)? 0 :this.beanUpdateDt.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof BeanStat) == false) {
            return false;
        }
        BeanStat rhs = ((BeanStat) other);
        return (((((((((this.beanUpdateTm == rhs.beanUpdateTm)||((this.beanUpdateTm!= null)&&this.beanUpdateTm.equals(rhs.beanUpdateTm)))&&((this.beanAddTm == rhs.beanAddTm)||((this.beanAddTm!= null)&&this.beanAddTm.equals(rhs.beanAddTm))))&&((this.beanUpdateUserIdRef == rhs.beanUpdateUserIdRef)||((this.beanUpdateUserIdRef!= null)&&this.beanUpdateUserIdRef.equals(rhs.beanUpdateUserIdRef))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.beanAddUserIdRef == rhs.beanAddUserIdRef)||((this.beanAddUserIdRef!= null)&&this.beanAddUserIdRef.equals(rhs.beanAddUserIdRef))))&&((this.beanAddDt == rhs.beanAddDt)||((this.beanAddDt!= null)&&this.beanAddDt.equals(rhs.beanAddDt))))&&((this.beanUpdateDt == rhs.beanUpdateDt)||((this.beanUpdateDt!= null)&&this.beanUpdateDt.equals(rhs.beanUpdateDt))));
    }

}
