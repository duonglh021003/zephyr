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
<h1>Cập Nhật Kích Thước</h1>
<form:form action="/zephyr/size/update" method="post" modelAttribute="detailSize">
    <div class="form-group row">
        <form:label path="idSize" class="col-sm-2 col-form-label">ID Kích Thước: </form:label>
        <div class="col-sm-10">
            <p class="form-control">${detailSize.idSize}</p>
            <input type="hidden" name="idSize" value="${detailSize.idSize}">
        </div>
    </div>

    <div class="form-group row">
        <form:label path="nameSize" class="col-sm-2 col-form-label">Tên Kích Thước:
        </form:label>
        <div class="col-sm-10">
            <input type="text" name="nameSize" value="${detailSize.nameSize}"
                   class="form-control" placeholder="Nhập Tên Kích Thước"/>
        </div>
    </div>

    <div class="form-group row">
        <form:label path="status" class="col-sm-2 col-form-label">Trạng Thái: </form:label>
        <div class="col-sm-10">
            <select name="status" class="form-control">
                <option value="1" ${detailSize.status==1 ? "selected" : "" }>Đang Hoạt Động
                </option>
                <option value="0" ${detailSize.status==0 ? "selected" : "" }>Ngừng Hoạt Động
                </option>
            </select>
        </div>
    </div>

    <form:button class="btn btn-success" type="submit">Lưu Lại</form:button>
</form:form>
</body>
</html>