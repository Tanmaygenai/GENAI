
package com.exavalu.agentportal.model.GWINDownload;

import java.util.HashMap;
import java.util.Map;


public class Link {

    private String rel;

    private String href;

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }


    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
