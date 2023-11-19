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

<%--<script>--%>
<%--    function isClientNull() {--%>

<%--        var clientString = sessionStorage.getItem("clientData");--%>
<%--        var client = JSON.parse(clientString);--%>
<%--        return client === null || client === undefined;--%>
<%--    }--%>

<%--    function addToCart() {--%>
<%--        var productInput = document.getElementById("product-input");--%>
<%--        var idProduct = productInput.getAttribute("value");--%>

<%--        var selectedSize = document.querySelector('input[name="size"]:checked');--%>
<%--        var idSize = selectedSize.value;--%>

<%--        var selectedColor = document.querySelector('input[name="color"]:checked');--%>
<%--        var idColor = selectedColor.value;--%>

<%--        var quantityInput = document.getElementById("number").value;--%>
<%--        var quantity = parseInt(quantityInput);--%>

<%--        var product = {--%>
<%--            idProduct: idProduct,--%>
<%--            idSize: idSize,--%>
<%--            idColor: idColor,--%>
<%--            quantity: quantity--%>
<%--        };--%>

<%--        var productsInCart = localStorage.getItem("productData");--%>
<%--        if (productsInCart) {--%>
<%--            // Nếu đã có dữ liệu, chuyển đổi từ chuỗi JSON thành mảng đối tượng--%>
<%--            productsInCart = JSON.parse(productsInCart);--%>

<%--            // Thêm sản phẩm mới vào mảng--%>
<%--            productsInCart.push(product);--%>
<%--        } else {--%>
<%--            // Nếu chưa có dữ liệu, tạo một mảng mới chứa sản phẩm đầu tiên--%>
<%--            productsInCart = [product];--%>
<%--        }--%>

<%--        // Chuyển đổi mảng đối tượng thành chuỗi JSON--%>
<%--        var productsInCartJSON = JSON.stringify(productsInCart);--%>

<%--        // Lưu mảng chứa thông tin sản phẩm vào localStorage--%>
<%--        localStorage.setItem("productData", productsInCartJSON);--%>

<%--        alert("aaaaaaaaaaaaaaaaaaaaa        "+productsInCartJSON);--%>
<%--        // var client;--%>
<%--        // if(client == null){--%>
<%--        //     localStorage.removeItem("productData");--%>
<%--        // }--%>



<%--        var url = "/zephyr/shop/list?productData="+encodeURIComponent(JSON.stringify(productsInCart));--%>
<%--        var xhr = new XMLHttpRequest();--%>
<%--        xhr.open("GET", url, true);--%>
<%--        xhr.onreadystatechange = function () {--%>
<%--            if (xhr.readyState === 4 && xhr.status === 200) {--%>
<%--                console.log("Data sent successfully");--%>
<%--                console.log(xhr.responseText);--%>
<%--            }--%>
<%--        };--%>
<%--        xhr.send();--%>

<%--        if (isClientNull()) {--%>
<%--            var url = "/zephyr/shop/addToCart?product=" + idProduct + "&size=" + idSize + "&color=" + idColor + "&quantity=" + quantity;--%>
<%--            var xhr = new XMLHttpRequest();--%>
<%--            xhr.open("GET", url, true);--%>
<%--            xhr.onreadystatechange = function () {--%>
<%--                if (xhr.readyState === 4 && xhr.status === 200) {--%>
<%--                    console.log("Data sent successfully");--%>
<%--                    console.log(xhr.responseText);--%>
<%--                }--%>
<%--            };--%>
<%--            xhr.send();--%>
<%--        } else {--%>

<%--            localStorage.removeItem("productData");--%>
<%--        }--%>


<%--    }--%>




<%--</script>--%>

</body>
</html>