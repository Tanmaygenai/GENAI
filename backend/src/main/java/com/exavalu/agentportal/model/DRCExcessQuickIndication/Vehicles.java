
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
    "lightVehicles",
    "mediumVehicles",
    "heavyVehicles",
    "extraHeavyVehicles"
})
@Generated("jsonschema2pojo")
public class Vehicles {

    @JsonProperty("lightVehicles")
    private Integer lightVehicles;
    @JsonProperty("mediumVehicles")
    private Integer mediumVehicles;
    @JsonProperty("heavyVehicles")
    private Integer heavyVehicles;
    @JsonProperty("extraHeavyVehicles")
    private Integer extraHeavyVehicles;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("lightVehicles")
    public Integer getLightVehicles() {
        return lightVehicles;
    }

    @JsonProperty("lightVehicles")
    public void setLightVehicles(Integer lightVehicles) {
        this.lightVehicles = lightVehicles;
    }

    public Vehicles withLightVehicles(Integer lightVehicles) {
        this.lightVehicles = lightVehicles;
        return this;
    }

    @JsonProperty("mediumVehicles")
    public Integer getMediumVehicles() {
        return mediumVehicles;
    }

    @JsonProperty("mediumVehicles")
    public void setMediumVehicles(Integer mediumVehicles) {
        this.mediumVehicles = mediumVehicles;
    }

    public Vehicles withMediumVehicles(Integer mediumVehicles) {
        this.mediumVehicles = mediumVehicles;
        return this;
    }

    @JsonProperty("heavyVehicles")
    public Integer getHeavyVehicles() {
        return heavyVehicles;
    }

    @JsonProperty("heavyVehicles")
    public void setHeavyVehicles(Integer heavyVehicles) {
        this.heavyVehicles = heavyVehicles;
    }

    public Vehicles withHeavyVehicles(Integer heavyVehicles) {
        this.heavyVehicles = heavyVehicles;
        return this;
    }

    @JsonProperty("extraHeavyVehicles")
    public Integer getExtraHeavyVehicles() {
        return extraHeavyVehicles;
    }

    @JsonProperty("extraHeavyVehicles")
    public void setExtraHeavyVehicles(Integer extraHeavyVehicles) {
        this.extraHeavyVehicles = extraHeavyVehicles;
    }

    public Vehicles withExtraHeavyVehicles(Integer extraHeavyVehicles) {
        this.extraHeavyVehicles = extraHeavyVehicles;
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

    public Vehicles withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Vehicles.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("lightVehicles");
        sb.append('=');
        sb.append(((this.lightVehicles == null)?"<null>":this.lightVehicles));
        sb.append(',');
        sb.append("mediumVehicles");
        sb.append('=');
        sb.append(((this.mediumVehicles == null)?"<null>":this.mediumVehicles));
        sb.append(',');
        sb.append("heavyVehicles");
        sb.append('=');
        sb.append(((this.heavyVehicles == null)?"<null>":this.heavyVehicles));
        sb.append(',');
        sb.append("extraHeavyVehicles");
        sb.append('=');
        sb.append(((this.extraHeavyVehicles == null)?"<null>":this.extraHeavyVehicles));
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
        result = ((result* 31)+((this.extraHeavyVehicles == null)? 0 :this.extraHeavyVehicles.hashCode()));
        result = ((result* 31)+((this.heavyVehicles == null)? 0 :this.heavyVehicles.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.lightVehicles == null)? 0 :this.lightVehicles.hashCode()));
        result = ((result* 31)+((this.mediumVehicles == null)? 0 :this.mediumVehicles.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Vehicles) == false) {
            return false;
        }
        Vehicles rhs = ((Vehicles) other);
        return ((((((this.extraHeavyVehicles == rhs.extraHeavyVehicles)||((this.extraHeavyVehicles!= null)&&this.extraHeavyVehicles.equals(rhs.extraHeavyVehicles)))&&((this.heavyVehicles == rhs.heavyVehicles)||((this.heavyVehicles!= null)&&this.heavyVehicles.equals(rhs.heavyVehicles))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.lightVehicles == rhs.lightVehicles)||((this.lightVehicles!= null)&&this.lightVehicles.equals(rhs.lightVehicles))))&&((this.mediumVehicles == rhs.mediumVehicles)||((this.mediumVehicles!= null)&&this.mediumVehicles.equals(rhs.mediumVehicles))));
    }

}
