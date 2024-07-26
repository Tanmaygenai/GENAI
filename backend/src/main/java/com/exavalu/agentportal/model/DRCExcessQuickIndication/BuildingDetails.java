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

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "riskTypeCd",
    "riskIdRef",
    "locationRef",
    "buildingDescription",
    "protectiveDevices",
    "sprinklered",
    "yearBuilt",
    "squareFootage",
    "estimatedReplacement",
    "constructionType",
    "roofType",
    "coverages"
})
public class BuildingDetails {
	
	@JsonProperty("riskTypeCd")
    private String riskTypeCd;
	@JsonProperty("riskIdRef")
    private String riskIdRef;
	@JsonProperty("locationRef")
    private String locationRef;
	@JsonProperty("buildingDescription")
    private String buildingDescription;
	@JsonProperty("protectiveDevices")
    private String protectiveDevices;
	@JsonProperty("sprinklered")
    private String sprinklered;
	@JsonProperty("yearBuilt")
    private String yearBuilt;
	@JsonProperty("squareFootage")
    private String squareFootage;
	@JsonProperty("estimatedReplacement")
    private String estimatedReplacement;
	@JsonProperty("constructionType")
    private String constructionType;
	@JsonProperty("roofType")
    private String roofType;
	@JsonProperty("coverages")
	private List<Coverage> coverages = new ArrayList<Coverage>();

	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("riskTypeCd")
	public String getRiskTypeCd() {
		return riskTypeCd;
	}
	
	@JsonProperty("riskTypeCd")
	public void setRiskTypeCd(String riskTypeCd) {
		this.riskTypeCd = riskTypeCd;
	}
	
	public BuildingDetails withRiskTypeCd(String riskTypeCd) {
	    this.riskTypeCd = riskTypeCd;
	    return this;
	}
	
	@JsonProperty("riskIdRef")
	public String getRiskIdRef() {
		return riskIdRef;
	}
	
	@JsonProperty("riskIdRef")
	public void setRiskIdRef(String riskIdRef) {
		this.riskIdRef = riskIdRef;
	}
	
	public BuildingDetails withRiskIdRef(String riskIdRef) {
	    this.riskIdRef = riskIdRef;
	    return this;
	}
	
	@JsonProperty("locationRef")
	public String getLocationRef() {
		return locationRef;
	}

	@JsonProperty("locationRef")
	public void setLocationRef(String locationRef) {
		this.locationRef = locationRef;
	}
	
	public BuildingDetails withLocationRef(String locationRef) {
	    this.locationRef = locationRef;
	    return this;
	}
	
	@JsonProperty("buildingDescription")
	public String getBuildingDescription() {
		return buildingDescription;
	}
	
	@JsonProperty("buildingDescription")
	public void setBuildingDescription(String buildingDescription) {
		this.buildingDescription = buildingDescription;
	}
	
	public BuildingDetails withBuildingDescription(String buildingDescription) {
	    this.buildingDescription = buildingDescription;
	    return this;
	}
	
	@JsonProperty("protectiveDevices")
	public String getProtectiveDevices() {
		return protectiveDevices;
	}
	
	@JsonProperty("protectiveDevices")
	public void setProtectiveDevices(String protectiveDevices) {
		this.protectiveDevices = protectiveDevices;
	}
	
	public BuildingDetails withProtectiveDevices(String protectiveDevices) {
	    this.protectiveDevices = protectiveDevices;
	    return this;
	}
	
	@JsonProperty("sprinklered")
	public String getSprinklered() {
		return sprinklered;
	}
	
	@JsonProperty("sprinklered")
	public void setSprinklered(String sprinklered) {
		this.sprinklered = sprinklered;
	}
	
	public BuildingDetails withSprinklered(String sprinklered) {
	    this.sprinklered = sprinklered;
	    return this;
	}
	
	@JsonProperty("yearBuilt")
	public String getYearBuilt() {
		return yearBuilt;
	}
	
	@JsonProperty("yearBuilt")
	public void setYearBuilt(String yearBuilt) {
		this.yearBuilt = yearBuilt;
	}
	
	public BuildingDetails withYearBuilt(String yearBuilt) {
	    this.yearBuilt = yearBuilt;
	    return this;
	}
	
	@JsonProperty("squareFootage")
	public String getSquareFootage() {
		return squareFootage;
	}
	
	@JsonProperty("squareFootage")
	public void setSquareFootage(String squareFootage) {
		this.squareFootage = squareFootage;
	}
	
	public BuildingDetails withSquareFootage(String squareFootage) {
	    this.squareFootage = squareFootage;
	    return this;
	}
	
	@JsonProperty("estimatedReplacement")
	public String getEstimatedReplacement() {
		return estimatedReplacement;
	}
	
	@JsonProperty("estimatedReplacement")
	public void setEstimatedReplacement(String estimatedReplacement) {
		this.estimatedReplacement = estimatedReplacement;
	}
	
	public BuildingDetails withEstimatedReplacement(String estimatedReplacement) {
	    this.estimatedReplacement = estimatedReplacement;
	    return this;
	}
	
	@JsonProperty("constructionType")
	public String getConstructionType() {
		return constructionType;
	}
	
	@JsonProperty("constructionType")
	public void setConstructionType(String constructionType) {
		this.constructionType = constructionType;
	}
	
	public BuildingDetails withConstructionType(String constructionType) {
	    this.constructionType = constructionType;
	    return this;
	}
	
	@JsonProperty("roofType")
	public String getRoofType() {
		return roofType;
	}
	
	@JsonProperty("roofType")
	public void setRoofType(String roofType) {
		this.roofType = roofType;
	}
	
	public BuildingDetails withRoofType(String roofType) {
	    this.roofType = roofType;
	    return this;
	}
	 
