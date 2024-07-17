package com.sunbase.servlet;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sunbase.dao.BlogDAO;
import com.sunbase.dao.impl.BlogDAOImpl;

@WebServlet("/DeleteBlogServlet")
public class DeleteBlogServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int blogId = Integer.parseInt(request.getParameter("id"));
        BlogDAO blogDAO = new BlogDAOImpl();
        
        boolean isDeleted = blogDAO.deleteBlog(blogId);
        
        if (isDeleted) {
            request.getSession().setAttribute("message", "Deletion successful");
        } else {
            request.getSession().setAttribute("message", "Deletion failed");
        }
        
        response.sendRedirect("HomeServlet");
    }
}
