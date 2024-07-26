
package com.exavalu.agentportal.model.GWINPolicy;

public class PolicyMini {


    private String id;

    private String systemId;

    private String customerRef;

    private String accountRef;

    private String version;

    private BasicPolicy basicPolicy;

    private Insured insured;




    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }



    public String getSystemId() {
        return systemId;
    }


    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }




    public String getCustomerRef() {
        return customerRef;
    }


    public void setCustomerRef(String customerRef) {
        this.customerRef = customerRef;
    }




    public String getAccountRef() {
        return accountRef;
    }


    public void setAccountRef(String accountRef) {
        this.accountRef = accountRef;
    }



    public String getVersion() {
        return version;
    }


    public void setVersion(String version) {
        this.version = version;
    }



    public BasicPolicy getBasicPolicy() {
        return basicPolicy;
    }

    public void setBasicPolicy(BasicPolicy basicPolicy) {
        this.basicPolicy = basicPolicy;
    }




    public Insured getInsured() {
        return insured;
    }


    public void setInsured(Insured insured) {
        this.insured = insured;
    }






}
