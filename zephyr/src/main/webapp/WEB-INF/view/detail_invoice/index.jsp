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
                <span><a href="/zephyr/admin/invoice/wait-for-confirmation">Invoice</a></span>
                <span style="color: #C0C0C0"> / detail invoice</span>

                <span style=" margin-left: 50%">MÃ ĐƠN HÀNG: <label style="color: red"> ${codeInvoice}</label></span>
            </div>

            <div class="row px-xl-6">
                <div class="col-lg-8">
                    <h5><span>Detail invoice</span></h5>
                    <div class="table-responsive">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>#</th>
                                <th>Products</th>
                                <th>Price</th>
                                <th>Quantity</th>
                                <th>Sub total</th>
                                <th>status</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${ listDetailInvoice }" var="detailInvoice" varStatus="i">
                                <tr>
                                    <td>${i.index + 1}</td>
                                    <td class="align-middle"><img
                                            src="/assets/images/client/${detailInvoice.productDetails.images}" alt="">
                                            ${detailShopping.productDetails.product.name}(${detailInvoice.productDetails.size.name}, ${detailInvoice.productDetails.color.name})
                                    </td>
                                    <td class="align-middle">${detailInvoice.unitPrice}00</td>
                                    <td class="align-middle">${detailInvoice.quantity}</td>
                                    <td class="align-middle">${detailInvoice.capitalSum}00</td>
                                    <td class="align-middle">${detailInvoice.status}</td>

                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="col-lg-4">
                    <form>
                        <h5><span>Address client</span></h5>
                        <div class="bg-light p-30 mb-5" style="">
                            <c:forEach items="${ listAddress }" var="address" varStatus="i">

                                <input type="text" style=" margin-bottom: 1px" class="form-control"
                                       value="${address.name}"/>
                                <input type="text" style=" margin-bottom: 1px" class="form-control"
                                       value="${address.phoneNumber}"/>
                                <input type="text" style=" margin-bottom: 1px" class="form-control"
                                       value="${address.clientAddress}"/>
                                <input type="text" style=" margin-bottom: 1px" class="form-control"
                                       value="${address.commune},${address.district},${address.city}"/>

                            </c:forEach>
                        </div>
                    </form>
                </div>
            </div>

            <div style="margin-top: 20px">
                <table class="table table-bordered">
                    <c:forEach items="${ listInvoice }" var="invoice">
                        <thead>
                        <tr>
                            <th>totalInvoice</th>
                            <th>${invoice.totalInvoice}</th>
                        </tr>
                        </thead>

                        <thead>
                        <tr>
                            <th>shippingMoney</th>
                            <th>+${invoice.shippingMoney}00</th>
                        </tr>
                        </thead>

                        <thead>
                        <tr>
                            <th>voucher</th>
                            <th>-${invoice.detailVoucherClient.reducedPrice}00</th>
                        </tr>
                        </thead>

                        <thead>
                        <tr>
                            <th>pointUsr</th>
                            <th>-${invoice.point}00</th>
                        </tr>
                        </thead>

                        <thead>
                        <tr>
                            <th>intoMoney</th>
                            <th style="color: red">${invoice.intoMoney}00</th>
                        </tr>
                        </thead>
                    </c:forEach>
                </table>
            </div>

            <div>
                <c:forEach items="${ listInvoice }" var="invoice">
                    <a type="button" href="/zephyr/admin/invoice/update-status-2?id=${invoice.id}"
                       class="btn btn-success" style="margin-left: 60%; margin-top: 30px"
                       onclick="if(!confirm('Bạn có chắc chắn muốn xác nhận đơn hàng này không?')){return false}else{alert('xác nhận thành công');}"
                    >confirm invoice</a>
                </c:forEach>
            </div>
        </div>
    </div>
</div>


</body>
</html>
