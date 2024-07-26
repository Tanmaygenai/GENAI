
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
    "personTypeCd",
    "bestWayToContact"
})
@Generated("jsonschema2pojo")
public class PersonInfo {

    @JsonProperty("id")
    private String id;
    @JsonProperty("personTypeCd")
    private String personTypeCd;
    @JsonProperty("bestWayToContact")
    private String bestWayToContact;
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

    public PersonInfo withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("personTypeCd")
    public String getPersonTypeCd() {
        return personTypeCd;
    }

    @JsonProperty("personTypeCd")
    public void setPersonTypeCd(String personTypeCd) {
        this.personTypeCd = personTypeCd;
    }

    public PersonInfo withPersonTypeCd(String personTypeCd) {
        this.personTypeCd = personTypeCd;
        return this;
    }

    @JsonProperty("bestWayToContact")
    public String getBestWayToContact() {
        return bestWayToContact;
    }

    @JsonProperty("bestWayToContact")
    public void setBestWayToContact(String bestWayToContact) {
        this.bestWayToContact = bestWayToContact;
    }

    public PersonInfo withBestWayToContact(String bestWayToContact) {
        this.bestWayToContact = bestWayToContact;
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

    public PersonInfo withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(PersonInfo.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("personTypeCd");
        sb.append('=');
        sb.append(((this.personTypeCd == null)?"<null>":this.personTypeCd));
        sb.append(',');
        sb.append("bestWayToContact");
        sb.append('=');
        sb.append(((this.bestWayToContact == null)?"<null>":this.bestWayToContact));
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
        result = ((result* 31)+((this.personTypeCd == null)? 0 :this.personTypeCd.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.bestWayToContact == null)? 0 :this.bestWayToContact.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PersonInfo) == false) {
            return false;
        }
        PersonInfo rhs = ((PersonInfo) other);
        return (((((this.personTypeCd == rhs.personTypeCd)||((this.personTypeCd!= null)&&this.personTypeCd.equals(rhs.personTypeCd)))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.bestWayToContact == rhs.bestWayToContact)||((this.bestWayToContact!= null)&&this.bestWayToContact.equals(rhs.bestWayToContact))));
    }

}
