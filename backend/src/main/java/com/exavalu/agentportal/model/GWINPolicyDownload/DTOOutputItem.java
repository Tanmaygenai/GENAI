
package com.exavalu.agentportal.model.GWINPolicyDownload;

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
    "id",
    "includeInd",
    "itemName",
    "itemDescription",
    "name",
    "deliveryCd",
    "defaultInd",
    "mergeFileURL",
    "formURL",
    "mailProofCd",
    "coversheetFormTemplateIdRef",
    "outputTemplateRecipientIdRef",
    "documentTypeCd",
    "numSimplexPrintPages",
    "numDuplexPrintPages",
    "printEngineCd",
    "sourceIdRef",
    "pDFURL",
    "partyInfo",
    "formCd"
})
@Generated("jsonschema2pojo")
public class DTOOutputItem {

    @JsonProperty("id")
    private String id;
    @JsonProperty("includeInd")
    private Boolean includeInd;
    @JsonProperty("itemName")
    private String itemName;
    @JsonProperty("itemDescription")
    private String itemDescription;
    @JsonProperty("name")
    private String name;
    @JsonProperty("deliveryCd")
    private String deliveryCd;
    @JsonProperty("defaultInd")
    private Boolean defaultInd;
    @JsonProperty("mergeFileURL")
    private String mergeFileURL;
    @JsonProperty("formURL")
    private String formURL;
    @JsonProperty("mailProofCd")
    private String mailProofCd;
    @JsonProperty("coversheetFormTemplateIdRef")
    private String coversheetFormTemplateIdRef;
    @JsonProperty("outputTemplateRecipientIdRef")
    private String outputTemplateRecipientIdRef;
    @JsonProperty("documentTypeCd")
    private String documentTypeCd;
    @JsonProperty("numSimplexPrintPages")
    private Integer numSimplexPrintPages;
    @JsonProperty("numDuplexPrintPages")
    private Integer numDuplexPrintPages;
    @JsonProperty("printEngineCd")
    private String printEngineCd;
    @JsonProperty("sourceIdRef")
    private String sourceIdRef;
    @JsonProperty("pDFURL")
    private String pDFURL;
    @JsonProperty("partyInfo")
    private PartyInfo__1 partyInfo;
    @JsonProperty("formCd")
    private String formCd;
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

    public DTOOutputItem withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("includeInd")
    public Boolean getIncludeInd() {
        return includeInd;
    }

    @JsonProperty("includeInd")
    public void setIncludeInd(Boolean includeInd) {
        this.includeInd = includeInd;
    }

    public DTOOutputItem withIncludeInd(Boolean includeInd) {
        this.includeInd = includeInd;
        return this;
    }

    @JsonProperty("itemName")
    public String getItemName() {
        return itemName;
    }

    @JsonProperty("itemName")
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public DTOOutputItem withItemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    @JsonProperty("itemDescription")
    public String getItemDescription() {
        return itemDescription;
    }

    @JsonProperty("itemDescription")
    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public DTOOutputItem withItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
        return this;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    public DTOOutputItem withName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("deliveryCd")
    public String getDeliveryCd() {
        return deliveryCd;
    }

    @JsonProperty("deliveryCd")
    public void setDeliveryCd(String deliveryCd) {
        this.deliveryCd = deliveryCd;
    }

    public DTOOutputItem withDeliveryCd(String deliveryCd) {
        this.deliveryCd = deliveryCd;
        return this;
    }

    @JsonProperty("defaultInd")
    public Boolean getDefaultInd() {
        return defaultInd;
    }

    @JsonProperty("defaultInd")
    public void setDefaultInd(Boolean defaultInd) {
        this.defaultInd = defaultInd;
    }

    public DTOOutputItem withDefaultInd(Boolean defaultInd) {
        this.defaultInd = defaultInd;
        return this;
    }

    @JsonProperty("mergeFileURL")
    public String getMergeFileURL() {
        return mergeFileURL;
    }

    @JsonProperty("mergeFileURL")
    public void setMergeFileURL(String mergeFileURL) {
        this.mergeFileURL = mergeFileURL;
    }

    public DTOOutputItem withMergeFileURL(String mergeFileURL) {
        this.mergeFileURL = mergeFileURL;
        return this;
    }

    @JsonProperty("formURL")
    public String getFormURL() {
        return formURL;
    }

    @JsonProperty("formURL")
    public void setFormURL(String formURL) {
        this.formURL = formURL;
    }

