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
    <span style="color: #C0C0C0"> / update</span>
</div>

<form:form action="/zephyr/admin/voucher-client/update?id=${voucherClient.id}" method="POST" modelAttribute="voucherClient">
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
                <label class="form-label">số lượng</label>
                <form:input path="quantity" class="form-control"/>
                <form:errors path="quantity" cssClass="errors"/><br>
            </div>
        </div>
        <div class="col-md-3">
            <div class="mb-3">
                <label class="form-label">ngày bắt đầu</label> <br>
                <input type="date" name="dateBegin" class="form-control" value="${voucherClient.dateBegin}" />
            </div>
        </div>
        <div class="col-md-3">
            <div class="mb-3">
                <label class="form-label">ngày kết thúc</label> <br>
                <input type="date" name="dateEnd" class="form-control" value="${voucherClient.dateEnd}" />
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-3">
            <div class="mb-3">
                ${voucherClient.inRank}
                <label class="form-label">inRank</label> <br>
                <select name="inRank" class="form-select">
                    <option value="1" ${voucherClient.inRank == "1" ? "checked" : ""}>Đồng hành</option>
                    <option value="2" ${voucherClient.inRank == '2' ? "checked" : ""}>Thân thiết</option>
                    <option value="3" ${voucherClient.inRank == "3" ? "checked" : ""}>Tri kỷ</option>
                    <option value="4" ${voucherClient.inRank == "4" ? "checked" : ""}>Vip</option>
                </select>
            </div>
        </div>

        <div class="col-md-3">
            <div class="mb-3">
                <label class="form-label">status</label> <br>
                <input type="radio" name="status" value="1" ${size.status == "1" ? "checked" : ""} checked=""/>đang
                hoạt động <br>
                <input type="radio" name="status" value="0" ${size.status == "0" ? "checked" : ""} />ngừng
                hoạt động
            </div>
        </div>
        <div class="col-md-3">
            <div class="mb-3">
                <label class="form-label">giá giảm</label>
                <form:input path="reducedPrice" class="form-control"/>
                <form:errors path="reducedPrice" cssClass="errors"/><br>
            </div>
        </div>
        <div class="col-md-3">
        </div>
    </div>

    <div class="row">
        <div class="col-md-3">
            <div class="mb-3">
                <label class="form-label">ngày tạo</label>
                <form:input path="dateCreate" class="form-control" readonly="true" cssStyle="color: black"/>
            </div>
        </div>
        <div class="col-md-3">
            <div class="mb-3">
                <label class="form-label">ngày sửa</label>
                <input type="text" name="dateUpdate" class="form-control" value="${dateUpdate}" readonly="readonly" style="color: black"/>
            </div>
        </div>
        <div class="col-md-3">
            <div class="mb-3">
                <label class="form-label">người tạo</label>
                <form:input path="userCreate" class="form-control" readonly="true" cssStyle="color: black"/>
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
                <form:button type="submit" class="btn btn-primary"
                             onclick="if(!confirm('Bạn có muốn sửa?')){return false}else{alert('sửa thành công');}">
                    update
                </form:button>
            </div>
        </div>
        <div class="col-md-3"></div>
    </div>
</form:form>

</body>
</html>
