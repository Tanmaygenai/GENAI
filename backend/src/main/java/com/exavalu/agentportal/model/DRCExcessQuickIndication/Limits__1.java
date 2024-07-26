
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
    "eachOccLmt",
    "generalAggr",
    "autoLimit",
    "employeeBenefitLiabLimit"
})
@Generated("jsonschema2pojo")
public class Limits__1 {

    @JsonProperty("eachOccLmt")
    private String eachOccLmt;
    @JsonProperty("generalAggr")
    private String generalAggr;
    @JsonProperty("autoLimit")
    private String autoLimit;
    @JsonProperty("employeeBenefitLiabLimit")
    private String employeeBenefitLiabLimit;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("eachOccLmt")
    public String getEachOccLmt() {
        return eachOccLmt;
    }

    @JsonProperty("eachOccLmt")
    public void setEachOccLmt(String eachOccLmt) {
        this.eachOccLmt = eachOccLmt;
    }

    public Limits__1 withEachOccLmt(String eachOccLmt) {
        this.eachOccLmt = eachOccLmt;
        return this;
    }

    @JsonProperty("generalAggr")
    public String getGeneralAggr() {
        return generalAggr;
    }

    @JsonProperty("generalAggr")
    public void setGeneralAggr(String generalAggr) {
        this.generalAggr = generalAggr;
    }

    public Limits__1 withGeneralAggr(String generalAggr) {
        this.generalAggr = generalAggr;
        return this;
    }

    @JsonProperty("autoLimit")
    public String getAutoLimit() {
        return autoLimit;
    }

    @JsonProperty("autoLimit")
    public void setAutoLimit(String autoLimit) {
        this.autoLimit = autoLimit;
    }

    public Limits__1 withAutoLimit(String autoLimit) {
        this.autoLimit = autoLimit;
        return this;
    }

    @JsonProperty("employeeBenefitLiabLimit")
    public String getEmployeeBenefitLiabLimit() {
        return employeeBenefitLiabLimit;
    }

    @JsonProperty("employeeBenefitLiabLimit")
    public void setEmployeeBenefitLiabLimit(String employeeBenefitLiabLimit) {
        this.employeeBenefitLiabLimit = employeeBenefitLiabLimit;
    }

    public Limits__1 withEmployeeBenefitLiabLimit(String employeeBenefitLiabLimit) {
        this.employeeBenefitLiabLimit = employeeBenefitLiabLimit;
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

    public Limits__1 withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Limits__1 .class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("eachOccLmt");
        sb.append('=');
        sb.append(((this.eachOccLmt == null)?"<null>":this.eachOccLmt));
        sb.append(',');
        sb.append("generalAggr");
        sb.append('=');
        sb.append(((this.generalAggr == null)?"<null>":this.generalAggr));
        sb.append(',');
        sb.append("autoLimit");
        sb.append('=');
        sb.append(((this.autoLimit == null)?"<null>":this.autoLimit));
        sb.append(',');
        sb.append("employeeBenefitLiabLimit");
        sb.append('=');
        sb.append(((this.employeeBenefitLiabLimit == null)?"<null>":this.employeeBenefitLiabLimit));
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
        result = ((result* 31)+((this.generalAggr == null)? 0 :this.generalAggr.hashCode()));
        result = ((result* 31)+((this.autoLimit == null)? 0 :this.autoLimit.hashCode()));
        result = ((result* 31)+((this.eachOccLmt == null)? 0 :this.eachOccLmt.hashCode()));
        result = ((result* 31)+((this.employeeBenefitLiabLimit == null)? 0 :this.employeeBenefitLiabLimit.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Limits__1) == false) {
            return false;
        }
        Limits__1 rhs = ((Limits__1) other);
        return ((((((this.generalAggr == rhs.generalAggr)||((this.generalAggr!= null)&&this.generalAggr.equals(rhs.generalAggr)))&&((this.autoLimit == rhs.autoLimit)||((this.autoLimit!= null)&&this.autoLimit.equals(rhs.autoLimit))))&&((this.eachOccLmt == rhs.eachOccLmt)||((this.eachOccLmt!= null)&&this.eachOccLmt.equals(rhs.eachOccLmt))))&&((this.employeeBenefitLiabLimit == rhs.employeeBenefitLiabLimit)||((this.employeeBenefitLiabLimit!= null)&&this.employeeBenefitLiabLimit.equals(rhs.employeeBenefitLiabLimit))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}
