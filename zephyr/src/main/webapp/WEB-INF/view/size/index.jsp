<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div style="margin-bottom: 30px">
    <span><a href="/zephyr/admin/staff/index">Product </a></span>
    <span style="color: #C0C0C0"> / index</span>
</div>
<div class="col-lg-12 grid-margin stretch-card">
    <div class="card">
        <div class="card-body">
            <a type="button" class="btn btn-success" data-toggle="modal"
               data-target="#myModalAddProduct"
               style="margin-bottom: 20px">add</a>
            <a  type="button" class="btn btn-warning" data-toggle="modal"
                data-target="#myModalRestore" style="margin-bottom: 20px; margin-left: 10px">
                restore
            </a>

            <div class="table-responsive">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th> # </th>
                        <th> code </th>
                        <th> name </th>
                        <th> dateCreate </th>
                        <th> dateUpdate </th>
                        <th> userCreate </th>
                        <th> userUpdate </th>
                        <th> status </th>
                        <th> update </th>
                        <th> delete </th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${ listSize.content }" var="size" varStatus="i">
                        <tr>
                            <td>${i.index+1}</td>
                            <td class="align-middle">${size.code}</td>
                            <td class="align-middle">${size.name}</td>
                            <td class="align-middle">${size.dateCreate}</td>
                            <td class="align-middle">${size.dateUpdate}</td>
                            <td class="align-middle">${size.userCreate}</td>
                            <td class="align-middle">${size.userUpdate}</td>
                            <td class="align-middle">${size.status == 1 ? "đang hoạt động" : "ngừng hoạt động"}</td>
                            <td class="align-middle">
                                <a class="btn btn-info" href="/zephyr/admin/size/view-update?id=${size.id}">
                                    update
                                </a>
                            </td>
                            <td class="align-middle">
                                <a  class="btn btn-danger" href="/zephyr/admin/size/delete?id=${size.id}"
                                    onclick="if(!confirm('Bạn có muốn xoá?')){return false}else{alert('xoá thành công');}">delete</a>
                            </td>

                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <nav aria-label="Page navigation example" style="margin-left: 50%; margin-top: 20px">
                    <ul class="pagination" >
                        <c:forEach begin="0" end="${ listSize.totalPages -1}" varStatus="loop" >
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

<%-- Model prodct --%>

<%--<div class="modal fade" id="myModalAddProduct" role="dialog">--%>
<%--    <div class="modal-dialog modal-lg">--%>
<%--        <div class="modal-content">--%>

<%--            <form:form action="/zephyr/admin/product-detail/add" method="POST" modelAttribute="productDetail">--%>

<%--                <div class="row">--%>
<%--                    <div class="col-md-3">--%>
<%--                        <div class="mb-3">--%>
<%--                            <label class="form-label">images</label>--%>
<%--                            <input type="file" name="images"  id="imageInput" onchange="previewImage(event)">--%>
<%--                            <img  id="imagePreview" src="" alt="" style="width: 82px">--%>

<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <div class="col-md-3">--%>
<%--                        <div class="mb-3">--%>
<%--                            <label class="form-label">inventory</label>--%>
<%--                            <form:input path="inventory" class="form-control"/>--%>
<%--                            <form:errors path="inventory" cssClass="errors"/><br>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <div class="col-md-3">--%>
<%--                        <div class="mb-3">--%>
<%--                            <label class="form-label">importPrice</label> <br>--%>
<%--                            <form:input path="importPrice" class="form-control"/>--%>
<%--                            <form:errors path="importPrice" cssClass="errors"/><br>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <div class="col-md-3">--%>
<%--                        <div class="mb-3">--%>
<%--                            <label class="form-label">price</label>--%>
<%--                            <form:input path="price" class="form-control"/>--%>
<%--                            <form:errors path="price" cssClass="errors"/><br>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>

<%--                <div class="row">--%>
<%--                    <div class="col-md-3">--%>
<%--                        <div class="mb-3">--%>
<%--                            <label class="form-label">describe</label>--%>
<%--                            <form:input path="describe" class="form-control"/>--%>
<%--                            <form:errors path="describe" cssClass="errors"/><br>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <div class="col-md-3">--%>
<%--                        <div class="mb-3">--%>
<%--                            <label class="form-label"></label> <br>--%>
<%--                            <input type="radio" name="status" value="1" ${productDetail.status == "1" ? "checked" : ""} checked=""/>đang--%>
<%--                            hoạt động--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <div class="col-md-3">--%>

