package com.sunbase.servlet;

import com.sunbase.dao.BlogDAO;
import com.sunbase.dao.impl.BlogDAOImpl;
import com.sunbase.models.Blog;
import com.sunbase.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/BlogRegisterServlet")
public class BlogRegisterServlet extends HttpServlet {
    private BlogDAO blogDAO;

    @Override
    public void init() {
        blogDAO = new BlogDAOImpl(); // Initialize the DAO
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String imagePath = request.getParameter("imagePath");

        // Get the user from session to retrieve authorId
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int authorId = user.getId(); 
        
        // Create a new Blog object using the constructor
        Blog newBlog = new Blog(title, content, imagePath, authorId);

        // Use the DAO to create the blog
        boolean createBlog = blogDAO.createBlog(newBlog);
        
        if (createBlog) {
            session = request.getSession();
            session.setAttribute("message", "New Blog Added");
       } else {
           request.getSession().setAttribute("message", "Failed to add blog");
       }

        // Redirect to HomeServlet
        response.sendRedirect("HomeServlet");
    }
}
