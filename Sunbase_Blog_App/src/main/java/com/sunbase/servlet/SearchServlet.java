package com.sunbase.servlet;

import com.sunbase.models.Blog;
import com.sunbase.models.User;
import com.sunbase.dao.BlogDAO;
import com.sunbase.dao.impl.BlogDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
    private BlogDAO blogDAO;

    @Override
    public void init() {
        blogDAO = new BlogDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchType = request.getParameter("searchType");
        String query = request.getParameter("query");

        HttpSession session = request.getSession();
        List<Blog> blogs = null;

        try {
            switch (searchType) {
                case "title":
                    blogs = blogDAO.getBlogsByTitle(query);
                    break;
                case "date":
                    blogs = blogDAO.getBlogsByDate(query);
                    break;
                case "author":
                    int authorId = Integer.parseInt(query);
                    blogs = blogDAO.getBlogsByAuthor(authorId);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
       //Check whether  blog is empty 
        if(blogs != null && !blogs.isEmpty()) {
        	session.setAttribute("blogs", blogs);
        	session.setAttribute("message", "Search results for " + query);
        }else{
        	session.setAttribute("blogs", null);
        	session.setAttribute("message", "No results for " + query);
        }


        // Check the role and redirect accordingly
        User user = (User)session.getAttribute("user");
        if (user != null) {
            if ("Admin".equalsIgnoreCase(user.getRole())) {
                response.sendRedirect("adminDashboard.jsp");
            } else if ("Viewer".equalsIgnoreCase(user.getRole())) {
                response.sendRedirect("viewer.jsp");
            }
        } else {
            response.sendRedirect("index.jsp"); // Redirect to login if no user in session
        }
    }
}
