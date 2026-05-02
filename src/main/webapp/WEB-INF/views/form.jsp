<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${pageTitle} - Student Management System</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background: #f0f2f5; color: #333; }
        header { background: linear-gradient(135deg, #1a237e, #283593); color: white; padding: 18px 40px; display: flex; align-items: center; justify-content: space-between; box-shadow: 0 2px 8px rgba(0,0,0,0.3); }
        header h1 { font-size: 1.6rem; letter-spacing: 1px; }
        .container { max-width: 560px; margin: 60px auto; padding: 0 20px; }
        .form-card { background: white; border-radius: 12px; box-shadow: 0 4px 16px rgba(0,0,0,0.1); overflow: hidden; }
        .form-header { background: linear-gradient(135deg, #1a237e, #283593); color: white; padding: 22px 30px; }
        .form-header h2 { font-size: 1.3rem; }
        .form-header p { font-size: 0.85rem; opacity: 0.75; margin-top: 4px; }
        .form-body { padding: 30px; }
        .form-group { margin-bottom: 22px; }
        .form-group label { display: block; font-weight: 600; font-size: 0.9rem; margin-bottom: 7px; color: #444; }
        .form-group input, .form-group select { width: 100%; padding: 11px 14px; border: 1.5px solid #c5cae9; border-radius: 7px; font-size: 0.95rem; color: #333; background: #fafafa; transition: border-color 0.2s, box-shadow 0.2s; outline: none; }
        .form-group input:focus, .form-group select:focus { border-color: #1a237e; box-shadow: 0 0 0 3px rgba(26,35,126,0.12); background: white; }
        .btn-row { display: flex; gap: 12px; margin-top: 28px; }
        .btn-submit { flex: 1; background: #1a237e; color: white; padding: 12px; border: none; border-radius: 7px; font-size: 1rem; font-weight: 600; cursor: pointer; transition: background 0.2s; }
        .btn-submit:hover { background: #283593; }
        .btn-cancel { flex: 1; background: #eceff1; color: #546e7a; padding: 12px; border: none; border-radius: 7px; font-size: 1rem; font-weight: 600; cursor: pointer; text-align: center; text-decoration: none; transition: background 0.2s; display: flex; align-items: center; justify-content: center; }
        .btn-cancel:hover { background: #cfd8dc; }
        .alert-error { background: #ffebee; color: #c62828; border-left: 4px solid #ef5350; padding: 12px 18px; border-radius: 6px; margin-bottom: 20px; font-size: 0.92rem; }
        footer { text-align: center; margin-top: 30px; color: #aaa; font-size: 0.85rem; }
    </style>
</head>
<body>

<header>
    <h1>🎓 Student Management System</h1>
    <span>Spring Boot | JPA | H2</span>
</header>

<div class="container">
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-error">${errorMessage}</div>
    </c:if>

    <div class="form-card">
        <div class="form-header">
            <h2>${pageTitle}</h2>
            <p>Fill in the details below and submit.</p>
        </div>

        <div class="form-body">
            <form action="${formAction}" method="post">
                <div class="form-group">
                    <label for="name">Student Name <span style="color:#e53935">*</span></label>
                    <input type="text" id="name" name="name" value="${student.name}" placeholder="e.g., Alice Johnson" required />
                </div>

                <div class="form-group">
                    <label for="email">Email <span style="color:#e53935">*</span></label>
                    <input type="email" id="email" name="email" value="${student.email}" placeholder="e.g., alice@test.com" required />
                </div>

                <div class="form-group">
                    <label for="courseId">Course <span style="color:#e53935">*</span></label>
                    <select id="courseId" name="courseId" required>
                        <option value="" disabled selected>-- Select Course --</option>
                        <c:forEach var="course" items="${courses}">
                            <option value="${course.id}" ${student.course != null && student.course.id == course.id ? 'selected' : ''}>
                                ${course.name}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="btn-row">
                    <button type="submit" class="btn-submit">${buttonLabel}</button>
                    <a href="/" class="btn-cancel">Cancel</a>
                </div>
            </form>
        </div>
    </div>
</div>

<footer>
    &copy; 2024 Student Management System &mdash; Built with Spring Boot &amp; JSP
</footer>

</body>
</html>
