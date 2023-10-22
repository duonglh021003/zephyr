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
<h1>Cập Nhật Nhà Sản Xuất</h1>
<form:form action="/zephyr/origin/update" method="post" modelAttribute="detailOrigin">
    <div class="form-group row">
        <form:label path="idOrigin" class="col-sm-2 col-form-label">ID Nhà Sản Xuất: </form:label>
        <div class="col-sm-10">
            <p class="form-control">${detailOrigin.idOrigin}</p>
            <input type="hidden" name="idOrigin" value="${detailOrigin.idOrigin}">
        </div>
    </div>

    <div class="form-group row">
        <form:label path="nameOrigin" class="col-sm-2 col-form-label">Tên Nhà Sản Xuất:
        </form:label>
        <div class="col-sm-10">
            <input type="text" name="nameOrigin" value="${detailOrigin.nameOrigin}"
                   class="form-control" placeholder="Nhập Tên Nhà Sản Xuất" />
        </div>
    </div>

    <div class="form-group row">
        <form:label path="status" class="col-sm-2 col-form-label">Trạng Thái: </form:label>
        <div class="col-sm-10">
            <select name="status" class="form-control">
                <option value="1" ${detailOrigin.status==1 ? "selected" : "" }>Đang Hoạt Động
                </option>
                <option value="0" ${detailOrigin.status==0 ? "selected" : "" }>Ngừng Hoạt Động
                </option>
            </select>
        </div>
    </div>

    <form:button class="btn btn-success" type="submit">Lưu Lại</form:button>
</form:form>
</body>
</html>