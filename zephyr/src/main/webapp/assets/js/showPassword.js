const togglePasswordButton = document.getElementById("togglePasswordButton");
const password = document.getElementById("password");

togglePasswordButton.addEventListener("click", function() {
    if (password.type === "password") {
        password.type = "text";
        togglePasswordButton.textContent = "";
    } else {
        password.type = "password";
        togglePasswordButton.textContent = "";
    }
});