package com.redhat.demos.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "address")
public class AddressEntity {

    @Id @GeneratedValue
    private Long id;

    private String street;

    private String street2;

    private String city;

    private String stateOrProvince;

    private String postCode;

    private String countryCode;

    protected AddressEntity() {
    }

    protected AddressEntity(String street, String street2, String city, String stateOrProvince, String postCode, String countryCode) {
        this.street = street;
        this.street2 = street2;
        this.city = city;
        this.stateOrProvince = stateOrProvince;
        this.postCode = postCode;
        this.countryCode = countryCode;
    }

    Long getId() {
        return id;
    }

    void setId(Long id) {
        this.id = id;
    }

    String getStreet() {
        return street;
    }

    void setStreet(String street) {
        this.street = street;
    }

    String getStreet2() {
        return street2;
    }

    void setStreet2(String street2) {
        this.street2 = street2;
    }

    String getCity() {
        return city;
    }

    void setCity(String city) {
        this.city = city;
    }

    String getStateOrProvince() {
        return stateOrProvince;
    }

    void setStateOrProvince(String stateOrProvince) {
        this.stateOrProvince = stateOrProvince;
    }

    String getPostCode() {
        return postCode;
    }

    void setPostCode(String postCode) {
        this.postCode = postCode;
    }
}
