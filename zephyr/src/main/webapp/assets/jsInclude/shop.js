// $(document).ready(function () {
//     $('input[type="checkbox"]').change(function () {
//         var searchUrl = "/zephyr/shop/search";
//         window.location.href = searchUrl;
//     });
// });

$(document).ready(function () {
    $('input[type="checkbox"]').change(function () {
        // Lấy các giá trị của checkbox được chọn
        var minPrice = $('#minPrice').is(':checked');
        var maxPrice = $('#maxPrice').is(':checked');
        var nameColor = $('#nameColor').is(':checked');
        var nameSize = $('#nameSize').is(':checked');

        // Tạo đối tượng dữ liệu để gửi đến controller
        var searchData = {
            minPrice: minPrice,
            maxPrice: maxPrice,
            nameColor: nameColor,
            nameSize: nameSize
        };
        console.log('true');

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
