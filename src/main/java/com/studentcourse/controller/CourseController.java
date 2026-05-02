package com.studentcourse.controller;

import com.studentcourse.entity.Course;
import com.studentcourse.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public String listCourses(Model model) {
        model.addAttribute("courses", courseService.getAll());
        model.addAttribute("pageTitle", "Manage Courses");
        return "course-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("course", new Course());
        model.addAttribute("pageTitle", "Add New Course");
        model.addAttribute("formAction", "/courses/save");
        model.addAttribute("buttonLabel", "Add Course");
        return "course-form";
    }

    @PostMapping("/save")
    public String saveCourse(@RequestParam("name") String name,
                             @RequestParam("credits") Integer credits,
                             RedirectAttributes redirectAttributes) {
        try {
            Course course = new Course(name, credits);
            courseService.save(course);
            redirectAttributes.addFlashAttribute("successMessage", "Course '" + name + "' added successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error saving course: " + e.getMessage());
        }
        return "redirect:/courses";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        try {
            Course course = courseService.getById(id);
            model.addAttribute("course", course);
            model.addAttribute("pageTitle", "Edit Course");
            model.addAttribute("formAction", "/courses/update/" + id);
            model.addAttribute("buttonLabel", "Update Course");
            return "course-form";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Course not found");
            return "redirect:/courses";
        }
    }

    @PostMapping("/update/{id}")
    public String updateCourse(@PathVariable Long id,
                               @RequestParam("name") String name,
                               @RequestParam("credits") Integer credits,
                               RedirectAttributes redirectAttributes) {
        try {
            Course updatedCourse = new Course(name, credits);
            courseService.update(id, updatedCourse);
            redirectAttributes.addFlashAttribute("successMessage", "Course updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error updating course: " + e.getMessage());
        }
        return "redirect:/courses";
    }

    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            courseService.delete(id);
            redirectAttributes.addFlashAttribute("successMessage", "Course deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error deleting course: " + e.getMessage());
        }
        return "redirect:/courses";
    }
}
