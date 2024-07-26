
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
    "policyType",
//    "productRef",
    "effectiveDt",
    "expirationDt",
    "territoryCd",
    "controllingState",
    "extendedAttributes",
})
@Generated("jsonschema2pojo")
public class PolicyInfo {

    @JsonProperty("policyType")
    private String policyType;
    @JsonProperty("productRef")
    private String productRef;
    @JsonProperty("effectiveDt")
    private String effectiveDt;
    @JsonProperty("expirationDt")
    private String expirationDt;
    @JsonProperty("territoryCd")
    private String territoryCd;
    @JsonProperty("controllingState")
    private String controllingState;
    @JsonProperty("QuoteType")
    private String QuoteType;
    @JsonProperty("TermType")
    private String TermType;
    public String getQuoteType() {
		return QuoteType;
	}

	public void setQuoteType(String quoteType) {
		QuoteType = quoteType;
	}

	public String getTermType() {
		return TermType;
	}

	public void setTermType(String termType) {
		TermType = termType;
	}

	@JsonProperty("extendedAttributes")
    private List<ExtendedAttribute> extendedAttributes = new ArrayList<ExtendedAttribute>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("policyType")
    public String getPolicyType() {
        return policyType;
    }

    @JsonProperty("policyType")
    public void setPolicyType(String policyType) {
        this.policyType = policyType;
    }

    public PolicyInfo withPolicyType(String policyType) {
        this.policyType = policyType;
        return this;
    }

    @JsonProperty("productRef")
    public String getProductRef() {
        return productRef;
    }

    @JsonProperty("productRef")
    public void setProductRef(String productRef) {
        this.productRef = productRef;
    }

    public PolicyInfo withProductRef(String productRef) {
        this.productRef = productRef;
        return this;
    }

    @JsonProperty("effectiveDt")
    public String getEffectiveDt() {
        return effectiveDt;
    }

    @JsonProperty("effectiveDt")
    public void setEffectiveDt(String effectiveDt) {
        this.effectiveDt = effectiveDt;
    }

    public PolicyInfo withEffectiveDt(String effectiveDt) {
        this.effectiveDt = effectiveDt;
        return this;
    }

    @JsonProperty("expirationDt")
    public String getExpirationDt() {
        return expirationDt;
    }

    @JsonProperty("expirationDt")
    public void setExpirationDt(String expirationDt) {
        this.expirationDt = expirationDt;
    }

    public PolicyInfo withExpirationDt(String expirationDt) {
        this.expirationDt = expirationDt;
        return this;
    }

    @JsonProperty("territoryCd")
    public String getTerritoryCd() {
        return territoryCd;
    }
    
    @JsonProperty("territoryCd")
    public void setTerritoryCd(String territoryCd) {
        this.territoryCd = territoryCd;
    }

     public PolicyInfo withTerritoryCd(String territoryCd) {
        this.territoryCd = territoryCd;
        return this;
    }

    @JsonProperty("controllingState")
    public String getControllingState() {
        return controllingState;
    }

    @JsonProperty("controllingState")
    public void setControllingState(String controllingState) {
        this.controllingState = controllingState;
    }

    public PolicyInfo withControllingState(String controllingState) {
        this.controllingState = controllingState;
        return this;
    }

    @JsonProperty("extendedAttributes")
    public List<ExtendedAttribute> getExtendedAttributes() {
        return extendedAttributes;
    }

    @JsonProperty("extendedAttributes")
    public void setExtendedAttributes(List<ExtendedAttribute> extendedAttributes) {
        this.extendedAttributes = extendedAttributes;
    }

    public PolicyInfo withExtendedAttributes(List<ExtendedAttribute> extendedAttributes) {
        this.extendedAttributes = extendedAttributes;
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

    public PolicyInfo withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(PolicyInfo.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("policyType");
        sb.append('=');
        sb.append(((this.policyType == null)?"<null>":this.policyType));
        sb.append(',');
        sb.append("productRef");
        sb.append('=');
        sb.append(((this.productRef == null)?"<null>":this.productRef));
        sb.append(',');
        sb.append("effectiveDt");
        sb.append('=');
        sb.append(((this.effectiveDt == null)?"<null>":this.effectiveDt));
        sb.append(',');
        sb.append("expirationDt");
        sb.append('=');
        sb.append(((this.expirationDt == null)?"<null>":this.expirationDt));
        sb.append(',');
        sb.append("territoryCd");
        sb.append('=');
        sb.append(((this.territoryCd == null)?"<null>":this.territoryCd));
        sb.append(',');
        sb.append("controllingState");
        sb.append('=');
        sb.append(((this.controllingState == null)?"<null>":this.controllingState));
        sb.append(',');
        sb.append("extendedAttributes");
        sb.append('=');
        sb.append(((this.extendedAttributes == null)?"<null>":this.extendedAttributes));
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
        result = ((result* 31)+((this.expirationDt == null)? 0 :this.expirationDt.hashCode()));
        result = ((result* 31)+((this.effectiveDt == null)? 0 :this.effectiveDt.hashCode()));
        result = ((result* 31)+((this.controllingState == null)? 0 :this.controllingState.hashCode()));
        result = ((result* 31)+((this.policyType == null)? 0 :this.policyType.hashCode()));
        result = ((result* 31)+((this.productRef == null)? 0 :this.productRef.hashCode()));
        result = ((result* 31)+((this.territoryCd == null)? 0 :this.territoryCd.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.extendedAttributes == null)? 0 :this.extendedAttributes.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PolicyInfo) == false) {
            return false;
        }
        PolicyInfo rhs = ((PolicyInfo) other);
        return ((((((((this.expirationDt == rhs.expirationDt)||((this.expirationDt!= null)&&this.expirationDt.equals(rhs.expirationDt)))&&((this.effectiveDt == rhs.effectiveDt)||((this.effectiveDt!= null)&&this.effectiveDt.equals(rhs.effectiveDt))))&&((this.controllingState == rhs.controllingState)||((this.controllingState!= null)&&this.controllingState.equals(rhs.controllingState))))&&((this.policyType == rhs.policyType)||((this.policyType!= null)&&this.policyType.equals(rhs.policyType))))&&((this.productRef == rhs.productRef)||((this.productRef!= null)&&this.productRef.equals(rhs.productRef))))&&((this.territoryCd == rhs.territoryCd)||((this.territoryCd!= null)&&this.territoryCd.equals(rhs.territoryCd)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.extendedAttributes == rhs.extendedAttributes)||((this.extendedAttributes!= null)&&this.extendedAttributes.equals(rhs.extendedAttributes))));
    }

}
