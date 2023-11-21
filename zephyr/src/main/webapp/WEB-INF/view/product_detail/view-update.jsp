<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>

</head>
<body>
<form:form action="/zephyr/admin/product-detail/update?id=${productDetail.id}" method="POST" modelAttribute="productDetail">

    <div class="row">
        <div class="col-md-3">
            <div class="mb-3">
                <label class="form-label">images</label>
                <input type="file" name="images" id="imageInput" value="${productDetail.images}" onchange="previewImage(event)">
                <img  id="imagePreview" src="/assets/images/client/${productDetail.images}" alt="" style="width: 82px">

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
                        <option value="${product.id}" ${productDetail.product.id == product.id ? "selected" : ""}>${product.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="col-md-3">
            <div class="mb-3">
                <label class="form-label">origin</label> <br>
                <select name="origin" class="form-control">
                    <c:forEach items="${listOrigin}" var="origin">
                        <option value="${origin.id}" ${productDetail.origin.id == origin.id ? "selected" : ""}>${origin.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="col-md-3">
            <div class="mb-3">
                <label class="form-label">color</label> <br>
                <select name="color"  class="form-control">
                    <c:forEach items="${listColor}" var="color">
                        <option value="${color.id}" ${productDetail.color.id == color.id ? "selected" : ""}>${color.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="col-md-3">
            <div class="mb-3">
                <label class="form-label">size</label> <br>
                <select name="size" class="form-control">
                    <c:forEach items="${listSize}" var="size">
                        <option value="${size.id}" ${productDetail.size.id == size.id ? "selected" : ""}>${size.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
    </div>


    <div class="row">
        <div class="col-md-3">
            <div class="mb-3">
                <label class="form-label">ngày tạo</label>
                <input type="text" name="dateCreate" class="form-control" value="${productDetail.dateCreate}"/>
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
                <form:input path="userCreate" class="form-control"/>
                <form:errors path="userCreate" cssClass="errors"/><br>
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
                <form:button type="submit" class="btn btn-primary"
                             onclick="if(!confirm('Bạn có muốn sửa?')){return false}else{alert('sửa thành công');}">
                    update
                </form:button>
            </div>
        </div>
        <div class="col-md-3"></div>
    </div>


</form:form>

<script src="${pageContext.request.contextPath}/assets/jsSell/img.js"></script>

</body>
</html>
