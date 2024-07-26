
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
    "prodAndComp",
    "generalAggr",
    "personalAndAdv"
})
@Generated("jsonschema2pojo")
public class Limits {

    @JsonProperty("eachOccLmt")
    private String eachOccLmt;
    @JsonProperty("prodAndComp")
    private String prodAndComp;
    @JsonProperty("generalAggr")
    private String generalAggr;
    @JsonProperty("personalAndAdv")
    private String personalAndAdv;
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

    public Limits withEachOccLmt(String eachOccLmt) {
        this.eachOccLmt = eachOccLmt;
        return this;
    }

    @JsonProperty("prodAndComp")
    public String getProdAndComp() {
        return prodAndComp;
    }

    @JsonProperty("prodAndComp")
    public void setProdAndComp(String prodAndComp) {
        this.prodAndComp = prodAndComp;
    }

    public Limits withProdAndComp(String prodAndComp) {
        this.prodAndComp = prodAndComp;
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

    public Limits withGeneralAggr(String generalAggr) {
        this.generalAggr = generalAggr;
        return this;
    }

    @JsonProperty("personalAndAdv")
    public String getPersonalAndAdv() {
        return personalAndAdv;
    }

    @JsonProperty("personalAndAdv")
    public void setPersonalAndAdv(String personalAndAdv) {
        this.personalAndAdv = personalAndAdv;
    }

    public Limits withPersonalAndAdv(String personalAndAdv) {
        this.personalAndAdv = personalAndAdv;
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

    public Limits withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Limits.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("eachOccLmt");
        sb.append('=');
        sb.append(((this.eachOccLmt == null)?"<null>":this.eachOccLmt));
        sb.append(',');
        sb.append("prodAndComp");
        sb.append('=');
        sb.append(((this.prodAndComp == null)?"<null>":this.prodAndComp));
        sb.append(',');
        sb.append("generalAggr");
        sb.append('=');
        sb.append(((this.generalAggr == null)?"<null>":this.generalAggr));
        sb.append(',');
        sb.append("personalAndAdv");
        sb.append('=');
        sb.append(((this.personalAndAdv == null)?"<null>":this.personalAndAdv));
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
        result = ((result* 31)+((this.prodAndComp == null)? 0 :this.prodAndComp.hashCode()));
        result = ((result* 31)+((this.generalAggr == null)? 0 :this.generalAggr.hashCode()));
        result = ((result* 31)+((this.personalAndAdv == null)? 0 :this.personalAndAdv.hashCode()));
        result = ((result* 31)+((this.eachOccLmt == null)? 0 :this.eachOccLmt.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Limits) == false) {
            return false;
        }
        Limits rhs = ((Limits) other);
        return ((((((this.prodAndComp == rhs.prodAndComp)||((this.prodAndComp!= null)&&this.prodAndComp.equals(rhs.prodAndComp)))&&((this.generalAggr == rhs.generalAggr)||((this.generalAggr!= null)&&this.generalAggr.equals(rhs.generalAggr))))&&((this.personalAndAdv == rhs.personalAndAdv)||((this.personalAndAdv!= null)&&this.personalAndAdv.equals(rhs.personalAndAdv))))&&((this.eachOccLmt == rhs.eachOccLmt)||((this.eachOccLmt!= null)&&this.eachOccLmt.equals(rhs.eachOccLmt))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}
