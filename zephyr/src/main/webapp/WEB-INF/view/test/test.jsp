<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<script>
    <%--
    @GetMapping("/path/to/java/endpoint")
        public String handleRequest(@RequestParam("id") Long id,Model model) {
    //        Long id = address.getId();
            // Xử lý giá trị id ở đâyssss
            Address address = addressService.detail(Long.valueOf(id));
            System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbb       "+address);
            model.addAttribute("addressUpdate", address);
            System.out.println("Received id: " + id);

            model.addAttribute("viewClient", "/WEB-INF/view/demo.jsp");

            return "layout/client";
        }
        --%>

<%--    function handleClick(productDetails) {--%>
<%--        alert("id: " + productDetails);--%>

<%--        // Gửi yêu cầu AJAX đến máy chủ Java--%>
<%--        var xhr = new XMLHttpRequest();--%>
<%--        xhr.open("GET", "/zephyr/shop/path/to/java/endpoint?id=" + encodeURIComponent(productDetails), true);--%>
<%--        xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");--%>

<%--        $("#myModalUpdate").attr("data-address-id", productDetails);--%>

<%--        // Gửi dữ liệu JSON tới máy chủ Java--%>
<%--        var data = JSON.stringify({id: productDetails});--%>
<%--        xhr.send(data);--%>
<%--    }--%>
<%--</script>--%>


</body>
</html>