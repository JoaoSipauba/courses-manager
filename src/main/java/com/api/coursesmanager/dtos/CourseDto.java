package com.api.coursesmanager.dtos;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
public class CourseDto {
    @NotBlank(message = "CourseName cannot be empty.")
    private String courseName;
    @NotNull(message = "CourseWorkload cannot be empty.")
    @Min(value = 10, message = "CourseWorkload should be greater than 10.")
    private Integer courseWorkload;

}
