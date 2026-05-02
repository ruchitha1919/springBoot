package com.studentcourse.service;

import com.studentcourse.entity.Student;
import com.studentcourse.entity.StudentWithCourseDTO;
import com.studentcourse.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public List<StudentWithCourseDTO> getAllWithCourseDetails() {
        return studentRepository.fetchStudentsWithCourseInfo();
    }

    public List<StudentWithCourseDTO> searchByNameWithDetails(String name) {
        return studentRepository.searchStudentsWithCourseInfo(name);
    }

    public Student getById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
    }

    public void save(Student student) {
        studentRepository.save(student);
    }

    public void update(Long id, Student updatedStudent) {
        Student student = getById(id);
        student.setName(updatedStudent.getName());
        student.setEmail(updatedStudent.getEmail());
        student.setCourse(updatedStudent.getCourse());
        studentRepository.save(student);
    }

    public void delete(Long id) {
        studentRepository.deleteById(id);
    }
}
