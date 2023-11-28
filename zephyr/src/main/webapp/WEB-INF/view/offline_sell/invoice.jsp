<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

</head>
<body>

<div class="col-lg-12 grid-margin stretch-card">
    <div class="card">
        <div class="card-body">

            <div class="row">
                <div class="col-md-6">
                    <div class="mb-3">
                        <a type="button" class="btn btn-success" href="/zephyr/admin/sell/add"
                           style="margin-bottom: 20px">create
                            invoice</a> <br>
                        <c:forEach items="${ listInvoiceStatus0 }" var="invoiceStatus0" varStatus="i">
                            <a type="button" class="btn btn-light"
                               href="/zephyr/admin/sell/invoice?id=${invoiceStatus0.id}">
                                <tr>
                                    <td class="align-middle">${invoiceStatus0.code} </td>
                                </tr>
                            </a>
                        </c:forEach>
                        <div>
                            <c:forEach items="${ listInvoice }" var="invoice" varStatus="i">
                                <br>
                                <a type="button" class="btn btn-danger"
                                   href="/zephyr/admin/sell/invoice/delete?id=${invoice.id}">
                                    cancel invoice
                                </a>
                            </c:forEach>
                        </div>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="mb-3">
                        <div class="row">

                            <div class="col-md-6">
                                <div class="mb-3">
                                    <c:forEach items="${ listInvoice }" var="invoice" varStatus="i">
                                        <form action="/zephyr/admin/sell/invoice/client-add" method="get">
                                            <section>
                                                <option>
                                                    <input type="text" name="id" value="${invoice.id}">
                                                </option>
                                                <input name="phoneNumber" value="${invoice.client.phoneNumber}"
                                                       placeholder="phoneNumber">
                                                <button class="btn btn-success ms-2">client</button>
                                            </section>
                                        </form>
                                    </c:forEach>
                                </div>
                            </div>

                            <div class="col-md-6" style="margin-top: 20px">
                                <div class="mb-3 ">
                                    <c:forEach items="${ listInvoice }" var="invoice">
                                        <a type="button"
                                           href="/zephyr/admin/sell/invoice/add-point-use?id=${invoice.id}"
                                           class="btn btn-primary">use</a>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <c:forEach items="${ listInvoice }" var="invoice" varStatus="i">
                                <div class="col-md-6">
                                    <div class="mb-3">
                                        <input name="nameClient" style="color: black" class="form-control"
                                               value="${invoice.client.name}"
                                               placeholder="name" readonly="readonly">
                                    </div>

                                </div>
                                <div class="col-md-6">
                                    <div class="mb-3 d-flex">
                                        <input name="nameClient" style="color: black" class="form-control"
                                               value="${invoice.client.pointUsr}"
                                               placeholder="point use" readonly="readonly">
                                    </div>
                                </div>
                            </c:forEach>

                        </div>
                        <form action="/zephyr/admin/sell/invoice/add-voucher">
                            <c:forEach items="${ listInvoice }" var="invoice" varStatus="i">
                                <select name="voucher">
                                    <option>vui lòng chọn voucher</option>
                                    <c:forEach items="${listVoucherPrice}" var="voucher">
                                        <option value="${voucher.id}">${voucher.name} ( ${voucher.reducedPrice}00 đ )
                                        </option>
                                    </c:forEach>
                                    <input type="hidden" name="id" value="${invoice.id}">
                                    <button class="btn btn-success ms-2">apply</button>
                                </select>
                            </c:forEach>
                        </form>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-lg-12 grid-margin stretch-card">
        <div class="col-md-7 grid-margin stretch-card">
            <div class="card">
                <div class="card-body">
                    <a type="button" class="btn btn-success" data-toggle="modal"
                       data-target="#myModalAddProduct"
                       style="margin-bottom: 20px">add product</a>
                    <div class="table-responsive" style="margin-top: 10px">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>#</th>
                                <th>Products</th>
                                <th>Price</th>
                                <th>Quantity</th>
                                <th>Sub total</th>
                                <th>acction</th>
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
                                    <td class="align-middle">
                                        <div class="input-group quantity mx-auto" style="width: 100px;">
                                            <div class="input-group-btn">
                                                <form action="/zephyr/admin/sell/less?id=${detailInvoice.id}"
                                                      method="post">
                                                    <button class="btn btn-sm btn-primary btn-minus">
                                                        <i class="fa fa-minus"></i>
                                                    </button>
                                                </form>
                                            </div>
                                            <input style="height: 24px" type="text" name="quantity"
                                                   class="form-control "
                                                   value="${detailInvoice.quantity}">
                                            <div class="input-group-btn">
                                                <form action="/zephyr/admin/sell/plus?id=${detailInvoice.id}"
                                                      method="post">
                                                    <button class="btn btn-sm btn-primary btn-plus">
                                                        <i class="fa fa-plus"></i>
                                                    </button>
                                                </form>
                                            </div>
                                        </div>
                                    </td>
                                    <td class="align-middle">${detailInvoice.subTotalDetailInvoice()}00</td>
                                    <td class="align-middle">

                                        <a type="button" class="btn btn-danger"
                                           href="/zephyr/admin/sell/delete?id=${detailInvoice.id}"
                                           onclick="if(!confirm('Bạn có muốn xoá sản phẩm ?')){return false}else{alert('xoá thành công');}">
                                            delete
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

        <div class="col-md-5 grid-margin stretch-card">
            <div class="card">
                <div class="card-body">
                    <div class="table-responsive">
                        <form action="/zephyr/admin/sell/invoice/update" method="post">
                            <div>
                                <c:forEach items="${ listInvoice }" var="invoice" varStatus="i">
                                    <div class="border-bottom pt-3 pb-2" style="margin-top: -20px;line-height: 10px">
                                        <div class="d-flex justify-content-between mb-3">
                                            <h6>mã hoá đơn: </h6>
                                            <input style="border: none;outline: none;color: red" name="code"
                                                   value="${invoice.code}" readonly="readonly">
                                        </div>
                                        <hr>
                                        <div class="d-flex justify-content-between mb-3">
                                            <p>giờ tạo: </p>
                                            <input style="border: none;outline: none;" name="hourMinute"
                                                   value="${invoice.hourMinute}" readonly="readonly">
                                        </div>
                                        <hr>
                                        <div class="d-flex justify-content-between mb-3">
                                            <p>ngày tạo: </p>
                                            <input style="border: none;outline: none;" name="dateCreate"
                                                   value="${invoice.dateCreate}" readonly="readonly">
                                        </div>
                                        <hr>
                                        <div class="d-flex justify-content-between mb-3">
                                            <p>tổng tiền hoá đơn: </p>
                                            <input style="border: none;outline: none;" name="totalInvoice"
                                                   value="${totalInvoice}00" readonly="readonly">
                                        </div>
                                        <hr>
                                        <div class="d-flex justify-content-between mb-3">
                                            <p>điểm sử dụng: </p>
                                            <input style="border: none;outline: none;" name="point"
                                                   value="${invoice.point}00" readonly="readonly">
                                        </div>
                                        <hr>
                                        <div class="d-flex justify-content-between mb-3">
                                            <p>tiền phiếu giảm giá: </p>
                                            <input style="border: none;outline: none;"
                                                   value="${invoice.voucher.reducedPrice}00" readonly="readonly">
                                        </div>
                                        <hr>
                                        <div class="d-flex justify-content-between mb-3">
                                            <h6>thành tiền: </h6>
                                            <input id="intoMoney" style="border: none;outline: none;color: red"
                                                   name="intoMoney"
                                                   value="${invoice.intoMoney}00" readonly="readonly">
                                        </div>
                                        <hr>
                                        <div class="d-flex justify-content-between mb-3">
                                            <h6>tiền khách hàng trả: </h6>
                                            <input id="customerPayment" style="border: none;outline: none;color: blue"
                                                   name="clientGiveMoney"
                                                   value="">
                                        </div>
                                        <hr>
                                        <div class="d-flex justify-content-between mb-3">
                                            <h6>tiền trả lại khách hàng: </h6>
                                            <input id="changeAmount" style="border: none;outline: none;color: blue"
                                                   name="returnClientMoney"
                                                   value="">
                                        </div>
                                        <hr>
                                        <div class="d-flex justify-content-between mb-3">
                                            <p>ghi chú: </p>
                                            <textarea style="height: 50px" name="note">${invoice.note}</textarea>
                                        </div>
                                    </div>

                                </c:forEach>

                            </div>

                            <div class="mb-5">
                                <div class="bg-light p-30">
                                    <button class="btn btn-block btn-primary font-weight-bold py-3"
                                            onclick="if(!confirm('Bạn có chắc chắn muốn mua hàng?')){return false}else{alert('mua hàng thành công');}">
                                        Place Order
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%-- Model prodct --%>

