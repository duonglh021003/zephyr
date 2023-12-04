<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>

</head>
<body>

<div class="container-fluid">
    <div class="row px-xl-5">
        <div class="col-12">
            <nav class="breadcrumb bg-light mb-30">
                <a class="breadcrumb-item text-dark" href="/zephyr/home" style="text-decoration: none">Home</a>
                <a class="breadcrumb-item active" href="/zephyr/purchase-order" style="text-decoration: none">purchase
                    Order</a>
                <span class="breadcrumb-item active">detail</span>
            </nav>
        </div>
    </div>
</div>

<div class="container-fluid">
    <div class="row px-xl-5">
        <div class="col-lg-12 table-responsive mb-5">

            <nav class="breadcrumb bg-light mb-30">
                <span style=" margin-left: 70%">MÃ ĐƠN HÀNG: <label style="color: red"> ${codeInvoice} </label></span>
                <span style="margin-left: 20px; margin-right: 20px"> | </span>
                <span style="color: red;text-transform: uppercase"> ${statusInvoice} </span>
                <img style="width: 1100px; margin-left: 100px"
                     src="${pageContext.request.contextPath}/assets/images/invoice/${images}" alt="">
            </nav>
            <span style="  border: none;outline: none; height: 50px; color: black; background: white; margin-top: -30px"
                  class="form-control">
                <thead>
                <tr>
                    <span style="margin-left: 125px">
                    <span>
                        <th>Đơn Hàng Đã Đặt</th>
                    </span>

                    <span style="margin-left: 100px">
                        <th>Đã Xác Nhận Đơn Hàng</th>
                    </span>

                    <span style="margin-left: 80px">
                        <th>Đã Giao Cho ĐVVC</th>
                    </span>
                    <span style="margin-left: 90px">
                        <th>Đã Nhận Được Hàng</th>
                    </span>
                    <span style="margin-left: 95px">
                        <th>Đơn Hàng Hoàn Thành</th>
                    </span>
                    </span>

                </tr>
                </thead>
            </span>
            <div style="display: flex; width: 100%; background: white;border: none;outline: none;" class="form-control">
                <c:forEach items="${ listDate }" var="date" varStatus="i">
                    <span style=" color: #909090; background: white; margin-top: -20px; margin-left: 120px">
                        <div>
                            <th>${date.hourMinute} ${date.dateCreate}</th>
                        </div>
                    </span>
                </c:forEach>
            </div>
        </div>
    </div>
</div>

<div class="container-fluid">
    <div class="row px-xl-5">
        <div class="col-lg-12 table-responsive mb-5">
            <nav class="breadcrumb bg-light mb-30">
                <div class="col-lg-8">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>sản phẩm</th>
                            <th>giá</th>
                            <th>số lượng</th>
                            <th>tổng tiền</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${ listDetailInvoice }" var="detailInvoice" varStatus="i">
                            <tr>
                                <td>${i.index + 1}</td>
                                <td class="align-middle"><img
                                        src="/assets/images/client/${detailInvoice.productDetails.images}" style="width: 50px" alt="">
                                        ${detailShopping.productDetails.product.name}(${detailInvoice.productDetails.size.name}, ${detailInvoice.productDetails.color.name})
                                </td>
                                <td class="align-middle">${detailInvoice.unitPrice}00 đ</td>
                                <td class="align-middle">${detailInvoice.quantity}</td>
                                <td class="align-middle">${detailInvoice.capitalSum}00 đ</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>

                <div class="col-lg-4">
                    <table class="table table-bordered">
                        <c:forEach items="${ listInvoice }" var="invoice">
                            <thead>
                            <tr>
                                <th>tổng tiền hoá đơn tạm tính</th>
                                <th>${invoice.totalInvoice}00 đ</th>
                            </tr>
                            </thead>

                            <thead>
                            <tr>
                                <th>tiền ship</th>
                                <th>+${invoice.shippingMoney}00 đ</th>
                            </tr>
                            </thead>

                            <thead>
                            <tr>
                                <th>tiền giảm voucher</th>
                                <th>-${invoice.detailVoucherClient.reducedPrice}00 đ</th>
                            </tr>
                            </thead>

                            <thead>
                            <tr>
                                <th>tiền giảm điểm sử dụng</th>
                                <th>-${invoice.point}00 đ</th>
                            </tr>
                            </thead>

                            <thead>
                            <tr>
                                <th>thành tiền</th>
                                <th style="color: red">${invoice.intoMoney}00 đ</th>
                            </tr>
                            </thead>
                        </c:forEach>
                    </table>
                </div>
            </nav>
        </div>
    </div>
</div>


</body>
</html>