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

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @GetMapping("/")
    public String listStudents(@RequestParam(value = "search", required = false) String search, Model model) {
        try {
            if (search != null && !search.trim().isEmpty()) {
                model.addAttribute("students", studentService.searchByNameWithDetails(search));
                model.addAttribute("pageTitle", "Search Results for '" + search + "'");
            } else {
                model.addAttribute("students", studentService.getAllWithCourseDetails());
                model.addAttribute("pageTitle", "All Students");
            }
            model.addAttribute("searchKeyword", search);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Could not load students: " + e.getMessage());
        }
        return "list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        try {
            model.addAttribute("student", new Student());
            model.addAttribute("courses", courseService.getAll());
            model.addAttribute("pageTitle", "Add New Student");
            model.addAttribute("formAction", "/save");
            model.addAttribute("buttonLabel", "Add Student");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Could not load form");
        }
        return "form";
    }

    @PostMapping("/save")
    public String saveStudent(@RequestParam("name") String name,
                             @RequestParam("email") String email,
                             @RequestParam("courseId") Long courseId,
                             RedirectAttributes ra) {
        try {
            Course course = courseService.getById(courseId);
            studentService.save(new Student(name, email, course));
            ra.addFlashAttribute("successMessage", "Student saved successfully!");
        } catch (DataIntegrityViolationException e) {
            ra.addFlashAttribute("errorMessage", "Integrity violation: Duplicate email.");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMessage", "Error: " + e.getMessage());
        }
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        try {
            model.addAttribute("student", studentService.getById(id));
            model.addAttribute("courses", courseService.getAll());
            model.addAttribute("pageTitle", "Edit Student");
            model.addAttribute("formAction", "/update/" + id);
            model.addAttribute("buttonLabel", "Update Student");
            return "form";
        } catch (Exception e) {
            return "redirect:/";
        }
    }

    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable Long id,
                               @RequestParam("name") String name,
                               @RequestParam("email") String email,
                               @RequestParam("courseId") Long courseId,
                               RedirectAttributes ra) {
        try {
            Course course = courseService.getById(courseId);
            studentService.update(id, new Student(name, email, course));
            ra.addFlashAttribute("successMessage", "Student updated!");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMessage", "Update failed.");
        }
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id, RedirectAttributes ra) {
        try {
            studentService.delete(id);
            ra.addFlashAttribute("successMessage", "Student deleted.");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMessage", "Delete failed.");
        }
        return "redirect:/";
    }
}