	public List<Coverage> getCoverages() {
		return coverages;
	}

	@JsonProperty("coverages")
	public void setCoverages(List<Coverage> coverages) {
		this.coverages = coverages;
	}

	@JsonProperty("coverages")
	public BuildingDetails withCoverages(List<Coverage> coverages) {
	    this.coverages = coverages;
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

    public BuildingDetails withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(BuildingDetails.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("riskTypeCd");
        sb.append('=');
        sb.append(((this.riskTypeCd == null)?"<null>":this.riskTypeCd));
        sb.append(',');
        sb.append("riskIdRef");
        sb.append('=');
        sb.append(((this.riskIdRef == null)?"<null>":this.riskIdRef));
        sb.append(',');
        sb.append("locationRef");
        sb.append('=');
        sb.append(((this.locationRef == null)?"<null>":this.locationRef));
        sb.append(',');
        sb.append("buildingDescription");
        sb.append('=');
        sb.append(((this.buildingDescription == null)?"<null>":this.buildingDescription));
        sb.append(',');
        sb.append("protectiveDevices");
        sb.append('=');
        sb.append(((this.protectiveDevices == null)?"<null>":this.protectiveDevices));
        sb.append(',');
        sb.append("sprinklered");
        sb.append('=');
        sb.append(((this.sprinklered == null)?"<null>":this.sprinklered));
        sb.append(',');
        sb.append("yearBuilt");
        sb.append('=');
        sb.append(((this.yearBuilt == null)?"<null>":this.yearBuilt));
        sb.append(',');
        sb.append("squareFootage");
        sb.append('=');
        sb.append(((this.squareFootage == null)?"<null>":this.squareFootage));
        sb.append(',');
        sb.append("estimatedReplacement");
        sb.append('=');
        sb.append(((this.estimatedReplacement == null)?"<null>":this.estimatedReplacement));
        sb.append(',');
        sb.append("constructionType");
        sb.append('=');
        sb.append(((this.constructionType == null)?"<null>":this.constructionType));
        sb.append(',');
        sb.append("roofType");
        sb.append('=');
        sb.append(((this.roofType == null)?"<null>":this.roofType));
        sb.append(',');
        sb.append("coverages");
        sb.append('=');
        sb.append(((this.coverages == null)?"<null>":this.coverages));
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
        result = ((result* 31)+((this.riskTypeCd == null)? 0 :this.riskTypeCd.hashCode()));
        result = ((result* 31)+((this.riskIdRef == null)? 0 :this.riskIdRef.hashCode()));
        result = ((result* 31)+((this.locationRef == null)? 0 :this.locationRef.hashCode()));
        result = ((result* 31)+((this.buildingDescription == null)? 0 :this.buildingDescription.hashCode()));
        result = ((result* 31)+((this.protectiveDevices == null)? 0 :this.protectiveDevices.hashCode()));
        result = ((result* 31)+((this.sprinklered == null)? 0 :this.sprinklered.hashCode()));
        result = ((result* 31)+((this.yearBuilt == null)? 0 :this.yearBuilt.hashCode()));
        result = ((result* 31)+((this.squareFootage == null)? 0 :this.squareFootage.hashCode()));
        result = ((result* 31)+((this.estimatedReplacement == null)? 0 :this.estimatedReplacement.hashCode()));
        result = ((result* 31)+((this.constructionType == null)? 0 :this.constructionType.hashCode()));
        result = ((result* 31)+((this.roofType == null)? 0 :this.roofType.hashCode()));
        result = ((result* 31)+((this.coverages == null)? 0 :this.coverages.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }
    
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof BuildingDetails) == false) {
            return false;
        }
        BuildingDetails rhs = ((BuildingDetails) other);
        return (((((this.riskTypeCd == rhs.riskTypeCd)||((this.riskTypeCd!= null)&&this.riskTypeCd.equals(rhs.riskTypeCd)))&&
        		((this.riskIdRef == rhs.riskIdRef)||((this.riskIdRef!= null)&&this.riskIdRef.equals(rhs.riskIdRef))))&&
        		((this.locationRef == rhs.locationRef)||((this.locationRef!= null)&&this.locationRef.equals(rhs.locationRef))))&&
        		((this.buildingDescription == rhs.buildingDescription)||((this.buildingDescription!= null)&&this.buildingDescription.equals(rhs.buildingDescription))))&&
        		((this.protectiveDevices == rhs.protectiveDevices)||((this.protectiveDevices!= null)&&this.protectiveDevices.equals(rhs.protectiveDevices))&&
        		((this.sprinklered == rhs.sprinklered)||((this.sprinklered!= null)&&this.sprinklered.equals(rhs.sprinklered))&&
        		((this.yearBuilt == rhs.yearBuilt)||((this.yearBuilt!= null)&&this.yearBuilt.equals(rhs.yearBuilt))&&
        		((this.squareFootage == rhs.squareFootage)||((this.squareFootage!= null)&&this.squareFootage.equals(rhs.squareFootage))&&
        		((this.yearBuilt == rhs.yearBuilt)||((this.yearBuilt!= null)&&this.yearBuilt.equals(rhs.yearBuilt))&&
        		((this.estimatedReplacement == rhs.estimatedReplacement)||((this.estimatedReplacement!= null)&&this.estimatedReplacement.equals(rhs.estimatedReplacement))&&
        		((this.constructionType == rhs.constructionType)||((this.constructionType!= null)&&this.constructionType.equals(rhs.constructionType))&&
        		((this.roofType == rhs.roofType)||((this.roofType!= null)&&this.roofType.equals(rhs.roofType))&&
        		((this.coverages == rhs.coverages)||((this.coverages!= null)&&this.coverages.equals(rhs.coverages))&&
        		((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))
        		))))))))));
    }
}
