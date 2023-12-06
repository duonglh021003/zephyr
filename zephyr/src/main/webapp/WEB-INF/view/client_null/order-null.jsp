<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container-fluid" >
    <div class="row px-xl-5">
        <div class="col-12">
            <a  type="button" class="btn btn-success" data-toggle="modal"
                data-target="#myModalAddress" style="margin-bottom: 20px; margin-left: 10px">
                thêm địa chỉ
            </a>

            <nav class="breadcrumb bg-light mb-30">

                <c:forEach items="${ listAddress }" var="address" varStatus="i">
                    <form>
                        <td><strong style="color: black; margin-right: 10px">${address.name} </strong>  </td>
                        <td><strong style="color: black; margin-right: 20px">${address.phoneNumber}  </strong></td>
                        <td>${address.clientAddress},  </td>
                        <td>${address.commune},  </td>
                        <td>${address.district},  </td>
                        <td>${address.city} </td>
                        <td><span style="color: red; border: 1px solid red;font-size: 11px; margin-left: 15px">
                                ${address.status == 1 ? "mặc định" : ""}
                        </span></td>
                        <button class="btn btn-default" type="button" data-toggle="modal" data-target="#myModal" style="color: blue; margin-left: 50px" href="/zephyr/admin/client/index?id=${client.id}" >thay đổi</button>
                    </form>
                </c:forEach>
            </nav>
        </div>
    </div>
</div>

<div class="modal fade" id="myModalAddress" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">add address</h4>
            </div>
            <form action="/zephyr/order-null/address-null/add" method="post">
                <div class="modal-body">

                    <div class="row">
                        <div class="col-md-6">
                            <div class="mb-3">
                                <input class="form-control" name="name" placeholder="name">
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="mb-3">
                                <input class="form-control" name="phoneNumber" placeholder="phone">
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6">
                            <select class="form-control" name="city" id="city" aria-label=".form-select-sm" >
                                <option value="${address.city}" selected>${address.city}</option>
                            </select>
                        </div>
                        <div class="col-md-6">
                            <select class="form-control" name="district" id="district" aria-label=".form-select-sm">
                                <option value="${address.district}" selected>${address.district}</option>
                            </select>
                        </div>
                    </div>

                    <div class="row" style="margin-top: 15px">
                        <div class="col-md-6">
                            <div class="mb-3">
                                <select class="form-control" name="commune" id="ward" aria-label=".form-select-sm">
                                    <option value="${address.commune}" selected>${address.commune}</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="mb-3">
                                <input class="form-control" name="clientAddress" placeholder="địa chỉ cụ thể">
                            </div>
                        </div>
                    </div>

                </div>
                <div class="modal-footer" style="margin-top: 70px">
                    <button type="button" style="border: 1px solid #eeeeee" class="btn btn-default" data-dismiss="modal" data-toggle="modal" data-target="#myModal">Close</button>
                    <button  class="btn btn-default" style="background: red;color: white" >add</button>
                </div>
            </form>
        </div>
    </div>
</div>

<%--model 3 update address--%>


