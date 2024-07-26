
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
    "responseParams",
    "dTOOutput"
})
@Generated("jsonschema2pojo")
public class PolicyDocDownload {

    @JsonProperty("responseParams")
    private List<ResponseParam> responseParams = new ArrayList<ResponseParam>();
    @JsonProperty("dTOOutput")
    private List<DTOOutput> dTOOutput = new ArrayList<DTOOutput>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("responseParams")
    public List<ResponseParam> getResponseParams() {
        return responseParams;
    }

    @JsonProperty("responseParams")
    public void setResponseParams(List<ResponseParam> responseParams) {
        this.responseParams = responseParams;
    }

    public PolicyDocDownload withResponseParams(List<ResponseParam> responseParams) {
        this.responseParams = responseParams;
        return this;
    }

    @JsonProperty("dTOOutput")
    public List<DTOOutput> getdTOOutput() {
        return dTOOutput;
    }

    @JsonProperty("dTOOutput")
    public void setdTOOutput(List<DTOOutput> dTOOutput) {
        this.dTOOutput = dTOOutput;
    }

    public PolicyDocDownload withdTOOutput(List<DTOOutput> dTOOutput) {
        this.dTOOutput = dTOOutput;
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

    public PolicyDocDownload withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(PolicyDocDownload.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("responseParams");
        sb.append('=');
        sb.append(((this.responseParams == null)?"<null>":this.responseParams));
        sb.append(',');
        sb.append("dTOOutput");
        sb.append('=');
        sb.append(((this.dTOOutput == null)?"<null>":this.dTOOutput));
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
        result = ((result* 31)+((this.dTOOutput == null)? 0 :this.dTOOutput.hashCode()));
        result = ((result* 31)+((this.responseParams == null)? 0 :this.responseParams.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PolicyDocDownload) == false) {
            return false;
        }
        PolicyDocDownload rhs = ((PolicyDocDownload) other);
        return ((((this.dTOOutput == rhs.dTOOutput)||((this.dTOOutput!= null)&&this.dTOOutput.equals(rhs.dTOOutput)))&&((this.responseParams == rhs.responseParams)||((this.responseParams!= null)&&this.responseParams.equals(rhs.responseParams))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}
