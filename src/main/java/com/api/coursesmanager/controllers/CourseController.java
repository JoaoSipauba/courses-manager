package com.api.coursesmanager.controllers;

import com.api.coursesmanager.dtos.CourseDto;
import com.api.coursesmanager.models.CourseModel;
import com.api.coursesmanager.services.CourseService;
import com.api.coursesmanager.services.ReportService;
import com.api.coursesmanager.services.StudentService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private ReportService reportService;

    @PostMapping
    public ResponseEntity<Object> saveCourse(@RequestBody @Valid CourseDto courseDto ){
        var courseModel = new CourseModel();
        BeanUtils.copyProperties(courseDto, courseModel);
        courseModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.save(courseModel));
    }

    @GetMapping
    public ResponseEntity<Object> getAllCourses(){
        return ResponseEntity.status(HttpStatus.OK).body(courseService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneCourse(@PathVariable(value = "id") UUID id){
        Optional<CourseModel> courseModelOptional = courseService.findById(id);
        if (!courseModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(courseModelOptional.get());
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<Object> getStudentsByCourse(@PathVariable(value = "id") UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(studentService.findByCourseId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCourse(@PathVariable(value = "id") UUID id,
                                               @RequestBody @Valid CourseDto courseDto){
        Optional<CourseModel> courseModelOptional = courseService.findById(id);
        if (!courseModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found.");
        }
        var courseModel = new CourseModel();
        BeanUtils.copyProperties(courseDto, courseModel);
        courseModel.setId(courseModelOptional.get().getId());
        courseModel.setRegistrationDate(courseModelOptional.get().getRegistrationDate());
        return ResponseEntity.status(HttpStatus.OK).body(courseService.save(courseModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCourse(@PathVariable(value = "id") UUID id){
        Optional<CourseModel> courseModelOptional = courseService.findById(id);
        if (!courseModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found.");
        }
        courseService.delete(courseModelOptional.get());

        return ResponseEntity.status(HttpStatus.OK).body("Course deleted successfully.");
    }

    @PostMapping("/report")
    public ResponseEntity<Object> generateCoursesReport() throws FileNotFoundException, JRException {

        reportService.exportCourses();

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
