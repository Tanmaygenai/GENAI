
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
    "liabilityLimit",
    "underlyingInsurance"
})
@Generated("jsonschema2pojo")
public class CommlUnderLyingInsurance {

    @JsonProperty("liabilityLimit")
    private String liabilityLimit;
    @JsonProperty("underlyingInsurance")
    private List<UnderlyingInsurance> underlyingInsurance = new ArrayList<UnderlyingInsurance>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("liabilityLimit")
    public String getLiabilityLimit() {
        return liabilityLimit;
    }

    @JsonProperty("liabilityLimit")
    public void setLiabilityLimit(String liabilityLimit) {
        this.liabilityLimit = liabilityLimit;
    }

    public CommlUnderLyingInsurance withLiabilityLimit(String liabilityLimit) {
        this.liabilityLimit = liabilityLimit;
        return this;
    }

    @JsonProperty("underlyingInsurance")
    public List<UnderlyingInsurance> getUnderlyingInsurance() {
        return underlyingInsurance;
    }

    @JsonProperty("underlyingInsurance")
    public void setUnderlyingInsurance(List<UnderlyingInsurance> underlyingInsurance) {
        this.underlyingInsurance = underlyingInsurance;
    }

    public CommlUnderLyingInsurance withUnderlyingInsurance(List<UnderlyingInsurance> underlyingInsurance) {
        this.underlyingInsurance = underlyingInsurance;
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

    public CommlUnderLyingInsurance withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(CommlUnderLyingInsurance.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("liabilityLimit");
        sb.append('=');
        sb.append(((this.liabilityLimit == null)?"<null>":this.liabilityLimit));
        sb.append(',');
        sb.append("underlyingInsurance");
        sb.append('=');
        sb.append(((this.underlyingInsurance == null)?"<null>":this.underlyingInsurance));
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
        result = ((result* 31)+((this.underlyingInsurance == null)? 0 :this.underlyingInsurance.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.liabilityLimit == null)? 0 :this.liabilityLimit.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CommlUnderLyingInsurance) == false) {
            return false;
        }
        CommlUnderLyingInsurance rhs = ((CommlUnderLyingInsurance) other);
        return ((((this.underlyingInsurance == rhs.underlyingInsurance)||((this.underlyingInsurance!= null)&&this.underlyingInsurance.equals(rhs.underlyingInsurance)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.liabilityLimit == rhs.liabilityLimit)||((this.liabilityLimit!= null)&&this.liabilityLimit.equals(rhs.liabilityLimit))));
    }

}
