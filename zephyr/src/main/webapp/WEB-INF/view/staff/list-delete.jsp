<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>

</head>
<body>

<div class="col-lg-12 grid-margin stretch-card">
    <div class="card">
        <div class="card-body">
            <div style="margin-bottom: 30px">
                <span><a href="/zephyr/admin/staff/index">Staff</a></span>
                <span style="color: #C0C0C0"> / delete</span>
            </div>
            <div class="table-responsive">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th> # </th>
                        <th> code </th>
                        <th> name </th>
                        <th> dateOfBirth </th>
                        <th> phoneNumber </th>
                        <th> gmail </th>
                        <th> gender </th>
                        <th> address </th>
                        <th> commune </th>
                        <th> district </th>
                        <th> city </th>
                        <th> password </th>
                        <th> status </th>
                        <th> position </th>
                        <th> acction </th>

                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${ listStaff.content }" var="staff" varStatus="i">
                        <tr>
                            <td>${i.index+1}</td>
                            <td>${staff.code}</td>
                            <td>${staff.name}</td>
                            <td>${staff.dateOfBirth}</td>
                            <td>${staff.phoneNumber}</td>
                            <td>${staff.gmail}</td>
                            <td>${staff.gender  == true ? "nam" : "nữ"}</td>
                            <td>${staff.address}</td>
                            <td>${staff.commune}</td>
                            <td>${staff.district}</td>
                            <td>${staff.city}</td>
                            <td>${staff.password}</td>
                            <td>${staff.status == 1 ? "đang hoạt động" : "ngừng hoạt động"}</td>
                            <td>${staff.position.name}</td>
                            <td>
                                <a class="btn btn-default" href="/zephyr/admin/staff/restore?id=${staff.id}"
                                   onclick="if(!confirm('Bạn có muốn khôi phục?')){return false}else{alert('khôi phục thành công');}">khôi phục</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <nav aria-label="Page navigation example" style="margin-left: 500px; margin-top: 20px">
                    <ul class="pagination" >
                        <c:forEach begin="0" end="${ listStaff.totalPages -1}" varStatus="loop" >
                            <li class="page-item"  >
                                <a class="page-link" href="/zephyr/admin/staff/list-delete?page=${loop.begin + loop.count - 1}" >
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