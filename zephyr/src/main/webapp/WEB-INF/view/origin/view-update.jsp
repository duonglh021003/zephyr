<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>

</head>
<body>
<div style="margin-bottom: 30px">
    <span><a href="/zephyr/admin/origin/index">origin </a></span>
    <span style="color: #C0C0C0"> / update</span>
</div>
<form:form action="/zephyr/admin/origin/update?id=${origin.id}" method="POST" modelAttribute="origin">
    <div class="row">
        <div class="col-md-4">
            <div class="mb-3">
                <label class="form-label">code</label>
                <form:input path="code" class="form-control"/>
                <form:errors path="code" cssClass="errors"/><br>
            </div>
        </div>

        <div class="col-md-4">
            <div class="mb-3">
                <label class="form-label">name</label>
                <form:input path="name" class="form-control"/>
                <form:errors path="name" cssStyle="color: red"/><br>
            </div>
        </div>
        <div class="col-md-4">
            <div class="mb-3">
                <label class="form-label">status</label> <br>
                <input type="radio" name="status" value="1" ${origin.status == "1" ? "checked" : ""} checked=""/>đang
                hoạt động <br>
                <input type="radio" name="status" value="0" ${origin.status == "0" ? "checked" : ""} />ngừng
                hoạt động
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-6">
            <div class="mb-3">
                <label class="form-label">ngày tạo</label>
                <form:input path="dateCreate" class="form-control"/>
                <form:errors path="dateCreate" cssClass="errors"/><br>
            </div>
        </div>
        <div class="col-md-6">
            <div class="mb-3">
                <label class="form-label">ngày sửa</label>
                <input type="text" name="dateUpdate" class="form-control" value="${dateUpdate}"/>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-6">
            <div class="mb-3">
                <label class="form-label">người tạo</label>
                <form:input path="userCreate" class="form-control"/>
                <form:errors path="userCreate" cssClass="errors"/><br>
            </div>
        </div>
        <div class="col-md-6">
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
                onclick="if(!confirm('Bạn có muốn sửa?')){return false}else{alert('sửa thành công');}">update</form:button>
            </div>
        </div>
        <div class="col-md-3"></div>
    </div>
</form:form>

</body>
</html>
