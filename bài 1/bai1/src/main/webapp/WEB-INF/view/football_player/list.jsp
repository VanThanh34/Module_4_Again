<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Danh sách cầu thủ bóng đá MU</title>
    <style>
        table {
            border-collapse: collapse;
            width: 80%;
            margin: 20px auto;
        }
        th, td {
            border: 1px solid #ccc;
            padding: 8px 12px;
            text-align: center;
        }
        th {
            background-color: #f2f2f2;
        }
        img {
            width: 80px;
            height: 100px;
            object-fit: cover;
            border-radius: 6px;
        }
    </style>
</head>
<body>
<h1 style="text-align: center;">Danh sách cầu thủ bóng đá MU</h1>

<table>
    <tr>
        <th>STT</th>
        <th>Avatar</th>
        <th>Họ và tên</th>
        <th>Ngày sinh</th>
        <th>Kinh nghiệm</th>
        <th>Vị trí</th>
    </tr>

    <c:forEach var="p" items="${playerList}" varStatus="var">
        <tr>
            <td>${var.index + 1}</td>
            <td><img src="${p.avatar}" alt="${p.name}"></td>
            <td>${p.name}</td>
            <td>${p.dob}</td>
            <td>${p.experience}</td>
            <td>${p.position}</td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
