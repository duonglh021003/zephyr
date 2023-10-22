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
<h1>Thêm Màu Sắc</h1>
<form:form action="/zephyr/color/add" method="post" modelAttribute="color">
    <div class="form-group row">
        <form:label path="nameColor" class="col-sm-2 col-form-label">Tên Màu Sắc:
        </form:label>
        <div class="col-sm-10">
            <input type="text" name="nameColor" value="${color.nameColor}"
                   class="form-control" placeholder="Nhập Tên Màu Sắc"/>
        </div>
    </div>

    <div class="form-group row">
        <form:label path="status" class="col-sm-2 col-form-label">Trạng Thái: </form:label>
        <div class="col-sm-10">
            <input type="text" class="form-control" value="Đang Hoạt Động" readonly/>
            <input type="hidden" name="status" value="${color.status}">
        </div>
    </div>

    <form:button class="btn btn-primary" type="reset">Làm Mới</form:button>
    <form:button class="btn btn-success" type="submit">Lưu Lại</form:button>
</form:form>
</body>
</html>