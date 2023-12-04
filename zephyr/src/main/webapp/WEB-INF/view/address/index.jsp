<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>

</head>
<body>


<div style="margin-bottom: 40px">
    <span><a href="/zephyr/admin/client/index?id=1">address</a></span>
    <span style="color: #C0C0C0"> / index</span>

</div>
<div class="table-responsive">
    <table class="table table-bordered">
        <thead>
        <tr>
            <th> #</th>
            <th> code</th>
            <th> name client</th>
            <th> phoneNumber</th>
            <th> address</th>
            <th> commune</th>
            <th> district</th>
            <th> city</th>
            <th> dateCreate</th>
            <th> status</th>
            <th> acction</th>

        </tr>
        </thead>

        <tbody>
        <c:forEach items="${ listAddress }" var="address" varStatus="i">
            <tr>
                <td>${i.index+1}</td>
                <td>${address.code}</td>
                <td>${address.name}</td>
                <td>${address.phoneNumber}</td>
                <td>${address.clientAddress}</td>
                <td>${address.commune}</td>
                <td>${address.district}</td>
                <td>${address.city}</td>
                <td>${address.dateCreate}</td>
                <td>${address.status == 1 ? "mặc định" : ""}</td>
                <td>
                    <a class="btn btn-default" href="/zephyr/admin/address/view-update?id=${address.id}">update</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>


</body>
</html>