<div class="modal fade" id="myModalAddProduct" role="dialog">
    <div class="modal-dialog modal-xl">
        <div class="modal-content">
            <table class="table table-bordered">
                <thead class="thead-dark">
                <tr>
                    <th>#</th>
                    <th>Products</th>
                    <th>quantity</th>
                    <th>price</th>
                    <th>size</th>
                    <th>color</th>
                    <th>acction</th>
                </tr>
                </thead>
                <tbody class="align-middle">

                <c:forEach items="${ listDetailProduct }" var="detailProduct" varStatus="i">
                    <form action="/zephyr/admin/sell/add-product?id=${detailProduct.product.id}">
                        <tr>
                            <td>${i.index + 1}</td>
                            <td class="align-middle"><img
                                    src="/assets/images/client/${detailProduct.images}" alt="">
                                    ${detailProduct.product.name}
                            </td>
                            <td class="align-middle">
                                <input type="number" style="width: 50px" name="quantity" value="1">
                            </td>
                            <td class="align-middle">${detailProduct.price}00</td>


                            <input type="hidden" name="id" value="${detailProduct.product.id}">


                            <td class="align-middle">
                                <select name="size" class="form-select">
                                    <c:forEach items="${listSize}" var="size">
                                        <option value="${size.id}">${size.name}</option>
                                    </c:forEach>
                                </select>
                            </td>

                            <td class="align-middle">
                                <select name="color" class="form-select">
                                    <c:forEach items="${listColor}" var="color">
                                        <option value="${color.id}">${color.name}</option>
                                    </c:forEach>
                                </select>
                            </td>

                            <c:forEach items="${ listInvoice }" var="invoice" varStatus="i">
                                <input type="hidden" name="idInvoice" value="${invoice.id}">
                            </c:forEach>

                            <td class="align-middle">
                                <button class="btn btn-info">
                                    add
                                </button>
                            </td>

                        </tr>
                    </form>
                </c:forEach>

                </tbody>
            </table>
        </div>
    </div>
</div>

<%-- End product--%>


<script src="${pageContext.request.contextPath}/assets/jsSell/into-money.js"></script>
</body>
</html>