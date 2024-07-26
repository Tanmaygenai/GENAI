
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
    "deductibles",
    "desc"
})
@Generated("jsonschema2pojo")
public class Coverage {

    @JsonProperty("coverageCd")
    private String coverageCd;
    @JsonProperty("desc")
    private String desc;
	@JsonProperty("limits")
    private List<Limit> limits = new ArrayList<Limit>();
    @JsonProperty("deductibles")
    private List<Deductible> deductibles = new ArrayList<Deductible>();
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

    public Coverage withCoverageCd(String coverageCd) {
        this.coverageCd = coverageCd;
        return this;
    }

    @JsonProperty("limits")
    public List<Limit> getLimits() {
        return limits;
    }

    @JsonProperty("limits")
    public void setLimits(List<Limit> limits) {
        this.limits = limits;
    }

    public Coverage withLimits(List<Limit> limits) {
        this.limits = limits;
        return this;
    }

    @JsonProperty("deductibles")
    public List<Deductible> getDeductibles() {
        return deductibles;
    }

    @JsonProperty("deductibles")
    public void setDeductibles(List<Deductible> deductibles) {
        this.deductibles = deductibles;
    }

    public Coverage withDeductibles(List<Deductible> deductibles) {
        this.deductibles = deductibles;
        return this;
    }
    
    @JsonProperty("desc")
    public String getDesc() {
		return desc;
	}

    @JsonProperty("desc")
	public void setDesc(String desc) {
		this.desc = desc;
	}
   
    public Coverage withDesc(String desc) {
        this.desc = desc;
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

    public Coverage withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Coverage.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("coverageCd");
        sb.append('=');
        sb.append(((this.coverageCd == null)?"<null>":this.coverageCd));
        sb.append(',');
        sb.append("limits");
        sb.append('=');
        sb.append(((this.limits == null)?"<null>":this.limits));
        sb.append(',');
        sb.append("desc");
        sb.append('=');
        sb.append(((this.desc == null)?"<null>":this.desc));
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
        result = ((result* 31)+((this.desc == null)? 0 :this.desc.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.limits == null)? 0 :this.limits.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Coverage) == false) {
            return false;
        }
        Coverage rhs = ((Coverage) other);
        return (((((this.coverageCd == rhs.coverageCd)||((this.coverageCd!= null)&&this.coverageCd.equals(rhs.coverageCd)))&&((this.deductibles == rhs.deductibles)||((this.deductibles!= null)&&this.deductibles.equals(rhs.deductibles))))&&((this.desc == rhs.desc)||((this.desc!= null)&&this.desc.equals(rhs.desc)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.limits == rhs.limits)||((this.limits!= null)&&this.limits.equals(rhs.limits))));
    }

}
