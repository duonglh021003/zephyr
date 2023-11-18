<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>

</head>
<body>
<form:form action="/zephyr/admin/address/update?id=${address.id}" method="POST" modelAttribute="address">
    <div style="margin-bottom: 40px">
        <span><a href="/zephyr/admin/client/index?id=1">Address</a></span>
        <span style="color: #C0C0C0"> / update</span>

    </div>

    <div class="row">
        <div class="col-md-3">
            <div class="mb-3">
                <label class="form-label">mã</label>
                <form:input path="code" class="form-control"/>
                <form:errors path="code" cssClass="errors"/><br>
            </div>
        </div>
        <div class="col-md-3">
            <div class="mb-3">
                <label class="form-label">tên</label>
                <form:input path="name" class="form-control"/>
                <form:errors path="name" cssClass="errors"/><br>
            </div>
        </div>
        <div class="col-md-3">
            <div class="mb-3">
                <label class="form-label">số điện thoại</label>
                <form:input path="phoneNumber" class="form-control"/>
                <form:errors path="phoneNumber" cssClass="errors"/><br>
            </div>
        </div>
        <div class="col-md-3">
            <div class="mb-3">
                <label class="form-label">địa chỉ</label>
                <form:input path="clientAddress" class="form-control"/>
                <form:errors path="clientAddress" cssClass="errors"/><br>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-3">
            <div class="mb-3">
                <label class="form-label">tỉnh/thành phố</label> <br>
                <select class="form-control" name="city" id="city" aria-label=".form-select-sm">
                    <option value="${address.city}" selected>${address.city}</option>
                </select>
            </div>
        </div>
        <div class="col-md-3">
            <div class="mb-3">
                <label class="form-label">quận/huyện</label> <br>
                <select class="form-control" name="district" id="district" aria-label=".form-select-sm">
                    <option value="${address.district}" selected>${address.district}</option>
                </select>
            </div>
        </div>
        <div class="col-md-3">
            <div class="mb-3">
                <label class="form-label">xã/phường</label> <br>
                <select class="form-control" name="commune" id="ward" aria-label=".form-select-sm">
                    <option value="${address.commune}" selected>${address.commune}</option>
                </select>
            </div>
        </div>
        <div class="col-md-3">
            <div class="mb-3">
                <div class="mb-3">
                    <label class="form-label">trạng thái</label> <br>
                    <input type="radio" name="status" value="1" ${address.status == "1" ? "checked" : ""} />mặc định<br>
                    <input type="radio" name="status" value="0" ${address.status == "0" ? "checked" : ""}/> bỏ chọn
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-3">
            <div class="mb-3">
                <label class="form-label">ngày tạo</label>
                <input type="text" name="dateCreate" class="form-control" value="${address.dateCreate}"/>
            </div>
        </div>
        <div class="col-md-3">

        </div>
        <div class="col-md-3">

        </div>
        <div class="col-md-3">

        </div>
    </div>


    <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <div class="mb-3">
                <form:button type="submit" class="btn btn-primary" onclick="add()">update</form:button>
            </div>
        </div>
        <div class="col-md-3"></div>
    </div>


</form:form>

<script>
    function add() {
        if (!confirm('Bạn có muốn sửa?')) {
            return false
        } else {
            alert('sửa thành công');
        }
    }
</script>

</body>
</html>
