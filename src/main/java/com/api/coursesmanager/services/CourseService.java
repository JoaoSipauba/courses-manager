package com.api.coursesmanager.services;

import com.api.coursesmanager.models.CourseModel;
import com.api.coursesmanager.repositories.CourseRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
}
