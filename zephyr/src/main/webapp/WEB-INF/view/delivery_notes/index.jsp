<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="col-lg-12 grid-margin stretch-card">
    <div class="card">
        <div class="card-body">
            <div style="margin-bottom: 30px">
                <span><a href="/zephyr/admin/staff/index">Invoice</a></span>
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
                    <c:forEach items="${ listInvoiceStaff }" var="invoiceStaff" varStatus="i">
                        <tr>
                            <td>${i.index + 1}</td>
                            <td class="align-middle">${invoiceStaff.code}</td>
                            <td class="align-middle">${invoiceStaff.hourMinute}</td>
                            <td class="align-middle">${invoiceStaff.dateCreate}</td>
                            <td class="align-middle">${invoiceStaff.intoMoney}</td>
                            <td class="align-middle">${invoiceStaff.getByStatus()}</td>
                            <td class="align-middle">
                                <a href="/zephyr/admin/detail-delivery-notes/detail?id=${invoiceStaff.id}">
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