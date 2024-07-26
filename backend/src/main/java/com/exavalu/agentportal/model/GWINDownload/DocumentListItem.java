
package com.exavalu.agentportal.model.GWINDownload;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocumentListItem {


    private String ref;

    private String type;

    private String name;

    private String description;

    private String addDt;

    private String addTm;

    private String addUser;

    private Boolean canDeleteInd;

    private Boolean canViewInd;

    private String templateIdRef;

    private String transactionNumber;

    private List<Link> links = new ArrayList<Link>();

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddDt() {
        return addDt;
    }

    public void setAddDt(String addDt) {
        this.addDt = addDt;
    }

    public String getAddTm() {
        return addTm;
    }

    public void setAddTm(String addTm) {
        this.addTm = addTm;
    }

    public String getAddUser() {
        return addUser;
    }

    public void setAddUser(String addUser) {
        this.addUser = addUser;
    }

    public Boolean getCanDeleteInd() {
        return canDeleteInd;
    }

    public void setCanDeleteInd(Boolean canDeleteInd) {
        this.canDeleteInd = canDeleteInd;
    }

    public Boolean getCanViewInd() {
        return canViewInd;
    }

    public void setCanViewInd(Boolean canViewInd) {
        this.canViewInd = canViewInd;
    }

    public String getTemplateIdRef() {
        return templateIdRef;
    }

    public void setTemplateIdRef(String templateIdRef) {
        this.templateIdRef = templateIdRef;
    }

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }


}
