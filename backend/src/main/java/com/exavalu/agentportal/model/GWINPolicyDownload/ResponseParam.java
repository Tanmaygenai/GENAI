
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
    "transactionResponseDt",
    "transactionTime",
    "userId",
    "userInfo",
    "errors",
    "cMMParams",
    "additionalParams",
    "xFDF",
    "transactionResponseTm"
})
@Generated("jsonschema2pojo")
public class ResponseParam {

    @JsonProperty("id")
    private String id;
    @JsonProperty("transactionResponseDt")
    private String transactionResponseDt;
    @JsonProperty("transactionTime")
    private String transactionTime;
    @JsonProperty("userId")
    private String userId;
    @JsonProperty("userInfo")
    private List<UserInfo> userInfo = new ArrayList<UserInfo>();
    @JsonProperty("errors")
    private List<Error> errors = new ArrayList<Error>();
    @JsonProperty("cMMParams")
    private List<CMMParam> cMMParams = new ArrayList<CMMParam>();
    @JsonProperty("additionalParams")
    private List<AdditionalParam> additionalParams = new ArrayList<AdditionalParam>();
    @JsonProperty("xFDF")
    private List<Xfdf> xFDF = new ArrayList<Xfdf>();
    @JsonProperty("transactionResponseTm")
    private String transactionResponseTm;
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

    public ResponseParam withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("transactionResponseDt")
    public String getTransactionResponseDt() {
        return transactionResponseDt;
    }

    @JsonProperty("transactionResponseDt")
    public void setTransactionResponseDt(String transactionResponseDt) {
        this.transactionResponseDt = transactionResponseDt;
    }

    public ResponseParam withTransactionResponseDt(String transactionResponseDt) {
        this.transactionResponseDt = transactionResponseDt;
        return this;
    }

    @JsonProperty("transactionTime")
    public String getTransactionTime() {
        return transactionTime;
    }

