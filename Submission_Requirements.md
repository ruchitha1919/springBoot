# Student & Course Management System | Spring Boot 3.2
**Author**: Adulla Ruchitha  
**Project Link**: [github.com/ruchitha1919/springBoot](https://github.com/ruchitha1919/springBoot)

---

## 1. Project Overview
This application is a robust **Student & Course Management System** designed to manage academic records with high performance and architectural integrity. It implements a **One-to-Many relationship** where a single Course can host multiple Students, while each Student belongs to exactly one Course.

### Technical Stack & Components
| Component | Technology / Detail |
| :--- | :--- |
| **Domain Entities** | Course 1 ↔ * Student (One-to-Many relationship) |
| **Core Framework** | Spring Boot 3.2 · Spring MVC · Spring Data JPA |
| **Database** | H2 In-Memory — schema rebuilt at startup; 10 rows seeded per table |
| **Presentation Layer** | JSP templates · JSTL · Spring Form Tag Library |
| **Build Tool** | Apache Maven — WAR packaging for JSP support |

---

## 2. Entity Relationship Design
The structural backbone consists of two database-backed objects. The **Course** model captures academic program data, while the **Student** model holds personal details and carries a mandatory reference back to a specific course.

### Entity Highlights
*   **Course**: Captures `id`, `name`, and `credits`.
*   **Student**: Captures `id`, `name`, and `email` (Unique).

### Annotation Highlights
*   `@OneToMany(mappedBy="course", cascade=ALL)`: Designates Course as the owning side; persistence events cascade to linked student records.
*   `@ManyToOne @JoinColumn(name="course_id")`: Binds every student to its parent course via a foreign key.
*   `@Column(unique=true)` on the student email: Enforces database-level uniqueness.

---

## 3. Implementation Details

### 3.1 Automatic Database Population
The system uses `CommandLineRunner` in `DataInitializer.java` to populate the database with **10 Courses and 10 Students** on every startup. This ensures a fresh, realistic testing environment without manual data entry.

```java
// Sample Seeding Logic
Course c1 = new Course("Computer Science", 4);
courseRepository.save(c1);
studentRepository.save(new Student("Alice Johnson", "alice@test.com", c1));
```

### 3.2 Create — Accepting and Persisting Records
Forms (`form.jsp` and `course-form.jsp`) collect user data. The controller uses `@RequestParam` to map field values and handles persistence through the Service layer.

![Student Form](screenshots/student_form_final.png)

### 3.3 Read — N+1 Query Optimization
To avoid the **N+1 query problem** (where Hibernate fires a separate query for each student's course), a custom JPQL query joins both tables and projects the result into a `StudentWithCourseDTO`. This reduces database traffic to a **single round-trip**.

```java
@Query("SELECT new com.studentcourse.entity.StudentWithCourseDTO(s.id, s.name, s.email, c.id, c.name, c.credits) " +
       "FROM Student s INNER JOIN s.course c")
List<StudentWithCourseDTO> fetchStudentsWithCourseInfo();
```
![Student List](screenshots/student_list_final.png)

---

## 4. Layered Architecture
The code is organized into five tiers to ensure modularity and testability:

| Layer | Key Classes | Core Responsibility |
| :--- | :--- | :--- |
| **Entity** | Course.java, Student.java | JPA table mappings and validation |
| **Repository** | CourseRepository, StudentRepository | Data access and custom JPQL queries |
| **Service** | CourseService, StudentService | Business logic and exception translation |
| **Controller** | CourseController, StudentController | HTTP routing and flash messaging |
| **View** | 4 JSP templates | Server-side HTML via JSTL |

---

## 5. Technical Challenges & Resolutions

### Challenge 1 — JSP Rendering in Spring Boot 3
*   **What went wrong**: Standard Spring Boot JAR packaging often fails to find JSP files in `WEB-INF`.
*   **Resolution**: Switched to **WAR packaging** and marked `tomcat-embed-jasper` as `provided`, allowing the embedded server to handle JSPs correctly.

### Challenge 2 — The N+1 Query Problem
*   **What went wrong**: Fetching students with their courses caused multiple database hits per page load.
*   **Resolution**: Implemented a **DTO Projection** with an `INNER JOIN` query, fetching all required data in one efficient SQL execution.

### Challenge 3 — Unique Constraint Violations
*   **What went wrong**: Duplicate emails caused raw stack traces to appear in the browser.
*   **Resolution**: Wrapped save operations in a `try-catch` for `DataIntegrityViolationException`, returning a user-friendly **Flash Message**.

### Challenge 4 — Circular References in JSON/Lombok
*   **What went wrong**: Bidirectional relationships caused infinite recursion in `toString()` calls.
*   **Resolution**: Used `@ToString.Exclude` on the students collection in the Course entity to break the loop.

### Challenge 5 — Case-Insensitive Search
*   **What went wrong**: Standard repository searches were case-sensitive, leading to poor user experience.
*   **Resolution**: Implemented `findByNameContainingIgnoreCase` in the repository layer for more flexible search results.

---

## 6. Running the Application
1.  **JDK 17** is required.
2.  Run the application using Maven:
    ```powershell
    ./mvnw spring-boot:run
    ```
3.  Access the landing page at `http://localhost:8080`.

---
*End of Submission File*
