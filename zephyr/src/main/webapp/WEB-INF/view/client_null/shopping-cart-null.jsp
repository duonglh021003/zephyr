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
                <a class="breadcrumb-item text-dark" href="#">Home</a>
                <span class="breadcrumb-item active">Shopping Cart</span>
            </nav>
        </div>
    </div>
</div>
<!-- Breadcrumb End -->


<!-- Cart Start -->
<div class="container-fluid">
    <div class="row px-xl-5">
        <div class="col-lg-8 table-responsive mb-5">
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

        <div class="col-lg-4">
            <h5 class="section-title position-relative text-uppercase mb-3"><span
                    class="bg-secondary pr-3">Cart Summary</span></h5>
            <div class="bg-light p-30 mb-5">
                <div class="border-bottom pb-2">
                    <h6>Product</h6>
                    <div class="d-flex justify-content-between mb-3">
                        <table>
                            <tr>
                                <td><p>${clientNullSession.productDetails.product.name}</p></td>
                            </tr>
                        </table>

                        <table>
                            <tr>
                                <td><h6>${clientNullSession.subTotal()}00 đ</h6></td>
                            </tr>
                        </table>
                    </div>

                </div>

                <div class="pt-2">
                    <div class="d-flex justify-content-between mt-2">
                        <h5>Total</h5>
                        <h5>${clientNullSession.subTotal()}00 đ</h5>

                    </div>
                    <a class="btn btn-block btn-primary font-weight-bold my-3 py-3" href="/zephyr/order-null/add">order</a>
                </div>

            </div>
        </div>
    </div>
</div>




</body>
</html>