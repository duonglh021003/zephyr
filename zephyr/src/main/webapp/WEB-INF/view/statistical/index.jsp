<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

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
                            <a href="/zephyr/admin/statistical/invoice/products-sold-during-the-day">
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
                            <a href="/zep">
                                <span class="mdi mdi-arrow-top-right icon-item"></span>
                            </a>

                        </div>
                    </div>
                </div>

                <div class="row" style="margin-top: 10px">
                    <div class="col-9">
                        <h6 class="text-muted font-weight-normal">Revenue in month</h6>
                    </div>
                    <div class="col-3">
                        <a type="button" class="btn btn-success">filter</a>
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
                        <a type="button" class="btn btn-success">filter</a>
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
                                <td class="align-middle">${invoice.intoMoney}</td>
                                <td class="align-middle">
                                    <a href="/zephyr/admin/invoice-detail/detail?id=${invoice.id}">
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


                        </tbody>

                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>