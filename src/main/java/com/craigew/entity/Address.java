package com.craigew.entity;

import javax.persistence.Entity;

@Entity
public class Address extends BaseEntity{
    private String addressLine1;
    private String addressLine2;
    private String suburb;
    private String postalCode;
    private String country;

    public Address(String addressLine1, String addressLine2, String suburb, String postalCode, String country) {
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.suburb = suburb;
        this.postalCode = postalCode;
        this.country = country;
    }

    public Address() {
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public String getSuburb() {
        return suburb;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}