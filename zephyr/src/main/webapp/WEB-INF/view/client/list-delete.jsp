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
            <div style="margin-bottom: 40px">
                <span><a href="/zephyr/admin/client/index?id=1">Client</a></span>
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
                        <th> pointUsr </th>
                        <th> accumulatedScore </th>
                        <th> password </th>
                        <th> dateCreate </th>
                        <th> dateUpdate </th>
                        <th> userCreate </th>
                        <th> userUpdate </th>
                        <th> status </th>
                        <th> rank </th>
                        <th> acction </th>

                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${ listClient.content }" var="client" varStatus="i">
                        <tr>
                            <td>${i.index+1}</td>
                            <td>${client.code}</td>
                            <td>${client.name}</td>
                            <td>${client.dateOfBirth}</td>
                            <td>${client.phoneNumber}</td>
                            <td>${client.gmail}</td>
                            <td>${client.gender == true ? "nam" : "nữ"}</td>
                            <td>${client.pointUsr}</td>
                            <td>${client.accumulatedScore}</td>
                            <td>${client.password}</td>
                            <td>${client.dateCreate}</td>
                            <td>${client.dateUpdate}</td>
                            <td>${client.userCreate}</td>
                            <td>${client.userUpdate}</td>
                            <td>${client.status == 1 ? "đang hoạt động" : "ngừng hoạt động"}</td>
                            <td>${client.clickRank()}</td>
                            <td>
                                <a class="btn btn-default" href="/zephyr/admin/client/list-delete?id=${client.id}" >show</a>
                                <a class="btn btn-default" href="/zephyr/admin/client/view-update?id=${client.id}"  >update</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <nav aria-label="Page navigation example" style="margin-left: 950px; margin-top: 20px">
                    <ul class="pagination" >
                        <c:forEach begin="0" end="${ listClient.totalPages -1}" varStatus="loop" >
                            <li class="page-item"  >
                                <a class="page-link" href="/zephyr/admin/client/index-page?page=${loop.begin + loop.count - 1}" >
                                        ${loop.begin + loop.count }
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                </nav>
            </div>
            <div class="content-wrapper">
                <%@include file="../address/index.jsp" %>
            </div>
        </div>
    </div>
</div>

<script>
    function update() {
        if (!confirm('Bạn có muốn xoá?')) {
            return false
        } else {
            alert('xoá thành công');
        }
    }
</script>

</body>
</html>