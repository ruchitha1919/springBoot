<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Student Form</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <c:if test="${not empty error}">
                    <div class="alert alert-danger">${error}</div>
                </c:if>
                <div class="card shadow">
                    <div class="card-header bg-success text-white">
                        <h4 class="mb-0">${student.id == null ? 'Add' : 'Edit'} Student</h4>
                    </div>
                    <div class="card-body">
                        <form:form action="/students/save" modelAttribute="student" method="post">
                            <form:hidden path="id"/>
                            <div class="mb-3">
                                <label class="form-label">Student Name</label>
                                <form:input path="name" class="form-control" />
                                <form:errors path="name" cssClass="text-danger small" />
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Email</label>
                                <form:input path="email" type="email" class="form-control" />
                                <form:errors path="email" cssClass="text-danger small" />
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Course</label>
                                <form:select path="course.id" class="form-select">
                                    <form:option value="" label="-- Select Course --"/>
                                    <form:options items="${courses}" itemValue="id" itemLabel="name"/>
                                </form:select>
                                <form:errors path="course" cssClass="text-danger small" />
                            </div>
                            <div class="d-flex justify-content-between">
                                <a href="/students" class="btn btn-secondary">Cancel</a>
                                <button type="submit" class="btn btn-success">Save Student</button>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
