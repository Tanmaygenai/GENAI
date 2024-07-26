
package com.exavalu.agentportal.model.GWINCabinate;

import java.util.HashMap;
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
    "pDFURL",
    "originalFilename",
    "description"
})

public class DocumentBean {

    @JsonProperty("id")
    private String id;
    @JsonProperty("pDFURL")
    private String pDFURL;
    @JsonProperty("originalFilename")
    private String originalFilename;
    @JsonProperty("description")
    private String description;
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

    public DocumentBean withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("pDFURL")
    public String getpDFURL() {
        return pDFURL;
    }

    @JsonProperty("pDFURL")
    public void setpDFURL(String pDFURL) {
        this.pDFURL = pDFURL;
    }

    public DocumentBean withpDFURL(String pDFURL) {
        this.pDFURL = pDFURL;
        return this;
    }

    @JsonProperty("originalFilename")
    public String getOriginalFilename() {
        return originalFilename;
    }

    @JsonProperty("originalFilename")
    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }

    public DocumentBean withOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
        return this;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    public DocumentBean withDescription(String description) {
        this.description = description;
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

    public DocumentBean withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String str = "<null>";
        sb.append(DocumentBean.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?str:this.id));
        sb.append(',');
        sb.append("pDFURL");
        sb.append('=');
        sb.append(((this.pDFURL == null)?str:this.pDFURL));
        sb.append(',');
        sb.append("originalFilename");
        sb.append('=');
        sb.append(((this.originalFilename == null)?str:this.originalFilename));
        sb.append(',');
        sb.append("description");
        sb.append('=');
        sb.append(((this.description == null)?str:this.description));
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
        result = ((result* 31)+((this.description == null)? 0 :this.description.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.pDFURL == null)? 0 :this.pDFURL.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.originalFilename == null)? 0 :this.originalFilename.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof DocumentBean) == false) {
            return false;
        }
        DocumentBean rhs = ((DocumentBean) other);
        return ((((((this.description == rhs.description)||((this.description!= null)&&this.description.equals(rhs.description)))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.pDFURL == rhs.pDFURL)||((this.pDFURL!= null)&&this.pDFURL.equals(rhs.pDFURL))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.originalFilename == rhs.originalFilename)||((this.originalFilename!= null)&&this.originalFilename.equals(rhs.originalFilename))));
    }

}
