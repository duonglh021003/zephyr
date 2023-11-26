<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div style="margin-bottom: 30px">
    <span><a href="/zephyr/admin/product/index">product </a></span>
    <span style="color: #C0C0C0"> / index</span>
</div>
<div class="col-lg-12 grid-margin stretch-card">
    <div class="card">
        <div class="card-body">
            <a style="margin-bottom: 20px" type="button" class="btn btn-success"
               href="/zephyr/admin/product/view-add">
                add
            </a>
            <a type="button" class="btn btn-warning" data-toggle="modal"
               data-target="#myModalRestore" style="margin-bottom: 20px; margin-left: 10px">
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
                        <th> #</th>
                        <th> code</th>
                        <th> name</th>
                        <th> dateCreate</th>
                        <th> dateUpdate</th>
                        <th> userCreate</th>
                        <th> userUpdate</th>
                        <th> status</th>
                        <th> update</th>
                        <th> delete</th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${ listProduct.content }" var="product" varStatus="i">
                        <tr>
                            <td>${i.index+1}</td>
                            <td class="align-middle">${product.code}</td>
                            <td class="align-middle">${product.name}</td>
                            <td class="align-middle">${product.dateCreate}</td>
                            <td class="align-middle">${product.dateUpdate}</td>
                            <td class="align-middle">${product.userCreate}</td>
                            <td class="align-middle">${product.userUpdate}</td>
                            <td class="align-middle">${product.status == 1 ? "đang hoạt động" : "ngừng hoạt động"}</td>
                            <td class="align-middle">
                                <a class="btn btn-info"
                                   href="/zephyr/admin/product/view-update?id=${product.id}">
                                    update
                                </a>
                            </td>
                            <td class="align-middle">
                                <a class="btn btn-danger" href="/zephyr/admin/product/delete?id=${product.id}"
                                   onclick="if(!confirm('Bạn có muốn xoá?')){return false}else{alert('xoá thành công');}">delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <nav aria-label="Page navigation example" style="margin-left: 50%; margin-top: 20px">
                    <ul class="pagination">
                        <c:forEach begin="0" end="${ listProduct.totalPages -1}" varStatus="loop">
                            <li class="page-item">
                                <a class="page-link"
                                   href="/zephyr/admin/product-detail/index?page=${loop.begin + loop.count - 1}">
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
            <form action="/zephyr/admin/product/search" method="get">
                <div class="modal-body">
                    <div class="row" >
                        <div class="col-md-12">
                            <div class="mb-3">
                                <input class="form-control" name="inputProduct" placeholder="input product">
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
<%-- Model restore--%>

<div class="modal fade" id="myModalRestore" role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th> #</th>
                    <th> code</th>
                    <th> name</th>
                    <th> dateCreate</th>
                    <th> dateUpdate</th>
                    <th> userCreate</th>
                    <th> userUpdate</th>
                    <th> status</th>
                    <th> restore</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${ listRestore }" var="restore" varStatus="i">
                    <tr>
                        <td>${i.index+1}</td>
                        <td class="align-middle">${restore.code}</td>
                        <td class="align-middle">${restore.name}</td>
                        <td class="align-middle">${restore.dateCreate}</td>
                        <td class="align-middle">${restore.dateUpdate}</td>
                        <td class="align-middle">${restore.userCreate}</td>
                        <td class="align-middle">${restore.userUpdate}</td>
                        <td class="align-middle">${restore.status == 1 ? "đang hoạt động" : "ngừng hoạt động"}</td>
                        <td class="align-middle">
                            <a class="btn btn-info" href="/zephyr/admin/product/restore?id=${restore.id}">
                                restore
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

        </div>
    </div>
</div>

<%--<script src="${pageContext.request.contextPath}/assets/jsSell/img.js"></script>--%>

<%-- End restore--%>
</body>
</html>