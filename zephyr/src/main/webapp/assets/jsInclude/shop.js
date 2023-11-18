// $(document).ready(function () {
//     $('input[type="checkbox"]').change(function () {
//         var searchUrl = "/zephyr/shop/search";
//         window.location.href = searchUrl;
//     });
// });

$(document).ready(function () {
    $('input[type="checkbox"]').change(function () {
        // Lấy các giá trị của checkbox được chọn
        var minPrice = parseInt($('#minPrice').val());
        var maxPrice = parseInt($('#minPrice').val());

        // Tạo đối tượng dữ liệu để gửi đến controller
        var searchData = {
            minPrice: minPrice,
            maxPrice: maxPrice,
        };
        console.log(minPrice);

        // Gửi yêu cầu AJAX tới controller
        $.ajax({

            type: "GET",
            url: "/zephyr/shop/search",
            data: searchData,
            success: function (response) {
                // Xử lý kết quả tìm kiếm ở đây
                console.log('aaaaaaaaaa');
            },
            error: function (xhr, status, error) {
                window.location.href = this.url;
                console.log('bbbbbbbbbbbbbbb');
                // Xử lý lỗi ở đây
            }
        });
    });
});
