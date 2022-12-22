package com.api.coursesmanager.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "TB_STUDENT")
public class StudentModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, length = 11, unique = true)
    private String cpf;
    @Embedded
    private AddressModel address;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, length = 11, unique = true)
    private String phone;
    @Column(nullable = false)
    private LocalDateTime registrationDate;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private CourseModel course;
}
