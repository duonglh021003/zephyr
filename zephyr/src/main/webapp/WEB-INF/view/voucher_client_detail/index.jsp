<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>

</head>
<body>
<div style="margin-bottom: 30px">
    <span><a href="/zephyr/admin/staff/index">voucher client</a></span>
    <span style="color: #C0C0C0"> / index</span>
</div>
<div class="col-lg-12 grid-margin stretch-card">
    <div class="card">
        <div class="card-body">
            <a type="button" class="btn btn-success" data-toggle="modal"
               data-target="#myModalAddVoucherClient"
               style="margin-bottom: 20px">add</a>
            <a  type="button" class="btn btn-warning" data-toggle="modal"
                data-target="#myModalRestore" style="margin-bottom: 20px; margin-left: 10px">
                restore
            </a>

            <div class="table-responsive">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th> # </th>
                        <th> code </th>
                        <th> quantity </th>
                        <th> dateBegin </th>
                        <th> dateEnd </th>
                        <th> reducedPrice </th>
                        <th> dateCreate </th>
                        <th> dateUpdate </th>
                        <th> userCreate </th>
                        <th> userUpdate </th>
                        <th> inRank </th>
                        <th> status </th>
                        <th> update </th>
                        <th> delete </th>

                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${ listDetail.content }" var="voucherClient" varStatus="i">
                        <tr>
                            <td>${i.index+1}</td>
                            <td class="align-middle">${voucherClient.code}</td>
                            <td class="align-middle">${voucherClient.quantity}</td>
                            <td class="align-middle">${voucherClient.dateBegin}</td>
                            <td class="align-middle">${voucherClient.dateEnd}</td>
                            <td class="align-middle">${voucherClient.reducedPrice}</td>
                            <td class="align-middle">${voucherClient.dateCreate}</td>
                            <td class="align-middle">${voucherClient.dateUpdate}</td>
                            <td class="align-middle">${voucherClient.userCreate}</td>
                            <td class="align-middle">${voucherClient.userUpdate}</td>
                            <td class="align-middle">${voucherClient.getRankVoucher()}</td>
                            <td class="align-middle">${voucherClient.status == 1 ? "đang hoạt động" : "ngừng hoạt động"}</td>
                            <td class="align-middle">
                                <a class="btn btn-info" href="/zephyr/admin/voucher-client/view-update?id=${voucherClient.id}">
                                    update
                                </a>
                            </td>
                            <td class="align-middle">
                                <a  class="btn btn-danger" href="/zephyr/admin/voucher-client/delete?id=${voucherClient.id}"
                                    onclick="if(!confirm('Bạn có muốn xoá?')){return false}else{alert('xoá thành công');}">cancel</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <nav aria-label="Page navigation example" style="margin-left: 50%; margin-top: 20px">
                    <ul class="pagination" >
                        <c:forEach begin="0" end="${ listVoucherClient.totalPages -1}" varStatus="loop" >
                            <li class="page-item"  >
                                <a class="page-link" href="/zephyr/admin/product-detail/index?page=${loop.begin + loop.count - 1}" >
                                        ${loop.begin + loop.count }
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</div>

</body>
</html>