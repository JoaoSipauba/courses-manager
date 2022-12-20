package com.api.coursesmanager.dtos;

import com.api.coursesmanager.models.AddressModel;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class StudentDto {

    @NotBlank()
    private String name;
    @NotBlank()
    @Min(value = 11)
    private String cpf;
    @NotNull()
    @Valid
    private AddressModel address;
    @NotBlank()
    private String email;
    @NotBlank()
    @Min(value = 11)
    private String phone;
    @NotNull()
    private UUID course;

}