    public DTOOutputItem withFormURL(String formURL) {
        this.formURL = formURL;
        return this;
    }

    @JsonProperty("mailProofCd")
    public String getMailProofCd() {
        return mailProofCd;
    }

    @JsonProperty("mailProofCd")
    public void setMailProofCd(String mailProofCd) {
        this.mailProofCd = mailProofCd;
    }

    public DTOOutputItem withMailProofCd(String mailProofCd) {
        this.mailProofCd = mailProofCd;
        return this;
    }

    @JsonProperty("coversheetFormTemplateIdRef")
    public String getCoversheetFormTemplateIdRef() {
        return coversheetFormTemplateIdRef;
    }

    @JsonProperty("coversheetFormTemplateIdRef")
    public void setCoversheetFormTemplateIdRef(String coversheetFormTemplateIdRef) {
        this.coversheetFormTemplateIdRef = coversheetFormTemplateIdRef;
    }

    public DTOOutputItem withCoversheetFormTemplateIdRef(String coversheetFormTemplateIdRef) {
        this.coversheetFormTemplateIdRef = coversheetFormTemplateIdRef;
        return this;
    }

    @JsonProperty("outputTemplateRecipientIdRef")
    public String getOutputTemplateRecipientIdRef() {
        return outputTemplateRecipientIdRef;
    }

    @JsonProperty("outputTemplateRecipientIdRef")
    public void setOutputTemplateRecipientIdRef(String outputTemplateRecipientIdRef) {
        this.outputTemplateRecipientIdRef = outputTemplateRecipientIdRef;
    }

    public DTOOutputItem withOutputTemplateRecipientIdRef(String outputTemplateRecipientIdRef) {
        this.outputTemplateRecipientIdRef = outputTemplateRecipientIdRef;
        return this;
    }

    @JsonProperty("documentTypeCd")
    public String getDocumentTypeCd() {
        return documentTypeCd;
    }

    @JsonProperty("documentTypeCd")
    public void setDocumentTypeCd(String documentTypeCd) {
        this.documentTypeCd = documentTypeCd;
    }

    public DTOOutputItem withDocumentTypeCd(String documentTypeCd) {
        this.documentTypeCd = documentTypeCd;
        return this;
    }

    @JsonProperty("numSimplexPrintPages")
    public Integer getNumSimplexPrintPages() {
        return numSimplexPrintPages;
    }

    @JsonProperty("numSimplexPrintPages")
    public void setNumSimplexPrintPages(Integer numSimplexPrintPages) {
        this.numSimplexPrintPages = numSimplexPrintPages;
    }

    public DTOOutputItem withNumSimplexPrintPages(Integer numSimplexPrintPages) {
        this.numSimplexPrintPages = numSimplexPrintPages;
        return this;
    }

    @JsonProperty("numDuplexPrintPages")
    public Integer getNumDuplexPrintPages() {
        return numDuplexPrintPages;
    }

    @JsonProperty("numDuplexPrintPages")
    public void setNumDuplexPrintPages(Integer numDuplexPrintPages) {
        this.numDuplexPrintPages = numDuplexPrintPages;
    }

    public DTOOutputItem withNumDuplexPrintPages(Integer numDuplexPrintPages) {
        this.numDuplexPrintPages = numDuplexPrintPages;
        return this;
    }

    @JsonProperty("printEngineCd")
    public String getPrintEngineCd() {
        return printEngineCd;
    }

    @JsonProperty("printEngineCd")
    public void setPrintEngineCd(String printEngineCd) {
        this.printEngineCd = printEngineCd;
    }

    public DTOOutputItem withPrintEngineCd(String printEngineCd) {
        this.printEngineCd = printEngineCd;
        return this;
    }

    @JsonProperty("sourceIdRef")
    public String getSourceIdRef() {
        return sourceIdRef;
    }

    @JsonProperty("sourceIdRef")
    public void setSourceIdRef(String sourceIdRef) {
        this.sourceIdRef = sourceIdRef;
    }

