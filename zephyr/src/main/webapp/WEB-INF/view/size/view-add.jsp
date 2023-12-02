<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div style="margin-bottom: 30px">
    <span><a href="/zephyr/admin/size/index">size </a></span>
    <span style="color: #C0C0C0"> / add</span>
</div>

<form:form action="/zephyr/admin/size/add" method="POST" modelAttribute="size">
    <div class="modal-body">
        <div class="row">
            <div class="col-md-6">
                <div class="mb-3">
                    <label class="form-label">tên</label>
                    <form:input path="name" class="form-control"/>
                    <form:errors path="name" cssStyle="color: red"/><br>
                </div>
            </div>
            <div class="col-md-6">
                <div class="mb-3">
                    <label class="form-label">trạng thái</label> <br>
                    <input type="radio" name="status" value="1" checked=""/>đang
                    hoạt động
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6">
                <div class="mb-3">
                    <label class="form-label">ngày tạo</label>
                    <input type="text" name="dateCreate" class="form-control" value="${dateUpdate}" style="color: black"
                           readonly="readonly"/>
                </div>
            </div>
            <div class="col-md-6">
                <div class="mb-3">
                    <label class="form-label">ngày sửa</label>
                    <input type="text" name="dateUpdate" class="form-control" value="${dateUpdate}" style="color: black"
                           readonly="readonly"/>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6">
                <div class="mb-3">
                    <label class="form-label">người tạo</label>
                    <input type="text" name="userCreate" class="form-control" value="${staffSession.name}"
                           style="color: black" readonly="readonly"/>
                </div>
            </div>
            <div class="col-md-6">
                <div class="mb-3">
                    <label class="form-label">người sửa</label>
                    <input type="text" name="userUpdate" class="form-control" value="${staffSession.name}"
                           style="color: black" readonly="readonly"/>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <div class="mb-3">
                    <form:button type="submit" class="btn btn-primary"
                                 onclick="if(!confirm('Bạn có muốn thêm?')){return false}else{alert('thêm thành công');}">
                        Add
                    </form:button>
                </div>
            </div>
            <div class="col-md-3"></div>
        </div>
    </div>

</form:form>

</body>
</html>