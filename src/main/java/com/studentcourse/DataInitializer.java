package com.studentcourse;

import com.studentcourse.entity.Course;
import com.studentcourse.entity.Student;
import com.studentcourse.repository.CourseRepository;
import com.studentcourse.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void run(String... args) throws Exception {
        if (courseRepository.count() > 0) return;

        // Create 10 Courses
        Course c1 = new Course("Computer Science", 4);
        Course c2 = new Course("Mathematics", 3);
        Course c3 = new Course("Physics", 4);
        Course c4 = new Course("Chemistry", 3);
        Course c5 = new Course("Biology", 3);
        Course c6 = new Course("Literature", 2);
        Course c7 = new Course("History", 2);
        Course c8 = new Course("Economics", 3);
        Course c9 = new Course("Art", 2);
        Course c10 = new Course("Philosophy", 2);

        courseRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10));

        // Create 10 Students
        studentRepository.save(new Student("Alice Johnson", "alice@test.com", c1));
        studentRepository.save(new Student("Bob Smith", "bob@test.com", c1));
        studentRepository.save(new Student("Charlie Brown", "charlie@test.com", c2));
        studentRepository.save(new Student("Diana Prince", "diana@test.com", c3));
        studentRepository.save(new Student("Ethan Hunt", "ethan@test.com", c4));
        studentRepository.save(new Student("Fiona Apple", "fiona@test.com", c5));
        studentRepository.save(new Student("George Miller", "george@test.com", c6));
        studentRepository.save(new Student("Hannah Abbott", "hannah@test.com", c7));
        studentRepository.save(new Student("Ian Wright", "ian@test.com", c8));
        studentRepository.save(new Student("Jenny Slate", "jenny@test.com", c9));
    }
}
