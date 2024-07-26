
package com.exavalu.agentportal.model.GWINPolicyDownload;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "addrTypeCd",
    "addr1",
    "city",
    "stateProvCd",
    "postalCode",
    "regionCd",
    "regionISOCd"
})
@Generated("jsonschema2pojo")
public class Address__1 {

    @JsonProperty("id")
    private String id;
    @JsonProperty("addrTypeCd")
    private String addrTypeCd;
    @JsonProperty("addr1")
    private String addr1;
    @JsonProperty("city")
    private String city;
    @JsonProperty("stateProvCd")
    private String stateProvCd;
    @JsonProperty("postalCode")
    private String postalCode;
    @JsonProperty("regionCd")
    private String regionCd;
    @JsonProperty("regionISOCd")
    private String regionISOCd;
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

    public Address__1 withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("addrTypeCd")
    public String getAddrTypeCd() {
        return addrTypeCd;
    }

    @JsonProperty("addrTypeCd")
    public void setAddrTypeCd(String addrTypeCd) {
        this.addrTypeCd = addrTypeCd;
    }

    public Address__1 withAddrTypeCd(String addrTypeCd) {
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

    public Address__1 withAddr1(String addr1) {
        this.addr1 = addr1;
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

    public Address__1 withCity(String city) {
        this.city = city;
        return this;
    }

    @JsonProperty("stateProvCd")
    public String getStateProvCd() {
        return stateProvCd;
    }

    @JsonProperty("stateProvCd")
    public void setStateProvCd(String stateProvCd) {
        this.stateProvCd = stateProvCd;
    }

    public Address__1 withStateProvCd(String stateProvCd) {
        this.stateProvCd = stateProvCd;
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

    public Address__1 withPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    @JsonProperty("regionCd")
    public String getRegionCd() {
        return regionCd;
    }

    @JsonProperty("regionCd")
    public void setRegionCd(String regionCd) {
        this.regionCd = regionCd;
    }

    public Address__1 withRegionCd(String regionCd) {
        this.regionCd = regionCd;
        return this;
    }

    @JsonProperty("regionISOCd")
    public String getRegionISOCd() {
        return regionISOCd;
    }

    @JsonProperty("regionISOCd")
    public void setRegionISOCd(String regionISOCd) {
        this.regionISOCd = regionISOCd;
    }

    public Address__1 withRegionISOCd(String regionISOCd) {
        this.regionISOCd = regionISOCd;
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

    public Address__1 withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Address__1 .class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("addrTypeCd");
        sb.append('=');
        sb.append(((this.addrTypeCd == null)?"<null>":this.addrTypeCd));
        sb.append(',');
        sb.append("addr1");
        sb.append('=');
        sb.append(((this.addr1 == null)?"<null>":this.addr1));
        sb.append(',');
        sb.append("city");
        sb.append('=');
        sb.append(((this.city == null)?"<null>":this.city));
        sb.append(',');
        sb.append("stateProvCd");
        sb.append('=');
        sb.append(((this.stateProvCd == null)?"<null>":this.stateProvCd));
        sb.append(',');
        sb.append("postalCode");
        sb.append('=');
        sb.append(((this.postalCode == null)?"<null>":this.postalCode));
        sb.append(',');
        sb.append("regionCd");
        sb.append('=');
        sb.append(((this.regionCd == null)?"<null>":this.regionCd));
        sb.append(',');
        sb.append("regionISOCd");
        sb.append('=');
        sb.append(((this.regionISOCd == null)?"<null>":this.regionISOCd));
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
        result = ((result* 31)+((this.addr1 == null)? 0 :this.addr1 .hashCode()));
        result = ((result* 31)+((this.city == null)? 0 :this.city.hashCode()));
        result = ((result* 31)+((this.stateProvCd == null)? 0 :this.stateProvCd.hashCode()));
        result = ((result* 31)+((this.postalCode == null)? 0 :this.postalCode.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.regionISOCd == null)? 0 :this.regionISOCd.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.regionCd == null)? 0 :this.regionCd.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Address__1) == false) {
            return false;
        }
        Address__1 rhs = ((Address__1) other);
        return ((((((((((this.addrTypeCd == rhs.addrTypeCd)||((this.addrTypeCd!= null)&&this.addrTypeCd.equals(rhs.addrTypeCd)))&&((this.addr1 == rhs.addr1)||((this.addr1 != null)&&this.addr1 .equals(rhs.addr1))))&&((this.city == rhs.city)||((this.city!= null)&&this.city.equals(rhs.city))))&&((this.stateProvCd == rhs.stateProvCd)||((this.stateProvCd!= null)&&this.stateProvCd.equals(rhs.stateProvCd))))&&((this.postalCode == rhs.postalCode)||((this.postalCode!= null)&&this.postalCode.equals(rhs.postalCode))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.regionISOCd == rhs.regionISOCd)||((this.regionISOCd!= null)&&this.regionISOCd.equals(rhs.regionISOCd))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.regionCd == rhs.regionCd)||((this.regionCd!= null)&&this.regionCd.equals(rhs.regionCd))));
    }

}
