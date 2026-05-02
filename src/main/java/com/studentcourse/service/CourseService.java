package com.studentcourse.service;

import com.studentcourse.entity.Course;
import com.studentcourse.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    public Course getById(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));
    }

    public void save(Course course) {
        courseRepository.save(course);
    }

    public void update(Long id, Course updatedCourse) {
        Course course = getById(id);
        course.setName(updatedCourse.getName());
        course.setCredits(updatedCourse.getCredits());
        courseRepository.save(course);
    }

    public void delete(Long id) {
        courseRepository.deleteById(id);
    }
}
