function validateForm() {
    var username = document.forms["registerForm"]["username"].value;
    var email = document.forms["registerForm"]["email"].value;
    var password = document.forms["registerForm"]["password"].value;
    var role = document.forms["registerForm"]["role"].value;
    var errorMessage = "";

    if (username == "") {
        errorMessage += "Username is required.\n";
    } else if (username.length < 5) {
        errorMessage += "Username must be at least 5 characters long.\n";
    }
    if (email == "") {
        errorMessage += "Email is required.\n";
    } else {
        var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
        if (!emailPattern.test(email)) {
            errorMessage += "Invalid email format.\n";
        }
    }
    if (password == "") {
        errorMessage += "Password is required.\n";
    } else {
        var passwordPattern = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
        if (!passwordPattern.test(password)) {
            errorMessage += "Password must be at least 8 characters long and include alphabets, numbers, and special symbols @$!%*?& \n";
        }
    }
    if (role == "") {
        errorMessage += "Role is required.\n";
    }

    if (errorMessage != "") {
        alert(errorMessage);
        return false;
    }
    return true;
}
