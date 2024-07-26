
package com.exavalu.agentportal.model.DRCExcessQuickIndication;

import java.util.HashMap;
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
    "Policy",
    "PUC",
    "Fraud",
    "DMV",
    "Cargo",
    "DOT"
})
@Generated("jsonschema2pojo")
public class PolicyFees {

    @JsonProperty("Policy")
    private String policy;
    @JsonProperty("PUC")
    private String puc;
    @JsonProperty("Fraud")
    private String fraud;
    @JsonProperty("DMV")
    private String dmv;
    @JsonProperty("Cargo")
    private String cargo;
    @JsonProperty("DOT")
    private String dot;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("Policy")
    public String getPolicy() {
        return policy;
    }

    @JsonProperty("Policy")
    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public PolicyFees withPolicy(String policy) {
        this.policy = policy;
        return this;
    }

    @JsonProperty("PUC")
    public String getPuc() {
        return puc;
    }

    @JsonProperty("PUC")
    public void setPuc(String puc) {
        this.puc = puc;
    }

    public PolicyFees withPuc(String puc) {
        this.puc = puc;
        return this;
    }

    @JsonProperty("Fraud")
    public String getFraud() {
        return fraud;
    }

    @JsonProperty("Fraud")
    public void setFraud(String fraud) {
        this.fraud = fraud;
    }

    public PolicyFees withFraud(String fraud) {
        this.fraud = fraud;
        return this;
    }

    @JsonProperty("DMV")
    public String getDmv() {
        return dmv;
    }

    @JsonProperty("DMV")
    public void setDmv(String dmv) {
        this.dmv = dmv;
    }

    public PolicyFees withDmv(String dmv) {
        this.dmv = dmv;
        return this;
    }

    @JsonProperty("Cargo")
    public String getCargo() {
        return cargo;
    }

    @JsonProperty("Cargo")
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public PolicyFees withCargo(String cargo) {
        this.cargo = cargo;
        return this;
    }

    @JsonProperty("DOT")
    public String getDot() {
        return dot;
    }

    @JsonProperty("DOT")
    public void setDot(String dot) {
        this.dot = dot;
    }

    public PolicyFees withDot(String dot) {
        this.dot = dot;
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

    public PolicyFees withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(PolicyFees.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("policy");
        sb.append('=');
        sb.append(((this.policy == null)?"<null>":this.policy));
        sb.append(',');
        sb.append("puc");
        sb.append('=');
        sb.append(((this.puc == null)?"<null>":this.puc));
        sb.append(',');
        sb.append("fraud");
        sb.append('=');
        sb.append(((this.fraud == null)?"<null>":this.fraud));
        sb.append(',');
        sb.append("dmv");
        sb.append('=');
        sb.append(((this.dmv == null)?"<null>":this.dmv));
        sb.append(',');
        sb.append("cargo");
        sb.append('=');
        sb.append(((this.cargo == null)?"<null>":this.cargo));
        sb.append(',');
        sb.append("dot");
        sb.append('=');
        sb.append(((this.dot == null)?"<null>":this.dot));
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
        result = ((result* 31)+((this.fraud == null)? 0 :this.fraud.hashCode()));
        result = ((result* 31)+((this.dot == null)? 0 :this.dot.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.dmv == null)? 0 :this.dmv.hashCode()));
        result = ((result* 31)+((this.cargo == null)? 0 :this.cargo.hashCode()));
        result = ((result* 31)+((this.policy == null)? 0 :this.policy.hashCode()));
        result = ((result* 31)+((this.puc == null)? 0 :this.puc.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PolicyFees) == false) {
            return false;
        }
        PolicyFees rhs = ((PolicyFees) other);
        return ((((((((this.fraud == rhs.fraud)||((this.fraud!= null)&&this.fraud.equals(rhs.fraud)))&&((this.dot == rhs.dot)||((this.dot!= null)&&this.dot.equals(rhs.dot))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.dmv == rhs.dmv)||((this.dmv!= null)&&this.dmv.equals(rhs.dmv))))&&((this.cargo == rhs.cargo)||((this.cargo!= null)&&this.cargo.equals(rhs.cargo))))&&((this.policy == rhs.policy)||((this.policy!= null)&&this.policy.equals(rhs.policy))))&&((this.puc == rhs.puc)||((this.puc!= null)&&this.puc.equals(rhs.puc))));
    }

}
