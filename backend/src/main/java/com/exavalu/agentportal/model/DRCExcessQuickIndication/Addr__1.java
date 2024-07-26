
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
    "addrTypeCd",
    "addr1",
    "addr2",
    "city",
    "state",
    "postalCode",
    "busCountry",
    "country"
})
@Generated("jsonschema2pojo")
public class Addr__1 {

    @JsonProperty("addrTypeCd")
    private String addrTypeCd;
    @JsonProperty("addr1")
    private String addr1;
    @JsonProperty("addr2")
    private String addr2;
    @JsonProperty("city")
    private String city;
    @JsonProperty("state")
    private String state;
    @JsonProperty("postalCode")
    private String postalCode;
    @JsonProperty("busCountry")
    private String busCountry;
    public String getBusCountry() {
		return busCountry;
	}

	public void setBusCountry(String busCountry) {
		this.busCountry = busCountry;
	}

	@JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("addrTypeCd")
    public String getAddrTypeCd() {
        return addrTypeCd;
    }

    @JsonProperty("addrTypeCd")
    public void setAddrTypeCd(String addrTypeCd) {
        this.addrTypeCd = addrTypeCd;
    }

    public Addr__1 withAddrTypeCd(String addrTypeCd) {
        this.addrTypeCd = addrTypeCd;
        return this;
    }

    @JsonProperty("addr1")
    public String getAddr1() {
        return addr1;
    }

    @JsonProperty("addr1")
    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    public Addr__1 withAddr1(String addr1) {
        this.addr1 = addr1;
        return this;
    }

    @JsonProperty("addr2")
    public String getAddr2() {
        return addr2;
    }

    @JsonProperty("addr2")
    public void setAddr2(String addr2) {
        this.addr2 = addr2;
    }

    public Addr__1 withAddr2(String addr2) {
        this.addr2 = addr2;
        return this;
    }

    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }

    public Addr__1 withCity(String city) {
        this.city = city;
        return this;
    }

    @JsonProperty("state")
    public String getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(String state) {
        this.state = state;
    }

    public Addr__1 withState(String state) {
        this.state = state;
        return this;
    }

    @JsonProperty("postalCode")
    public String getPostalCode() {
        return postalCode;
    }

    @JsonProperty("postalCode")
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    
    public Addr__1 withPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public Addr__1 withBusCountry(String busCountry) {
        this.busCountry = busCountry;
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

    public Addr__1 withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Addr__1 .class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("addrTypeCd");
        sb.append('=');
        sb.append(((this.addrTypeCd == null)?"<null>":this.addrTypeCd));
        sb.append(',');
        sb.append("addr1");
        sb.append('=');
        sb.append(((this.addr1 == null)?"<null>":this.addr1));
        sb.append(',');
        sb.append("addr2");
        sb.append('=');
        sb.append(((this.addr2 == null)?"<null>":this.addr2));
        sb.append(',');
        sb.append("city");
        sb.append('=');
        sb.append(((this.city == null)?"<null>":this.city));
        sb.append(',');
        sb.append("state");
        sb.append('=');
        sb.append(((this.state == null)?"<null>":this.state));
        sb.append(',');
        sb.append("postalCode");
        sb.append('=');
        sb.append(((this.postalCode == null)?"<null>":this.postalCode));
        sb.append(',');
        sb.append("busCountry");
        sb.append('=');
        sb.append(((this.busCountry == null)?"<null>":this.busCountry));
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
        result = ((result* 31)+((this.addrTypeCd == null)? 0 :this.addrTypeCd.hashCode()));
        result = ((result* 31)+((this.addr2 == null)? 0 :this.addr2 .hashCode()));
        result = ((result* 31)+((this.addr1 == null)? 0 :this.addr1 .hashCode()));
        result = ((result* 31)+((this.city == null)? 0 :this.city.hashCode()));
        result = ((result* 31)+((this.postalCode == null)? 0 :this.postalCode.hashCode()));
        result = ((result* 31)+((this.busCountry == null)? 0 :this.busCountry.hashCode()));
        result = ((result* 31)+((this.state == null)? 0 :this.state.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Addr__1) == false) {
            return false;
        }
        Addr__1 rhs = ((Addr__1) other);
        return ((((((((this.addrTypeCd == rhs.addrTypeCd)||((this.addrTypeCd!= null)&&this.addrTypeCd.equals(rhs.addrTypeCd)))&&((this.addr2 == rhs.addr2)||((this.addr2 != null)&&this.addr2 .equals(rhs.addr2))))&&(
        		(this.addr1 == rhs.addr1)||((this.addr1 != null)&&this.addr1 .equals(rhs.addr1))))&&
        		((this.city == rhs.city)||((this.city!= null)&&this.city.equals(rhs.city))))&&
        		((this.postalCode == rhs.postalCode)||((this.postalCode!= null)&&this.postalCode.equals(rhs.postalCode))))&&
        		((this.state == rhs.state)||((this.state!= null)&&this.state.equals(rhs.state))))&&
        		((this.busCountry == rhs.busCountry)||((this.busCountry!= null)&&this.busCountry.equals(rhs.busCountry))))&&
        		((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties)));
    }

}
