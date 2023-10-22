<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<h1>Thêm Chi Tiết Sản Phẩm</h1>
<form:form action="/zephyr/productdetail/add" method="post" modelAttribute="productDetail">
    <div class="form-group row">
        <form:label path="product" class="col-sm-2 col-form-label">Tên Sản Phẩm:
        </form:label>
        <select name="product" ${productDetail.product} class="form-control col-sm-10">
            <c:forEach items="${listProduct}" var="cbbProduct">
                <option value="${cbbProduct.idProduct}">${cbbProduct.nameProduct}</option>
            </c:forEach>
        </select>
    </div>

    <div class="form-group row">
        <form:label path="origin" class="col-sm-2 col-form-label">Tên Nhà Sản Xuất:
        </form:label>
        <select name="origin" ${productDetail.origin} class="form-control col-sm-10">
            <c:forEach items="${listOrigin}" var="cbbOrigin">
                <option value="${cbbOrigin.idOrigin}">${cbbOrigin.nameOrigin}</option>
            </c:forEach>
        </select>
    </div>

    <div class="form-group row">
        <form:label path="color" class="col-sm-2 col-form-label">Tên Màu Sắc:
        </form:label>
        <select name="color" ${productDetail.color} class="form-control col-sm-10">
            <c:forEach items="${listColor}" var="cbbColor">
                <option value="${cbbColor.idColor}">${cbbColor.nameColor}</option>
            </c:forEach>
        </select>
    </div>

    <div class="form-group row">
        <form:label path="size" class="col-sm-2 col-form-label">Tên Kích Thước:
        </form:label>
        <select name="size" ${productDetail.size} class="form-control col-sm-10">
            <c:forEach items="${listSize}" var="cbbSize">
                <option value="${cbbSize.idSize}">${cbbSize.nameSize}</option>
            </c:forEach>
        </select>
    </div>

    <div class="form-group row">
        <form:label path="images" class="col-sm-2 col-form-label">Ảnh Sản Phẩm: </form:label>
        <div class="col-sm-10">
            <div class="col-sm-8">
                <form:input type="file" id="imageInput" class="form-control" path="images"
                            value="${productDetail.images}" accept="/assets/images/*"/>
            </div>
            <div class="col-sm-4">
                <img id="previewImage" style="width: 200px; height: 200px;" alt=""/>
            </div>
        </div>
    </div>

    <div class="form-group row">
        <form:label path="describe" class="col-sm-2 col-form-label">Mô Tả:
        </form:label>
        <div class="col-sm-10">
            <input type="text" name="describe" value="${productDetail.describe}"
                   class="form-control"/>
        </div>
    </div>

    <div class="form-group row">
        <form:label path="inventory" class="col-sm-2 col-form-label">Số Lượng Tồn:
        </form:label>
        <div class="col-sm-10">
            <input type="text" name="inventory" value="${productDetail.inventory}"
                   class="form-control"/>
        </div>
    </div>

    <div class="form-group row">
        <form:label path="importPrice" class="col-sm-2 col-form-label">Giá Nhập:
        </form:label>
        <div class="col-sm-10">
            <input type="text" name="importPrice" value="${productDetail.importPrice}"
                   class="form-control"/>
        </div>
    </div>

    <div class="form-group row">
        <form:label path="price" class="col-sm-2 col-form-label">Giá Bán:
        </form:label>
        <div class="col-sm-10">
            <input type="text" name="price" value="${productDetail.price}"
                   class="form-control"/>
        </div>
    </div>

    <div class="form-group row">
        <form:label path="status" class="col-sm-2 col-form-label">Trạng Thái: </form:label>
        <div class="col-sm-10">
            <input type="text" class="form-control" value="Đang Hoạt Động" readonly/>
            <input type="hidden" name="status" value="${productDetail.status}">
        </div>
    </div>

    <form:button class="btn btn-primary" type="reset">Làm Mới</form:button>
    <form:button class="btn btn-success" type="submit">Lưu Lại</form:button>
</form:form>
<script type="text/javascript">
    document.getElementById('imageInput').addEventListener('change', function (e) {
        var reader = new FileReader();
        reader.onload = function (e) {
            document.getElementById('previewImage').src = e.target.result;
        }
        reader.readAsDataURL(this.files[0]);
    });
</script>
</body>
</html>