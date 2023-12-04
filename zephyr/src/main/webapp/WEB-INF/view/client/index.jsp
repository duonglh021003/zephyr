<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>

</head>
<body>
<div style="margin-bottom: 20px">
    <span><a href="/zephyr/admin/client/index?id=1">client</a></span>
    <span style="color: #C0C0C0"> / index</span>

</div>
<div class="col-lg-12 grid-margin stretch-card">
    <div class="card">
        <div class="card-body">
            <a style="margin-bottom: 20px" type="button" class="btn btn-success"
               href="/zephyr/admin/client/view-add">
                add
            </a>
            <a type="button" class="btn btn-warning" data-toggle="modal"
               data-target="#myModalRestore" style="margin-bottom: 20px; margin-left: 10px">
                restore
            </a>
            <a type="button" class="btn btn-primary" data-toggle="modal"
               data-target="#myModalSearch" style="margin-bottom: 20px; margin-left: 10px">
                search
            </a>
            <div class="table-responsive">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th> #</th>
                        <th> code</th>
                        <th> name</th>
                        <th> dateOfBirth</th>
                        <th> phoneNumber</th>
                        <th> gmail</th>
                        <th> gender</th>
                        <th> pointUsr</th>
                        <th> accumulatedScore</th>
                        <th> password</th>
                        <th> dateCreate</th>
                        <th> dateUpdate</th>
                        <th> userCreate</th>
                        <th> userUpdate</th>
                        <th> status</th>
                        <th> rank</th>
                        <th> acction</th>

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
                                <a class="btn btn-default" href="/zephyr/admin/client/index?id=${client.id}">show</a>
                                <a class="btn btn-default" href="/zephyr/admin/client/view-update?id=${client.id}">update</a>
                                <a class="btn btn-default" href="/zephyr/admin/client/delete?id=${client.id}"
                                   onclick="if(!confirm('Bạn có muốn xoá?')){return false}else{alert('xoá thành công');}">delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <nav aria-label="Page navigation example" style="margin-left: 950px; margin-top: 20px">
                    <ul class="pagination">
                        <c:forEach begin="0" end="${ listClient.totalPages -1}" varStatus="loop">
                            <li class="page-item">
                                <a class="page-link"
                                   href="/zephyr/admin/client/index-page?page=${loop.begin + loop.count - 1}">
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

<div class="col-lg-12 grid-margin stretch-card">
    <div class="card">
        <div class="card-body">
            <%@include file="../address/index.jsp" %>
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
            <form action="/zephyr/admin/client/search" method="get">
                <div class="modal-body">
                    <div class="row" >
                        <div class="col-md-12">
                            <div class="mb-3">
                                <input class="form-control" name="inputProduct" placeholder="input product">
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
<%-- Model restore--%>

<div class="modal fade" id="myModalRestore" role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th> #</th>
                    <th> code</th>
                    <th> name</th>
                    <th> dateOfBirth</th>
                    <th> phoneNumber</th>
                    <th> gmail</th>
                    <th> gender</th>
                    <th> pointUsr</th>
                    <th> accumulatedScore</th>
                    <th> status</th>
                    <th> rank</th>
                    <th> restore</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${ listRestore }" var="restore" varStatus="i">
                    <tr>
                        <td>${i.index+1}</td>
                        <td>${restore.code}</td>
                        <td>${restore.name}</td>
                        <td>${restore.dateOfBirth}</td>
                        <td>${restore.phoneNumber}</td>
                        <td>${restore.gmail}</td>
                        <td>${restore.gender == true ? "nam" : "nữ"}</td>
                        <td>${restore.pointUsr}</td>
                        <td>${restore.accumulatedScore}</td>
                        <td>${restore.status == 1 ? "đang hoạt động" : "ngừng hoạt động"}</td>
                        <td>${restore.clickRank()}</td>
                        <td class="align-middle">
                            <a class="btn btn-info" href="/zephyr/admin/client/restore?id=${restore.id}"
                               onclick="if(!confirm('Bạn có muốn khôi phục ?')){return false}else{alert('khôi phục thành công');}">
                                restore
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

        </div>
    </div>
</div>

<%-- End restore--%>

</body>
</html>