<%--                    </div>--%>
<%--                    <div class="col-md-3">--%>

<%--                    </div>--%>
<%--                </div>--%>

<%--                <div class="row">--%>
<%--                    <div class="col-md-3">--%>
<%--                        <div class="mb-3">--%>
<%--                            <label class="form-label">product</label> <br>--%>
<%--                            <select name="product"  class="form-control">--%>
<%--                                <c:forEach items="${listProduct}" var="product">--%>
<%--                                    <option value="${product.id}">${product.name}</option>--%>
<%--                                </c:forEach>--%>
<%--                            </select>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <div class="col-md-3">--%>
<%--                        <div class="mb-3">--%>
<%--                            <label class="form-label">origin</label> <br>--%>
<%--                            <select name="origin" class="form-control">--%>
<%--                                <c:forEach items="${listOrigin}" var="origin">--%>
<%--                                    <option value="${origin.id}">${origin.name}</option>--%>
<%--                                </c:forEach>--%>
<%--                            </select>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <div class="col-md-3">--%>
<%--                        <div class="mb-3">--%>
<%--                            <label class="form-label">color</label> <br>--%>
<%--                            <select name="color"  class="form-control">--%>
<%--                                <c:forEach items="${listColor}" var="color">--%>
<%--                                    <option value="${color.id}">${color.name}</option>--%>
<%--                                </c:forEach>--%>
<%--                            </select>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <div class="col-md-3">--%>
<%--                        <div class="mb-3">--%>
<%--                            <label class="form-label">size</label> <br>--%>
<%--                            <select name="size" class="form-control">--%>
<%--                                <c:forEach items="${listSize}" var="size">--%>
<%--                                    <option value="${size.id}">${size.name}</option>--%>
<%--                                </c:forEach>--%>
<%--                            </select>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>


<%--                <div class="row">--%>
<%--                    <div class="col-md-3">--%>
<%--                        <div class="mb-3">--%>
<%--                            <label class="form-label">ngày tạo</label>--%>
<%--                            <input type="text" name="dateCreate" class="form-control" value="${dateUpdate}"/>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <div class="col-md-3">--%>
<%--                        <div class="mb-3">--%>
<%--                            <label class="form-label">ngày sửa</label>--%>
<%--                            <input type="text" name="dateUpdate" class="form-control" value="${dateUpdate}"/>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <div class="col-md-3">--%>
<%--                        <div class="mb-3">--%>
<%--                            <label class="form-label">người tạo</label>--%>
<%--                            <input type="text" name="userCreate" class="form-control" value="${staffSession.name}"/>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <div class="col-md-3">--%>
<%--                        <div class="mb-3">--%>
<%--                            <label class="form-label">người sửa</label>--%>
<%--                            <input type="text" name="userUpdate" class="form-control" value="${staffSession.name}"/>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>

<%--                <div class="row">--%>
<%--                    <div class="col-md-3"></div>--%>
<%--                    <div class="col-md-6">--%>
<%--                        <div class="mb-3">--%>
<%--                            <form:button type="submit" class="btn btn-primary" onclick="add()">Add</form:button>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <div class="col-md-3"></div>--%>
<%--                </div>--%>


<%--            </form:form>--%>

<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>

<%-- End product--%>
<%-- Model restore --%>

<div class="modal fade" id="myModalRestore" role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">

            <table class="table table-bordered">
                <thead>
                <tr>
                    <th> # </th>
                    <th> code </th>
                    <th> name </th>
                    <th> dateCreate </th>
                    <th> dateUpdate </th>
                    <th> userCreate </th>
                    <th> userUpdate </th>
                    <th> status </th>
                    <th> restore </th>

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
                            <a  class="btn btn-danger" href="/zephyr/admin/size/restore?id=${restore.id}"
                                onclick="if(!confirm('Bạn có muốn khôi phục không?')){return false}else{alert('khôi phục thành công');}">restore</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

        </div>
    </div>
</div>


</body>
</html>