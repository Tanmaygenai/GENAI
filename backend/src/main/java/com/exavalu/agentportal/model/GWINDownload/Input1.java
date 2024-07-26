
package com.exavalu.agentportal.model.GWINDownload;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;




public class Input1 {

    private List<DocumentListItem> documentListItems = new ArrayList<DocumentListItem>();

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public List<DocumentListItem> getDocumentListItems() {
        return documentListItems;
    }

    public void setDocumentListItems(List<DocumentListItem> documentListItems) {
        this.documentListItems = documentListItems;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }


}
