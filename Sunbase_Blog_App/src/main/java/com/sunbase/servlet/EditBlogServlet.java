package com.sunbase.servlet;

import com.sunbase.models.Blog;
import com.sunbase.dao.BlogDAO;
import com.sunbase.dao.impl.BlogDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/EditBlogServlet")
public class EditBlogServlet extends HttpServlet {
    private BlogDAO blogDAO = new BlogDAOImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int blogId = Integer.parseInt(request.getParameter("id"));
        Blog blog = blogDAO.getBlogById(blogId);

        if (blog != null) {
            request.setAttribute("blog", blog);
            request.getRequestDispatcher("editblog.jsp").forward(request, response);
        } else {
        	request.getSession().setAttribute("message", "Blog not found");
            response.sendRedirect("adminDashboard.jsp");
        }
    }
}
