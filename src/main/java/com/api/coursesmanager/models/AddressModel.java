package com.api.coursesmanager.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Embeddable
@Data
public class AddressModel {
    @Column(nullable = false)
    @NotBlank
    private String address;
    @Column(nullable = false, length = 8)
    @NotBlank
    @Min(value = 8)
    private String cep;

}
