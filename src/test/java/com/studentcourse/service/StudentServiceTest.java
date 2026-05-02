package com.studentcourse.service;

import com.studentcourse.entity.Student;
import com.studentcourse.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    public void testSave() {
        Student s = new Student(null, "John", "john@test.com", null);
        when(studentRepository.save(any(Student.class))).thenReturn(new Student(1L, "John", "john@test.com", null));

        studentService.save(s);
        assertNotNull(s);
    }
}
