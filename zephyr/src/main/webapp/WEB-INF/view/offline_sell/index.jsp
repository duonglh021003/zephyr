<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div style="margin-bottom: 30px">
    <span><a href="/zephyr/admin/sell/index">sell</a></span>
    <span style="color: #C0C0C0"> / index</span>
</div>

<div class="col-lg-12 grid-margin stretch-card">
    <div class="card">
        <div class="card-body">
            <a type="button" class="btn btn-success" href="/zephyr/admin/sell/add" style="margin-bottom: 20px">create invoice</a>
            <span style="width: 500px; margin-left: 28.5%" class="alert alert-danger">${ errorMessage }</span> <br>
            <c:forEach items="${ listInvoiceStatus0 }" var="invoiceStatus0" varStatus="i">
                <a type="button" class="btn btn-light" href="/zephyr/admin/sell/invoice?id=${invoiceStatus0.id}">
                    <tr>
                        <td class="align-middle">${invoiceStatus0.code} </td>
                        <br>
                    </tr>

                </a>
            </c:forEach>
        </div>
    </div>
</div>

<%--<div class="col-lg-12 grid-margin stretch-card">--%>
<%--    <div class="card">--%>
<%--        <div class="card-body">--%>
<%--            <a type="button" class="btn btn-success" data-toggle="modal" data-target="#myModalAddProduct" style="margin-bottom: 20px">add product</a>--%>
<%--            <table class="table table-light table-borderless table-hover text-center mb-0">--%>
<%--                <thead class="thead-dark">--%>
<%--                <tr>--%>
<%--                    <th>#</th>--%>
<%--                    <th>Products</th>--%>
<%--                    <th>Price</th>--%>
<%--                    <th>Quantity</th>--%>
<%--                    <th>Sub total</th>--%>
<%--                    <th>Remove</th>--%>
<%--                </tr>--%>
<%--                </thead>--%>
<%--                <tbody class="align-middle">--%>
<%--                <c:forEach items="${ listDetailShoppingCart }" var="detailShopping" varStatus="i">--%>
<%--                    <tr>--%>
<%--                        <td>${i.index + 1}</td>--%>
<%--                        <td class="align-middle"><img src="/assets/images/client/${detailShopping.productDetails.images}" alt="" style="width: 50px">--%>
<%--                                ${detailShopping.productDetails.product.name}(${detailShopping.productDetails.size.name}, ${detailShopping.productDetails.color.name})</td>--%>
<%--                        <td class="align-middle">${detailShopping.unitPrice}00</td>--%>
<%--                        <td class="align-middle">--%>
<%--                            <div class="input-group quantity mx-auto" style="width: 100px;">--%>
<%--                                <div class="input-group-btn">--%>
<%--                                    <form action="/zephyr/shopping-cart/less?id=${detailShopping.id}" method="post">--%>
<%--                                        <button class="btn btn-sm btn-primary btn-minus" >--%>
<%--                                            <i class="fa fa-minus"></i>--%>
<%--                                        </button>--%>
<%--                                    </form>--%>
<%--                                </div>--%>

<%--                                <input  type="text" name="quantity" class="form-control form-control-sm bg-secondary border-0 text-center"--%>
<%--                                        value="${detailShopping.quantity}">--%>
<%--                                <div class="input-group-btn">--%>
<%--                                    <form action="/zephyr/shopping-cart/plus?id=${detailShopping.id}" method="post">--%>
<%--                                        <button class="btn btn-sm btn-primary btn-plus">--%>
<%--                                            <i class="fa fa-plus"></i>--%>
<%--                                        </button>--%>
<%--                                    </form>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </td>--%>
<%--                        <td class="align-middle">${detailShopping.subTotal()}00</td>--%>
<%--                        <td class="align-middle">--%>

<%--                            <a href="/zephyr/shopping-cart/delete?id=${detailShopping.id}"--%>
<%--                               onclick="if(!confirm('Bạn có xoá sản phẩm khỏi giỏ hàng?')){return false}else{alert('xoá thành công');}">--%>
<%--                                <button class="btn btn-sm btn-danger"><i class="fa fa-times"></i></button>--%>
<%--                            </a>--%>
<%--                        </td>--%>
<%--                    </tr>--%>
<%--                </c:forEach>--%>
<%--                </tbody>--%>
<%--            </table>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>


<%--&lt;%&ndash; Model prodct &ndash;%&gt;--%>

<%--<div class="modal fade" id="myModalAddProduct" role="dialog">--%>
<%--    <div class="modal-dialog">--%>
<%--        <div class="modal-content">--%>
<%--            <table class="table table-light table-borderless table-hover text-center mb-0">--%>
<%--                <thead class="thead-dark">--%>
<%--                <tr>--%>
<%--                    <th>#</th>--%>
<%--                    <th>Products</th>--%>
<%--                    <th>inventory</th>--%>
<%--                    <th>price</th>--%>
<%--                    <th>acction</th>--%>
<%--                </tr>--%>
<%--                </thead>--%>
<%--                <tbody class="align-middle">--%>
<%--                <c:forEach items="${ listDetailProduct }" var="detailProduct" varStatus="i">--%>
<%--                    <tr>--%>
<%--                        <td>${i.index + 1}</td>--%>
<%--                        <td class="align-middle"><img--%>
<%--                                src="/assets/images/client/${detailProduct.images}" alt="">--%>
<%--                                ${detailProduct.product.name}--%>
<%--                        </td>--%>
<%--                        <td class="align-middle">${detailProduct.inventory}</td>--%>
<%--                        <td class="align-middle">${detailProduct.price}00</td>--%>
<%--                        <td class="align-middle">--%>
<%--                            <a href="/zephyr/admin/detail-delivery-notes/detail?id=${invoiceStaff.id}">--%>
<%--                                <button class="btn btn-info">detail</button>--%>
<%--                            </a>--%>
<%--                        </td>--%>
<%--                    </tr>--%>
<%--                </c:forEach>--%>
<%--                </tbody>--%>
<%--            </table>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>

<%--&lt;%&ndash; End product&ndash;%&gt;--%>

</body>
</html>