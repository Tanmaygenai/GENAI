
package com.exavalu.agentportal.model.GWINPolicy;

import java.util.ArrayList;
import java.util.List;

public class PartyInfo {


    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }


    private List<Address> addresses = new ArrayList<Address>();



}
