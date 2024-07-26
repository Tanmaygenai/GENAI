package com.exavalu.agentportal.model.DRCExcessQuickIndication;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "businessType",
    "businessSubType",
    "businessYears",
    "businessYear",
    "primaryOperationsZip",
    "zipcode",
    "employees",
    "sale",
    "fleetLimit",
    "productType"
})
@Generated("jsonschema2pojo")
public class PrimaryBusinessInfo {
	
	@JsonProperty("businessType")
    private String businessType;
	@JsonProperty("businessSubType")
    private String businessSubType;
    @JsonProperty("businessYears")
    private String businessYears;
    @JsonProperty("businessYear")
    private String businessYear;
	@JsonProperty("primaryOperationsZip")
    private String primaryOperationsZip;
    @JsonProperty("zipcode")
    private String zipcode;
    @JsonProperty("employees")
    private String employees;
	@JsonProperty("sale")
    private String sale;
	@JsonProperty("productType")
    private String productType;
	@JsonProperty("fleetLimit")
    private String fleetLimit;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    
    @JsonProperty("businessType")
    public String getBusinessType() {
		return businessType;
	}
    
    public PrimaryBusinessInfo withBusinessType(String businessType) {
        this.businessType = businessType;
        return this;
    }

	@JsonProperty("businessType")
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
    
    @JsonProperty("businessSubType")
	public String getBusinessSubType() {
		return businessSubType;
	}
    
    public PrimaryBusinessInfo withBusinessSubType(String businessSubType) {
        this.businessSubType = businessSubType;
        return this;
    }
    
    @JsonProperty("businessSubType")
	public void setBusinessSubType(String businessSubType) {
		this.businessSubType = businessSubType;
	}
    
    @JsonProperty("businessYears")
	public String getBusinessYears() {
		return businessYears;
	}
    
    public PrimaryBusinessInfo withBusinessYears(String businessYears) {
        this.businessYears = businessYears;
        return this;
    }
    
    @JsonProperty("businessYears")
	public void setBusinessYears(String businessYears) {
		this.businessYears = businessYears;
	}
    
    @JsonProperty("businessYear")
    public String getBusinessYear() {
		return businessYear;
	}

    @JsonProperty("businessYear")
	public void setBusinessYear(String businessYear) {
		this.businessYear = businessYear;
	}
	
    public PrimaryBusinessInfo PrimaryBusinessYear(String businessYear) {
        this.businessYear = businessYear;
        return this;
    }
    
    @JsonProperty("primaryOperationsZip")
	public String getPrimaryOperationsZip() {
		return primaryOperationsZip;
	}
    
    public PrimaryBusinessInfo PrimaryOperationsZip(String primaryOperationsZip) {
        this.primaryOperationsZip = primaryOperationsZip;
        return this;
    }
    
    @JsonProperty("primaryOperationsZip")
	public void setPrimaryOperationsZip(String primaryOperationsZip) {
		this.primaryOperationsZip = primaryOperationsZip;
	}
    
    @JsonProperty("zipcode")
    public String getZipcode() {
		return zipcode;
	}

    @JsonProperty("zipcode")
    public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
    
	public PrimaryBusinessInfo withZipcode(String zipcode) {
		this.zipcode = zipcode;
		return this;
	}
    
	@JsonProperty("employees")
	public String getEmployees() {
		return employees;
	}

	@JsonProperty("employees")
	public void setEmployees(String employees) {
		this.employees = employees;
	}
	
	public PrimaryBusinessInfo withEmployees(String employees) {
		this.employees = employees;
		return this;
	}

	@JsonProperty("sale")
	public String getSale() {
		return sale;
	}

	@JsonProperty("sale")
	public void setSale(String sale) {
		this.sale = sale;
	}

	public PrimaryBusinessInfo withSale(String sale) {
		this.sale = sale;
		return this;
	}
	
