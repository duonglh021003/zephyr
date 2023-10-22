<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<h1>Quản Lý Màu Sắc</h1>
<form action="/zephyr/color/search" method="post">
    <h4>Tìm Kiếm Màu Sắc</h4>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Tên Màu Sắc:</label>
        <div class="col-sm-8">
            <input type="text" name="nameOrigin" class="form-control"
                   placeholder="Nhập Tên Màu Sắc Cần Tìm"/><br>
        </div>
        <div class="col-sm-2">
            <button class="btn btn-success" type="submit">Tìm Kiếm</button>
            <br>
        </div>
    </div>
</form>
<div class="navbar-nav">
    <h3>Danh Sách Màu Sắc</h3>
</div>
<div class="navbar-right">
    <a href="/zephyr/color/view-add" class="btn btn-info">Thêm Màu Sắc</a>
</div>
<br>
<table class="table table-bordered">
    <thead>
    <tr>
        <th>STT</th>
        <th>Mã Màu Sắc</th>
        <th>Tên Màu Sắc</th>
        <th>Ngày Tạo</th>
        <th>Ngày Cập Nhật</th>
        <th>Người Tạo</th>
        <th>Người Sửa</th>
        <th>Trạng Thái</th>
        <th>Hành Động</th>
    </tr>
    </thead>
    <tbody class="table-hover">
    <c:forEach items="${listClor.content}" var="color" varStatus="i">
        <tr>
            <td>${i.index + 1}</td>
            <td>${color.codeColor}</td>
            <td>${color.nameColor}</td>
            <td>${color.dateCreate}</td>
            <td>${color.dateUpdate}</td>
            <td>${color.userCreate}</td>
            <td>${color.userUpdate}</td>
            <td>${color.getAllStatus()}</td>
            <td>
                <a href="/zephyr/color/detail/${color.idColor}">Detail</a>
                <a href="/zephyr/color/delete/${color.idColor}">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<c:if test="${listColor.totalPages > 0}">
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center pagination-sm" style="margin-top: 40px;">
            <c:forEach var="i" begin="0" end="${listColor.totalPages - 1}">
                <li class="page-item ${i == page ? 'active' : ''}">
                    <a href="<c:url value='/zephyr/color'><c:param name='page' value='${i}'/></c:url>"
                       class="page-link">${i + 1}</a>
                </li>
            </c:forEach>
        </ul>
    </nav>
</c:if>
</body>
</html>