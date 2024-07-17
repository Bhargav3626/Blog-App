<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.sunbase.models.Blog" %>
<%
    Blog blog = (Blog) request.getAttribute("blog");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Blog</title>
    <link rel="stylesheet" type="text/css" href="css/editblog.css">
</head>
<body>
    <div class="container">
        <h2>Edit Blog</h2>
        <form action="UpdateBlogServlet" method="POST">
            <input type="hidden" name="id" value="<%= blog.getId() %>">
            <div class="input-group">
			    <label for="title">Title:</label>
			    <input type="text" name="title" id="title" value="<%= blog.getTitle() %>" required>
			</div>
			<div class="input-group">
			    <label for="content">Content:</label>
			    <textarea name="content" id="content" required><%= blog.getContent() %></textarea>
			</div>
			<div class="input-group">
			    <label for="imagePath">Image Path:</label>
			    <input type="text" name="imagePath" id="imagePath" value="<%= blog.getImageVideoPath() %>" required>
			</div>
            <button type="submit">Save</button>
            <a href="adminDashboard.jsp">Cancel</a>
        </form>
    </div>
</body>
</html>
