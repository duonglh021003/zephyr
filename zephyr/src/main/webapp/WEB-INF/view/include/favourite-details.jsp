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
                <span class="breadcrumb-item active">Favourite Detail</span>
            </nav>
        </div>
    </div>
</div>

<div class="container-fluid">
    <div class="row px-xl-5">
        <div class="col-lg-12 table-responsive mb-5">
            <h5 class="section-title position-relative text-uppercase mb-3"><span class="bg-secondary pr-3">favourite detail</span>
            </h5>
            <div class="bg-light p-30 mb-5">
                <div class="row">
                    <table class="table table-light table-borderless table-hover text-center mb-0">
                        <thead class="thead-dark">
                        <tr>
                            <th>#</th>
                            <th>product</th>
                            <th>price</th>
                            <th>inventory</th>
                            <th>date_create</th>
                            <th>status</th>
                            <th>acction</th>

                        </tr>
                        </thead>
                        <tbody class="align-middle">


                        <c:forEach items="${ listFavouriteDetail }" var="favouriteDetail" varStatus="i">
                            <tr>
                                <td>${i.index + 1}</td>
                                <td class="align-middle"><img
                                        src="/assets/images/client/${favouriteDetail.productDetails.images}" alt=""
                                        style="width: 50px;">
                                        ${favouriteDetail.productDetails.product.name}</td>

                                <td class="align-middle">${favouriteDetail.productDetails.price}</td>
                                <td class="align-middle">${favouriteDetail.productDetails.inventory}</td>
                                <td class="align-middle">${favouriteDetail.dateCreate}</td>
                                <td class="align-middle">${favouriteDetail.productDetails.status == 1 ? "đang hoạt động" : "ngừng hoạt động"}</td>
                                <td class="align-middle">
                                    <a  href="/zephyr/favourite-detail/index?id=${favouriteDetail.id}" methods="get"
                                       onclick="if(!confirm('Bạn có muốn thêm sản phẩm vào giỏ hàng?')){return false}else{alert('thêm thành công');}">
                                        <i class="fa fa-shopping-cart" style="margin-right: 15px;"></i></a>

                                    <a href="/zephyr/favourite-detail/delete?id=${favouriteDetail.id}"
                                       onclick="if(!confirm('Bạn có xoá sản phẩm khỏi danh sách yêu thích?')){return false}else{alert('xoá thành công');}">
                                        <button class="btn btn-sm btn-danger"><i class="fa fa-times"></i></button>
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
</div>
</body>
</html>
