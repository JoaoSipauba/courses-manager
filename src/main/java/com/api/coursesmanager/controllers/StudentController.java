package com.api.coursesmanager.controllers;

import com.api.coursesmanager.dtos.StudentDto;
import com.api.coursesmanager.models.CourseModel;
import com.api.coursesmanager.models.StudentModel;
import com.api.coursesmanager.services.CourseService;
import com.api.coursesmanager.services.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {
    final StudentService studentService;
    final CourseService courseService;

    public StudentController(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<Object> saveStudent(@RequestBody @Valid StudentDto studentDto ){
        var studentModel = new StudentModel();
        var courseId = studentDto.getCourse();
        Optional<CourseModel> courseModelOptional = courseService.findById(courseId);

        if (!courseModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found.");
        }
        if (studentService.existsByCpf(studentDto.getCpf())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: cpf is already in use!");
        }
        if (studentService.existsByEmail(studentDto.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: email is already in use!");
        }
        if (studentService.existsByPhone(studentDto.getPhone())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: phone is already in use!");
        }

        BeanUtils.copyProperties(studentDto, studentModel);
        studentModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        studentModel.setCourse(courseModelOptional.get());
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.save(studentModel));
    }
}
