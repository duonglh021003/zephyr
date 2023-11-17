<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>

</head>
<body>
<form:form action="/zephyr/admin/product-detail/add" method="POST" modelAttribute="productDetail">

    <div class="row">
        <div class="col-md-3">
            <div class="mb-3">
                <label class="form-label">mã</label>
                <input type="file" name="images" >
            </div>
        </div>
        <div class="col-md-3">
            <div class="mb-3">
                <label class="form-label">inventory</label>
                <form:input path="inventory" class="form-control"/>
                <form:errors path="inventory" cssClass="errors"/><br>
            </div>
        </div>
        <div class="col-md-3">
            <div class="mb-3">
                <label class="form-label">importPrice</label> <br>
                <form:input path="importPrice" class="form-control"/>
                <form:errors path="importPrice" cssClass="errors"/><br>
            </div>
        </div>
        <div class="col-md-3">
            <div class="mb-3">
                <label class="form-label">price</label>
                <form:input path="price" class="form-control"/>
                <form:errors path="price" cssClass="errors"/><br>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-3">
            <div class="mb-3">
                <label class="form-label">describe</label>
                <form:input path="describe" class="form-control"/>
                <form:errors path="describe" cssClass="errors"/><br>
            </div>
        </div>
        <div class="col-md-3">
            <div class="mb-3">
                <label class="form-label"></label> <br>
                <input type="radio" name="status" value="1" ${productDetail.status == "1" ? "checked" : ""} checked=""/>đang
                hoạt động
               </div>
        </div>
        <div class="col-md-3">

        </div>
        <div class="col-md-3">

        </div>
    </div>

    <div class="row">
        <div class="col-md-3">
            <div class="mb-3">
                <label class="form-label">product</label> <br>
                <select name="product"  class="form-control">
                    <c:forEach items="${listProduct}" var="product">
                        <option value="${product.id}">${product.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="col-md-3">
            <div class="mb-3">
                <label class="form-label">origin</label> <br>
                <select name="origin" class="form-control">
                    <c:forEach items="${listOrigin}" var="origin">
                        <option value="${origin.id}">${origin.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="col-md-3">
            <div class="mb-3">
                <label class="form-label">color</label> <br>
                <select name="color"  class="form-control">
                    <c:forEach items="${listColor}" var="color">
                        <option value="${color.id}">${color.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="col-md-3">
            <div class="mb-3">
                <label class="form-label">size</label> <br>
                <select name="size" class="form-control">
                    <c:forEach items="${listSize}" var="size">
                        <option value="${size.id}">${size.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
    </div>


    <div class="row">
        <div class="col-md-3">
            <div class="mb-3">
                <label class="form-label">ngày tạo</label>
                <input type="text" name="dateCreate" class="form-control" value="${dateUpdate}"/>
            </div>
        </div>
        <div class="col-md-3">
            <div class="mb-3">
                <label class="form-label">ngày sửa</label>
                <input type="text" name="dateUpdate" class="form-control" value="${dateUpdate}"/>
            </div>
        </div>
        <div class="col-md-3">
            <div class="mb-3">
                <label class="form-label">người tạo</label>
                <input type="text" name="userCreate" class="form-control" value="${staffSession.name}"/>
            </div>
        </div>
        <div class="col-md-3">
            <div class="mb-3">
                <label class="form-label">người sửa</label>
                <input type="text" name="userUpdate" class="form-control" value="${staffSession.name}"/>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <div class="mb-3">
                <form:button type="submit" class="btn btn-primary" onclick="add()">Add</form:button>
            </div>
        </div>
        <div class="col-md-3"></div>
    </div>


</form:form>



</body>
</html>
