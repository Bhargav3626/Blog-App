<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.sunbase.models.Blog" %>
<%@ page import="com.sunbase.models.User" %>
<%
    Blog blog = (Blog) request.getAttribute("blog");
    User user = (User) request.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><%= blog != null ? blog.getTitle() : "Blog Not Found" %></title>
    <link rel="stylesheet" type="text/css" href="css/readmoreblog.css">
    <% if (blog != null) { %>
        <link rel="icon" href="<%= blog.getImageVideoPath() %>">
    <% } %>
</head>
<body>
    <div class="container">
        <h2 class="blog-title"><%= blog != null ? blog.getTitle() : "Blog Not Found" %></h2>
        <% if (blog != null) { %>
            <div class="blog-image">
                <img src="<%= blog.getImageVideoPath() %>" alt="<%= blog.getTitle() %>">
            </div>
            <div class="blog-details">
                <div class="blog-author">
                    <span>Author Name: <%= user != null ? user.getUsername() : "Unknown" %></span>
                    <br>
                    <span>Author ID: <%= user != null ? user.getId() : "Unknown" %></span>
                </div>
                <div class="blog-dates">
                    <span>Created Date: <%= blog.getCreatedAt() %></span>
                    <br>
                    <span>Last Updated: <%= blog.getUpdatedAt() %></span>
                </div>
            </div>
            <div class="blog-content">
                <p><%= blog.getContent() %></p>
            </div>
        <% } else { %>
            <p>Blog not found.</p>
        <% } %>
    </div>
</body>
</html>
