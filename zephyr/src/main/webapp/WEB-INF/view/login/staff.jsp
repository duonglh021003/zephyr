<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<div style="margin-top: 100px; ">
    <h2 style="text-align: center; margin-right: 80px">Login</h2>
    <form action="/zephyr/staff/sign-in" method="post" style="margin-top: 50px ">
        <div class="row">
            <div class="col-md-4"></div>
            <div class="col-md-4">
                <div class="mb-3">
                    <label for="username">account: </label>
                    <input type="text" id="username" name="phoneNumber" required style="margin-left: 10px">
                </div>
            </div>

            <div class="col-md-4"></div>
        </div>

        <div class="row">
            <div class="col-md-4"></div>
            <div class="col-md-4">
                <div class="mb-3">
                    <div class="mb-3">
                        <label for="password">password: </label>
                        <input type="password" name="password" id="password" required>
                    </div>
                </div>
            </div>
            <div class="col-md-4"></div>
        </div>

        <div class="row">
            <div class="col-md-4"></div>
            <div class="col-md-4">
                <div class="mb-3">
                    <div class="mb-3">
                        <button type="submit" style="margin-left: 120px; background: blue; color: white">Đăng nhập
                        </button>
                    </div>
                </div>
            </div>
            <div class="col-md-4"></div>
        </div>
    </form>
</div>
</body>
</html>