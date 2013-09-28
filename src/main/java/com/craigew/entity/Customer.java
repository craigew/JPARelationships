package com.craigew.entity;

import javax.persistence.*;

@Entity
public class Customer extends BaseEntity{
    private String name;
    private String surname;
    private String identity_number;
    private int age;
    @OneToOne(cascade = CascadeType.ALL, optional=false)
    @JoinColumn(name="RESIDENTIAL_ADDRESS_ID")
    private Address residentialAddress;
    @OneToOne(cascade = CascadeType.ALL, optional=true)
    @JoinColumn(name="POSTAL_ADDRESS_ID")
    private Address postalAddress;

    public Customer(String name, String surname, String identity_number, int age, Address residentialAddress) {
        this.name = name;
        this.surname = surname;
        this.identity_number = identity_number;
        this.age = age;
        this.residentialAddress = residentialAddress;
    }

    protected Customer() {
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getIdentity_number() {
        return identity_number;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getResidentialAddress() {
        return residentialAddress;
    }

    public void setResidentialAddress(Address residentialAddress) {
        this.residentialAddress = residentialAddress;
    }

    public Address getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(Address postalAddress) {
        this.postalAddress = postalAddress;
    }
}
