<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<h1>Quản Lý Nhà Sản Xuất</h1>
<form action="/zephyr/origin/search" method="post">
    <h4>Tìm Kiếm Nhà Sản Xuất</h4>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Tên Nhà Sản Xuất:</label>
        <div class="col-sm-8">
            <input type="text" name="nameOrigin" class="form-control"
                   placeholder="Nhập Tên Nhà Sản Xuất Cần Tìm"/><br>
        </div>
        <div class="col-sm-2">
            <button class="btn btn-success" type="submit">Tìm Kiếm</button>
            <br>
        </div>
    </div>
</form>
<div class="navbar-nav">
    <h3>Danh Sách Nhà Sản Xuất</h3>
</div>
<div class="navbar-right">
    <a href="/zephyr/origin/view-add" class="btn btn-info">Thêm Nhà Sản Xuất</a>
</div>
<br>
<table class="table table-bordered">
    <thead>
    <tr>
        <th>STT</th>
        <th>Mã Nhà Sản Xuất</th>
        <th>Tên Nhà Sản Xuất</th>
        <th>Ngày Tạo</th>
        <th>Ngày Cập Nhật</th>
        <th>Người Tạo</th>
        <th>Người Sửa</th>
        <th>Trạng Thái</th>
        <th>Hành Động</th>
    </tr>
    </thead>
    <tbody class="table-hover">
    <c:forEach items="${listOrigin.content}" var="origin" varStatus="i">
        <tr>
            <td>${i.index + 1}</td>
            <td>${origin.codeOrigin}</td>
            <td>${origin.nameOrigin}</td>
            <td>${origin.dateCreate}</td>
            <td>${origin.dateUpdate}</td>
            <td>${origin.userCreate}</td>
            <td>${origin.userUpdate}</td>
            <td>${origin.getAllStatus()}</td>
            <td>
                <a href="/zephyr/origin/detail/${origin.idOrigin}">Detail</a>
                <a href="/zephyr/origin/delete/${origin.idOrigin}">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<c:if test="${listOrigin.totalPages > 0}">
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center pagination-sm" style="margin-top: 40px;">
            <c:forEach var="i" begin="0" end="${listOrigin.totalPages - 1}">
                <li class="page-item ${i == page ? 'active' : ''}">
                    <a href="<c:url value='/zephyr/origin'><c:param name='page' value='${i}'/></c:url>"
                       class="page-link">${i + 1}</a>
                </li>
            </c:forEach>
        </ul>
    </nav>
</c:if>
</body>
</html>