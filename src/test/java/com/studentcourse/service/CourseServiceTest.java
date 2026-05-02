package com.studentcourse.service;

import com.studentcourse.entity.Course;
import com.studentcourse.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseService courseService;

    @Test
    public void testGetAll() {
        Course c1 = new Course(1L, "Java", 4, null);
        Course c2 = new Course(2L, "Spring", 5, null);
        when(courseRepository.findAll()).thenReturn(Arrays.asList(c1, c2));

        List<Course> result = courseService.getAll();
        assertEquals(2, result.size());
        assertEquals("Java", result.get(0).getName());
    }
}
