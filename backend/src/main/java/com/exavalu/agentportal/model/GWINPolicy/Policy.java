
package com.exavalu.agentportal.model.GWINPolicy;

import java.util.ArrayList;

import java.util.List;

public class Policy {

    private List<PolicyListItem> policyListItems = new ArrayList<PolicyListItem>();
    public List<PolicyListItem> getPolicyListItems() {
        return policyListItems;
    }
    public void setPolicyListItems(List<PolicyListItem> policyListItems) {
        this.policyListItems = policyListItems;
    }


}
