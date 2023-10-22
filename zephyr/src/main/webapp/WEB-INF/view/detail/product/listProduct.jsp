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
<h1>Quản Lý Sản Phẩm</h1>
<form action="/zephyr/product/search" method="post">
    <h4>Tìm Kiếm Sản Phẩm</h4>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Tên Sản Phẩm:</label>
        <div class="col-sm-8">
            <input type="text" name="nameProduct" class="form-control"
                   placeholder="Nhập Tên Sản Phẩm Cần Tìm"/><br>
        </div>
        <div class="col-sm-2">
            <button class="btn btn-success" type="submit">Tìm Kiếm</button>
            <br>
        </div>
    </div>
</form>
<div class="navbar-nav">
    <h3>Danh Sách Sản Phẩm</h3>
</div>
<div class="navbar-right">
    <a href="/zephyr/product/view-add" class="btn btn-info">Thêm Sản Phẩm</a>
</div>
<br>
<table class="table table-bordered">
    <thead>
    <tr>
        <th>STT</th>
        <th>Mã Sản Phẩm</th>
        <th>Tên Sản Phẩm</th>
        <th>Ngày Tạo</th>
        <th>Ngày Cập Nhật</th>
        <th>Người Tạo</th>
        <th>Người Sửa</th>
        <th>Trạng Thái</th>
        <th>Hành Động</th>
    </tr>
    </thead>
    <tbody class="table-hover">
    <c:forEach items="${listProduct.content}" var="product" varStatus="i">
        <tr>
            <td>${i.index + 1}</td>
            <td>${product.codeProduct}</td>
            <td>${product.nameProduct}</td>
            <td>${product.dateCreate}</td>
            <td>${product.dateUpdate}</td>
            <td>${product.userCreate}</td>
            <td>${product.userUpdate}</td>
            <td>${product.getAllStatus()}</td>
            <td>
                <a href="/zephyr/product/detail/${product.idProduct}">Detail</a>
                <a href="/zephyr/product/delete/${product.idProduct}">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<c:if test="${listProduct.totalPages > 0}">
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center pagination-sm" style="margin-top: 40px;">
            <c:forEach var="i" begin="0" end="${listProduct.totalPages - 1}">
                <li class="page-item ${i == page ? 'active' : ''}">
                    <a href="<c:url value='/zephyr/product'><c:param name='page' value='${i}'/></c:url>"
                       class="page-link">${i + 1}</a>
                </li>
            </c:forEach>
        </ul>
    </nav>
</c:if>
</body>
</html>