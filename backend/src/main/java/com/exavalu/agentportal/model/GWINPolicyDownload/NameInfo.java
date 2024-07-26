
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
    "nameTypeCd",
    "givenName",
    "surname",
    "commercialName"
})
@Generated("jsonschema2pojo")
public class NameInfo {

    @JsonProperty("id")
    private String id;
    @JsonProperty("nameTypeCd")
    private String nameTypeCd;
    @JsonProperty("givenName")
    private String givenName;
    @JsonProperty("surname")
    private String surname;
    @JsonProperty("commercialName")
    private String commercialName;
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

    public NameInfo withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("nameTypeCd")
    public String getNameTypeCd() {
        return nameTypeCd;
    }

    @JsonProperty("nameTypeCd")
    public void setNameTypeCd(String nameTypeCd) {
        this.nameTypeCd = nameTypeCd;
    }

    public NameInfo withNameTypeCd(String nameTypeCd) {
        this.nameTypeCd = nameTypeCd;
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

    public NameInfo withGivenName(String givenName) {
        this.givenName = givenName;
        return this;
    }

    @JsonProperty("surname")
    public String getSurname() {
        return surname;
    }

    @JsonProperty("surname")
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public NameInfo withSurname(String surname) {
        this.surname = surname;
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

    public NameInfo withCommercialName(String commercialName) {
        this.commercialName = commercialName;
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

    public NameInfo withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(NameInfo.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("nameTypeCd");
        sb.append('=');
        sb.append(((this.nameTypeCd == null)?"<null>":this.nameTypeCd));
        sb.append(',');
        sb.append("givenName");
        sb.append('=');
        sb.append(((this.givenName == null)?"<null>":this.givenName));
        sb.append(',');
        sb.append("surname");
        sb.append('=');
        sb.append(((this.surname == null)?"<null>":this.surname));
        sb.append(',');
        sb.append("commercialName");
        sb.append('=');
        sb.append(((this.commercialName == null)?"<null>":this.commercialName));
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
        result = ((result* 31)+((this.nameTypeCd == null)? 0 :this.nameTypeCd.hashCode()));
        result = ((result* 31)+((this.surname == null)? 0 :this.surname.hashCode()));
        result = ((result* 31)+((this.givenName == null)? 0 :this.givenName.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.commercialName == null)? 0 :this.commercialName.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof NameInfo) == false) {
            return false;
        }
        NameInfo rhs = ((NameInfo) other);
        return (((((((this.nameTypeCd == rhs.nameTypeCd)||((this.nameTypeCd!= null)&&this.nameTypeCd.equals(rhs.nameTypeCd)))&&((this.surname == rhs.surname)||((this.surname!= null)&&this.surname.equals(rhs.surname))))&&((this.givenName == rhs.givenName)||((this.givenName!= null)&&this.givenName.equals(rhs.givenName))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.commercialName == rhs.commercialName)||((this.commercialName!= null)&&this.commercialName.equals(rhs.commercialName))));
    }

}
