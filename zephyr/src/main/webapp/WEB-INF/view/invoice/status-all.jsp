<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div style="margin-bottom: 30px">
    <span><a href="/zephyr/admin/invoice/status-all">invoice</a></span>
    <span style="color: #C0C0C0"> / all</span>
    <span style="color: red; margin-left: 300px">${error}</span>
</div>

<div class="col-lg-12 grid-margin stretch-card">
    <div class="card">
        <div class="card-body">
            <a type="button" class="btn btn-primary" data-toggle="modal"
               data-target="#myModalSearch" style="margin-bottom: 20px; margin-left: 10px">
                search
            </a>

            <a type="button" class="btn btn-primary" data-toggle="modal"
               data-target="#myModalStatus" style="margin-bottom: 20px; margin-left: 10px">
                lọc status
            </a>
            <a type="button" class="btn btn-primary" data-toggle="modal"
               data-target="#myModalDate" style="margin-bottom: 20px; margin-left: 10px">
                lọc date
            </a>
            <div class="table-responsive">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>code</th>
                        <th>hourMinute</th>
                        <th>dateCreate</th>
                        <th>intoMoney</th>
                        <th>status</th>
                        <th>acction</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${ listInvoiceStatusAll }" var="invoiceStatusAll" varStatus="i">
                        <tr>
                            <td>${i.index + 1}</td>
                            <td class="align-middle">${invoiceStatusAll.code}</td>
                            <td class="align-middle">${invoiceStatusAll.hourMinute}</td>
                            <td class="align-middle">${invoiceStatusAll.dateCreate}</td>
                            <td class="align-middle">${invoiceStatusAll.intoMoney}00 đ</td>
                            <td class="align-middle">${invoiceStatusAll.getByStatus()}</td>
                            <td class="align-middle">
                                <a href="/zephyr/admin/invoice-detail/detail?id=${invoiceStatusAll.id}">
                                    <button class="btn btn-info">detail</button>
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

<%-- Model search--%>

<div class="modal fade" id="myModalSearch" role="dialog">
    <div class="modal-dialog ">
        <div class="modal-content">

            <div class="modal-header">
                <h4 class="modal-title">my let input</h4>
            </div>
            <form action="/zephyr/admin/invoice/search" method="get">
                <div class="modal-body">
                    <div class="row" >
                        <div class="col-md-12">
                            <div class="mb-3">
                                <input class="form-control" name="inputInvoice" placeholder="input invoice">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer" style="margin-top: 70px">
                    <button type="button" style="border: 1px solid #eeeeee" class="btn btn-default" data-dismiss="modal" data-toggle="modal" data-target="#myModal">Close</button>
                    <button  class="btn btn-primary" style="color: white" >search</button>
                </div>
            </form>

        </div>
    </div>
</div>

<%-- End search--%>

<%-- Model status--%>

<div class="modal fade" id="myModalStatus" role="dialog">
    <div class="modal-dialog ">
        <div class="modal-content">

            <div class="modal-header">
                <h4 class="modal-title">my let input</h4>
            </div>
            <form action="/zephyr/admin/invoice/search-status" method="get">
                <div class="modal-body">
                    <div class="row" >
                        <div class="col-md-12">
                            <label class="form-label">status</label> <br>
                            <select name="status" class="form-control">
                                <option value="0" >chưa thanh toán</option>
                                <option value="1" >đặt hàng</option>
                                <option value="2" >chờ xác nhận</option>
                                <option value="3" >chờ lấy hàng</option>
                                <option value="4" >đang giao</option>
                                <option value="5" >đã nhận hàng</option>
                                <option value="6" >đã hoàn thành</option>
                                <option value="7" >đã huỷ</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="modal-footer" style="margin-top: 70px">
                    <button type="button" style="border: 1px solid #eeeeee" class="btn btn-default" data-dismiss="modal" data-toggle="modal" data-target="#myModal">Close</button>
                    <button  class="btn btn-primary" style="color: white" >search</button>
                </div>
            </form>

        </div>
    </div>
</div>

<%-- End status--%>

<%-- Model date--%>

<div class="modal fade" id="myModalDate" role="dialog">
    <div class="modal-dialog ">
        <div class="modal-content">

            <div class="modal-header">
                <h4 class="modal-title">my let input</h4>
            </div>
            <form action="/zephyr/admin/invoice/search-date" method="get">
                <div class="modal-body">
                    <div class="row" >
                        <div class="col-md-6">
                            <label class="form-label">ngày bắt đầu</label> <br>
                            <div class="mb-3">
                                <input type="date" class="form-control" name="dateBegin" >
                            </div>
                        </div>
                        <div class="col-md-6">
                            <label class="form-label">ngày kết thúc</label> <br>
                            <div class="mb-3">
                                <input type="date" class="form-control" name="dateEnd" >
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer" style="margin-top: 70px">
                    <button type="button" style="border: 1px solid #eeeeee" class="btn btn-default" data-dismiss="modal" data-toggle="modal" data-target="#myModal">Close</button>
                    <button  class="btn btn-primary" style="color: white" >search</button>
                </div>
            </form>

        </div>
    </div>
</div>

<%-- End date--%>
</body>
</html>