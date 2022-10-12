package com.api.coursesmanager.dtos;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class StudentDto {

    @NotBlank()
    private String studentName;
    @NotBlank()
    @Min(value = 11)
    private String cpf;
    @NotBlank()
    private String endereco;
    @NotBlank()
    @Min(value = 8)
    private String cep;
    @NotBlank()
    private String email;
    @NotBlank()
    @Min(value = 11)
    private String phone;
    @NotNull()
    private UUID course;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UUID getCourse() {
        return course;
    }

    public void setCourse(UUID courseId) {
        this.course = courseId;
    }
}
