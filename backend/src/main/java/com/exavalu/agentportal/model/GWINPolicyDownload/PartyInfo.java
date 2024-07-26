
package com.exavalu.agentportal.model.GWINPolicyDownload;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    "partyTypeCd",
    "phoneInfo",
    "addresses"
})
@Generated("jsonschema2pojo")
public class PartyInfo {

    @JsonProperty("id")
    private String id;
    @JsonProperty("partyTypeCd")
    private String partyTypeCd;
    @JsonProperty("phoneInfo")
    private List<PhoneInfo> phoneInfo = new ArrayList<PhoneInfo>();
    @JsonProperty("addresses")
    private List<Address> addresses = new ArrayList<Address>();
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

    public PartyInfo withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("partyTypeCd")
    public String getPartyTypeCd() {
        return partyTypeCd;
    }

    @JsonProperty("partyTypeCd")
    public void setPartyTypeCd(String partyTypeCd) {
        this.partyTypeCd = partyTypeCd;
    }

    public PartyInfo withPartyTypeCd(String partyTypeCd) {
        this.partyTypeCd = partyTypeCd;
        return this;
    }

    @JsonProperty("phoneInfo")
    public List<PhoneInfo> getPhoneInfo() {
        return phoneInfo;
    }

    @JsonProperty("phoneInfo")
    public void setPhoneInfo(List<PhoneInfo> phoneInfo) {
        this.phoneInfo = phoneInfo;
    }

    public PartyInfo withPhoneInfo(List<PhoneInfo> phoneInfo) {
        this.phoneInfo = phoneInfo;
        return this;
    }

    @JsonProperty("addresses")
    public List<Address> getAddresses() {
        return addresses;
    }

    @JsonProperty("addresses")
    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public PartyInfo withAddresses(List<Address> addresses) {
        this.addresses = addresses;
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

    public PartyInfo withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(PartyInfo.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("partyTypeCd");
        sb.append('=');
        sb.append(((this.partyTypeCd == null)?"<null>":this.partyTypeCd));
        sb.append(',');
        sb.append("phoneInfo");
        sb.append('=');
        sb.append(((this.phoneInfo == null)?"<null>":this.phoneInfo));
        sb.append(',');
        sb.append("addresses");
        sb.append('=');
        sb.append(((this.addresses == null)?"<null>":this.addresses));
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
        result = ((result* 31)+((this.phoneInfo == null)? 0 :this.phoneInfo.hashCode()));
        result = ((result* 31)+((this.addresses == null)? 0 :this.addresses.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.partyTypeCd == null)? 0 :this.partyTypeCd.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PartyInfo) == false) {
            return false;
        }
        PartyInfo rhs = ((PartyInfo) other);
        return ((((((this.phoneInfo == rhs.phoneInfo)||((this.phoneInfo!= null)&&this.phoneInfo.equals(rhs.phoneInfo)))&&((this.addresses == rhs.addresses)||((this.addresses!= null)&&this.addresses.equals(rhs.addresses))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.partyTypeCd == rhs.partyTypeCd)||((this.partyTypeCd!= null)&&this.partyTypeCd.equals(rhs.partyTypeCd))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}
