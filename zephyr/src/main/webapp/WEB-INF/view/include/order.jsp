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
            <label style="color: red"><strong>BILLING ADDRESS</strong></label>
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
<!-- Breadcrumb End -->

<%--model 1--%>
<div class="modal fade" id="myModal" role="dialog">

    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">my address</h4>
            </div>

            <form action="/zephyr/shop/order-address/update?id=${addressId}" method="post">
                <div class="modal-body">
                    <c:forEach items="${listAddresOrder}" var="address">
                        <div style="margin-left: 30px">
                            <input type="radio" name="address" ${address.status == "1" ? "checked" : ""} value="${address.id}">
                            <label><p style="color: black">${address.name} |</p></label>
                            <td>${address.phoneNumber}</td>
                            <td>
                                <a onclick="handleClick('${address.id}')" class="btn btn-default" style="color: blue; margin-left: 100px" href="/zephyr/shop/path/to/java/endpoint?id=${address.id}" data-dismiss="modal" data-toggle="modal" data-target="#myModalUpdate">update</a>
                            </td><br>
                            <p style="margin-top: -20px">
                            <td>${address.clientAddress}</td> <br>
                            <td>${address.commune},  </td>
                            <td>${address.district},  </td>
                            <td>${address.city} </td> <br>
                            <td><span style="color: red; border: 1px solid red; ">
                                    ${address.status == 1 ? "mặc định" : "💒"}
                            </span></td>
                            </p>
                            <hr>
                        </div>
                    </c:forEach>
                    <button  style="border: 1px solid #eeeeee" class="btn btn-default" type="button" data-dismiss="modal" data-toggle="modal" data-target="#myModalAdd">
                        <a style="font-size: 20px">+</a> add address</button>
                </div>
                <div class="modal-footer" style="margin-top: 70px">
                    <button type="button" style="border: 1px solid #eeeeee" class="btn btn-default" data-dismiss="modal" >Close</button>
                    <a class="btn btn-default" style="background: red;color: white" href="/zephyr/shop/order-address/update?id=${address.id}" >confirm</a>
                </div>
            </form>
        </div>
    </div>
</div>

<%--model 2 add address--%>

<div class="modal fade" id="myModalAdd" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">add address</h4>
            </div>
            <form action="/zephyr/shop/order-address/add" method="post">
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

                    <input type="radio" name="status" value="1" checked ${address.status == "1" ? "checked" : ""} /> đặt làm địa chỉ mặc định<br>
                    <input type="radio" name="status" value="0" ${address.status == "0" ? "checked" : ""} /> bỏ chọn<br>
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

                </div>
            </div>

            <div class="collapse mb-5" id="shipping-address">

                <h5 class="section-title position-relative text-uppercase mb-3"><span class="bg-secondary pr-3">Shipping Address</span></h5>
                <div class="bg-light p-30">

                    <div class="row">

                    </div>

                </div>
            </div>
        </div>
        <div class="col-lg-4">
            <form class="mb-30" action="">
                <div class="input-group">
                    <input type="text" class="form-control border-0 p-4" placeholder="Coupon Code">
                    <div class="input-group-append">
                        <button class="btn btn-primary">Apply Coupon</button>
                    </div>
                </div>
            </form>
            <h5 class="section-title position-relative text-uppercase mb-3"><span class="bg-secondary pr-3">Order Total</span></h5>
            <div class="bg-light p-30 mb-5">

                <div class="border-bottom">
                    <h6 class="mb-3">Products</h6>
                    <div class="d-flex justify-content-between">
                        <p>Product Name 1</p>
                        <p>$150</p>
                    </div>
                    <div class="d-flex justify-content-between">
                        <p>Product Name 2</p>
                        <p>$150</p>
                    </div>
                    <div class="d-flex justify-content-between">
                        <p>Product Name 3</p>
                        <p>$150</p>
                    </div>
                </div>
                <div class="border-bottom pt-3 pb-2">
                    <div class="d-flex justify-content-between mb-3">
                        <h6>Subtotal</h6>
                        <h6>$150</h6>
                    </div>
                    <div class="d-flex justify-content-between">
                        <h6 class="font-weight-medium">Shipping</h6>
                        <h6 class="font-weight-medium">$10</h6>
                    </div>
                </div>
                <div class="pt-2">
                    <div class="d-flex justify-content-between mt-2">
                        <h5>Total</h5>
                        <h5>$160</h5>
                    </div>
                </div>
            </div>
            <div class="mb-5">
                <h5 class="section-title position-relative text-uppercase mb-3"><span class="bg-secondary pr-3">Payment</span></h5>
                <div class="bg-light p-30">
                    <div class="form-group">
                        <div class="custom-control custom-radio">
                            <input type="radio" class="custom-control-input" name="payment" id="paypal">
                            <label class="custom-control-label" for="paypal">Paypal</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="custom-control custom-radio">
                            <input type="radio" class="custom-control-input" name="payment" id="directcheck">
                            <label class="custom-control-label" for="directcheck">Direct Check</label>
                        </div>
                    </div>
                    <div class="form-group mb-4">
                        <div class="custom-control custom-radio">
                            <input type="radio" class="custom-control-input" name="payment" id="banktransfer">
                            <label class="custom-control-label" for="banktransfer">Bank Transfer</label>
                        </div>
                    </div>
                    <button class="btn btn-block btn-primary font-weight-bold py-3">Place Order</button>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>