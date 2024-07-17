<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.sunbase.models.Blog" %>
<%
    List<Blog> blogs = (List<Blog>) session.getAttribute("blogs");
    int totalBlogs = blogs != null ? blogs.size() : 0;
    int blogsPerPage = 6;
    int currentPage = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
    int totalPages = (int) Math.ceil((double) totalBlogs / blogsPerPage);
    int startIndex = (currentPage - 1) * blogsPerPage;
    int endIndex = Math.min(startIndex + blogsPerPage, totalBlogs);
    
    // Message checking for edit and delete
    String message = (String) session.getAttribute("message");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" type="text/css" href="css/adminDashboard.css">
    <script src="js/search.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" />
</head>
<body>
    <nav class="navbar">
        <div class="nav-left">
            <a href="HomeServlet"><i class="fas fa-home"></i></a>
            <form class="search-form">
                <select id="searchType" name="searchType">
                    <option value="title">Search by Title</option>
                    <option value="date">Search by Date</option>
                    <option value="author">Search by Author ID</option>
                </select>
                <input type="text" placeholder="Search..." id="searchInput">
                <a href="javascript:void(0);" class="search-icon" onclick="searchBlogs()">
                    <i class="fa-solid fa-magnifying-glass"></i>
                </a>
            </form>
        </div>
        <a href="LogoutServlet" class="logout-button"><i class="fas fa-sign-out-alt"></i></a>
    </nav>

    <div class="container">
    	 <% if (message != null) { %>
		    <div class="message <%= message.contains("success") ? "success" : "error" %>">
		    	<span class="cancel" onclick="this.parentElement.style.display='none';">&times;</span>
		      	<%= message %></div>
		    <%
		        session.removeAttribute("message");
		    %>
		<% } %>
    
        <h2>Admin Dashboard</h2>
        <a href="blogregister.jsp" class="create-blog-button">Create New Blog</a>
        <div class="blog-cards">
            <% if (blogs != null) {
                for (int i = startIndex; i < endIndex; i++) { 
                    Blog blog = blogs.get(i);
            %>
                    <div class="blog-card">
                        <% if (blog.getImageVideoPath() != null) { %>
                            <img src="<%= blog.getImageVideoPath() %>" alt="<%= blog.getTitle() %>" class="blog-image">
                        <% } %>
                        <h3 class="blog-title"><%= blog.getTitle() %></h3>
                        <p class="blog-content"><%= blog.getContent().substring(0, Math.min(blog.getContent().length(), 100)) + "..." %></p>
                        <div class="admin-actions">
                            <a href="EditBlogServlet?id=<%= blog.getId() %>" class="edit-button"><i class="fas fa-edit"></i></a>
                            <a href="ReadMoreBlogServlet?id=<%= blog.getId() %>" target="_blank" class="read-more"><i class="fas fa-book-open"></i></a>
                            <a href="javascript:void(0);" class="delete-button" onclick="confirmDelete(<%= blog.getId() %>)"><i class="fas fa-trash-alt"></i></a>
                        </div>
                    </div>
            <% }
            } else { %>
                <p class="no-blogs">No blogs available.</p>
            <% } %>
        </div>
        <div class="pagination">
            <% if (currentPage > 1) { %>
                <a href="adminDashboard.jsp?page=<%= currentPage - 1 %>">&laquo; Previous</a>
            <% }
            for (int i = 1; i <= totalPages; i++) { %>
                <a href="adminDashboard.jsp?page=<%= i %>" class="<%= i == currentPage ? "active" : "" %>"><%= i %></a>
            <% }
            if (currentPage < totalPages) { %>
                <a href="adminDashboard.jsp?page=<%= currentPage + 1 %>">Next &raquo;</a>
            <% } %>
        </div>
    </div>
</body>
</html>
