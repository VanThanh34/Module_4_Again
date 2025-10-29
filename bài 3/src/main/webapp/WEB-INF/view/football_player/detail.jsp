<%--
  Created by IntelliJ IDEA.
  User: vvt13
  Date: 10/28/2025
  Time: 1:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Chi tiết cầu thủ - ${player.name}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background-color: #f8f9fa;
        }
        .container {
            max-width: 800px;
            margin-top: 40px;
            background: white;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0,0,0,0.1);
            padding: 30px;
        }
        .player-avatar {
            width: 200px;
            height: 250px;
            object-fit: cover;
            border-radius: 10px;
        }
        .player-info h2 {
            margin-bottom: 10px;
            color: #b30000;
        }
        .player-info p {
            font-size: 16px;
            margin: 5px 0;
        }
        .btn-back {
            background-color: #b30000;
            color: white;
        }
        .btn-back:hover {
            background-color: #990000;
        }
    </style>
</head>
<body>

<div class="container text-center">
    <h1 class="mb-4">Thông tin chi tiết cầu thủ</h1>

    <div class="row align-items-center">
        <div class="col-md-5">
            <img src="${player.avatar}" alt="${player.name}" class="player-avatar img-fluid">
        </div>
        <div class="col-md-7 text-start player-info">
            <h2>${player.name}</h2>
            <p><strong>Ngày sinh:</strong> ${player.dob}</p>
            <p><strong>Vị trí:</strong> ${player.position}</p>
            <p><strong>Kinh nghiệm:</strong> ${player.experience}</p>
        </div>
    </div>

    <div class="mt-4">
        <a href="/players" class="btn btn-back">← Quay lại danh sách</a>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

