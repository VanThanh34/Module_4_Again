<%--
  Created by IntelliJ IDEA.
  User: vvt13
  Date: 10/29/2025
  Time: 11:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head><title>Thêm cầu thủ mới</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: "Segoe UI", Arial, sans-serif;
        }

        .card {
            max-width: 600px;
            margin: 60px auto;
            border-radius: 12px;
            background-color: #fff;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            padding: 30px 40px;
        }

        h2 {
            text-align: center;
            color: #b30000;
            font-weight: bold;
            margin-bottom: 25px;
        }

        label {
            font-weight: 600;
            margin-bottom: 6px;
        }

        .form-control {
            border-radius: 6px;
        }

        .btn-save {
            background-color: #b30000;
            color: white;
            font-weight: 500;
            border: none;
        }

        .btn-save:hover {
            background-color: #990000;
        }

        .btn-back {
            background-color: #6c757d;
            color: white;
            font-weight: 500;
        }

        .btn-back:hover {
            background-color: #5a6268;
        }

        #message {
            width: fit-content;
            margin: 0 auto 15px;
            padding: 10px 20px;
            border-radius: 8px;
        }
    </style>

</head>
<body>
<div class="card"><h2>Thêm cầu thủ mới</h2>
    <c:if test="${not empty mess}">
        <div id="message" class="alert alert-success text-center">${mess}</div>
    </c:if>

    <form:form action="/players/add" method="post" modelAttribute="player">
        <div class="mb-3">
            <label for="name">Mã cầu thủ</label>
            <form:input path="id" id="id" cssClass="form-control" placeholder="Nhập mã cầu thủ"/>
        </div>
        <div class="mb-3">
            <label for="name">Họ và tên</label>
            <form:input path="name" id="name" cssClass="form-control" placeholder="Nhập họ tên cầu thủ"/>
        </div>

        <div class="mb-3">
            <label for="dob">Ngày sinh</label>
            <form:input path="dob" id="dob" type="date" cssClass="form-control"/>
        </div>

        <div class="mb-3">
            <label for="position">Vị trí</label>
            <form:input path="position" id="position" cssClass="form-control" placeholder="Ví dụ: Tiền đạo"/>
        </div>

        <div class="mb-3">
            <label for="experience">Kinh nghiệm</label>
            <form:textarea path="experience" id="experience" cssClass="form-control"
                           placeholder="Ví dụ: 5 năm thi đấu chuyên nghiệp"/>
        </div>

        <div class="mb-3">
            <label for="avatar">Avatar (URL hình ảnh)</label>
            <form:input path="avatar" id="avatar" cssClass="form-control" placeholder="Dán link ảnh cầu thủ"/>
        </div>

        <div class="d-flex justify-content-between mt-4">
            <button type="submit" class="btn btn-save px-4">Lưu cầu thủ</button>
            <a href="/players" class="btn btn-back px-4">← Quay lại danh sách</a>
        </div>
    </form:form>

</div>
<script> const message = document.getElementById('message');
if (message) {
    setTimeout(() => {
        message.style.transition = "opacity 0.5s";
        message.style.opacity = "0";
        setTimeout(() => message.remove(), 500);
    }, 3000);
} </script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>