
package com.exavalu.agentportal.model.GWINPolicyDownload;

import java.util.HashMap;
import javax.annotation.Generated;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "taxTypeCd"
})
@Generated("jsonschema2pojo")
public class TaxInfo {

    @JsonProperty("id")
    private String id;
    @JsonProperty("taxTypeCd")
    private String taxTypeCd;
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

    public TaxInfo withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("taxTypeCd")
    public String getTaxTypeCd() {
        return taxTypeCd;
    }

    @JsonProperty("taxTypeCd")
    public void setTaxTypeCd(String taxTypeCd) {
        this.taxTypeCd = taxTypeCd;
    }

    public TaxInfo withTaxTypeCd(String taxTypeCd) {
        this.taxTypeCd = taxTypeCd;
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

    public TaxInfo withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(TaxInfo.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("taxTypeCd");
        sb.append('=');
        sb.append(((this.taxTypeCd == null)?"<null>":this.taxTypeCd));
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
        result = ((result* 31)+((this.taxTypeCd == null)? 0 :this.taxTypeCd.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof TaxInfo) == false) {
            return false;
        }
        TaxInfo rhs = ((TaxInfo) other);
        return ((((this.taxTypeCd == rhs.taxTypeCd)||((this.taxTypeCd!= null)&&this.taxTypeCd.equals(rhs.taxTypeCd)))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}
