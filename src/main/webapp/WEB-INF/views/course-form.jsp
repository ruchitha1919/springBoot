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
        .container { max-width: 500px; margin: 60px auto; padding: 0 20px; }
        .form-card { background: white; border-radius: 12px; box-shadow: 0 4px 16px rgba(0,0,0,0.1); overflow: hidden; }
        .form-header { background: linear-gradient(135deg, #1a237e, #283593); color: white; padding: 22px 30px; }
        .form-body { padding: 30px; }
        .form-group { margin-bottom: 22px; }
        .form-group label { display: block; font-weight: 600; font-size: 0.9rem; margin-bottom: 7px; color: #444; }
        .form-group input { width: 100%; padding: 11px 14px; border: 1.5px solid #c5cae9; border-radius: 7px; font-size: 0.95rem; }
        .btn-row { display: flex; gap: 12px; margin-top: 28px; }
        .btn-submit { flex: 1; background: #1a237e; color: white; padding: 12px; border: none; border-radius: 7px; font-size: 1rem; font-weight: 600; cursor: pointer; }
        .btn-cancel { flex: 1; background: #eceff1; color: #546e7a; padding: 12px; border-radius: 7px; text-decoration: none; text-align: center; font-weight: 600; }
    </style>
</head>
<body>

<header>
    <h1>🎓 Student Management System</h1>
</header>

<div class="container">
    <div class="form-card">
        <div class="form-header">
            <h2>${pageTitle}</h2>
        </div>

        <div class="form-body">
            <form action="${formAction}" method="post">
                <div class="form-group">
                    <label for="name">Course Name</label>
                    <input type="text" id="name" name="name" value="${course.name}" required />
                </div>

                <div class="form-group">
                    <label for="credits">Credits</label>
                    <input type="number" id="credits" name="credits" value="${course.credits}" min="1" max="10" required />
                </div>

                <div class="btn-row">
                    <button type="submit" class="btn-submit">${buttonLabel}</button>
                    <a href="/courses" class="btn-cancel">Cancel</a>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>
