package com.sunbase.servlet; 

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.sunbase.dao.BlogDAO; 
import com.sunbase.dao.impl.BlogDAOImpl; 
import com.sunbase.models.Blog; 
import com.sunbase.models.User; 

@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	
    private BlogDAO blogDAO;
    
    @Override
    public void init() throws ServletException {
    	blogDAO = new BlogDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        

        if (user != null) {
        	String role = user.getRole();
            List<Blog> blogs = blogDAO.getAllBlogs();
            session.setAttribute("blogs", blogs);

            if ("Admin".equals(role)) {
                response.sendRedirect("adminDashboard.jsp");
            } else if ("Viewer".equals(role)) {
                response.sendRedirect("viewer.jsp");
            }
        } else {
            response.sendRedirect("index.jsp"); // Redirect to login if not logged in
        }
    }
}
