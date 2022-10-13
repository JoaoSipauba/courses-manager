package com.api.coursesmanager.controllers;

import com.api.coursesmanager.dtos.StudentDto;
import com.api.coursesmanager.models.CourseModel;
import com.api.coursesmanager.models.StudentModel;
import com.api.coursesmanager.services.CourseService;
import com.api.coursesmanager.services.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/students")
public class StudentController {
    final StudentService studentService;
    final CourseService courseService;

    public StudentController(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Object> saveStudent(@RequestBody @Valid StudentDto studentDto ){
        var studentModel = new StudentModel();
        var courseId = studentDto.getCourse();
        Optional<CourseModel> courseModelOptional = courseService.findById(courseId);

        if (courseModelOptional.isEmpty()){
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

    @GetMapping
    public ResponseEntity<Object> getAllStudents(){
        return ResponseEntity.status(HttpStatus.OK).body(studentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getStudentById(@PathVariable(value = "id") UUID id){
        Optional<StudentModel> studentModelOptional = studentService.findById(id);
        if (studentModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(studentModelOptional.get());
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateStudent(@PathVariable(value = "id") UUID id,
                                              @RequestBody @Valid StudentDto studentDto ){
        Optional<StudentModel> studentModelOptional = studentService.findById(id);
        if (studentModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found.");
        }

        var studentModel = new StudentModel();
        BeanUtils.copyProperties(studentDto, studentModel);
        studentModel.setId(studentModelOptional.get().getId());
        studentModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        studentModel.setCourse(studentModelOptional.get().getCourse());

        return ResponseEntity.status(HttpStatus.OK).body(studentService.save(studentModel));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteStudent(@PathVariable(value = "id") UUID id){
        Optional<StudentModel> studentModelOptional = studentService.findById(id);
        if (studentModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found.");
        }

        studentService.delete(studentModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body("Student deleted successfully.");
    }
}
