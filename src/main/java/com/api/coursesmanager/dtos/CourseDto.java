package com.api.coursesmanager.dtos;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


public class CourseDto {
    @NotBlank(message = "CourseName cannot be empty.")
    private String courseName;
    @Min(value = 10, message = "CourseWorkload should be greater than 10.")
    private Number courseWorkload;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Number getCourseWorkload() {
        return courseWorkload;
    }

    public void setCourseWorkload(Number courseWorkload) {
        this.courseWorkload = courseWorkload;
    }
}
