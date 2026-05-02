package com.studentcourse.controller;

import com.studentcourse.entity.Course;
import com.studentcourse.entity.Student;
import com.studentcourse.service.CourseService;
import com.studentcourse.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @GetMapping("/")
    public String listStudents(@RequestParam(value = "search", required = false) String search, Model model) {
        try {
            List<Student> students;
            if (search != null && !search.trim().isEmpty()) {
                students = studentService.searchByName(search);
                model.addAttribute("pageTitle", "Search Results for '" + search + "'");
            } else {
                students = studentService.getAll();
                model.addAttribute("pageTitle", "All Students");
            }
            model.addAttribute("students", students);
            model.addAttribute("searchKeyword", search);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Could not load students: " + e.getMessage());
        }
        return "list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        try {
            List<Course> courses = courseService.getAll();
            model.addAttribute("student", new Student());
            model.addAttribute("courses", courses);
            model.addAttribute("pageTitle", "Add New Student");
            model.addAttribute("formAction", "/save");
            model.addAttribute("buttonLabel", "Add Student");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Could not load form: " + e.getMessage());
        }
        return "form";
    }

    @PostMapping("/save")
    public String saveStudent(@RequestParam("name") String name,
                             @RequestParam("email") String email,
                             @RequestParam("courseId") Long courseId,
                             RedirectAttributes redirectAttributes) {
        try {
            Course course = courseService.getById(courseId);
            Student student = new Student(name, email, course);
            studentService.save(student);
            redirectAttributes.addFlashAttribute("successMessage", "Student '" + name + "' added successfully!");
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Integrity violation: Duplicate email or invalid data.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error saving student: " + e.getMessage());
        }
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        try {
            Student student = studentService.getById(id);
            List<Course> courses = courseService.getAll();
            model.addAttribute("student", student);
            model.addAttribute("courses", courses);
            model.addAttribute("pageTitle", "Edit Student");
            model.addAttribute("formAction", "/update/" + id);
            model.addAttribute("buttonLabel", "Update Student");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Student not found: " + e.getMessage());
            return "list";
        }
        return "form";
    }

    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable Long id,
                               @RequestParam("name") String name,
                               @RequestParam("email") String email,
                               @RequestParam("courseId") Long courseId,
                               RedirectAttributes redirectAttributes) {
        try {
            Course course = courseService.getById(courseId);
            Student updatedStudent = new Student(name, email, course);
            studentService.update(id, updatedStudent);
            redirectAttributes.addFlashAttribute("successMessage", "Student updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error updating student: " + e.getMessage());
        }
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            studentService.delete(id);
            redirectAttributes.addFlashAttribute("successMessage", "Student deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error deleting student: " + e.getMessage());
        }
        return "redirect:/";
    }
}
