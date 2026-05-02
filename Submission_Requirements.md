# Project Submission: Student & Course Management System

**Author**: Adulla Ruchitha  
**Tech Stack**: Spring Boot 3.2, Spring Data JPA, JSP, H2, Maven (WAR Packaging)

---

## 1. Project Overview
This application is a comprehensive **Student & Course Management System** designed to handle academic records. It implements a **One-to-Many relationship** where one Course can have multiple Students enrolled. The project follows a strict five-tier MVC architecture and mirrors the professional aesthetic of modern management systems.

## 2. Entity Relationship Design
The system revolves around two core entities:
*   **Course (One)**: Acts as the parent entity. Each course has a unique name and a credit value.
*   **Student (Many)**: Acts as the child entity. Each student is associated with exactly one course through a `ManyToOne` mapping.

### JPA Mapping Details:
```java
// Course Entity
@OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
private List<Student> students;

// Student Entity
@ManyToOne
@JoinColumn(name = "course_id")
private Course course;
```

## 3. Implementation Details

### A. Read Operation (Listing Students)
The homepage displays a complete list of students with their enrolled courses. It also features a **Search** bar to filter students by name.

**Code Snapshot (Controller):**
```java
@GetMapping("/")
public String listStudents(@RequestParam(value = "search", required = false) String search, Model model) {
    List<Student> students = (search != null) ? studentService.searchByName(search) : studentService.getAll();
    model.addAttribute("students", students);
    return "list";
}
```
![Student List](file:///C:/Users/Ruchitha%20A/.gemini/antigravity/brain/975d2305-0b38-4d01-a684-e277c088f489/student_list_1777743663905.png)

### B. Create Operation (Adding Students/Courses)
Forms allow for adding new students and courses. Student creation includes a dropdown to select from available courses.

**Code Snapshot (Service):**
```java
public void save(Student student) {
    studentRepository.save(student);
}
```
![Student Form](file:///C:/Users/Ruchitha%20A/.gemini/antigravity/brain/975d2305-0b38-4d01-a684-e277c088f489/student_form_1777743679727.png)

### C. Update Operation (Editing Details)
Existing records can be modified through pre-filled forms. The controller handles the update logic by fetching the existing ID and merging changes.

![Course Form](file:///C:/Users/Ruchitha%20A/.gemini/antigravity/brain/975d2305-0b38-4d01-a684-e277c088f489/course_form_1777743709958.png)

## 4. Challenges & Solutions

### Challenge 1: JSP Integration in Spring Boot 3
**Problem**: Spring Boot 3 with embedded Tomcat requires specific configurations for JSP support when packaged as a JAR.
**Solution**: Switched to **WAR packaging** and added `tomcat-embed-jasper` with `provided` scope to ensure the JSP engine is correctly initialized.

### Challenge 2: Data Integrity & Error Feedback
**Problem**: Saving a student with a duplicate email caused a generic 500 error.
**Solution**: Implemented a `try-catch` block in the Controller to intercept `DataIntegrityViolationException` and return a user-friendly **Flash Message** using `RedirectAttributes`.

### Challenge 3: Automated Data Seeding
**Problem**: Manually entering 20 rows of data for every test run was inefficient.
**Solution**: Developed a `DataInitializer` using `CommandLineRunner` to automatically seed **10 Courses** and **10 Students** on every application startup.

## 5. Project Repository
The complete source code is available at:
**GitHub**: [https://github.com/ruchitha1919/SpringBoot](https://github.com/ruchitha1919/SpringBoot)

---
*End of Submission Requirements*
