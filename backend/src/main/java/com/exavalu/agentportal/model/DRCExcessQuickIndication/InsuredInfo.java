
package com.exavalu.agentportal.model.DRCExcessQuickIndication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "entityType",
    "commercialName",
    "dba",
    "givenName",
    "surName",
    "addr",
    "primaryPhone",
    "secondaryPhone",
    "insuredPhoneNo",
    "emailAddress",
    "insuredEmail",
    "primaryBusinessInfo",
    "dob",
    "occupation",
    "maritalStatus",
    "gender"
})
@Generated("jsonschema2pojo")
public class InsuredInfo {

    @JsonProperty("entityType")
    private String entityType;
    @JsonProperty("commercialName")
    private String commercialName;
    @JsonProperty("dba")
    private String dba;
    @JsonProperty("givenName")
    private String givenName;
    @JsonProperty("surName")
    private String surName;
    @JsonProperty("addr")
    private List<Addr> addr = new ArrayList<Addr>();
    @JsonProperty("primaryBusinessInfo")
    private PrimaryBusinessInfo primaryBusinessInfo;
	@JsonProperty("primaryPhone")
    private String primaryPhone;
    @JsonProperty("secondaryPhone")
    private String secondaryPhone;
    @JsonProperty("emailAddress")
    private String emailAddress;
    @JsonProperty("insuredPhoneNo")
    private String insuredPhoneNo;
    @JsonProperty("insuredEmail")
    private String insuredEmail;
    @JsonProperty("organization")
    private String organization;
    @JsonProperty("producerCode")
    private String producerCode;
    @JsonProperty("dob")
    private String dob;
    @JsonProperty("occupation")
    private String occupation;
    @JsonProperty("maritalStatus")
    private String maritalStatus;
	@JsonProperty("gender")
    private String gender;
	
	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
    
    @JsonProperty("dob")
	public String getDob() {
		return dob;
	}

    @JsonProperty("dob")
	public void setDob(String dob) {
		this.dob = dob;
	}

    public InsuredInfo withDob(String dob) {
		this.dob = dob;
		return this;
	}
    
	@JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("entityType")
	public String getEntityType() {
		return entityType;
	}

	@JsonProperty("entityType")
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public InsuredInfo withEntityType(String entityType) {
		this.entityType = entityType;
		return this;
	}

	@JsonProperty("commercialName")
	public String getCommercialName() {
		return commercialName;
	}

	@JsonProperty("commercialName")
	public void setCommercialName(String commercialName) {
		this.commercialName = commercialName;
	}

	@JsonProperty("organization")
	public String getOrganization() {
		return organization;
	}

	@JsonProperty("organization")
	public void setOrganization(String organization) {
		this.organization = organization;
	}

	@JsonProperty("producerCode")
	public String getProducerCode() {
		return producerCode;
	}

	@JsonProperty("producerCode")
	public void setProducerCode(String producerCode) {
		this.producerCode = producerCode;
	}

	public InsuredInfo withCommercialName(String commercialName) {
		this.commercialName = commercialName;
		return this;
	}

	@JsonProperty("dba")
	public String getDba() {
		return dba;
	}

	@JsonProperty("dba")
	public void setDba(String dba) {
		this.dba = dba;
	}

	public InsuredInfo withDba(String dba) {
		this.dba = dba;
		return this;
	}

	@JsonProperty("givenName")
	public String getGivenName() {
		return givenName;
	}

	@JsonProperty("givenName")
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public InsuredInfo withGivenName(String givenName) {
		this.givenName = givenName;
		return this;
	}

	@JsonProperty("surName")
	public String getSurName() {
		return surName;
	}

	@JsonProperty("surName")
	public void setSurName(String surName) {
		this.surName = surName;
	}

	public InsuredInfo withSurName(String surName) {
		this.surName = surName;
		return this;
	}

	@JsonProperty("addr")
	public List<Addr> getAddr() {
		return addr;
	}

	@JsonProperty("addr")
	public void setAddr(List<Addr> addr) {
		this.addr = addr;
	}

	public InsuredInfo withAddr(List<Addr> addr) {
		this.addr = addr;
		return this;
	}

	@JsonProperty("primaryPhone")
	public String getPrimaryPhone() {
		return primaryPhone;
	}

	@JsonProperty("primaryPhone")
	public void setPrimaryPhone(String primaryPhone) {
		this.primaryPhone = primaryPhone;
	}

	public InsuredInfo withPrimaryPhone(String primaryPhone) {
		this.primaryPhone = primaryPhone;
		return this;
	}

