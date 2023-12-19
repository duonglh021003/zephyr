<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>

</head>
<body>
<div style="margin-bottom: 30px">
    <span><a href="/zephyr/admin/staff/index">staff</a></span>
    <span style="color: #C0C0C0"> / index</span>
</div>
<div class="col-lg-12 grid-margin stretch-card">
    <div class="card">
        <div class="card-body">
            <a style="margin-bottom: 20px" type="button" class="btn btn-success"
               href="/zephyr/admin/staff/view-add">
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
                        <th> dateCreate </th>
                        <th> dateUpdate </th>
                        <th> userCreate </th>
                        <th> userUpdate </th>
                        <th> status </th>
                        <th> position </th>
                        <th> update </th>
                        <th> delete </th>

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
                            <td>${staff.gender == true ? "nam" : "nữ"}</td>
                            <td>${staff.address}</td>
                            <td>${staff.commune}</td>
                            <td>${staff.district}</td>
                            <td>${staff.city}</td>
                            <td>${staff.password}</td>
                            <td>${staff.dateCreate}</td>
                            <td>${staff.dateUpdate}</td>
                            <td>${staff.userCreate}</td>
                            <td>${staff.userUpdate}</td>
                            <td>${staff.status == 1 ? "đang hoạt động" : "ngừng hoạt động"}</td>
                            <td>${staff.position.name}</td>
                            <td>
                                <a class="btn btn-info" href="/zephyr/admin/staff/view-update?id=${staff.id}">
                                    update
                                </a>
                            </td>
                            <td>
                                <a class="btn btn-danger" href="/zephyr/admin/staff/delete?id=${staff.id}"
                                   onclick="if(!confirm('Bạn có muốn xoá?')){return false}else{alert('xoá thành công');}">
                                    cancel
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <nav aria-label="Page navigation example" style="margin-left: 950px; margin-top: 20px">
                    <ul class="pagination" >
                        <c:forEach begin="0" end="${ listStaff.totalPages -1}" varStatus="loop" >
                            <li class="page-item"  >
                                <a class="page-link" href="/zephyr/admin/staff/index?page=${loop.begin + loop.count - 1}" >
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

<%-- Model search--%>

<div class="modal fade" id="myModalSearch" role="dialog">
    <div class="modal-dialog ">
        <div class="modal-content">

            <div class="modal-header">
                <h4 class="modal-title">my let input</h4>
            </div>
            <form action="/zephyr/admin/product/search" method="get">
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
                    <th> # </th>
                    <th> code </th>
                    <th> name </th>
                    <th> dateOfBirth </th>
                    <th> phoneNumber </th>
                    <th> gmail </th>
                    <th> gender </th>
                    <th> status </th>
                    <th> position </th>
                    <th> restore </th>

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
                        <td>${restore.gender  == true ? "nam" : "nữ"}</td>
                        <td>${restore.status == 1 ? "đang hoạt động" : "ngừng hoạt động"}</td>
                        <td>${restore.position.name}</td>
                        <td>
                            <a class="btn btn-info" href="/zephyr/admin/staff/restore?id=${restore.id}"
                               onclick="if(!confirm('Bạn có muốn khôi phục?')){return false}else{alert('khôi phục thành công');}">khôi phục</a>
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