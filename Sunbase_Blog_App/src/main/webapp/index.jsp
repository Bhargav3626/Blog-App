<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="css/login.css">
    <script src="js/login.js"></script>
</head>
<body>
    <div class="container">
        <h2>Login</h2>

        <% if (request.getAttribute("errorMessage") != null) { %>
            <div class="error" style="color: red;">
                <%= request.getAttribute("errorMessage") %>
            </div>
        <% } %>

        <form name="loginForm" action="LoginServlet" method="post" onsubmit="return validateLogin();">
            <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" placeholder="Enter your username" required>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" placeholder="Enter your password" required>
            </div>
            <div class="form-group">
                <button type="submit">Login</button>
            </div>
        </form>
        <div class="register-link">
            <p>Don't have an account? <a href="register.jsp">Register</a></p>
        </div>
    </div>
</body>
</html>
