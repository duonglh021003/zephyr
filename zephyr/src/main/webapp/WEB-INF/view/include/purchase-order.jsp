<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>

    <link href="${pageContext.request.contextPath}/assets/css/purchase-order.css" rel="stylesheet">
</head>
<body>

<div class="container-fluid">
    <div class="row px-xl-5">
        <div class="col-12">
            <nav class="breadcrumb bg-light mb-30">
                <a class="breadcrumb-item text-dark" href="/zephyr/home" style="text-decoration: none">Home</a>
                <span class="breadcrumb-item active">Purchase Order</span>
            </nav>
        </div>
    </div>
</div>

<div class="container-fluid">
    <div class="row px-xl-5">
        <div class="col-lg-12 table-responsive mb-5">
            <button class="tablink" onclick="openPage('All', this, 'FFD333')" id="defaultOpen">Tất cả</button>
            <button class="tablink" onclick="openPage('2', this, 'FFD333')" >chờ xác nhận</button>
            <button class="tablink" onclick="openPage('3', this, 'FFD333')" >chờ lấy hàng</button>
            <button class="tablink" onclick="openPage('4', this, 'FFD333')">chờ giao hàng</button>
            <button class="tablink" onclick="openPage('5', this, 'FFD333')">đã nhận hàng</button>
            <button class="tablink" onclick="openPage('7', this, 'FFD333')">đã huỷ</button>
            <button class="tablink" onclick="openPage('8', this, 'FFD333')">đổi hàng</button>

            <div id="All" class="tabcontent">
                <div class="bg-light p-30 mb-5">
                    <div class="row">
                        <table class="table table-light table-borderless table-hover text-center mb-0">
                            <thead class="thead-dark">
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
                            <tbody class="align-middle">
                            <c:forEach items="${ listInvoice }" var="invoice" varStatus="i">
                                <tr>
                                    <td>${i.index + 1}</td>
                                    <td class="align-middle">${invoice.code}</td>
                                    <td class="align-middle">${invoice.hourMinute}</td>
                                    <td class="align-middle">${invoice.dateCreate}</td>
                                    <td class="align-middle">${invoice.intoMoney}00 đ</td>
                                    <td class="align-middle">${invoice.getByStatus()}</td>
                                    <td class="align-middle">
                                        <a href="/zephyr/purchase-order/detail?id=${invoice.id}">
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

            <div id="2" class="tabcontent">
                <div class="bg-light p-30 mb-5">
                    <div class="row">
                        <table class="table table-light table-borderless table-hover text-center mb-0">
                            <thead class="thead-dark">
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
                            <tbody class="align-middle">
                            <c:forEach items="${ listInvoiceStatus02 }" var="invoiceStatus1" varStatus="i">
                                <tr>
                                    <td>${i.index + 1}</td>
                                    <td class="align-middle">${invoiceStatus1.code}</td>
                                    <td class="align-middle">${invoiceStatus1.hourMinute}</td>
                                    <td class="align-middle">${invoiceStatus1.dateCreate}</td>
                                    <td class="align-middle">${invoiceStatus1.intoMoney}00 đ</td>
                                    <td class="align-middle">${invoiceStatus1.getByStatus()}</td>
                                    <td class="align-middle">
                                        <a href="/zephyr/purchase-order/detail?id=${invoiceStatus1.id}">
                                            <button class="btn btn-info">detail</button>
                                        </a>

                                        <a href="/zephyr/purchase-order/update-status2?id=${invoiceStatus1.id}">
                                            <button class="btn btn-sm btn-danger" style="height: 38px"
                                                    onclick="if(!confirm('Bạn có muốn huỷ đơn hàng này không?')){return false}else{alert('huỷ thành công');}">cancel invoice
                                            </button>
                                        </a>
                                    </td>

                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <div id="3" class="tabcontent">
                <div class="bg-light p-30 mb-5">
                    <div class="row">
                        <table class="table table-light table-borderless table-hover text-center mb-0">
                            <thead class="thead-dark">
                            <tr>
                                <th>#</th>
                                <th>code</th>
                                <th>hourMinute</th>
                                <th>dateCreate</th>
                                <th>intoMoney</th>
                                <th>status</th>
                            </tr>
                            </thead>
                            <tbody class="align-middle">
                            <c:forEach items="${ listInvoiceStatus03 }" var="invoiceStatus3" varStatus="i">
                                <tr>
                                    <td>${i.index + 1}</td>
                                    <td class="align-middle">${invoiceStatus3.code}</td>
                                    <td class="align-middle">${invoiceStatus3.hourMinute}</td>
                                    <td class="align-middle">${invoiceStatus3.dateCreate}</td>
                                    <td class="align-middle">${invoiceStatus3.intoMoney}00 đ</td>
                                    <td class="align-middle">${invoiceStatus3.getByStatus()}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <div id="4" class="tabcontent">
                <div class="bg-light p-30 mb-5">
                    <div class="row">
                        <table class="table table-light table-borderless table-hover text-center mb-0">
                            <thead class="thead-dark">
                            <tr>
                                <th>#</th>
                                <th>code</th>
                                <th>hourMinute</th>
                                <th>dateCreate</th>
                                <th>intoMoney</th>
                                <th>status</th>
                            </tr>
                            </thead>
                            <tbody class="align-middle">
                            <c:forEach items="${ listInvoiceStatus04 }" var="invoiceStatus4" varStatus="i">
                                <tr>
                                    <td>${i.index + 1}</td>
                                    <td class="align-middle">${invoiceStatus4.code}</td>
                                    <td class="align-middle">${invoiceStatus4.hourMinute}</td>
                                    <td class="align-middle">${invoiceStatus4.dateCreate}</td>
                                    <td class="align-middle" >${invoiceStatus4.intoMoney}00 đ</td>
                                    <td class="align-middle">${invoiceStatus4.getByStatus()}</td>

                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <div id="5" class="tabcontent">
                <div class="bg-light p-30 mb-5">
                    <div class="row">
                        <table class="table table-light table-borderless table-hover text-center mb-0">
                            <thead class="thead-dark">
                            <tr>
                                <th>#</th>
                                <th>code</th>
                                <th>hourMinute</th>
                                <th>dateCreate</th>
                                <th>intoMoney</th>
                                <th>status</th>
                            </tr>
                            </thead>
                            <tbody class="align-middle">
                            <c:forEach items="${ listInvoiceStatus05 }" var="invoiceStatus5" varStatus="i">
                                <tr>
                                    <td>${i.index + 1}</td>
                                    <td class="align-middle">${invoiceStatus5.code}</td>
                                    <td class="align-middle">${invoiceStatus5.hourMinute}</td>
                                    <td class="align-middle">${invoiceStatus5.dateCreate}</td>
                                    <td class="align-middle">${invoiceStatus5.intoMoney}00 đ</td>
                                    <td class="align-middle">${invoiceStatus5.getByStatus()}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <div id="7" class="tabcontent">
                <div class="bg-light p-30 mb-5">
                    <div class="row">
                        <table class="table table-light table-borderless table-hover text-center mb-0">
                            <thead class="thead-dark">
                            <tr>
                                <th>#</th>
                                <th>code</th>
                                <th>hourMinute</th>
                                <th>dateCreate</th>
                                <th>intoMoney</th>
                                <th>status</th>
                            </tr>
                            </thead>
                            <tbody class="align-middle">
                            <c:forEach items="${ listInvoiceStatus07 }" var="invoiceStatus7" varStatus="i">
                                <tr>
                                    <td>${i.index + 1}</td>
                                    <td class="align-middle">${invoiceStatus7.code}</td>
                                    <td class="align-middle">${invoiceStatus7.hourMinute}</td>
                                    <td class="align-middle">${invoiceStatus7.dateCreate}</td>
                                    <td class="align-middle">${invoiceStatus7.intoMoney}</td>
                                    <td class="align-middle">${invoiceStatus7.getByStatus()}</td>


                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <div id="8" class="tabcontent">
                <h3>7</h3>
                <p>Who we are and what we do.</p>
            </div>
        </div>
    </div>
</div>


<script src="${pageContext.request.contextPath}/assets/jsInclude/purchase-order.js"></script>
</body>
</html>