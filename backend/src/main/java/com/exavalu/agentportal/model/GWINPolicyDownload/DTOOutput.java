
package com.exavalu.agentportal.model.GWINPolicyDownload;

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

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "documentType",
    "outputTemplateIdRef",
    "outputNumber",
    "transactionNumber",
    "addUser",
    "addDt",
    "addTm",
    "status",
    "questionReplies",
    "dTOOutputItem",
    "submissionNumber"
})
@Generated("jsonschema2pojo")
public class DTOOutput {

    @JsonProperty("id")
    private String id;
    @JsonProperty("documentType")
    private String documentType;
    @JsonProperty("outputTemplateIdRef")
    private String outputTemplateIdRef;
    @JsonProperty("outputNumber")
    private String outputNumber;
    @JsonProperty("transactionNumber")
    private String transactionNumber;
    @JsonProperty("addUser")
    private String addUser;
    @JsonProperty("addDt")
    private String addDt;
    @JsonProperty("addTm")
    private String addTm;
    @JsonProperty("status")
    private String status;
    @JsonProperty("questionReplies")
    private QuestionReplies questionReplies;
    @JsonProperty("dTOOutputItem")
    private List<DTOOutputItem> dTOOutputItem = new ArrayList<DTOOutputItem>();
    @JsonProperty("submissionNumber")
    private String submissionNumber;
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

    public DTOOutput withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("documentType")
    public String getDocumentType() {
        return documentType;
    }

    @JsonProperty("documentType")
    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public DTOOutput withDocumentType(String documentType) {
        this.documentType = documentType;
        return this;
    }

    @JsonProperty("outputTemplateIdRef")
    public String getOutputTemplateIdRef() {
        return outputTemplateIdRef;
    }

    @JsonProperty("outputTemplateIdRef")
    public void setOutputTemplateIdRef(String outputTemplateIdRef) {
        this.outputTemplateIdRef = outputTemplateIdRef;
    }

    public DTOOutput withOutputTemplateIdRef(String outputTemplateIdRef) {
        this.outputTemplateIdRef = outputTemplateIdRef;
        return this;
    }

    @JsonProperty("outputNumber")
    public String getOutputNumber() {
        return outputNumber;
    }

    @JsonProperty("outputNumber")
    public void setOutputNumber(String outputNumber) {
        this.outputNumber = outputNumber;
    }

    public DTOOutput withOutputNumber(String outputNumber) {
        this.outputNumber = outputNumber;
        return this;
    }

    @JsonProperty("transactionNumber")
    public String getTransactionNumber() {
        return transactionNumber;
    }

