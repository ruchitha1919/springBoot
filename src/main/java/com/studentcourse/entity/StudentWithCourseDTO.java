package com.studentcourse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentWithCourseDTO {
    private Long id;
    private String name;
    private String email;
    private Long courseId;
    private String courseName;
    private Integer courseCredits;
}
