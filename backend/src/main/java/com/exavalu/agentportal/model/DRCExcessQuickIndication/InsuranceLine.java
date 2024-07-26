
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
    "lineCd",
    "liabilityLimit",
    "towingAndLaborInd",
    "rentalReimbursementInd",
    "medicalPayments",
    "nonOwnedInd",
    "comprehensiveDed",
    "collisionDed",
    "nonOwnedAuto",
    "numberOfHiredVehicles",
    "coverages",
    "risks",
    "risk"
})
@Generated("jsonschema2pojo")
public class InsuranceLine {

    @JsonProperty("lineCd")
    private String lineCd;
    @JsonProperty("liabilityLimit")
    private String liabilityLimit;
	@JsonProperty("towingAndLaborInd")
    private String towingAndLaborInd;
    @JsonProperty("rentalReimbursementInd")
    private String rentalReimbursementInd;
    @JsonProperty("medicalPayments")
    private String medicalPayments;
    @JsonProperty("nonOwnedInd")
    private String nonOwnedInd;
    @JsonProperty("comprehensiveDed")
    private String comprehensiveDed;
    @JsonProperty("collisionDed")
    private String collisionDed;
    @JsonProperty("nonOwnedAuto")
    private String nonOwnedAuto;
    @JsonProperty("numberOfHiredVehicles")
    private String numberOfHiredVehicles;
	@JsonProperty("coverages")
    private List<Coverage> coverages = new ArrayList<Coverage>();
    @JsonProperty("risks")
    private List<Risk> risks = new ArrayList<Risk>();
    @JsonProperty("risk")
    private List<Risk> risk = new ArrayList<Risk>();

	@JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("lineCd")
    public String getLineCd() {
        return lineCd;
    }

    @JsonProperty("lineCd")
    public void setLineCd(String lineCd) {
        this.lineCd = lineCd;
    }

    public InsuranceLine withLineCd(String lineCd) {
        this.lineCd = lineCd;
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
  	
    public InsuranceLine withSetLiabilityLimit(String liabilityLimit) {
        this.liabilityLimit = liabilityLimit;
        return this;
    }
    
    @JsonProperty("towingAndLaborInd")
    public String getTowingAndLaborInd() {
		return towingAndLaborInd;
	}

    @JsonProperty("towingAndLaborInd")
	public void setTowingAndLaborInd(String towingAndLaborInd) {
		this.towingAndLaborInd = towingAndLaborInd;
	}
	
	public InsuranceLine withTowingAndLaborInd(String towingAndLaborInd) {
        this.towingAndLaborInd = towingAndLaborInd;
        return this;
    }

	@JsonProperty("rentalReimbursementInd")
	public String getRentalReimbursementInd() {
		return rentalReimbursementInd;
	}

	@JsonProperty("rentalReimbursementInd")
	public void setRentalReimbursementInd(String rentalReimbursementInd) {
		this.rentalReimbursementInd = rentalReimbursementInd;
	}

	public InsuranceLine withRentalReimbursementInd(String rentalReimbursementInd) {
        this.rentalReimbursementInd = rentalReimbursementInd;
        return this;
    }
	
	 @JsonProperty("medicalPayments")
	public String getMedicalPayments() {
		return medicalPayments;
	}

	 @JsonProperty("medicalPayments")
	public void setMedicalPayments(String medicalPayments) {
		this.medicalPayments = medicalPayments;
	}

	public InsuranceLine withMedicalPayments(String medicalPayments) {
        this.medicalPayments = medicalPayments;
        return this;
    }
	
	@JsonProperty("risk")
	public List<Risk> getRisk() {
		return risk;
	}

	@JsonProperty("risk")
	public void setRisk(List<Risk> risk) {
		this.risk = risk;
	}
	
	public InsuranceLine withRisk(List<Risk> risk) {
        this.risk = risk;
        return this;
    }
	
	@JsonProperty("nonOwnedInd")
	public String getNonOwnedInd() {
		return nonOwnedInd;
	}

	@JsonProperty("nonOwnedInd")
	public void setNonOwnedInd(String nonOwnedInd) {
		this.nonOwnedInd = nonOwnedInd;
	}

	public InsuranceLine withNonOwnedInd(String nonOwnedInd) {
        this.nonOwnedInd = nonOwnedInd;
        return this;
    }
	
	@JsonProperty("comprehensiveDed")
	public String getComprehensiveDed() {
		return comprehensiveDed;
	}

	@JsonProperty("comprehensiveDed")
	public void setComprehensiveDed(String comprehensiveDed) {
		this.comprehensiveDed = comprehensiveDed;
	}

	public InsuranceLine withComprehensiveDed(String comprehensiveDed) {
        this.comprehensiveDed = comprehensiveDed;
        return this;
    }
	
	@JsonProperty("collisionDed")
	public String getCollisionDed() {
		return collisionDed;
	}

	@JsonProperty("collisionDed")
	public void setCollisionDed(String collisionDed) {
		this.collisionDed = collisionDed;
	}

	public InsuranceLine withCollisionDed(String collisionDed) {
        this.collisionDed = collisionDed;
        return this;
    }
	
	@JsonProperty("nonOwnedAuto")
	public String getNonOwnedAuto() {
		return nonOwnedAuto;
	}
	
	@JsonProperty("nonOwnedAuto")
	public void setNonOwnedAuto(String nonOwnedAuto) {
		this.nonOwnedAuto = nonOwnedAuto;
	}

	public InsuranceLine withNonOwnedAuto(String nonOwnedAuto) {
        this.nonOwnedAuto = nonOwnedAuto;
        return this;
    }
	
	@JsonProperty("numberOfHiredVehicles")
	public String getNumberOfHiredVehicles() {
		return numberOfHiredVehicles;
	}

	@JsonProperty("numberOfHiredVehicles")
	public void setNumberOfHiredVehicles(String numberOfHiredVehicles) {
		this.numberOfHiredVehicles = numberOfHiredVehicles;
	}
	
	public InsuranceLine withNumberOfHiredVehicles(String numberOfHiredVehicles) {
        this.numberOfHiredVehicles = numberOfHiredVehicles;
        return this;
    }

	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}

