function previewImage(event) {
    var input = event.target; // Input file
    var imagePreview = document.getElementById("imagePreview");
    var reader = new FileReader();
    reader.onload = function() {
        imagePreview.src = reader.result;
    };
    reader.readAsDataURL(input.files[0]);
    // Hoặc nếu bạn muốn hiển thị tên file ảnh
    // var fileName = input.files[0].name;
    // var fileNameElement = document.getElementById("fileName");
    // fileNameElement.textContent = fileName;
}