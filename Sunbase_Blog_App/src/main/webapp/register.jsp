<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <link rel="stylesheet" type="text/css" href="css/register.css">
    <script src="js/register.js"></script>
</head>
<body>
    <div class="container">
        <h2>Register</h2>
        
        <form name="registerForm" action="RegisterServlet" method="post" onsubmit="return validateForm();">
            <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" placeholder="Enter your username"
                       value="<%= request.getAttribute("username") != null ? request.getAttribute("username") : "" %>">
                <% if (request.getAttribute("usernameError") != null) { %>
                    <div class="error" style="color: red;">
                        <%= request.getAttribute("usernameError") %>
                    </div>
                <% } %>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" placeholder="Enter your email"
                       value="<%= request.getAttribute("email") != null ? request.getAttribute("email") : "" %>">
                <% if (request.getAttribute("emailError") != null) { %>
                    <div class="error" style="color: red;">
                        <%= request.getAttribute("emailError") %>
                    </div>
                <% } %>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" placeholder="Enter your password">
            </div>
            <div class="form-group">
                <label for="role">Role:</label>
                <select id="role" name="role">
                    <option value="">Select your role</option>
                    <option value="Admin" <%= "Admin".equals(request.getAttribute("role")) ? "selected" : "" %>>Admin</option>
                    <option value="Viewer" <%= "Viewer".equals(request.getAttribute("role")) ? "selected" : "" %>>Viewer</option>
                </select>
            </div>
            <div class="form-group">
                <button type="submit">Register</button>
            </div>
        </form>
        <div class="login-link">
            <p>Already have an account? <a href="index.jsp">Login</a></p>
        </div>
    </div>
</body>
</html>