    @JsonProperty("coverages")
    public List<Coverage> getCoverages() {
        return coverages;
    }

    @JsonProperty("coverages")
    public void setCoverages(List<Coverage> coverages) {
        this.coverages = coverages;
    }

    public InsuranceLine withCoverages(List<Coverage> coverages) {
        this.coverages = coverages;
        return this;
    }

    @JsonProperty("risks")
    public List<Risk> getRisks() {
        return risks;
    }

    @JsonProperty("risks")
    public void setRisks(List<Risk> risks) {
        this.risks = risks;
    }

    public InsuranceLine withRisks(List<Risk> risks) {
        this.risks = risks;
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

    public InsuranceLine withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(InsuranceLine.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("lineCd");
        sb.append('=');
        sb.append(((this.lineCd == null)?"<null>":this.lineCd));
        sb.append(',');
        sb.append("liabilityLimit");
        sb.append('=');
        sb.append(((this.liabilityLimit == null)?"<null>":this.liabilityLimit));
        sb.append(',');
        sb.append("towingAndLaborInd");
        sb.append('=');
        sb.append(((this.towingAndLaborInd == null)?"<null>":this.towingAndLaborInd));
        sb.append(',');
        sb.append("rentalReimbursementInd");
        sb.append('=');
        sb.append(((this.rentalReimbursementInd == null)?"<null>":this.rentalReimbursementInd));
        sb.append(',');
        sb.append("medicalPayments");
        sb.append('=');
        sb.append(((this.medicalPayments == null)?"<null>":this.medicalPayments));
        sb.append(',');
        sb.append("risk");
        sb.append('=');
        sb.append(((this.risk == null)?"<null>":this.risk));
        sb.append(',');
        sb.append("nonOwnedInd");
        sb.append('=');
        sb.append(((this.nonOwnedInd == null)?"<null>":this.nonOwnedInd));
        sb.append(',');
        sb.append("comprehensiveDed");
        sb.append('=');
        sb.append(((this.comprehensiveDed == null)?"<null>":this.comprehensiveDed));
        sb.append(',');
        sb.append("collisionDed");
        sb.append('=');
        sb.append(((this.collisionDed == null)?"<null>":this.collisionDed));
        sb.append(',');
        sb.append("nonOwnedAuto");
        sb.append('=');
        sb.append(((this.nonOwnedAuto == null)?"<null>":this.nonOwnedAuto));
        sb.append(',');
        sb.append("numberOfHiredVehicles");
        sb.append('=');
        sb.append(((this.numberOfHiredVehicles == null)?"<null>":this.numberOfHiredVehicles));
        sb.append(',');
        sb.append("coverages");
        sb.append('=');
        sb.append(((this.coverages == null)?"<null>":this.coverages));
        sb.append(',');
        sb.append("risks");
        sb.append('=');
        sb.append(((this.risks == null)?"<null>":this.risks));
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
        result = ((result* 31)+((this.lineCd == null)? 0 :this.lineCd.hashCode()));
        result = ((result* 31)+((this.liabilityLimit == null)? 0 :this.liabilityLimit.hashCode()));
        result = ((result* 31)+((this.towingAndLaborInd == null)? 0 :this.towingAndLaborInd.hashCode()));
        result = ((result* 31)+((this.rentalReimbursementInd == null)? 0 :this.rentalReimbursementInd.hashCode()));
        result = ((result* 31)+((this.medicalPayments == null)? 0 :this.medicalPayments.hashCode()));
        result = ((result* 31)+((this.nonOwnedInd == null)? 0 :this.nonOwnedInd.hashCode()));
        result = ((result* 31)+((this.comprehensiveDed == null)? 0 :this.comprehensiveDed.hashCode()));
        result = ((result* 31)+((this.collisionDed == null)? 0 :this.collisionDed.hashCode()));
        result = ((result* 31)+((this.nonOwnedAuto == null)? 0 :this.nonOwnedAuto.hashCode()));
        result = ((result* 31)+((this.numberOfHiredVehicles == null)? 0 :this.numberOfHiredVehicles.hashCode()));
        result = ((result* 31)+((this.risks == null)? 0 :this.risks.hashCode()));
        result = ((result* 31)+((this.risk == null)? 0 :this.risk.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.coverages == null)? 0 :this.coverages.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof InsuranceLine) == false) {
            return false;
        }
        InsuranceLine rhs = ((InsuranceLine) other);
        return (((((this.lineCd == rhs.lineCd)||((this.lineCd!= null)&&this.lineCd.equals(rhs.lineCd)))&&
        		(((this.liabilityLimit == rhs.liabilityLimit)||((this.liabilityLimit!= null)&&this.liabilityLimit.equals(rhs.liabilityLimit)))&&
        		((this.towingAndLaborInd == rhs.towingAndLaborInd)||((this.towingAndLaborInd!= null)&&this.towingAndLaborInd.equals(rhs.towingAndLaborInd))&&
        		((this.rentalReimbursementInd == rhs.rentalReimbursementInd)||((this.rentalReimbursementInd!= null)&&this.rentalReimbursementInd.equals(rhs.rentalReimbursementInd))&&
        		((this.medicalPayments == rhs.medicalPayments)||((this.medicalPayments!= null)&&this.medicalPayments.equals(rhs.medicalPayments))&&
        		((this.nonOwnedInd == rhs.nonOwnedInd)||((this.nonOwnedInd!= null)&&this.nonOwnedInd.equals(rhs.nonOwnedInd))&&
        		((this.comprehensiveDed == rhs.comprehensiveDed)||((this.comprehensiveDed!= null)&&this.comprehensiveDed.equals(rhs.comprehensiveDed))&&
        		((this.collisionDed == rhs.collisionDed)||((this.collisionDed!= null)&&this.collisionDed.equals(rhs.collisionDed))&&
        		((this.nonOwnedAuto == rhs.nonOwnedAuto)||((this.nonOwnedAuto!= null)&&this.nonOwnedAuto.equals(rhs.nonOwnedAuto))&&
        		((this.numberOfHiredVehicles == rhs.numberOfHiredVehicles)||((this.numberOfHiredVehicles!= null)&&this.numberOfHiredVehicles.equals(rhs.numberOfHiredVehicles))&&
        		((this.risks == rhs.risks)||((this.risks!= null)&&this.risks.equals(rhs.risk))))&&
        		((this.risk == rhs.risk)||((this.risk!= null)&&this.risk.equals(rhs.risk)))&&
        		((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))&&
        		((this.coverages == rhs.coverages)||((this.coverages!= null)&&this.coverages.equals(rhs.coverages)))))))))))))));
    }

}