<!-- Checkout Start -->
<div class="container-fluid">
    <div class="row px-xl-5">
        <div class="col-lg-8">
            <h5 class="section-title position-relative text-uppercase mb-3"><span class="bg-secondary pr-3">Shopping Cart</span></h5>
            <div class="bg-light p-30 mb-5">
                <div class="row">

                    <table class="table table-light table-borderless table-hover text-center mb-0">
                        <thead class="thead-dark">
                        <tr>
                            <th>#</th>
                            <th>Products</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Sub total</th>
                            <th>Remove</th>
                        </tr>
                        </thead>
                        <tbody class="align-middle">
                        <tr>
                            <td>${i.index + 1}</td>
                            <td class="align-middle"><img src="/assets/images/client/${clientNullSession.productDetails.images}" alt="" style="width: 50px">
                                ${clientNullSession.productDetails.product.name}(${clientNullSession.productDetails.size.name}, ${clientNullSession.productDetails.color.name})</td>
                            <td class="align-middle">${clientNullSession.unitPrice}00 đ</td>
                            <td class="align-middle">
                                <div class="input-group quantity mx-auto" style="width: 100px;">
                                    <div class="input-group-btn">
                                        <form action="/zephyr/shopping-cart/less?id=${clientNullSession.id}" method="post">
                                            <button class="btn btn-sm btn-primary btn-minus" >
                                                <i class="fa fa-minus"></i>
                                            </button>
                                        </form>
                                    </div>

                                    <input  type="text" name="quantity" class="form-control form-control-sm bg-secondary border-0 text-center"
                                            value="${clientNullSession.quantity}">
                                    <div class="input-group-btn">
                                        <form action="/zephyr/shopping-cart/plus?id=${clientNullSession.id}" method="post">
                                            <button class="btn btn-sm btn-primary btn-plus">
                                                <i class="fa fa-plus"></i>
                                            </button>
                                        </form>
                                    </div>
                                </div>
                            </td>
                            <td class="align-middle">${clientNullSession.subTotal()}00 đ</td>
                            <td class="align-middle">

                                <a href="/zephyr/shopping-cart/delete?id=${clientNullSession.id}"
                                   onclick="if(!confirm('Bạn có xoá sản phẩm khỏi giỏ hàng?')){return false}else{alert('xoá thành công');}">
                                    <button class="btn btn-sm btn-danger"><i class="fa fa-times"></i></button>
                                </a>
                            </td>
                        </tr>
                        </tbody>
                    </table>

                </div>
            </div>

            <div class="collapse mb-5" id="shipping-address">
                <h5 class="section-title position-relative text-uppercase mb-3"><span class="bg-secondary pr-3">Shipping Address</span></h5>
                <div class="bg-light p-30">
                    <div class="row"></div>
                </div>
            </div>
        </div>

        <div class="col-lg-4">
            <form action="/zephyr/order-null/update" method="post">
                <h5 class="section-title position-relative text-uppercase mb-3"><span class="bg-secondary pr-3">Order Total</span></h5>
                <div class="bg-light p-30 mb-5">
                    <c:forEach items="${ listInvoice }" var="invoice" varStatus="i">
                        <div class="border-bottom pt-3 pb-2" style="margin-top: -20px;line-height: 10px">

                            <div class="d-flex justify-content-between mb-3">
                                <h6>mã hoá đơn: </h6>
                                <input style="border: none;outline: none;color: red" name="code" value="${invoice.code}">
                            </div>
                            <hr>
                            <div class="d-flex justify-content-between mb-3">
                                <p>giờ tạo: </p>
                                <input style="border: none;outline: none;" name="hourMinute" value="${invoice.hourMinute}">
                            </div>
                            <hr>
                            <div class="d-flex justify-content-between mb-3">
                                <p>ngày tạo: </p>
                                <input style="border: none;outline: none;" name="dateCreate" value="${invoice.dateCreate}">
                            </div>
                            <hr>
                            <div class="d-flex justify-content-between mb-3">
                                <p>tổng tiền hoá đơn: </p>
                                <input style="border: none;outline: none;" name="totalInvoice" value="${invoice.totalInvoice}00">
                            </div>
                            <hr>
                            <div class="d-flex justify-content-between mb-3">
                                <p>điểm sử dụng: </p>
                                <input style="border: none;outline: none;" name="point" value="${invoice.point}00">
                            </div>
                            <hr>
                            <div class="d-flex justify-content-between mb-3">
                                <p>tiền phiếu giảm giá: </p>
                                <input style="border: none;outline: none;"  value="${invoice.detailVoucherClient.reducedPrice}00">
                            </div>
                            <hr>
                            <div class="d-flex justify-content-between mb-3">
                                <p>tiền ship: </p>
                                <input style="border: none;outline: none;" name="shippingMoney" value="${invoice.shippingMoney}00">
                            </div>
                            <hr>
                            <div class="d-flex justify-content-between mb-3">
                                <h6>thành tiền: </h6>
                                <input style="border: none;outline: none;" name="intoMoney" value="${invoice.intoMoney}00">
                            </div>
                            <hr>
                            <div class="d-flex justify-content-between mb-3">
                                <p>ghi chú: </p>
                                <textarea style="height: 50px" name="note" >${invoice.note}</textarea>
                            </div>
                        </div>

                    </c:forEach>

                </div>

                <div class="mb-5">
                    <h5 class="section-title position-relative text-uppercase mb-3"><span class="bg-secondary pr-3">Payment</span></h5>
                    <div class="bg-light p-30">
                        <div class="form-group">
                            <div class="custom-control custom-radio">
                                <input type="radio" name="payment" checked value="1" /> Thanh toán khi nhận hàng
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="custom-control custom-radio">
                                <input type="radio" name="payment"  value="0" /> Chuyển khoản ngân hàng
                            </div>
                        </div>
                        <button class="btn btn-block btn-primary font-weight-bold py-3"
                                onclick="if(!confirm('Bạn có chắc chắn muốn đặt hàng?')){return false}else{alert('đặt hàng thành công');}">Place Order</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>