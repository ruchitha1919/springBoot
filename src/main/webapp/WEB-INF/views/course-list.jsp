<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Course Management - Student System</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background: #f0f2f5; color: #333; }
        header { background: linear-gradient(135deg, #1a237e, #283593); color: white; padding: 18px 40px; display: flex; align-items: center; justify-content: space-between; box-shadow: 0 2px 8px rgba(0,0,0,0.3); }
        header h1 { font-size: 1.6rem; letter-spacing: 1px; }
        nav { background: #283593; display: flex; gap: 4px; padding: 0 40px; }
        nav a { color: rgba(255,255,255,0.8); text-decoration: none; padding: 12px 20px; font-size: 0.9rem; font-weight: 600; transition: background 0.2s, color 0.2s; border-bottom: 3px solid transparent; }
        nav a:hover { color: white; background: rgba(255,255,255,0.1); }
        nav a.active { color: white; border-bottom: 3px solid #ffd740; }
        .container { max-width: 1000px; margin: 40px auto; padding: 0 20px; }
        .toolbar { display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px; }
        .toolbar h2 { font-size: 1.3rem; color: #1a237e; }
        .btn-add { background: #1a237e; color: white; padding: 10px 22px; border: none; border-radius: 6px; text-decoration: none; font-size: 0.95rem; font-weight: 600; cursor: pointer; transition: background 0.2s; }
        .btn-add:hover { background: #283593; }
        .alert { padding: 12px 18px; border-radius: 6px; margin-bottom: 20px; font-size: 0.95rem; }
        .alert-success { background: #e8f5e9; color: #2e7d32; border-left: 4px solid #4caf50; }
        .card { background: white; border-radius: 10px; box-shadow: 0 2px 10px rgba(0,0,0,0.08); overflow: hidden; }
        table { width: 100%; border-collapse: collapse; }
        thead tr { background: #1a237e; color: white; }
        thead th { padding: 14px 18px; text-align: left; font-size: 0.9rem; letter-spacing: 0.5px; text-transform: uppercase; }
        tbody tr { border-bottom: 1px solid #e8eaf6; transition: background 0.15s; }
        tbody tr:hover { background: #e8eaf6; }
        tbody td { padding: 13px 18px; font-size: 0.95rem; }
        .btn-edit { background: #ff8f00; color: white; padding: 6px 12px; border-radius: 5px; text-decoration: none; font-size: 0.82rem; font-weight: 600; }
        .btn-delete { background: #c62828; color: white; padding: 6px 12px; border-radius: 5px; text-decoration: none; font-size: 0.82rem; font-weight: 600; }
        footer { text-align: center; margin-top: 40px; color: #aaa; font-size: 0.85rem; }
    </style>
</head>
<body>

<header>
    <h1>🎓 Student Management System</h1>
</header>

<nav>
    <a href="/">👨‍🎓 Students</a>
    <a href="/courses" class="active">📚 Courses</a>
</nav>

<div class="container">
    <c:if test="${not empty successMessage}">
        <div class="alert alert-success">${successMessage}</div>
    </c:if>

    <div class="toolbar">
        <h2>${pageTitle}</h2>
        <a href="/courses/add" class="btn-add">+ Add New Course</a>
    </div>

    <div class="card">
        <table>
            <thead>
                <tr>
                    <th>#</th>
                    <th>Course Name</th>
                    <th>Credits</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="course" items="${courses}" varStatus="status">
                    <tr>
                        <td>${status.count}</td>
                        <td><strong>${course.name}</strong></td>
                        <td>${course.credits}</td>
                        <td>
                            <a href="/courses/edit/${course.id}" class="btn-edit">✎ Edit</a>
                            <a href="/courses/delete/${course.id}" class="btn-delete" onclick="return confirm('Are you sure?');">🗑 Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<footer>
    &copy; 2024 Student Management System &mdash; Course Administration
</footer>

</body>
</html>
