<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--<div class="container-fluid">--%>
<%--    <div class="row px-xl-12">--%>
<%--        <div class="col-12">--%>
<%--            <nav class="breadcrumb bg-light mb-30">--%>
<%--                <img style="width: 1000px"--%>
<%--                     src="${pageContext.request.contextPath}/assets/images/invoice/9.png" alt="">--%>

<%--            </nav>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>

<div class="col-lg-12 grid-margin stretch-card">
    <div class="card">
        <div class="card-body">
            <div style="margin-bottom: 30px">
                <span><a href="/zephyr/admin/staff/index">Staff</a></span>
                <span style="color: #C0C0C0"> / invoice</span>
                <span style="color: #C0C0C0"> / wait for confirmation</span>
            </div>
            <div class="table-responsive">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>code</th>
                        <th>hourMinute</th>
                        <th>dateCreate</th>
                        <th>intoMoney</th>
                        <th>status</th>
                        <th>acction</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${ listInvoiceStatus02 }" var="invoiceStatus02" varStatus="i">
                        <tr>
                            <td>${i.index + 1}</td>
                            <td class="align-middle">${invoiceStatus02.code}</td>
                            <td class="align-middle">${invoiceStatus02.hourMinute}</td>
                            <td class="align-middle">${invoiceStatus02.dateCreate}</td>
                            <td class="align-middle">${invoiceStatus02.intoMoney}</td>
                            <td class="align-middle">${invoiceStatus02.getByStatus()}</td>
                            <td class="align-middle">
                                <a href="/zephyr/admin/invoice-detail/detail?id=${invoiceStatus02.id}">
                                    <button class="btn btn-info">detail</button>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

</body>
</html>