<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>

</head>
<body>
<form:form action="/zephyr/admin/client/update?id=${client.id}" method="POST" modelAttribute="client">
    <div style="margin-bottom: 40px">
        <span><a href="/zephyr/admin/client/index?id=1">Client</a></span>
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
                <label class="form-label">ngày sinh</label> <br>
                <input type="date" class="form-control" name="dateOfBirth" value="${client.dateOfBirth}"/>
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
                <input type="radio" name="gender" value="true" ${client.gender == "true" ? "checked" : ""} checked=""/>nam
                <input type="radio" name="gender" value="false" ${client.gender == "false" ? "checked" : ""}/>nữ
            </div>
        </div>
        <div class="col-md-3">
            <div class="mb-3">
                <label class="form-label">điểm sử dụng</label>
                <form:input path="pointUsr" class="form-control"/>
                <form:errors path="pointUsr" cssClass="errors"/><br>
            </div>
        </div>
        <div class="col-md-3">
            <div class="mb-3">
                <label class="form-label">điểm tích luỹ</label>
                <form:input path="accumulatedScore" class="form-control"/>
                <form:errors path="accumulatedScore" cssClass="errors"/><br>
            </div>
        </div>
    </div>

    <div class="row">
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
                <input type="radio" name="status" value="1" ${client.status == "1" ? "checked" : ""} checked=""/>đang
                hoạt động <br>
                <input type="radio" name="status" value="0" ${client.status == "0" ? "checked" : ""}/>ngừng hoạt dộng
            </div>
        </div>
        <div class="col-md-3">
            <div class="mb-3">
                <label class="form-label">rank</label> <br>
                <input type="text" name="rank" class="form-control" value="${client.idRank()}"/>
            </div>
        </div>
        <div class="col-md-3">
            <div class="mb-3">
                <label class="form-label">rank</label> <br>
                <input type="text" name="shoppingCart" class="form-control" value="${client.shoppingCart.id}"/>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-3">
            <div class="mb-3">
                <label class="form-label">ngày tạo</label>
                <input type="text" name="dateCreate" class="form-control" value="${client.dateCreate}"/>
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
