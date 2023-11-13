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
                <span class="breadcrumb-item active">Voucher</span>
                <p style="color: red; margin-left: 350px">${errorMessage}</p>
            </nav>

        </div>
    </div>
</div>

<div class="container-fluid">
    <div class="row px-xl-5">
        <div class="col-lg-12 table-responsive mb-5">
            <h5 class="section-title position-relative text-uppercase mb-3"><span class="bg-secondary pr-3">Voucher</span>
            </h5>
            <div class="bg-light p-30 mb-5">
                <div class="row">
                    <table class="table table-light table-borderless table-hover text-center mb-0">
                        <thead class="thead-dark">
                        <tr>
                            <th>#</th>
                            <th>code</th>
                            <th>quantity</th>
                            <th>dateBegin</th>
                            <th>dateEnd</th>
                            <th>reducedPrice</th>
                            <th>dateCreate</th>
                            <th>inRank</th>
                            <th>status</th>
                            <th>acction</th>

                        </tr>
                        </thead>
                        <tbody class="align-middle">


                        <c:forEach items="${ listVoucherClient }" var="voucherClient" varStatus="i">
                            <tr>
                                <td>${i.index + 1}</td>
                                <td class="align-middle">${voucherClient.code}</td>
                                <td class="align-middle">${voucherClient.quantity}</td>
                                <td class="align-middle">${voucherClient.dateBegin}</td>
                                <td class="align-middle">${voucherClient.dateEnd}</td>
                                <td class="align-middle">${voucherClient.reducedPrice}</td>
                                <td class="align-middle">${voucherClient.dateCreate}</td>
                                <td class="align-middle">${voucherClient.inRank}</td>
                                <td class="align-middle">${voucherClient.status == 1 ? "đang hoạt động" : "ngừng hoạt động"}</td>
                                <td class="align-middle">
                                    <a  href="/zephyr/voucher/add?id=${voucherClient.id}" methods="get"
                                        onclick="if(!confirm('Bạn có muốn thêm phiếu giảm giá?')){return false}else{alert('thêm thành công');}">
                                        <img class="img-xs rounded-circle" style="width: 20px;height: 20px;"
                                             src="${pageContext.request.contextPath}/assets/images/client/voucher.png" alt="">
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