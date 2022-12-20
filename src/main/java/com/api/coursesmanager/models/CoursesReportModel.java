package com.api.coursesmanager.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoursesReportModel {
    private String courseName;
    private Integer courseWorkload;
    private Long students;
}
