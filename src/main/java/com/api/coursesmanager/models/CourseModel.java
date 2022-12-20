package com.api.coursesmanager.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "TB_COURSE")
public class CourseModel implements Serializable {
    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, length = 60)
    private String courseName;
    @Column(nullable = false, length = 6)
    private Integer courseWorkload;
    @Column(nullable = false)
    private LocalDateTime registrationDate;
}
