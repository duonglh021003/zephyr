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
                    <c:forEach items="${ listVoucherClient.content }" var="voucherClient" varStatus="i">
                        <tr>
                            <td>${i.index+1}</td>
                            <td class="align-middle">${voucherClient.code}</td>
                            <td class="align-middle">${voucherClient.quantity}</td>
                            <td class="align-middle">${voucherClient.dateBegin}</td>
                            <td class="align-middle">${voucherClient.dateEnd}</td>
                            <td class="align-middle">${voucherClient.reducedPrice}00</td>
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
                                    onclick="if(!confirm('Bạn có muốn xoá?')){return false}else{alert('xoá thành công');}">delete</a>
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

<%-- Model prodct --%>

<div class="modal fade" id="myModalAddVoucherClient" role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <form:form action="/zephyr/admin/voucher-client/add" method="POST" modelAttribute="voucherClient">
                <div class="row">
                    <div class="col-md-3">
                        <div class="mb-3">
                            <label class="form-label">giá giảm</label>
                            <form:input path="reducedPrice" class="form-control"/>
                            <form:errors path="reducedPrice" cssClass="errors"/><br>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="mb-3">
                            <label class="form-label">số lượng</label>
                            <form:input path="quantity" class="form-control"/>
                            <form:errors path="quantity" cssClass="errors"/><br>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="mb-3">
                            <label class="form-label">ngày bắt đầu</label> <br>
                            <input type="date" name="dateBegin" class="form-control"/>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="mb-3">
                            <label class="form-label">ngày kết thúc</label> <br>
                            <input type="date" name="dateEnd" class="form-control"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-3">
                        <div class="mb-3">
                            <label class="form-label">inRank</label> <br>
                            <select name="inRank" class="form-select">
                                <option value="1">Đồng hành</option>
                                <option value="2">Thân thiết</option>
                                <option value="3">Tri kỷ</option>
                                <option value="4">Vip</option>
                            </select>
                        </div>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">trạng thái</label> <br>
                        <input type="radio" name="status" value="1" checked=""/>đang
                        hoạt động
                    </div>
                    <div class="col-md-3">
                    </div>
                    <div class="col-md-3">
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-3">
                        <div class="mb-3">
                            <label class="form-label">ngày tạo</label>
                            <input type="text" name="dateCreate" class="form-control" value="${dateUpdate}"/>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="mb-3">
                            <label class="form-label">ngày sửa</label>
                            <input type="text" name="dateUpdate" class="form-control" value="${dateUpdate}"/>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="mb-3">
                            <label class="form-label">người tạo</label>
                            <input type="text" name="userCreate" class="form-control" value="${staffSession.name}"/>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="mb-3">
                            <label class="form-label">người sửa</label>
                            <input type="text" name="userUpdate" class="form-control" value="${staffSession.name}"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-3"></div>
                    <div class="col-md-6">
                        <div class="mb-3">
                            <form:button type="submit" class="btn btn-primary" onclick="add()">Add</form:button>
                        </div>
                    </div>
                    <div class="col-md-3"></div>
                </div>
            </form:form>

        </div>
    </div>
</div>

<%-- End product--%>

<%-- Model restore --%>

<div class="modal fade" id="myModalRestore" role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
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
                    <th> inRank </th>
                    <th> status </th>
                    <th> restore </th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${ listVoucherClientStatus0 }" var="voucherClientStatus0" varStatus="i">
                <form action="/zephyr/admin/voucher-client/restore" method="get">
                    <tr>
                        <td>${i.index+1}</td>
                        <td class="align-middle">${voucherClientStatus0.code}</td>
                        <td class="align-middle">
                            <input type="hidden" name="id" value="${voucherClientStatus0.id}">
                            <input type="number" style="width: 70px" name="quantity" value="${voucherClientStatus0.quantity}" />
                        </td>
                        <td class="align-middle">
                            <input type="date"  name="dateBegin" value="${voucherClientStatus0.dateBegin}" />
                        </td>

                        <td class="align-middle">
                            <input type="date"  name="dateEnd" value="${voucherClientStatus0.dateEnd}" />
                        </td>
                        <td class="align-middle">${voucherClientStatus0.reducedPrice}</td>
                        <td class="align-middle">${voucherClientStatus0.dateCreate}</td>
                        <td class="align-middle">${voucherClientStatus0.dateUpdate}</td>

                        <td class="align-middle">${voucherClientStatus0.getRankVoucher()}</td>
                        <td class="align-middle">${voucherClientStatus0.status == 1 ? "đang hoạt động" : "ngừng hoạt động"}</td>
                        <td class="align-middle">
                            <button  class="btn btn-warning"
                                     onclick="if(!confirm('Bạn có muốn khôi phục?')){return false}else{alert('khôi phục thành công');}">
                                restore
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

<%-- End restore--%>
</body>
</html>