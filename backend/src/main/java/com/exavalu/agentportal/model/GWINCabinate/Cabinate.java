
package com.exavalu.agentportal.model.GWINCabinate;

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

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "cabinetName",
    "documentBean"
})

public class Cabinate {

    @JsonProperty("id")
    private String id;
    @JsonProperty("cabinetName")
    private String cabinetName;
    @JsonProperty("documentBean")
    private List<DocumentBean> documentBean = new ArrayList<DocumentBean>();
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

    public Cabinate withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("cabinetName")
    public String getCabinetName() {
        return cabinetName;
    }

    @JsonProperty("cabinetName")
    public void setCabinetName(String cabinetName) {
        this.cabinetName = cabinetName;
    }

    public Cabinate withCabinetName(String cabinetName) {
        this.cabinetName = cabinetName;
        return this;
    }

    @JsonProperty("documentBean")
    public List<DocumentBean> getDocumentBean() {
        return documentBean;
    }

    @JsonProperty("documentBean")
    public void setDocumentBean(List<DocumentBean> documentBean) {
        this.documentBean = documentBean;
    }

    public Cabinate withDocumentBean(List<DocumentBean> documentBean) {
        this.documentBean = documentBean;
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

    public Cabinate withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String str="<null>";
        sb.append(Cabinate.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?str:this.id));
        sb.append(',');
        sb.append("cabinetName");
        sb.append('=');
        sb.append(((this.cabinetName == null)?str:this.cabinetName));
        sb.append(',');
        sb.append("documentBean");
        sb.append('=');
        sb.append(((this.documentBean == null)?str:this.documentBean));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?str:this.additionalProperties));
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
        result = ((result* 31)+((this.cabinetName == null)? 0 :this.cabinetName.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.documentBean == null)? 0 :this.documentBean.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Cabinate) == false) {
            return false;
        }
        Cabinate rhs = ((Cabinate) other);
        return (((((this.cabinetName == rhs.cabinetName)||((this.cabinetName!= null)&&this.cabinetName.equals(rhs.cabinetName)))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.documentBean == rhs.documentBean)||((this.documentBean!= null)&&this.documentBean.equals(rhs.documentBean))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}
