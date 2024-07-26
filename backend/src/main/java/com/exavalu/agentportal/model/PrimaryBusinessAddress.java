package com.exavalu.agentportal.model;

public class PrimaryBusinessAddress {
private String address1;
private String address2;

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    private String postcode;

    @Override
    public String toString() {
        return "PrimaryBusinessAddress{" +
                "address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", postcode='" + postcode + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    private String state;
private String city;
private String country;
}
