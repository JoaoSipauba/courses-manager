package com.api.coursesmanager.repositories;

import com.api.coursesmanager.models.CourseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface CourseRepository extends JpaRepository<CourseModel, UUID> {
    @Query(value = "SELECT tb_course.course_name AS courseName, tb_course.course_workload AS courseWorkload, (SELECT COUNT(*) FROM tb_student WHERE tb_student.course_id = tb_course.id) AS students FROM tb_course", nativeQuery = true)
    String[][] findAllCoursesAndStudentCount();
}
