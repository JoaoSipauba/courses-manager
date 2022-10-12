package com.api.coursesmanager.services;

import com.api.coursesmanager.models.CourseModel;
import com.api.coursesmanager.repositories.CourseRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CourseService {
    final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Transactional
    public CourseModel save(CourseModel courseModel){
        return courseRepository.save(courseModel);
    }

    public List<CourseModel> findAll(){
        return courseRepository.findAll();
    }

    public Optional<CourseModel> findById(UUID id) {
        return courseRepository.findById(id);
    }

    public void delete(CourseModel courseModel) {
        courseRepository.delete(courseModel);
    }
}
