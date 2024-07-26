
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
    "autoUnderLyingPolicy",
    "monolineUnderLyingInsurance",
    "excessUnderLyingInsurance",
    "otherUnderLyingInsurance"
})
@Generated("jsonschema2pojo")
public class UnderlyingInsurance {

    @JsonProperty("autoUnderLyingPolicy")
    private List<AutoUnderLyingPolicy> autoUnderLyingPolicy = new ArrayList<AutoUnderLyingPolicy>();
	@JsonProperty("monolineUnderLyingInsurance")
    private List<MonolineUnderLyingInsurance> monolineUnderLyingInsurance = new ArrayList<MonolineUnderLyingInsurance>();
    @JsonProperty("excessUnderLyingInsurance")
    private List<ExcessUnderLyingInsurance> excessUnderLyingInsurance = new ArrayList<ExcessUnderLyingInsurance>();
    @JsonProperty("otherUnderLyingInsurance")
    private List<OtherUnderLyingInsurance> otherUnderLyingInsurance = new ArrayList<OtherUnderLyingInsurance>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("autoUnderLyingPolicy")
    public List<AutoUnderLyingPolicy> getAutoUnderLyingPolicy() {
		return autoUnderLyingPolicy;
	}

    @JsonProperty("autoUnderLyingPolicy")
	public void setAutoUnderLyingPolicy(List<AutoUnderLyingPolicy> autoUnderLyingPolicy) {
		this.autoUnderLyingPolicy = autoUnderLyingPolicy;
	}

    public UnderlyingInsurance withAutoUnderLyingPolicy(List<AutoUnderLyingPolicy> autoUnderLyingPolicy) {
        this.autoUnderLyingPolicy = autoUnderLyingPolicy;
        return this;
    }
    
    @JsonProperty("monolineUnderLyingInsurance")
	public List<MonolineUnderLyingInsurance> getMonolineUnderLyingInsurance() {
		return monolineUnderLyingInsurance;
	}

    @JsonProperty("monolineUnderLyingInsurance")
	public void setMonolineUnderLyingInsurance(List<MonolineUnderLyingInsurance> monolineUnderLyingInsurance) {
		this.monolineUnderLyingInsurance = monolineUnderLyingInsurance;
	}

    public UnderlyingInsurance withMonolineUnderLyingInsurance(List<MonolineUnderLyingInsurance> monolineUnderLyingInsurance) {
        this.monolineUnderLyingInsurance = monolineUnderLyingInsurance;
        return this;
    }

    @JsonProperty("excessUnderLyingInsurance")
	public List<ExcessUnderLyingInsurance> getExcessUnderLyingInsurance() {
		return excessUnderLyingInsurance;
	}

    @JsonProperty("excessUnderLyingInsurance")
	public void setExcessUnderLyingInsurance(List<ExcessUnderLyingInsurance> excessUnderLyingInsurance) {
		this.excessUnderLyingInsurance = excessUnderLyingInsurance;
	}

    public UnderlyingInsurance withExcessUnderLyingInsurance(List<ExcessUnderLyingInsurance> excessUnderLyingInsurance) {
        this.excessUnderLyingInsurance = excessUnderLyingInsurance;
        return this;
    }

    @JsonProperty("otherUnderLyingInsurance")
	public List<OtherUnderLyingInsurance> getOtherUnderLyingInsurance() {
		return otherUnderLyingInsurance;
	}

    @JsonProperty("otherUnderLyingInsurance")
	public void setOtherUnderLyingInsurance(List<OtherUnderLyingInsurance> otherUnderLyingInsurance) {
		this.otherUnderLyingInsurance = otherUnderLyingInsurance;
	}

    public UnderlyingInsurance withOtherUnderLyingInsurance(List<OtherUnderLyingInsurance> otherUnderLyingInsurance) {
        this.otherUnderLyingInsurance = otherUnderLyingInsurance;
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

    public UnderlyingInsurance withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(UnderlyingInsurance.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("autoUnderLyingPolicy");
        sb.append('=');
        sb.append(((this.autoUnderLyingPolicy == null)?"<null>":this.autoUnderLyingPolicy));
        sb.append(',');
        sb.append("monolineUnderLyingInsurance");
        sb.append('=');
        sb.append(((this.monolineUnderLyingInsurance == null)?"<null>":this.monolineUnderLyingInsurance));
        sb.append(',');
        sb.append("excessUnderLyingInsurance");
        sb.append('=');
        sb.append(((this.excessUnderLyingInsurance == null)?"<null>":this.excessUnderLyingInsurance));
        sb.append(',');
        sb.append("otherUnderLyingInsurance");
        sb.append('=');
        sb.append(((this.otherUnderLyingInsurance == null)?"<null>":this.otherUnderLyingInsurance));
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
        result = ((result* 31)+((this.excessUnderLyingInsurance == null)? 0 :this.excessUnderLyingInsurance.hashCode()));
        result = ((result* 31)+((this.monolineUnderLyingInsurance == null)? 0 :this.monolineUnderLyingInsurance.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.autoUnderLyingPolicy == null)? 0 :this.autoUnderLyingPolicy.hashCode()));
        result = ((result* 31)+((this.otherUnderLyingInsurance == null)? 0 :this.otherUnderLyingInsurance.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof UnderlyingInsurance) == false) {
            return false;
        }
        UnderlyingInsurance rhs = ((UnderlyingInsurance) other);
        return ((((((this.excessUnderLyingInsurance == rhs.excessUnderLyingInsurance)||((this.excessUnderLyingInsurance!= null)&&this.excessUnderLyingInsurance.equals(rhs.excessUnderLyingInsurance)))&&((this.monolineUnderLyingInsurance == rhs.monolineUnderLyingInsurance)||((this.monolineUnderLyingInsurance!= null)&&this.monolineUnderLyingInsurance.equals(rhs.monolineUnderLyingInsurance))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.autoUnderLyingPolicy == rhs.autoUnderLyingPolicy)||((this.autoUnderLyingPolicy!= null)&&this.autoUnderLyingPolicy.equals(rhs.autoUnderLyingPolicy))))&&((this.otherUnderLyingInsurance == rhs.otherUnderLyingInsurance)||((this.otherUnderLyingInsurance!= null)&&this.otherUnderLyingInsurance.equals(rhs.otherUnderLyingInsurance))));
    }

}