    public DTOOutputItem withSourceIdRef(String sourceIdRef) {
        this.sourceIdRef = sourceIdRef;
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

    public DTOOutputItem withpDFURL(String pDFURL) {
        this.pDFURL = pDFURL;
        return this;
    }

    @JsonProperty("partyInfo")
    public PartyInfo__1 getPartyInfo() {
        return partyInfo;
    }

    @JsonProperty("partyInfo")
    public void setPartyInfo(PartyInfo__1 partyInfo) {
        this.partyInfo = partyInfo;
    }

    public DTOOutputItem withPartyInfo(PartyInfo__1 partyInfo) {
        this.partyInfo = partyInfo;
        return this;
    }

    @JsonProperty("formCd")
    public String getFormCd() {
        return formCd;
    }

    @JsonProperty("formCd")
    public void setFormCd(String formCd) {
        this.formCd = formCd;
    }

    public DTOOutputItem withFormCd(String formCd) {
        this.formCd = formCd;
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

    public DTOOutputItem withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(DTOOutputItem.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("includeInd");
        sb.append('=');
        sb.append(((this.includeInd == null)?"<null>":this.includeInd));
        sb.append(',');
        sb.append("itemName");
        sb.append('=');
        sb.append(((this.itemName == null)?"<null>":this.itemName));
        sb.append(',');
        sb.append("itemDescription");
        sb.append('=');
        sb.append(((this.itemDescription == null)?"<null>":this.itemDescription));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("deliveryCd");
        sb.append('=');
        sb.append(((this.deliveryCd == null)?"<null>":this.deliveryCd));
        sb.append(',');
        sb.append("defaultInd");
        sb.append('=');
        sb.append(((this.defaultInd == null)?"<null>":this.defaultInd));
        sb.append(',');
        sb.append("mergeFileURL");
        sb.append('=');
        sb.append(((this.mergeFileURL == null)?"<null>":this.mergeFileURL));
        sb.append(',');
        sb.append("formURL");
        sb.append('=');
        sb.append(((this.formURL == null)?"<null>":this.formURL));
        sb.append(',');
        sb.append("mailProofCd");
        sb.append('=');
        sb.append(((this.mailProofCd == null)?"<null>":this.mailProofCd));
        sb.append(',');
        sb.append("coversheetFormTemplateIdRef");
        sb.append('=');
        sb.append(((this.coversheetFormTemplateIdRef == null)?"<null>":this.coversheetFormTemplateIdRef));
        sb.append(',');
        sb.append("outputTemplateRecipientIdRef");
        sb.append('=');
        sb.append(((this.outputTemplateRecipientIdRef == null)?"<null>":this.outputTemplateRecipientIdRef));
        sb.append(',');
        sb.append("documentTypeCd");
        sb.append('=');
        sb.append(((this.documentTypeCd == null)?"<null>":this.documentTypeCd));
        sb.append(',');
        sb.append("numSimplexPrintPages");
        sb.append('=');
        sb.append(((this.numSimplexPrintPages == null)?"<null>":this.numSimplexPrintPages));
        sb.append(',');
        sb.append("numDuplexPrintPages");
        sb.append('=');
        sb.append(((this.numDuplexPrintPages == null)?"<null>":this.numDuplexPrintPages));
        sb.append(',');
        sb.append("printEngineCd");
        sb.append('=');
        sb.append(((this.printEngineCd == null)?"<null>":this.printEngineCd));
        sb.append(',');
        sb.append("sourceIdRef");
        sb.append('=');
        sb.append(((this.sourceIdRef == null)?"<null>":this.sourceIdRef));
        sb.append(',');
        sb.append("pDFURL");
        sb.append('=');
        sb.append(((this.pDFURL == null)?"<null>":this.pDFURL));
        sb.append(',');
        sb.append("partyInfo");
        sb.append('=');
        sb.append(((this.partyInfo == null)?"<null>":this.partyInfo));
        sb.append(',');
        sb.append("formCd");
        sb.append('=');
        sb.append(((this.formCd == null)?"<null>":this.formCd));
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
        result = ((result* 31)+((this.mailProofCd == null)? 0 :this.mailProofCd.hashCode()));
        result = ((result* 31)+((this.pDFURL == null)? 0 :this.pDFURL.hashCode()));
        result = ((result* 31)+((this.includeInd == null)? 0 :this.includeInd.hashCode()));
        result = ((result* 31)+((this.deliveryCd == null)? 0 :this.deliveryCd.hashCode()));
        result = ((result* 31)+((this.mergeFileURL == null)? 0 :this.mergeFileURL.hashCode()));
        result = ((result* 31)+((this.outputTemplateRecipientIdRef == null)? 0 :this.outputTemplateRecipientIdRef.hashCode()));
        result = ((result* 31)+((this.sourceIdRef == null)? 0 :this.sourceIdRef.hashCode()));
        result = ((result* 31)+((this.defaultInd == null)? 0 :this.defaultInd.hashCode()));
        result = ((result* 31)+((this.documentTypeCd == null)? 0 :this.documentTypeCd.hashCode()));
        result = ((result* 31)+((this.numDuplexPrintPages == null)? 0 :this.numDuplexPrintPages.hashCode()));
        result = ((result* 31)+((this.itemName == null)? 0 :this.itemName.hashCode()));
        result = ((result* 31)+((this.partyInfo == null)? 0 :this.partyInfo.hashCode()));
        result = ((result* 31)+((this.formURL == null)? 0 :this.formURL.hashCode()));
        result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
        result = ((result* 31)+((this.numSimplexPrintPages == null)? 0 :this.numSimplexPrintPages.hashCode()));
        result = ((result* 31)+((this.coversheetFormTemplateIdRef == null)? 0 :this.coversheetFormTemplateIdRef.hashCode()));
        result = ((result* 31)+((this.printEngineCd == null)? 0 :this.printEngineCd.hashCode()));
        result = ((result* 31)+((this.formCd == null)? 0 :this.formCd.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.itemDescription == null)? 0 :this.itemDescription.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof DTOOutputItem) == false) {
            return false;
        }
        DTOOutputItem rhs = ((DTOOutputItem) other);
        return ((((((((((((((((((((((this.mailProofCd == rhs.mailProofCd)||((this.mailProofCd!= null)&&this.mailProofCd.equals(rhs.mailProofCd)))&&((this.pDFURL == rhs.pDFURL)||((this.pDFURL!= null)&&this.pDFURL.equals(rhs.pDFURL))))&&((this.includeInd == rhs.includeInd)||((this.includeInd!= null)&&this.includeInd.equals(rhs.includeInd))))&&((this.deliveryCd == rhs.deliveryCd)||((this.deliveryCd!= null)&&this.deliveryCd.equals(rhs.deliveryCd))))&&((this.mergeFileURL == rhs.mergeFileURL)||((this.mergeFileURL!= null)&&this.mergeFileURL.equals(rhs.mergeFileURL))))&&((this.outputTemplateRecipientIdRef == rhs.outputTemplateRecipientIdRef)||((this.outputTemplateRecipientIdRef!= null)&&this.outputTemplateRecipientIdRef.equals(rhs.outputTemplateRecipientIdRef))))&&((this.sourceIdRef == rhs.sourceIdRef)||((this.sourceIdRef!= null)&&this.sourceIdRef.equals(rhs.sourceIdRef))))&&((this.defaultInd == rhs.defaultInd)||((this.defaultInd!= null)&&this.defaultInd.equals(rhs.defaultInd))))&&((this.documentTypeCd == rhs.documentTypeCd)||((this.documentTypeCd!= null)&&this.documentTypeCd.equals(rhs.documentTypeCd))))&&((this.numDuplexPrintPages == rhs.numDuplexPrintPages)||((this.numDuplexPrintPages!= null)&&this.numDuplexPrintPages.equals(rhs.numDuplexPrintPages))))&&((this.itemName == rhs.itemName)||((this.itemName!= null)&&this.itemName.equals(rhs.itemName))))&&((this.partyInfo == rhs.partyInfo)||((this.partyInfo!= null)&&this.partyInfo.equals(rhs.partyInfo))))&&((this.formURL == rhs.formURL)||((this.formURL!= null)&&this.formURL.equals(rhs.formURL))))&&((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name))))&&((this.numSimplexPrintPages == rhs.numSimplexPrintPages)||((this.numSimplexPrintPages!= null)&&this.numSimplexPrintPages.equals(rhs.numSimplexPrintPages))))&&((this.coversheetFormTemplateIdRef == rhs.coversheetFormTemplateIdRef)||((this.coversheetFormTemplateIdRef!= null)&&this.coversheetFormTemplateIdRef.equals(rhs.coversheetFormTemplateIdRef))))&&((this.printEngineCd == rhs.printEngineCd)||((this.printEngineCd!= null)&&this.printEngineCd.equals(rhs.printEngineCd))))&&((this.formCd == rhs.formCd)||((this.formCd!= null)&&this.formCd.equals(rhs.formCd))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.itemDescription == rhs.itemDescription)||((this.itemDescription!= null)&&this.itemDescription.equals(rhs.itemDescription))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}
