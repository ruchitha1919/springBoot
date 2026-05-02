package com.studentcourse.repository;

import com.studentcourse.entity.Student;
import com.studentcourse.entity.StudentWithCourseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT new com.studentcourse.entity.StudentWithCourseDTO(s.id, s.name, s.email, c.id, c.name, c.credits) " +
           "FROM Student s INNER JOIN s.course c")
    List<StudentWithCourseDTO> fetchStudentsWithCourseInfo();

    List<Student> findByNameContainingIgnoreCase(String name);
}
