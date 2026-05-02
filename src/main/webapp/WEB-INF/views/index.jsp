<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Student Course Management</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <style>
        body { background-color: #f8f9fa; font-family: 'Inter', sans-serif; }
        .hero { background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); color: white; padding: 60px 0; border-radius: 0 0 50px 50px; margin-bottom: 40px; }
        .card { border: none; border-radius: 15px; transition: transform 0.3s; box-shadow: 0 4px 6px rgba(0,0,0,0.1); }
        .card:hover { transform: translateY(-5px); }
        .btn-custom { border-radius: 25px; padding: 10px 30px; font-weight: 600; }
    </style>
</head>
<body>
    <div class="hero text-center">
        <h1>Student Course Management System</h1>
        <p>Manage your students and academic courses with ease</p>
    </div>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-4">
                <div class="card p-4 mb-4">
                    <h3>Students</h3>
                    <p>Add, view, and manage student information.</p>
                    <a href="/students" class="btn btn-primary btn-custom">Go to Students</a>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card p-4 mb-4">
                    <h3>Courses</h3>
                    <p>Configure course details and credits.</p>
                    <a href="/courses" class="btn btn-secondary btn-custom">Go to Courses</a>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
