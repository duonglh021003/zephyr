<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>

</head>
<body>
<div style="margin-bottom: 30px">
    <span><a href="/zephyr/admin/staff/index">Staff</a></span>
    <span style="color: #C0C0C0"> / index</span>
</div>
<div class="col-lg-12 grid-margin stretch-card">
    <div class="card">
        <div class="card-body">

            <div class="table-responsive">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th> # </th>
                        <th> product </th>
                        <th> inventory </th>
                        <th> importPrice </th>
                        <th> price </th>
                        <th> dateCreate </th>
                        <th> dateUpdate </th>
                        <th> userCreate </th>
                        <th> userUpdate </th>
                        <th> origin </th>
                        <th> status </th>

                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${ listProductDetail.content }" var="productDetail" varStatus="i">
                        <tr>
                            <td>${i.index+1}</td>
                            <td class="align-middle"><img src="/assets/images/client/${productDetail.images}" alt="" >
                                    ${productDetail.product.name}(${productDetail.size.name}, ${productDetail.color.name})</td>
                            <td class="align-middle">${productDetail.inventory}</td>
                            <td class="align-middle">${productDetail.importPrice}00</td>
                            <td class="align-middle">${productDetail.price}00</td>
                            <td class="align-middle">${productDetail.dateCreate}</td>
                            <td class="align-middle">${productDetail.dateUpdate}</td>
                            <td class="align-middle">${productDetail.userCreate}</td>
                            <td class="align-middle">${productDetail.userUpdate}</td>
                            <td class="align-middle">${productDetail.origin.name}</td>
                            <td class="align-middle">${productDetail.status == 1 ? "đang hoạt động" : "ngừng hoạt động"}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <nav aria-label="Page navigation example" style="margin-left: 50%; margin-top: 20px">
                    <ul class="pagination" >
                        <c:forEach begin="0" end="${ listProductDetail.totalPages -1}" varStatus="loop" >
                            <li class="page-item"  >
                                <a class="page-link" href="/zephyr/admin/product-detail/index?page=${loop.begin + loop.count - 1}" >
                                        ${loop.begin + loop.count }
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</div>

</body>
</html>