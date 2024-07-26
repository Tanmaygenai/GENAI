
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
    "fullTermAmount",
    "triaPremium",
    "policyFees",
    "liabilityLimit",
    "locator",
    "message",
    "workFlow",
    "policyData"
})
@Generated("jsonschema2pojo")
public class PolicyPremiumInfo {

    @JsonProperty("fullTermAmount")
    private Integer fullTermAmount;
    @JsonProperty("triaPremium")
    private Integer triaPremium;
    @JsonProperty("policyFees")
    private PolicyFees policyFees;
    @JsonProperty("liabilityLimit")
    private String liabilityLimit;
    @JsonProperty("locator")
    private String locator;
	@JsonProperty("message")
    private String message;
    public String getPolicyData() {
		return policyData;
	}

	public void setPolicyData(String policyData) {
		this.policyData = policyData;
	}

	@JsonProperty("workFlow")
    private String workFlow;
    @JsonProperty("policyData")
    private String policyData;
    
    public String getWorkFlow() {
  		return workFlow;
  	}

  	public void setWorkFlow(String workFlow) {
  		this.workFlow = workFlow;
  	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("fullTermAmount")
    public Integer getFullTermAmount() {
        return fullTermAmount;
    }

    @JsonProperty("fullTermAmount")
    public void setFullTermAmount(Integer fullTermAmount) {
        this.fullTermAmount = fullTermAmount;
    }

    public PolicyPremiumInfo withFullTermAmount(Integer fullTermAmount) {
        this.fullTermAmount = fullTermAmount;
        return this;
    }
    @JsonProperty("liabilityLimit")
    public String getLiabilityLimit() {
        return liabilityLimit;
    }
    @JsonProperty("liabilityLimit")
    public void setLiabilityLimit(String liabilityLimit) {
        this.liabilityLimit = liabilityLimit;
    }

    public PolicyPremiumInfo withTLiabilityLimit(String liabilityLimit) {
        this.liabilityLimit = liabilityLimit;
        return this;
    }

    @JsonProperty("triaPremium")
    public Integer getTriaPremium() {
        return triaPremium;
    }

    @JsonProperty("triaPremium")
    public void setTriaPremium(Integer triaPremium) {
        this.triaPremium = triaPremium;
    }

    public PolicyPremiumInfo withTriaPremium(Integer triaPremium) {
        this.triaPremium = triaPremium;
        return this;
    }

    @JsonProperty("policyFees")
    public PolicyFees getPolicyFees() {
        return policyFees;
    }

    @JsonProperty("policyFees")
    public void setPolicyFees(PolicyFees policyFees) {
        this.policyFees = policyFees;
    }

    public PolicyPremiumInfo withPolicyFees(PolicyFees policyFees) {
        this.policyFees = policyFees;
        return this;
    }

    @JsonProperty("locator")
    public String getLocator() {
  		return locator;
  	}

    @JsonProperty("locator")
  	public void setLocator(String locator) {
  		this.locator = locator;
  	}
  	
    public PolicyPremiumInfo withPolicyFees(String locator) {
        this.locator = locator;
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

    public PolicyPremiumInfo withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PolicyPremiumInfo [");
		if (fullTermAmount != null) {
			builder.append("fullTermAmount=");
			builder.append(fullTermAmount);
			builder.append(", ");
		}
		if (triaPremium != null) {
			builder.append("triaPremium=");
			builder.append(triaPremium);
			builder.append(", ");
		}
		if (policyFees != null) {
			builder.append("policyFees=");
			builder.append(policyFees);
			builder.append(", ");
		}
		if (liabilityLimit != null) {
			builder.append("liabilityLimit=");
			builder.append(liabilityLimit);
			builder.append(", ");
		}
		if (locator != null) {
			builder.append("locator=");
			builder.append(locator);
			builder.append(", ");
		}
		if (message != null) {
			builder.append("message=");
			builder.append(message);
			builder.append(", ");
		}
		if (workFlow != null) {
			builder.append("workFlow=");
			builder.append(workFlow);
			builder.append(", ");
		}
		if (policyData != null) {
			builder.append("policyData=");
			builder.append(policyData);
			builder.append(", ");
		}
		if (additionalProperties != null) {
			builder.append("additionalProperties=");
			builder.append(additionalProperties);
		}
		builder.append("]");
		return builder.toString();
	}

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((additionalProperties == null) ? 0 : additionalProperties.hashCode());
		result = prime * result + ((fullTermAmount == null) ? 0 : fullTermAmount.hashCode());
		result = prime * result + ((liabilityLimit == null) ? 0 : liabilityLimit.hashCode());
		result = prime * result + ((locator == null) ? 0 : locator.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((policyData == null) ? 0 : policyData.hashCode());
		result = prime * result + ((policyFees == null) ? 0 : policyFees.hashCode());
		result = prime * result + ((triaPremium == null) ? 0 : triaPremium.hashCode());
		result = prime * result + ((workFlow == null) ? 0 : workFlow.hashCode());
		return result;
	}

    @Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		PolicyPremiumInfo other = (PolicyPremiumInfo) obj;
		if (additionalProperties == null) {
			if (other.additionalProperties != null) {
				return false;
			}
		} else if (!additionalProperties.equals(other.additionalProperties)) {
			return false;
		}
		if (fullTermAmount == null) {
			if (other.fullTermAmount != null) {
				return false;
			}
		} else if (!fullTermAmount.equals(other.fullTermAmount)) {
			return false;
		}
		if (liabilityLimit == null) {
			if (other.liabilityLimit != null) {
				return false;
			}
		} else if (!liabilityLimit.equals(other.liabilityLimit)) {
			return false;
		}
		if (locator == null) {
			if (other.locator != null) {
				return false;
			}
		} else if (!locator.equals(other.locator)) {
			return false;
		}
		if (message == null) {
			if (other.message != null) {
				return false;
			}
		} else if (!message.equals(other.message)) {
			return false;
		}
		if (policyData == null) {
			if (other.policyData != null) {
				return false;
			}
		} else if (!policyData.equals(other.policyData)) {
			return false;
		}
		if (policyFees == null) {
			if (other.policyFees != null) {
				return false;
			}
		} else if (!policyFees.equals(other.policyFees)) {
			return false;
		}
		if (triaPremium == null) {
			if (other.triaPremium != null) {
				return false;
			}
		} else if (!triaPremium.equals(other.triaPremium)) {
			return false;
		}
		if (workFlow == null) {
			if (other.workFlow != null) {
				return false;
			}
		} else if (!workFlow.equals(other.workFlow)) {
			return false;
		}
		return true;
	}

}
