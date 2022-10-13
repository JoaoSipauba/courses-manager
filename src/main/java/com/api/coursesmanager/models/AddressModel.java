package com.api.coursesmanager.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AddressModel {
    @Column(nullable = false)
    private String address;
    @Column(nullable = false, length = 8)
    private String cep;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
