
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
    "riskTypeCd",
    "locationRef",
    "coverages",
    "vehicleInfo",
    "buildings",
    "expandedProperty",
    "propertyDeductible"
})
@Generated("jsonschema2pojo")
public class Risk {

    @JsonProperty("riskTypeCd")
    private String riskTypeCd;
    @JsonProperty("locationRef")
    private Integer locationRef;
    @JsonProperty("buildings")
    private List<BuildingDetails> buildings = new ArrayList<BuildingDetails>();;
	@JsonProperty("coverages")
    private List<Coverage__1> coverages = new ArrayList<Coverage__1>();
    @JsonProperty("vehicleInfo")
    private VehicleInfo vehicleInfo;
    @JsonProperty("expandedProperty")
    private String expandedProperty;
    @JsonProperty("propertyDeductible")
    private String propertyDeductible;

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

    public Risk withRiskTypeCd(String riskTypeCd) {
        this.riskTypeCd = riskTypeCd;
        return this;
    }

    @JsonProperty("buildings")
    public List<BuildingDetails> getBuildings() {
		return buildings;
	}

    @JsonProperty("buildings")
	public void setBuildings(List<BuildingDetails> buildings) {
		this.buildings = buildings;
	}
    
    public Risk withBuildings(List<BuildingDetails> buildings) {
	    this.buildings = buildings;
	    return this;
	}

    @JsonProperty("locationRef")
    public Integer getLocationRef() {
        return locationRef;
    }

    @JsonProperty("locationRef")
    public void setLocationRef(Integer locationRef) {
        this.locationRef = locationRef;
    }

    public Risk withLocationRef(Integer locationRef) {
        this.locationRef = locationRef;
        return this;
    }

    @JsonProperty("coverages")
    public List<Coverage__1> getCoverages() {
        return coverages;
    }

    @JsonProperty("coverages")
    public void setCoverages(List<Coverage__1> coverages) {
        this.coverages = coverages;
    }

    public Risk withCoverages(List<Coverage__1> coverages) {
        this.coverages = coverages;
        return this;
    }

    @JsonProperty("vehicleInfo")
    public VehicleInfo getVehicleInfo() {
        return vehicleInfo;
    }

    @JsonProperty("vehicleInfo")
    public void setVehicleInfo(VehicleInfo vehicleInfo) {
        this.vehicleInfo = vehicleInfo;
    }

    public Risk withVehicleInfo(VehicleInfo vehicleInfo) {
        this.vehicleInfo = vehicleInfo;
        return this;
    }

    @JsonProperty("expandedProperty")
    public String getExpandedProperty() {
		return expandedProperty;
	}

    @JsonProperty("expandedProperty")
	public void setExpandedProperty(String expandedProperty) {
		this.expandedProperty = expandedProperty;
	}

    public Risk withExpandedProperty(String expandedProperty) {
        this.expandedProperty = expandedProperty;
        return this;
    }

    @JsonProperty("propertyDeductible")
	public String getPropertyDeductible() {
		return propertyDeductible;
	}

    @JsonProperty("propertyDeductible")
	public void setPropertyDeductible(String propertyDeductible) {
		this.propertyDeductible = propertyDeductible;
	}
	
    public Risk withPropertyDeductible(String propertyDeductible) {
        this.propertyDeductible = propertyDeductible;
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

    public Risk withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Risk.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("riskTypeCd");
        sb.append('=');
        sb.append(((this.riskTypeCd == null)?"<null>":this.riskTypeCd));
        sb.append(',');
        sb.append("locationRef");
        sb.append('=');
        sb.append(((this.locationRef == null)?"<null>":this.locationRef));
        sb.append(',');
        sb.append("buildings");
        sb.append('=');
        sb.append(((this.buildings == null)?"<null>":this.buildings));
        sb.append(',');
        sb.append("expandedProperty");
        sb.append('=');
        sb.append(((this.expandedProperty == null)?"<null>":this.expandedProperty));
        sb.append(',');
        sb.append("propertyDeductible");
        sb.append('=');
        sb.append(((this.propertyDeductible == null)?"<null>":this.propertyDeductible));
        sb.append(',');
        sb.append("coverages");
        sb.append('=');
        sb.append(((this.coverages == null)?"<null>":this.coverages));
        sb.append(',');
        sb.append("vehicleInfo");
        sb.append('=');
        sb.append(((this.vehicleInfo == null)?"<null>":this.vehicleInfo));
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
        result = ((result* 31)+((this.vehicleInfo == null)? 0 :this.vehicleInfo.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.locationRef == null)? 0 :this.locationRef.hashCode()));
        result = ((result* 31)+((this.buildings == null)? 0 :this.buildings.hashCode()));
        result = ((result* 31)+((this.coverages == null)? 0 :this.coverages.hashCode()));
        result = ((result* 31)+((this.expandedProperty == null)? 0 :this.expandedProperty.hashCode()));
        result = ((result* 31)+((this.propertyDeductible == null)? 0 :this.propertyDeductible.hashCode()));
        result = ((result* 31)+((this.riskTypeCd == null)? 0 :this.riskTypeCd.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Risk) == false) {
            return false;
        }
        Risk rhs = ((Risk) other);
        return ((((((this.vehicleInfo == rhs.vehicleInfo)||((this.vehicleInfo!= null)&&this.vehicleInfo.equals(rhs.vehicleInfo)))&&
        		((this.buildings == rhs.buildings)||((this.buildings!= null)&&this.buildings.equals(rhs.buildings)))&&
        		((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&
        		((this.locationRef == rhs.locationRef)||((this.locationRef!= null)&&this.locationRef.equals(rhs.locationRef))))&&
        		((this.coverages == rhs.coverages)||((this.coverages!= null)&&this.coverages.equals(rhs.coverages))))&&
        		((this.riskTypeCd == rhs.riskTypeCd)||((this.riskTypeCd!= null)&&this.riskTypeCd.equals(rhs.riskTypeCd))&&
        		((this.propertyDeductible == rhs.propertyDeductible)||((this.propertyDeductible!= null)&&this.propertyDeductible.equals(rhs.propertyDeductible))))&&
        		((this.expandedProperty == rhs.expandedProperty)||((this.expandedProperty!= null)&&this.expandedProperty.equals(rhs.expandedProperty))&&
        		((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties)))));
    }

}
