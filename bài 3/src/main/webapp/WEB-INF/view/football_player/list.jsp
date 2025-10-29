<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Danh sách cầu thủ bóng đá MU</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

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
        #message {
            width: fit-content;
            margin: 15px auto;
            padding: 10px 20px;
            border-radius: 8px;
        }
    </style>
</head>
<body>

<h1 style="text-align: center;">Danh sách cầu thủ bóng đá MU</h1>

<c:if test="${not empty mess}">
    <div id="message" class="alert alert-success text-center">${mess}</div>
</c:if>

<table>
    <tr>
        <th>STT</th>
        <th>Avatar</th>
        <th>Họ và tên</th>
        <th>Ngày sinh</th>
        <th>Kinh nghiệm</th>
        <th>Vị trí</th>
        <th>Hành động</th>
    </tr>

    <c:forEach var="p" items="${playerList}" varStatus="var">
        <tr>
            <td>${var.index + 1}</td>
            <td><img src="${p.avatar}" alt="${p.name}"></td>
            <td>${p.name}</td>
            <td>${p.dob}</td>
            <td>${p.experience}</td>
            <td>${p.position}</td>
            <td>
                <a href="/players/detail/${p.id}" class="btn btn-info btn-sm">Chi tiết</a>
                <button type="button" class="btn btn-danger btn-sm"
                        data-bs-toggle="modal"
                        data-bs-target="#deleteModal"
                        data-id="${p.id}"
                        data-name="${p.name}">
                    Xóa
                </button>
            </td>
        </tr>
    </c:forEach>
</table>


<div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <form id="deleteForm" method="post">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="deleteModalLabel">Xác nhận xoá</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <div class="modal-body">
                    <p>Bạn có chắc chắn muốn xoá <strong id="playerName"></strong> không?</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Huỷ</button>
                    <button type="submit" class="btn btn-danger">Xoá</button>
                </div>
            </div>
        </form>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script>

    const deleteModal = document.getElementById('deleteModal');
    deleteModal.addEventListener('show.bs.modal', event => {
        const button = event.relatedTarget;
        const id = button.getAttribute('data-id');
        const name = button.getAttribute('data-name');
        document.getElementById('playerName').textContent = name;
        document.getElementById('deleteForm').action = '/players/delete/' + id;
    });


    const message = document.getElementById('message');
    if (message) {
        setTimeout(() => {
            message.style.transition = "opacity 0.5s";
            message.style.opacity = "0";
            setTimeout(() => message.remove(), 500);
        }, 3000);
    }
</script>

</body>
</html>
