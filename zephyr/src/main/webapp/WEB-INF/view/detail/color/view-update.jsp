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
<h1>Cập Nhật Màu Sắc</h1>
<form:form action="/zephyr/color/update" method="post" modelAttribute="detailColor">
    <div class="form-group row">
        <form:label path="idColor" class="col-sm-2 col-form-label">ID Màu Sắc: </form:label>
        <div class="col-sm-10">
            <p class="form-control">${detailColor.idColor}</p>
            <input type="hidden" name="idColor" value="${detailColor.idColor}">
        </div>
    </div>

    <div class="form-group row">
        <form:label path="nameColor" class="col-sm-2 col-form-label">Tên Màu Sắc:
        </form:label>
        <div class="col-sm-10">
            <input type="text" name="nameColor" value="${detailColor.nameColor}"
                   class="form-control" placeholder="Nhập Tên Màu Sắc"/>
        </div>
    </div>

    <div class="form-group row">
        <form:label path="status" class="col-sm-2 col-form-label">Trạng Thái: </form:label>
        <div class="col-sm-10">
            <select name="status" class="form-control">
                <option value="1" ${detailColor.status==1 ? "selected" : "" }>Đang Hoạt Động
                </option>
                <option value="0" ${detailColor.status==0 ? "selected" : "" }>Ngừng Hoạt Động
                </option>
            </select>
        </div>
    </div>

    <form:button class="btn btn-success" type="submit">Lưu Lại</form:button>
</form:form>
</body>
</html>