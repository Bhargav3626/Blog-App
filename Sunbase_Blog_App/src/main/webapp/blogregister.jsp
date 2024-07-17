<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Blog Registration</title>
    <link rel="stylesheet" type="text/css" href="css/blogregister.css">
</head>
<body>
    <div class="container">
        <h2>Register a New Blog</h2>
        <form action="BlogRegisterServlet" method="post">
            <div class="form-group">
                <label for="title">Title:</label>
                <input type="text" id="title" name="title" placeholder="Enter blog title" required>
            </div>
            <div class="form-group">
                <label for="content">Content:</label>
                <textarea id="content" name="content" placeholder="Enter blog content" required></textarea>
            </div>
            <div class="form-group">
                <label for="imagePath">Image Path:</label>
                <input type="text" id="imagePath" name="imagePath" placeholder="Enter image path" required>
            </div>
            <div class="form-group">
                <button type="submit">Submit</button>
            </div>
        </form>
        <div class="link">
            <p><a href="HomeServlet">View Blogs</a></p>
        </div>
    </div>
</body>
</html>
