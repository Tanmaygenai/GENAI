
package com.exavalu.agentportal.model.DRCExcessQuickIndication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    "coverageCd",
    "limits",
    "deductibles"
})
@Generated("jsonschema2pojo")
public class Coverage__1 {

    @JsonProperty("coverageCd")
    private String coverageCd;
    @JsonProperty("limits")
    private List<Limit__1> limits = new ArrayList<Limit__1>();
    @JsonProperty("deductibles")
    private List<Deductible__1> deductibles = new ArrayList<Deductible__1>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("coverageCd")
    public String getCoverageCd() {
        return coverageCd;
    }

    @JsonProperty("coverageCd")
    public void setCoverageCd(String coverageCd) {
        this.coverageCd = coverageCd;
    }

    public Coverage__1 withCoverageCd(String coverageCd) {
        this.coverageCd = coverageCd;
        return this;
    }

    @JsonProperty("limits")
    public List<Limit__1> getLimits() {
        return limits;
    }

    @JsonProperty("limits")
    public void setLimits(List<Limit__1> limits) {
        this.limits = limits;
    }

    public Coverage__1 withLimits(List<Limit__1> limits) {
        this.limits = limits;
        return this;
    }

    @JsonProperty("deductibles")
    public List<Deductible__1> getDeductibles() {
        return deductibles;
    }

    @JsonProperty("deductibles")
    public void setDeductibles(List<Deductible__1> deductibles) {
        this.deductibles = deductibles;
    }

    public Coverage__1 withDeductibles(List<Deductible__1> deductibles) {
        this.deductibles = deductibles;
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

    public Coverage__1 withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Coverage__1 .class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("coverageCd");
        sb.append('=');
        sb.append(((this.coverageCd == null)?"<null>":this.coverageCd));
        sb.append(',');
        sb.append("limits");
        sb.append('=');
        sb.append(((this.limits == null)?"<null>":this.limits));
        sb.append(',');
        sb.append("deductibles");
        sb.append('=');
        sb.append(((this.deductibles == null)?"<null>":this.deductibles));
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
        result = ((result* 31)+((this.coverageCd == null)? 0 :this.coverageCd.hashCode()));
        result = ((result* 31)+((this.deductibles == null)? 0 :this.deductibles.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.limits == null)? 0 :this.limits.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Coverage__1) == false) {
            return false;
        }
        Coverage__1 rhs = ((Coverage__1) other);
        return (((((this.coverageCd == rhs.coverageCd)||((this.coverageCd!= null)&&this.coverageCd.equals(rhs.coverageCd)))&&((this.deductibles == rhs.deductibles)||((this.deductibles!= null)&&this.deductibles.equals(rhs.deductibles))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.limits == rhs.limits)||((this.limits!= null)&&this.limits.equals(rhs.limits))));
    }

}
