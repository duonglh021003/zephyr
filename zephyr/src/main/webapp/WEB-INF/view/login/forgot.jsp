<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/login.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
</head>
<body>

<div class="container" id="container">
        <form action="/zephyr/forgot-submit" method="post" id="formForgot">
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <h1 style="margin-top: -80px;line-height: 100px;text-align: center">Forgot Password</h1>
                    Enter your user account's verified email address and we will send you a password reset link.
                    <input type="text" placeholder="Email" style="margin-bottom: 30px">
                    <button >Submit</button>
                </div>
                <div class="col-md-4"></div>

            </div>

        </form>
</div>

<script src="${pageContext.request.contextPath}/assets/js/login.js"></script>


</body>
</html>