    public InsuredInfo withEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }
    
    @JsonProperty("insuredPhoneNo")
    public String getInsuredPhoneNo() {
		return insuredPhoneNo;
	}

    @JsonProperty("insuredPhoneNo")
	public void setInsuredPhoneNo(String insuredPhoneNo) {
		this.insuredPhoneNo = insuredPhoneNo;
	}

    public InsuredInfo withInsuredPhoneNo(String insuredPhoneNo) {
        this.insuredPhoneNo = insuredPhoneNo;
        return this;
    }
    
    @JsonProperty("insuredEmail")
	public String getInsuredEmail() {
		return insuredEmail;
	}

    @JsonProperty("insuredEmail")
	public void setInsuredEmail(String insuredEmail) {
		this.insuredEmail = insuredEmail;
	}

    public InsuredInfo withInsuredEmail(String insuredEmail) {
        this.insuredEmail = insuredEmail;
        return this;
    }
    
    @JsonProperty("primaryBusinessInfo")
    public PrimaryBusinessInfo getPrimaryBusinessInfo() {
		return primaryBusinessInfo;
	}

    @JsonProperty("primaryBusinessInfo")
	public void setPrimaryBusinessInfo(PrimaryBusinessInfo primaryBusinessInfo) {
		this.primaryBusinessInfo = primaryBusinessInfo;
	}
    
    public InsuredInfo withPrimaryBusinessInfo(PrimaryBusinessInfo primaryBusinessInfo) {
        this.primaryBusinessInfo = primaryBusinessInfo;
        return this;
    }
	
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }
	@JsonProperty("secondaryPhone")
	public String getSecondaryPhone() {
		return secondaryPhone;
	}

	@JsonProperty("secondaryPhone")
	public void setSecondaryPhone(String secondaryPhone) {
		this.secondaryPhone = secondaryPhone;
	}

	public InsuredInfo withSecondaryPhone(String secondaryPhone) {
		this.secondaryPhone = secondaryPhone;
		return this;
	}

	@JsonProperty("emailAddress")
	public String getEmailAddress() {
		return emailAddress;
	}

    @Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InsuredInfo [");
		if (entityType != null) {
			builder.append("entityType=");
			builder.append(entityType);
			builder.append(", ");
		}
		if (commercialName != null) {
			builder.append("commercialName=");
			builder.append(commercialName);
			builder.append(", ");
		}
		if (dba != null) {
			builder.append("dba=");
			builder.append(dba);
			builder.append(", ");
		}
		if (givenName != null) {
			builder.append("givenName=");
			builder.append(givenName);
			builder.append(", ");
		}
		if (surName != null) {
			builder.append("surName=");
			builder.append(surName);
			builder.append(", ");
		}
		if (addr != null) {
			builder.append("addr=");
			builder.append(addr);
			builder.append(", ");
		}
		if (primaryBusinessInfo != null) {
			builder.append("primaryBusinessInfo=");
			builder.append(primaryBusinessInfo);
			builder.append(", ");
		}
		if (primaryPhone != null) {
			builder.append("primaryPhone=");
			builder.append(primaryPhone);
			builder.append(", ");
		}
		if (secondaryPhone != null) {
			builder.append("secondaryPhone=");
			builder.append(secondaryPhone);
			builder.append(", ");
		}
		if (emailAddress != null) {
			builder.append("emailAddress=");
			builder.append(emailAddress);
			builder.append(", ");
		}
		if (insuredPhoneNo != null) {
			builder.append("insuredPhoneNo=");
			builder.append(insuredPhoneNo);
			builder.append(", ");
		}
		if (insuredEmail != null) {
			builder.append("insuredEmail=");
			builder.append(insuredEmail);
			builder.append(", ");
		}
		if (organization != null) {
			builder.append("organization=");
			builder.append(organization);
			builder.append(", ");
		}
		if (producerCode != null) {
			builder.append("producerCode=");
			builder.append(producerCode);
			builder.append(", ");
		}
		if (dob != null) {
			builder.append("dob=");
			builder.append(dob);
			builder.append(", ");
		}
		if (occupation != null) {
			builder.append("occupation=");
			builder.append(occupation);
			builder.append(", ");
		}
		if (maritalStatus != null) {
			builder.append("maritalStatus=");
			builder.append(maritalStatus);
			builder.append(", ");
		}
		if (gender != null) {
			builder.append("gender=");
			builder.append(gender);
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
		result = prime * result + ((addr == null) ? 0 : addr.hashCode());
		result = prime * result + ((commercialName == null) ? 0 : commercialName.hashCode());
		result = prime * result + ((dba == null) ? 0 : dba.hashCode());
		result = prime * result + ((dob == null) ? 0 : dob.hashCode());
		result = prime * result + ((emailAddress == null) ? 0 : emailAddress.hashCode());
		result = prime * result + ((entityType == null) ? 0 : entityType.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((givenName == null) ? 0 : givenName.hashCode());
		result = prime * result + ((insuredEmail == null) ? 0 : insuredEmail.hashCode());
		result = prime * result + ((insuredPhoneNo == null) ? 0 : insuredPhoneNo.hashCode());
		result = prime * result + ((maritalStatus == null) ? 0 : maritalStatus.hashCode());
		result = prime * result + ((occupation == null) ? 0 : occupation.hashCode());
		result = prime * result + ((organization == null) ? 0 : organization.hashCode());
		result = prime * result + ((primaryBusinessInfo == null) ? 0 : primaryBusinessInfo.hashCode());
		result = prime * result + ((primaryPhone == null) ? 0 : primaryPhone.hashCode());
		result = prime * result + ((producerCode == null) ? 0 : producerCode.hashCode());
		result = prime * result + ((secondaryPhone == null) ? 0 : secondaryPhone.hashCode());
		result = prime * result + ((surName == null) ? 0 : surName.hashCode());
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
		InsuredInfo other = (InsuredInfo) obj;
		if (additionalProperties == null) {
			if (other.additionalProperties != null) {
				return false;
			}
		} else if (!additionalProperties.equals(other.additionalProperties)) {
			return false;
		}
		if (addr == null) {
			if (other.addr != null) {
				return false;
			}
		} else if (!addr.equals(other.addr)) {
			return false;
		}
		if (commercialName == null) {
			if (other.commercialName != null) {
				return false;
			}
		} else if (!commercialName.equals(other.commercialName)) {
			return false;
		}
		if (dba == null) {
			if (other.dba != null) {
				return false;
			}
		} else if (!dba.equals(other.dba)) {
			return false;
		}
		if (dob == null) {
			if (other.dob != null) {
				return false;
			}
		} else if (!dob.equals(other.dob)) {
			return false;
		}
		if (emailAddress == null) {
			if (other.emailAddress != null) {
				return false;
			}
		} else if (!emailAddress.equals(other.emailAddress)) {
			return false;
		}
		if (entityType == null) {
			if (other.entityType != null) {
				return false;
			}
		} else if (!entityType.equals(other.entityType)) {
			return false;
		}
		if (gender == null) {
			if (other.gender != null) {
				return false;
			}
		} else if (!gender.equals(other.gender)) {
			return false;
		}
		if (givenName == null) {
			if (other.givenName != null) {
				return false;
			}
		} else if (!givenName.equals(other.givenName)) {
			return false;
		}
		if (insuredEmail == null) {
			if (other.insuredEmail != null) {
				return false;
			}
		} else if (!insuredEmail.equals(other.insuredEmail)) {
			return false;
		}
		if (insuredPhoneNo == null) {
			if (other.insuredPhoneNo != null) {
				return false;
			}
		} else if (!insuredPhoneNo.equals(other.insuredPhoneNo)) {
			return false;
		}
		if (maritalStatus == null) {
			if (other.maritalStatus != null) {
				return false;
			}
		} else if (!maritalStatus.equals(other.maritalStatus)) {
			return false;
		}
		if (occupation == null) {
			if (other.occupation != null) {
				return false;
			}
		} else if (!occupation.equals(other.occupation)) {
			return false;
		}
		if (organization == null) {
			if (other.organization != null) {
				return false;
			}
		} else if (!organization.equals(other.organization)) {
			return false;
		}
		if (primaryBusinessInfo == null) {
			if (other.primaryBusinessInfo != null) {
				return false;
			}
		} else if (!primaryBusinessInfo.equals(other.primaryBusinessInfo)) {
			return false;
		}
		if (primaryPhone == null) {
			if (other.primaryPhone != null) {
				return false;
			}
		} else if (!primaryPhone.equals(other.primaryPhone)) {
			return false;
		}
		if (producerCode == null) {
			if (other.producerCode != null) {
				return false;
			}
		} else if (!producerCode.equals(other.producerCode)) {
			return false;
		}
		if (secondaryPhone == null) {
			if (other.secondaryPhone != null) {
				return false;
			}
		} else if (!secondaryPhone.equals(other.secondaryPhone)) {
			return false;
		}
		if (surName == null) {
			if (other.surName != null) {
				return false;
			}
		} else if (!surName.equals(other.surName)) {
			return false;
		}
		return true;
	}
	@JsonProperty("emailAddress")
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public InsuredInfo withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}
}
