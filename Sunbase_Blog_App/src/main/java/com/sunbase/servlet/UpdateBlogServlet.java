package com.sunbase.servlet;

import com.sunbase.models.Blog;
import com.sunbase.dao.BlogDAO;
import com.sunbase.dao.impl.BlogDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/UpdateBlogServlet")
public class UpdateBlogServlet extends HttpServlet {
    private BlogDAO blogDAO = new BlogDAOImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String imagePath = request.getParameter("imagePath");
        
        HttpSession session = request.getSession();
        
        Blog blog = new Blog();
        blog.setId(id);
        blog.setTitle(title);
        blog.setContent(content);
        blog.setImageVideoPath(imagePath);

        boolean isUpdated = blogDAO.updateBlog(blog);

        if (isUpdated) {
             session = request.getSession();
             session.setAttribute("message", "Update successful");
        } else {
            request.getSession().setAttribute("message", "Update failed");
        }

        response.sendRedirect("HomeServlet");
    }
}
