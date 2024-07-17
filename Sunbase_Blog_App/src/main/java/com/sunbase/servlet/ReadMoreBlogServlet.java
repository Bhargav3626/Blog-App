package com.sunbase.servlet;

import com.sunbase.dao.BlogDAO;
import com.sunbase.dao.impl.BlogDAOImpl;
import com.sunbase.dao.impl.UserDAOImpl;
import com.sunbase.models.Blog;
import com.sunbase.models.User;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ReadMoreBlogServlet")
public class ReadMoreBlogServlet extends HttpServlet {
	
	private BlogDAO blogDAOImpl;
	
	@Override
	public void init() throws ServletException {
		 blogDAOImpl = new BlogDAOImpl();
	}
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int blogId = Integer.parseInt(request.getParameter("id"));
        
        Blog blogById = blogDAOImpl.getBlogById(blogId);
        

        if (blogById != null) {
            
            UserDAOImpl userDAOImpl = new UserDAOImpl();
            User userById = userDAOImpl.getUserById(blogById.getAuthorId());

            request.setAttribute("blog", blogById);
            request.setAttribute("user", userById);
            request.getRequestDispatcher("readmoreblog.jsp").forward(request, response);
        } else {
            request.getSession().setAttribute("message", "Blog not found.");
            response.sendRedirect("HomeServlet");
        }
    }
}
