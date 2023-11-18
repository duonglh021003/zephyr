<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div style="margin-bottom: 30px">
    <span><a href="/zephyr/admin/statistical/index">statistical</a></span>
    <span style="color: #C0C0C0"> / index</span>
</div>
<div class="row">
    <div class="col-xl-3 col-sm-6 grid-margin stretch-card">
        <div class="card">
            <div class="card-body">
                <div class="row">
                    <div class="col-9">
                        <div class="d-flex align-items-center align-self-start">
                            <h4>${productDay} product</h4>
                        </div>
                    </div>
                    <div class="col-3">
                        <div class="icon icon-box-success ">
                            <a href="/zephyr/admin/statistical/index">
                                <span class="mdi mdi-arrow-top-right icon-item"></span>
                            </a>
                        </div>
                    </div>
                </div>
                <h6 class="text-muted font-weight-normal" style="margin-top: 10px">Products sold during the day</h6>
            </div>
        </div>
    </div>
    <div class="col-xl-3 col-sm-6 grid-margin stretch-card">
        <div class="card">
            <div class="card-body">
                <div class="row">
                    <div class="col-9">
                        <div class="d-flex align-items-center align-self-start">
                            <h4 class="mb-0">${IntoMoneyDayPresent}00 đ</h4>
                        </div>
                    </div>
                    <div class="col-3">
                        <div class="icon icon-box-success">
                            <span class="mdi mdi-arrow-top-right icon-item"></span>
                        </div>
                    </div>
                </div>
                <h6 class="text-muted font-weight-normal" style="margin-top: 10px">Revenue in day</h6>
            </div>
        </div>
    </div>
    <div class="col-xl-3 col-sm-6 grid-margin stretch-card">
        <div class="card">
            <div class="card-body">
                <div class="row">
                    <div class="col-9">
                        <div class="d-flex align-items-center align-self-start">
                            <h4 class="mb-0">${IntoMoneyMonthPresent}00 đ</h4>
                        </div>
                        <p class="mb-0" style="color: #a4a4a4">${quantityMonthPresent} product</p>
                    </div>
                    <div class="col-3">
                        <div class="icon icon-box-success ">
                            <a href="/zephyr/admin/statistical/revenue-in-month">
                                <span class="mdi mdi-arrow-top-right icon-item"></span>
                            </a>

                        </div>
                    </div>
                </div>

                <div class="row" style="margin-top: 10px">
                    <div class="col-9">
                        <h6 class="text-muted font-weight-normal">Revenue in month</h6>
                    </div>
                </div>

            </div>
        </div>
    </div>
    <div class="col-xl-3 col-sm-6 grid-margin stretch-card">
        <div class="card">
            <div class="card-body">
                <div class="row">
                    <div class="col-9">
                        <div class="d-flex align-items-center align-self-start">
                            <h4 class="mb-0">${IntoMoneyYearPresent}00 đ</h4>
                        </div>
                        <p class="mb-0" style="color: #a4a4a4">${quantityYearPresent} product</p>
                    </div>
                    <div class="col-3">
                        <div class="icon icon-box-success ">
                            <span class="mdi mdi-arrow-top-right icon-item"></span>
                        </div>
                    </div>
                </div>
                <div class="row" style="margin-top: 10px">
                    <div class="col-9">
                        <h6 class="text-muted font-weight-normal">Revenue in year</h6>
                    </div>
                    <div class="col-3">
                        <a type="button" class="btn btn-success" data-toggle="modal" data-target="#myModalYear">filter</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-md-6 grid-margin stretch-card">
        <div class="card">
            <div class="card-body">
                <h4 class="card-title">invoice</h4>
                <div class="table-responsive">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>code</th>
                            <th>dateCreate</th>
                            <th>intoMoney</th>
                            <th>acction</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${ listInvoice }" var="invoice" varStatus="i">
                            <tr>
                                <td>${i.index + 1}</td>
                                <td class="align-middle">${invoice.code}</td>
                                <td class="align-middle">${invoice.dateCreate}</td>
                                <td class="align-middle">${invoice.intoMoney}00</td>
                                <td class="align-middle">
                                    <a href="/zephyr/admin/statistical/revenue-in-year/detail?id=${invoice.id}">
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
    <div class="col-md-6 grid-margin stretch-card">
        <div class="card">
            <div class="card-body">
                <h4 class="card-title">invoice detail</h4>
                <div class="table-responsive">
                    <table class="table table-bordered">

                        <thead>
                        <tr>
                            <th>#</th>
                            <th>Products</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Sub total</th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach items="${ listInvoiceDetail }" var="detailInvoice" varStatus="i">
                            <tr>
                                <td>${i.index + 1}</td>
                                <td class="align-middle"><img
                                        src="/assets/images/client/${detailInvoice.productDetails.images}" alt="">
                                        ${detailShopping.productDetails.product.name}(${detailInvoice.productDetails.size.name}, ${detailInvoice.productDetails.color.name})
                                </td>
                                <td class="align-middle">${detailInvoice.unitPrice}00</td>
                                <td class="align-middle">${detailInvoice.quantity}</td>
                                <td class="align-middle">${detailInvoice.capitalSum}00</td>
                            </tr>
                        </c:forEach>

                        </tbody>

                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<%-- Model month --%>

<div class="modal fade" id="myModalYear" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">my let input</h4>
            </div>
            <form action="/zephyr/admin/statistical/revenue-in-year/search" method="get">
                <div class="modal-body">
                    <div class="row" >
                        <div class="col-md-12">
                            <div class="mb-3">
                                <input class="form-control" name="year" placeholder="year">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer" style="margin-top: 70px">
                    <button type="button" style="border: 1px solid #eeeeee" class="btn btn-default" data-dismiss="modal" data-toggle="modal" data-target="#myModal">Close</button>
                    <button  class="btn btn-default" style="background: red;color: white" >search</button>
                </div>
            </form>
        </div>
    </div>
</div>

<%-- End month--%>
</body>
</html>