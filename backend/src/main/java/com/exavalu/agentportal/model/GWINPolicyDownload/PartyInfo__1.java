
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
    "personInfo",
    "emailInfo",
    "taxInfo",
    "phoneInfo",
    "nameInfo",
    "addresses"
})
@Generated("jsonschema2pojo")
public class PartyInfo__1 {

    @JsonProperty("id")
    private String id;
    @JsonProperty("partyTypeCd")
    private String partyTypeCd;
    @JsonProperty("personInfo")
    private PersonInfo personInfo;
    @JsonProperty("emailInfo")
    private EmailInfo emailInfo;
    @JsonProperty("taxInfo")
    private TaxInfo taxInfo;
    @JsonProperty("phoneInfo")
    private List<PhoneInfo__1> phoneInfo = new ArrayList<PhoneInfo__1>();
    @JsonProperty("nameInfo")
    private NameInfo nameInfo;
    @JsonProperty("addresses")
    private List<Address__1> addresses = new ArrayList<Address__1>();
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

    public PartyInfo__1 withId(String id) {
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

    public PartyInfo__1 withPartyTypeCd(String partyTypeCd) {
        this.partyTypeCd = partyTypeCd;
        return this;
    }

    @JsonProperty("personInfo")
    public PersonInfo getPersonInfo() {
        return personInfo;
    }

    @JsonProperty("personInfo")
    public void setPersonInfo(PersonInfo personInfo) {
        this.personInfo = personInfo;
    }

    public PartyInfo__1 withPersonInfo(PersonInfo personInfo) {
        this.personInfo = personInfo;
        return this;
    }

    @JsonProperty("emailInfo")
    public EmailInfo getEmailInfo() {
        return emailInfo;
    }

    @JsonProperty("emailInfo")
    public void setEmailInfo(EmailInfo emailInfo) {
        this.emailInfo = emailInfo;
    }

    public PartyInfo__1 withEmailInfo(EmailInfo emailInfo) {
        this.emailInfo = emailInfo;
        return this;
    }

    @JsonProperty("taxInfo")
    public TaxInfo getTaxInfo() {
        return taxInfo;
    }

    @JsonProperty("taxInfo")
    public void setTaxInfo(TaxInfo taxInfo) {
        this.taxInfo = taxInfo;
    }

    public PartyInfo__1 withTaxInfo(TaxInfo taxInfo) {
        this.taxInfo = taxInfo;
        return this;
    }

    @JsonProperty("phoneInfo")
    public List<PhoneInfo__1> getPhoneInfo() {
        return phoneInfo;
    }

    @JsonProperty("phoneInfo")
    public void setPhoneInfo(List<PhoneInfo__1> phoneInfo) {
        this.phoneInfo = phoneInfo;
    }

    public PartyInfo__1 withPhoneInfo(List<PhoneInfo__1> phoneInfo) {
        this.phoneInfo = phoneInfo;
        return this;
    }

    @JsonProperty("nameInfo")
    public NameInfo getNameInfo() {
        return nameInfo;
    }

    @JsonProperty("nameInfo")
    public void setNameInfo(NameInfo nameInfo) {
        this.nameInfo = nameInfo;
    }

    public PartyInfo__1 withNameInfo(NameInfo nameInfo) {
        this.nameInfo = nameInfo;
        return this;
    }

    @JsonProperty("addresses")
    public List<Address__1> getAddresses() {
        return addresses;
    }

    @JsonProperty("addresses")
    public void setAddresses(List<Address__1> addresses) {
        this.addresses = addresses;
    }

    public PartyInfo__1 withAddresses(List<Address__1> addresses) {
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

    public PartyInfo__1 withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(PartyInfo__1 .class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("partyTypeCd");
        sb.append('=');
        sb.append(((this.partyTypeCd == null)?"<null>":this.partyTypeCd));
        sb.append(',');
        sb.append("personInfo");
        sb.append('=');
        sb.append(((this.personInfo == null)?"<null>":this.personInfo));
        sb.append(',');
        sb.append("emailInfo");
        sb.append('=');
        sb.append(((this.emailInfo == null)?"<null>":this.emailInfo));
        sb.append(',');
        sb.append("taxInfo");
        sb.append('=');
        sb.append(((this.taxInfo == null)?"<null>":this.taxInfo));
        sb.append(',');
        sb.append("phoneInfo");
        sb.append('=');
        sb.append(((this.phoneInfo == null)?"<null>":this.phoneInfo));
        sb.append(',');
        sb.append("nameInfo");
        sb.append('=');
        sb.append(((this.nameInfo == null)?"<null>":this.nameInfo));
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
        result = ((result* 31)+((this.addresses == null)? 0 :this.addresses.hashCode()));
        result = ((result* 31)+((this.emailInfo == null)? 0 :this.emailInfo.hashCode()));
        result = ((result* 31)+((this.phoneInfo == null)? 0 :this.phoneInfo.hashCode()));
        result = ((result* 31)+((this.nameInfo == null)? 0 :this.nameInfo.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.partyTypeCd == null)? 0 :this.partyTypeCd.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.personInfo == null)? 0 :this.personInfo.hashCode()));
        result = ((result* 31)+((this.taxInfo == null)? 0 :this.taxInfo.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PartyInfo__1) == false) {
            return false;
        }
        PartyInfo__1 rhs = ((PartyInfo__1) other);
        return ((((((((((this.addresses == rhs.addresses)||((this.addresses!= null)&&this.addresses.equals(rhs.addresses)))&&((this.emailInfo == rhs.emailInfo)||((this.emailInfo!= null)&&this.emailInfo.equals(rhs.emailInfo))))&&((this.phoneInfo == rhs.phoneInfo)||((this.phoneInfo!= null)&&this.phoneInfo.equals(rhs.phoneInfo))))&&((this.nameInfo == rhs.nameInfo)||((this.nameInfo!= null)&&this.nameInfo.equals(rhs.nameInfo))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.partyTypeCd == rhs.partyTypeCd)||((this.partyTypeCd!= null)&&this.partyTypeCd.equals(rhs.partyTypeCd))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.personInfo == rhs.personInfo)||((this.personInfo!= null)&&this.personInfo.equals(rhs.personInfo))))&&((this.taxInfo == rhs.taxInfo)||((this.taxInfo!= null)&&this.taxInfo.equals(rhs.taxInfo))));
    }

}
