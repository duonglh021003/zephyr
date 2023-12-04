<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>

</head>
<body>
<form:form action="/zephyr/admin/staff/add" method="POST" modelAttribute="staff">
    <div style="margin-bottom: 30px">
        <span><a href="/zephyr/admin/staff/index">Staff</a></span>
        <span style="color: #C0C0C0"> / add</span>
    </div>

    <div class="row">
        <div class="col-md-3">
            <div class="mb-3">
                <label class="form-label">chức vụ</label> <br>
                <select name="position" ${staff.position.name} class="form-control">
                    <c:forEach items="${listPosition}" var="position">
                        <option value="${position.id}">${position.name}</option>
                    </c:forEach>
                </select>
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
                <label class="form-label">ngày sinh</label> <br>
                <input type="date" name="dateOfBirth" class="form-control" value="${staff.dateOfBirth}"/>
            </div>
        </div>
        <div class="col-md-3">
            <div class="mb-3">
                <label class="form-label">số điện thoại</label>
                <form:input path="phoneNumber" class="form-control"/>
                <form:errors path="phoneNumber" cssClass="errors"/><br>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-3">
            <div class="mb-3">
                <label class="form-label">gmail</label>
                <form:input path="gmail" class="form-control"/>
                <form:errors path="gmail" cssClass="errors"/><br>
            </div>
        </div>
        <div class="col-md-3">
            <div class="mb-3">
                <label class="form-label">giới tính</label> <br>
                <input type="radio" name="gender" value="true" ${staff.gender == "true" ? "checked" : ""} checked=""/>nam
                <input type="radio" name="gender" value="false" ${staff.gender == "false" ? "checked" : ""}/>nữ
            </div>
        </div>
        <div class="col-md-3">
            <div class="mb-3">
                <label class="form-label">địa chỉ</label>
                <form:input path="address" class="form-control"/>
                <form:errors path="address" cssClass="errors"/><br>
            </div>
        </div>
        <div class="col-md-3">
            <div class="mb-3">
                <label class="form-label">tỉnh/thành phố</label> <br>
                <select class="form-control" name="city"  id="city" aria-label=".form-select-sm">
                    <option value="" selected>Chọn tỉnh/thành</option>
                </select>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-3">
            <div class="mb-3">
                <label class="form-label">quận/huyện</label> <br>
                <select class="form-control" name="district" id="district" aria-label=".form-select-sm">
                    <option value="" selected>Chọn quận/huyện</option>
                </select>
            </div>
        </div>
        <div class="col-md-3">
            <div class="mb-3">
                <label class="form-label">xã/phường</label> <br>
                <select class="form-control" name="commune" id="ward" aria-label=".form-select-sm">
                    <option value="" selected>Chọn phường/xã</option>
                </select>
            </div>

        </div>
        <div class="col-md-3">
            <div class="mb-3">
                <label class="form-label">password</label>
                <form:input path="password" class="form-control"/>
                <form:errors path="password" cssClass="errors"/><br>
            </div>
        </div>
        <div class="col-md-3">
            <div class="mb-3">
                <label class="form-label">trạng thái</label> <br>
                <input type="radio" name="status" value="1" ${staff.status == "1" ? "checked" : ""} checked=""/>đang
                hoạt động
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

<script>
    function add() {
        if (!confirm('Bạn có muốn thêm?')) {
            return false
        } else {
            alert('thêm thành công');
        }
    }
</script>


</body>
</html>
