package com.sunbase.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;
import com.sunbase.dao.UserDAO; 
import com.sunbase.dao.impl.UserDAOImpl; 
import com.sunbase.models.User; 

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	 private UserDAO userDAO;
	
	@Override
	public void init() throws ServletException {
		userDAO = new UserDAOImpl();
	}
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Check if the user exists
        User user = userDAO.getUserByUsername(username);

        if (user != null) {
            // Verify the password
            if (BCrypt.checkpw(password, user.getPassword())) {
                // Password is correct, create a session
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                
                // Redirect to home servlet
                response.sendRedirect("HomeServlet");

            } else {
                // Invalid password
                request.setAttribute("errorMessage", "Invalid details. Please try again.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } else {
            // User does not exist
            request.setAttribute("errorMessage", "Username does not exist. Please register.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
