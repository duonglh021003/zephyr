<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div style="margin-bottom: 30px">
    <span><a href="/zephyr/admin/product-detail/index">product detail</a></span>
    <span style="color: #C0C0C0"> / index</span>
</div>
<div class="col-lg-12 grid-margin stretch-card">
    <div class="card">
        <div class="card-body">
            <a style="margin-bottom: 20px" type="button" class="btn btn-success"
               href="/zephyr/admin/product-detail/view-add" >
                add
            </a>
            <a  type="button" class="btn btn-warning" data-toggle="modal"
                data-target="#myModalRestore" style="margin-bottom: 20px; margin-left: 10px" >
                restore
            </a>
            <a type="button" class="btn btn-primary" data-toggle="modal"
               data-target="#myModalSearch" style="margin-bottom: 20px; margin-left: 10px">
                search
            </a>

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
                        <th> update </th>
                        <th> delete </th>

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
                            <td class="align-middle">
                                <a class="btn btn-info" href="/zephyr/admin/product-detail/view-update?id=${productDetail.id}">
                                    update
                                </a>
                            </td>
                            <td class="align-middle">
                                <a  class="btn btn-danger" href="/zephyr/admin/product-detail/delete?id=${productDetail.id}"
                                   onclick="if(!confirm('Bạn có muốn xoá?')){return false}else{alert('xoá thành công');}">delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <nav aria-label="Page navigation example" style="margin-left: 20px; margin-top: 20px">
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

<%-- Model search--%>

<div class="modal fade" id="myModalSearch" role="dialog">
    <div class="modal-dialog ">
        <div class="modal-content">

            <div class="modal-header">
                <h4 class="modal-title">my let input</h4>
            </div>
            <form action="/zephyr/admin/product-detail/search" method="get">
                <div class="modal-body">
                    <div class="row" >
                        <div class="col-md-12">
                            <div class="mb-3">
                                <input class="form-control" name="inputProductDetail" placeholder="input product detail">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer" style="margin-top: 70px">
                    <button type="button" style="border: 1px solid #eeeeee" class="btn btn-default" data-dismiss="modal" data-toggle="modal" data-target="#myModal">Close</button>
                    <button  class="btn btn-primary" style="color: white" >search</button>
                </div>
            </form>

        </div>
    </div>
</div>

<%-- End search--%>

<%-- Model restore --%>

<div class="modal fade" id="myModalRestore" role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">

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
                    <th> restore </th>

                </tr>
                </thead>

                <tbody>
                <c:forEach items="${ listProductDetailStatus0 }" var="productDetailStatus0" varStatus="i">
                    <form action="/zephyr/admin/product-detail/restore" method="get">
                    <tr>
                        <td>${i.index+1}</td>
                        <td class="align-middle"><img src="/assets/images/client/${productDetailStatus0.images}" alt="" >
                                ${productDetailStatus0.product.name}(${productDetailStatus0.size.name}, ${productDetailStatus0.color.name})</td>
                        <td class="align-middle">
                            <input type="hidden" name="id" value="${productDetailStatus0.id}">
                            <input type="number" style="width: 70px" name="inventory" value="${productDetailStatus0.inventory}" />
                        </td>
                        <td class="align-middle">${productDetailStatus0.importPrice}00</td>
                        <td class="align-middle">${productDetailStatus0.price}00</td>
                        <td class="align-middle">${productDetailStatus0.dateCreate}</td>
                        <td class="align-middle">${productDetailStatus0.dateUpdate}</td>
                        <td class="align-middle">${productDetailStatus0.userCreate}</td>
                        <td class="align-middle">${productDetailStatus0.userUpdate}</td>
                        <td class="align-middle">${productDetailStatus0.origin.name}</td>
                        <td class="align-middle">${productDetailStatus0.status == 1 ? "đang hoạt động" : "ngừng hoạt động"}</td>
                        <td class="align-middle">
                            <button  class="btn btn-warning"
                                onclick="if(!confirm('Bạn có muốn khôi phục?')){return false}else{alert('khôi phục thành công');}">
                                restore
                            </button>
                        </td>

                    </tr>
                    </form>
                </c:forEach>
                </tbody>
            </table>

        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/assets/jsSell/img.js"></script>

<%-- End restore--%>
</body>
</html>