    @JsonProperty("transactionTime")
    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }

    public ResponseParam withTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
        return this;
    }

    @JsonProperty("userId")
    public String getUserId() {
        return userId;
    }

    @JsonProperty("userId")
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ResponseParam withUserId(String userId) {
        this.userId = userId;
        return this;
    }

    @JsonProperty("userInfo")
    public List<UserInfo> getUserInfo() {
        return userInfo;
    }

    @JsonProperty("userInfo")
    public void setUserInfo(List<UserInfo> userInfo) {
        this.userInfo = userInfo;
    }

    public ResponseParam withUserInfo(List<UserInfo> userInfo) {
        this.userInfo = userInfo;
        return this;
    }

    @JsonProperty("errors")
    public List<Error> getErrors() {
        return errors;
    }

    @JsonProperty("errors")
    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    public ResponseParam withErrors(List<Error> errors) {
        this.errors = errors;
        return this;
    }

    @JsonProperty("cMMParams")
    public List<CMMParam> getcMMParams() {
        return cMMParams;
    }

    @JsonProperty("cMMParams")
    public void setcMMParams(List<CMMParam> cMMParams) {
        this.cMMParams = cMMParams;
    }

    public ResponseParam withcMMParams(List<CMMParam> cMMParams) {
        this.cMMParams = cMMParams;
        return this;
    }

    @JsonProperty("additionalParams")
    public List<AdditionalParam> getAdditionalParams() {
        return additionalParams;
    }

    @JsonProperty("additionalParams")
    public void setAdditionalParams(List<AdditionalParam> additionalParams) {
        this.additionalParams = additionalParams;
    }

    public ResponseParam withAdditionalParams(List<AdditionalParam> additionalParams) {
        this.additionalParams = additionalParams;
        return this;
    }

    @JsonProperty("xFDF")
    public List<Xfdf> getxFDF() {
        return xFDF;
    }

    @JsonProperty("xFDF")
    public void setxFDF(List<Xfdf> xFDF) {
        this.xFDF = xFDF;
    }

    public ResponseParam withxFDF(List<Xfdf> xFDF) {
        this.xFDF = xFDF;
        return this;
    }

    @JsonProperty("transactionResponseTm")
    public String getTransactionResponseTm() {
        return transactionResponseTm;
    }

    @JsonProperty("transactionResponseTm")
    public void setTransactionResponseTm(String transactionResponseTm) {
        this.transactionResponseTm = transactionResponseTm;
    }

    public ResponseParam withTransactionResponseTm(String transactionResponseTm) {
        this.transactionResponseTm = transactionResponseTm;
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

    public ResponseParam withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ResponseParam.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("transactionResponseDt");
        sb.append('=');
        sb.append(((this.transactionResponseDt == null)?"<null>":this.transactionResponseDt));
        sb.append(',');
        sb.append("transactionTime");
        sb.append('=');
        sb.append(((this.transactionTime == null)?"<null>":this.transactionTime));
        sb.append(',');
        sb.append("userId");
        sb.append('=');
        sb.append(((this.userId == null)?"<null>":this.userId));
        sb.append(',');
        sb.append("userInfo");
        sb.append('=');
        sb.append(((this.userInfo == null)?"<null>":this.userInfo));
        sb.append(',');
        sb.append("errors");
        sb.append('=');
        sb.append(((this.errors == null)?"<null>":this.errors));
        sb.append(',');
        sb.append("cMMParams");
        sb.append('=');
        sb.append(((this.cMMParams == null)?"<null>":this.cMMParams));
        sb.append(',');
        sb.append("additionalParams");
        sb.append('=');
        sb.append(((this.additionalParams == null)?"<null>":this.additionalParams));
        sb.append(',');
        sb.append("xFDF");
        sb.append('=');
        sb.append(((this.xFDF == null)?"<null>":this.xFDF));
        sb.append(',');
        sb.append("transactionResponseTm");
        sb.append('=');
        sb.append(((this.transactionResponseTm == null)?"<null>":this.transactionResponseTm));
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
        result = ((result* 31)+((this.userInfo == null)? 0 :this.userInfo.hashCode()));
        result = ((result* 31)+((this.transactionResponseTm == null)? 0 :this.transactionResponseTm.hashCode()));
        result = ((result* 31)+((this.additionalParams == null)? 0 :this.additionalParams.hashCode()));
        result = ((result* 31)+((this.transactionResponseDt == null)? 0 :this.transactionResponseDt.hashCode()));
        result = ((result* 31)+((this.xFDF == null)? 0 :this.xFDF.hashCode()));
        result = ((result* 31)+((this.cMMParams == null)? 0 :this.cMMParams.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.transactionTime == null)? 0 :this.transactionTime.hashCode()));
        result = ((result* 31)+((this.userId == null)? 0 :this.userId.hashCode()));
        result = ((result* 31)+((this.errors == null)? 0 :this.errors.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ResponseParam) == false) {
            return false;
        }
        ResponseParam rhs = ((ResponseParam) other);
        return ((((((((((((this.userInfo == rhs.userInfo)||((this.userInfo!= null)&&this.userInfo.equals(rhs.userInfo)))&&((this.transactionResponseTm == rhs.transactionResponseTm)||((this.transactionResponseTm!= null)&&this.transactionResponseTm.equals(rhs.transactionResponseTm))))&&((this.additionalParams == rhs.additionalParams)||((this.additionalParams!= null)&&this.additionalParams.equals(rhs.additionalParams))))&&((this.transactionResponseDt == rhs.transactionResponseDt)||((this.transactionResponseDt!= null)&&this.transactionResponseDt.equals(rhs.transactionResponseDt))))&&((this.xFDF == rhs.xFDF)||((this.xFDF!= null)&&this.xFDF.equals(rhs.xFDF))))&&((this.cMMParams == rhs.cMMParams)||((this.cMMParams!= null)&&this.cMMParams.equals(rhs.cMMParams))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.transactionTime == rhs.transactionTime)||((this.transactionTime!= null)&&this.transactionTime.equals(rhs.transactionTime))))&&((this.userId == rhs.userId)||((this.userId!= null)&&this.userId.equals(rhs.userId))))&&((this.errors == rhs.errors)||((this.errors!= null)&&this.errors.equals(rhs.errors))));
    }

}