    @JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return additionalProperties;
	}
	
    @JsonAnyGetter
	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}
    
    public PrimaryBusinessInfo withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }
    
    @JsonProperty("productType")
    public String getProductType() {
		return productType;
	}
    
    public PrimaryBusinessInfo withProductType(String productType) {
        this.productType = productType;
        return this;
    }

	@JsonProperty("productType")
	public void setProductType(String productType) {
		this.productType = productType;
	}

	@JsonProperty("fleetLimit")
	public String getFleetLimit() {
		return fleetLimit;
	}
	
	public PrimaryBusinessInfo withFleetLimit(String fleetLimit) {
        this.fleetLimit = fleetLimit;
        return this;
    }

	@JsonProperty("fleetLimit")
	public void setFleetLimit(String fleetLimit) {
		this.fleetLimit = fleetLimit;
	}
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(PrimaryBusinessInfo.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("businessType");
        sb.append('=');
        sb.append(((this.businessType == null)?"<null>":this.businessType));
        sb.append(',');
        sb.append("businessSubType");
        sb.append('=');
        sb.append(((this.businessSubType == null)?"<null>":this.businessSubType));
        sb.append(',');
        sb.append("businessYears");
        sb.append('=');
        sb.append(((this.businessYears == null)?"<null>":this.businessYears));
        sb.append(',');
        sb.append("businessYear");
        sb.append('=');
        sb.append(((this.businessYear == null)?"<null>":this.businessYear));
        sb.append(',');
        sb.append("primaryOperationsZip");
        sb.append('=');
        sb.append(((this.primaryOperationsZip == null)?"<null>":this.primaryOperationsZip));
        sb.append(',');
        sb.append("zipcode");
        sb.append('=');
        sb.append(((this.zipcode == null)?"<null>":this.zipcode));
        sb.append(',');
        sb.append("employees");
        sb.append('=');
        sb.append(((this.employees == null)?"<null>":this.employees));
        sb.append(',');
        sb.append("sale");
        sb.append('=');
        sb.append(((this.sale == null)?"<null>":this.sale));
        sb.append(',');
        sb.append("productType");
        sb.append('=');
        sb.append(((this.productType == null)?"<null>":this.productType));
        sb.append(',');
        sb.append("fleetLimit");
        sb.append('=');
        sb.append(((this.fleetLimit == null)?"<null>":this.fleetLimit));
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
        result = ((result* 31)+((this.businessType == null)? 0 :this.businessType.hashCode()));
        result = ((result* 31)+((this.businessSubType == null)? 0 :this.businessSubType.hashCode()));
        result = ((result* 31)+((this.zipcode == null)? 0 :this.zipcode.hashCode()));
        result = ((result* 31)+((this.employees == null)? 0 :this.employees.hashCode()));
        result = ((result* 31)+((this.sale == null)? 0 :this.sale.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.businessYears == null)? 0 :this.businessYears.hashCode()));
        result = ((result* 31)+((this.businessYear == null)? 0 :this.businessYear.hashCode()));
        result = ((result* 31)+((this.productType == null)? 0 :this.productType.hashCode()));
        result = ((result* 31)+((this.fleetLimit == null)? 0 :this.fleetLimit.hashCode()));
        result = ((result* 31)+((this.primaryOperationsZip == null)? 0 :this.primaryOperationsZip.hashCode()));
        return result;
    }
    
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PrimaryBusinessInfo) == false) {
            return false;
        }
        PrimaryBusinessInfo rhs = ((PrimaryBusinessInfo) other);
        return ((((((((this.businessType == rhs.businessType)||((this.businessType!= null)&&this.businessType.equals(rhs.businessType)))&&
        		((this.businessSubType == rhs.businessSubType)||((this.businessSubType!= null)&&this.businessSubType.equals(rhs.businessSubType))))&&
        		((this.zipcode == rhs.zipcode)||((this.zipcode!= null)&&this.zipcode.equals(rhs.zipcode)))&&
        		((this.employees == rhs.employees)||((this.employees!= null)&&this.employees.equals(rhs.employees)))&&
        		((this.sale == rhs.sale)||((this.sale!= null)&&this.sale.equals(rhs.sale)))&&
        		((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&
        		((this.businessYears == rhs.businessYears)||((this.businessYears!= null)&&this.businessYears.equals(rhs.businessYears))))&&
        		((this.businessYear == rhs.businessYear)||((this.businessYear!= null)&&this.businessYear.equals(rhs.businessYear))))&&
        		((this.productType == rhs.productType)||((this.productType!= null)&&this.productType.equals(rhs.productType)))&&
        		((this.fleetLimit == rhs.fleetLimit)||((this.fleetLimit!= null)&&this.fleetLimit.equals(rhs.fleetLimit)))&&
        		((this.primaryOperationsZip == rhs.primaryOperationsZip)||((this.primaryOperationsZip!= null)&&this.primaryOperationsZip.equals(rhs.primaryOperationsZip)))));
    }
}