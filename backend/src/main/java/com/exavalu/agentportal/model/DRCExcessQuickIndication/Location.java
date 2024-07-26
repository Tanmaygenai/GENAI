
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
    "locationNumber",
    "locationDesc",
    "addr"
})
@Generated("jsonschema2pojo")
public class Location {
	
    @JsonProperty("locationNumber")
    private Integer locationNumber;
    @JsonProperty("locationDesc")
    private String locationDesc;
	@JsonProperty("addr")
    private Addr__1 addr;
    @JsonProperty("AddressType")
    private String AddressType;
    public String getAddressType() {
		return AddressType;
	}

	public void setAddressType(String addressType) {
		AddressType = addressType;
	}

	@JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("locationNumber")
    public Integer getLocationNumber() {
        return locationNumber;
    }

    @JsonProperty("locationNumber")
    public void setLocationNumber(Integer locationNumber) {
        this.locationNumber = locationNumber;
    }

    public Location withLocationNumber(Integer locationNumber) {
        this.locationNumber = locationNumber;
        return this;
    }

    @JsonProperty("addr")
    public Addr__1 getAddr() {
        return addr;
    }

    @JsonProperty("addr")
    public void setAddr(Addr__1 addr) {
        this.addr = addr;
    }

    public Location withAddr(Addr__1 addr) {
        this.addr = addr;
        return this;
    }
    
    @JsonProperty("locationDesc")
    public String getLocationDesc() {
		return locationDesc;
	}

    @JsonProperty("locationDesc")
	public void setLocationDesc(String locationDesc) {
		this.locationDesc = locationDesc;
	}

	public Location LocationDesc(String locationDesc) {
        this.locationDesc = locationDesc;
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

    public Location withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Location.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("locationNumber");
        sb.append('=');
        sb.append(((this.locationNumber == null)?"<null>":this.locationNumber));
        sb.append(',');
        sb.append("locationDesc");
        sb.append('=');
        sb.append(((this.locationDesc == null)?"<null>":this.locationDesc));
        sb.append(',');
        sb.append("addr");
        sb.append('=');
        sb.append(((this.addr == null)?"<null>":this.addr));
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
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.locationDesc == null)? 0 :this.locationDesc.hashCode()));
        result = ((result* 31)+((this.locationNumber == null)? 0 :this.locationNumber.hashCode()));
        result = ((result* 31)+((this.addr == null)? 0 :this.addr.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Location) == false) {
            return false;
        }
        Location rhs = ((Location) other);
        return ((((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties)))&&(
        		(this.locationNumber == rhs.locationNumber)||((this.locationNumber!= null)&&this.locationNumber.equals(rhs.locationNumber))))&&
        		((this.locationDesc == rhs.locationDesc)||((this.locationDesc!= null)&&this.locationDesc.equals(rhs.locationDesc))))&&
        		((this.addr == rhs.addr)||((this.addr!= null)&&this.addr.equals(rhs.addr)));
    }

}