    @JsonProperty("transactionNumber")
    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public DTOOutput withTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
        return this;
    }

    @JsonProperty("addUser")
    public String getAddUser() {
        return addUser;
    }

    @JsonProperty("addUser")
    public void setAddUser(String addUser) {
        this.addUser = addUser;
    }

    public DTOOutput withAddUser(String addUser) {
        this.addUser = addUser;
        return this;
    }

    @JsonProperty("addDt")
    public String getAddDt() {
        return addDt;
    }

    @JsonProperty("addDt")
    public void setAddDt(String addDt) {
        this.addDt = addDt;
    }

    public DTOOutput withAddDt(String addDt) {
        this.addDt = addDt;
        return this;
    }

    @JsonProperty("addTm")
    public String getAddTm() {
        return addTm;
    }

    @JsonProperty("addTm")
    public void setAddTm(String addTm) {
        this.addTm = addTm;
    }

    public DTOOutput withAddTm(String addTm) {
        this.addTm = addTm;
        return this;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    public DTOOutput withStatus(String status) {
        this.status = status;
        return this;
    }

    @JsonProperty("questionReplies")
    public QuestionReplies getQuestionReplies() {
        return questionReplies;
    }

    @JsonProperty("questionReplies")
    public void setQuestionReplies(QuestionReplies questionReplies) {
        this.questionReplies = questionReplies;
    }

    public DTOOutput withQuestionReplies(QuestionReplies questionReplies) {
        this.questionReplies = questionReplies;
        return this;
    }

    @JsonProperty("dTOOutputItem")
    public List<DTOOutputItem> getdTOOutputItem() {
        return dTOOutputItem;
    }

    @JsonProperty("dTOOutputItem")
    public void setdTOOutputItem(List<DTOOutputItem> dTOOutputItem) {
        this.dTOOutputItem = dTOOutputItem;
    }

    public DTOOutput withdTOOutputItem(List<DTOOutputItem> dTOOutputItem) {
        this.dTOOutputItem = dTOOutputItem;
        return this;
    }

    @JsonProperty("submissionNumber")
    public String getSubmissionNumber() {
        return submissionNumber;
    }

    @JsonProperty("submissionNumber")
    public void setSubmissionNumber(String submissionNumber) {
        this.submissionNumber = submissionNumber;
    }

    public DTOOutput withSubmissionNumber(String submissionNumber) {
        this.submissionNumber = submissionNumber;
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

    public DTOOutput withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(DTOOutput.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("documentType");
        sb.append('=');
        sb.append(((this.documentType == null)?"<null>":this.documentType));
        sb.append(',');
        sb.append("outputTemplateIdRef");
        sb.append('=');
        sb.append(((this.outputTemplateIdRef == null)?"<null>":this.outputTemplateIdRef));
        sb.append(',');
        sb.append("outputNumber");
        sb.append('=');
        sb.append(((this.outputNumber == null)?"<null>":this.outputNumber));
        sb.append(',');
        sb.append("transactionNumber");
        sb.append('=');
        sb.append(((this.transactionNumber == null)?"<null>":this.transactionNumber));
        sb.append(',');
        sb.append("addUser");
        sb.append('=');
        sb.append(((this.addUser == null)?"<null>":this.addUser));
        sb.append(',');
        sb.append("addDt");
        sb.append('=');
        sb.append(((this.addDt == null)?"<null>":this.addDt));
        sb.append(',');
        sb.append("addTm");
        sb.append('=');
        sb.append(((this.addTm == null)?"<null>":this.addTm));
        sb.append(',');
        sb.append("status");
        sb.append('=');
        sb.append(((this.status == null)?"<null>":this.status));
        sb.append(',');
        sb.append("questionReplies");
        sb.append('=');
        sb.append(((this.questionReplies == null)?"<null>":this.questionReplies));
        sb.append(',');
        sb.append("dTOOutputItem");
        sb.append('=');
        sb.append(((this.dTOOutputItem == null)?"<null>":this.dTOOutputItem));
        sb.append(',');
        sb.append("submissionNumber");
        sb.append('=');
        sb.append(((this.submissionNumber == null)?"<null>":this.submissionNumber));
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
        result = ((result* 31)+((this.submissionNumber == null)? 0 :this.submissionNumber.hashCode()));
        result = ((result* 31)+((this.documentType == null)? 0 :this.documentType.hashCode()));
        result = ((result* 31)+((this.addUser == null)? 0 :this.addUser.hashCode()));
        result = ((result* 31)+((this.transactionNumber == null)? 0 :this.transactionNumber.hashCode()));
        result = ((result* 31)+((this.outputNumber == null)? 0 :this.outputNumber.hashCode()));
        result = ((result* 31)+((this.addDt == null)? 0 :this.addDt.hashCode()));
        result = ((result* 31)+((this.dTOOutputItem == null)? 0 :this.dTOOutputItem.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.questionReplies == null)? 0 :this.questionReplies.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.outputTemplateIdRef == null)? 0 :this.outputTemplateIdRef.hashCode()));
        result = ((result* 31)+((this.addTm == null)? 0 :this.addTm.hashCode()));
        result = ((result* 31)+((this.status == null)? 0 :this.status.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof DTOOutput) == false) {
            return false;
        }
        DTOOutput rhs = ((DTOOutput) other);
        return ((((((((((((((this.submissionNumber == rhs.submissionNumber)||((this.submissionNumber!= null)&&this.submissionNumber.equals(rhs.submissionNumber)))&&((this.documentType == rhs.documentType)||((this.documentType!= null)&&this.documentType.equals(rhs.documentType))))&&((this.addUser == rhs.addUser)||((this.addUser!= null)&&this.addUser.equals(rhs.addUser))))&&((this.transactionNumber == rhs.transactionNumber)||((this.transactionNumber!= null)&&this.transactionNumber.equals(rhs.transactionNumber))))&&((this.outputNumber == rhs.outputNumber)||((this.outputNumber!= null)&&this.outputNumber.equals(rhs.outputNumber))))&&((this.addDt == rhs.addDt)||((this.addDt!= null)&&this.addDt.equals(rhs.addDt))))&&((this.dTOOutputItem == rhs.dTOOutputItem)||((this.dTOOutputItem!= null)&&this.dTOOutputItem.equals(rhs.dTOOutputItem))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.questionReplies == rhs.questionReplies)||((this.questionReplies!= null)&&this.questionReplies.equals(rhs.questionReplies))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.outputTemplateIdRef == rhs.outputTemplateIdRef)||((this.outputTemplateIdRef!= null)&&this.outputTemplateIdRef.equals(rhs.outputTemplateIdRef))))&&((this.addTm == rhs.addTm)||((this.addTm!= null)&&this.addTm.equals(rhs.addTm))))&&((this.status == rhs.status)||((this.status!= null)&&this.status.equals(rhs.status))));
    }

}
