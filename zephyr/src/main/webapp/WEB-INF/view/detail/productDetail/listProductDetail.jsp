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
<h1>Quản Lý Chi Tiết Sản Phẩm</h1>
<div class="navbar-nav">
    <h3>Danh Sách Thông Tin Sản Phẩm</h3>
</div>
<div class="navbar-right">
    <a href="/zephyr/productdetail/view-add" class="btn btn-info">Thêm Thông Tin Sản Phẩm</a>
</div>
<br>
<table class="table table-bordered">
    <thead>
    <tr>
        <th>STT</th>
        <th>Tên Sản Phẩm</th>
        <th>Màu Sản Phẩm</th>
        <th>Nhà Sản Xuất</th>
        <th>Kích Thước</th>
        <th>Ảnh Sản Phẩm</th>
        <th>Mô Tả</th>
        <th>Số Lượng Tồn</th>
        <th>Giá Nhập</th>
        <th>Giá Bán</th>
        <th>Trạng Thái</th>
        <th>Hành Động</th>
    </tr>
    </thead>
    <tbody class="table-hover">
    <c:forEach items="${listProductDetail.content}" var="productDetail" varStatus="i">
        <tr>
            <td>${i.index + 1}</td>
            <td>${productDetail.product.nameProduct}</td>
            <td>${productDetail.color.nameColor}</td>
            <td>${productDetail.origin.nameOrigin}</td>
            <td>${productDetail.size.nameSize}</td>
            <td>
                <img src="${pageContext.request.contextPath}/assets/images/ + ${productDetail.images}" style="width: 200px; height: 200px;" alt=""/>
            </td>
            <td>${productDetail.describe}</td>
            <td>${productDetail.inventory}</td>
            <td>${productDetail.importPrice}</td>
            <td>${productDetail.price}</td>
            <td>${productDetail.getAllStatus()}</td>
            <td>
                <a href="/zephyr/productdetail/detail/${productDetail.idProductDetail}">Detail</a>
                <a href="/zephyr/productdetail/delete/${productDetail.idProductDetail}">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<c:if test="${listProductDetail.totalPages > 0}">
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center pagination-sm" style="margin-top: 40px;">
            <c:forEach var="i" begin="0" end="${listProductDetail.totalPages - 1}">
                <li class="page-item ${i == page ? 'active' : ''}">
                    <a href="<c:url value='/zephyr/productdetail'><c:param name='page' value='${i}'/></c:url>"
                       class="page-link">${i + 1}</a>
                </li>
            </c:forEach>
        </ul>
    </nav>
</c:if>
</body>
</html>