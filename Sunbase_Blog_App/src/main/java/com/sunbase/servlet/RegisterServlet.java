package com.sunbase.servlet;

import com.sunbase.dao.UserDAO;
import com.sunbase.dao.impl.UserDAOImpl;
import com.sunbase.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	
	private UserDAO userDAO;
	
	@Override
	public void init() throws ServletException {
		 userDAO = new UserDAOImpl();
	}
     

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        boolean hasError = false;

        // Check if username exists
        User existingUserByUsername = userDAO.getUserByUsername(username);
        if (existingUserByUsername != null) {
            request.setAttribute("usernameError", "Username already exists.");
            hasError = true;
        }

        // Check if email exists
        User existingUserByEmail = userDAO.getUserByEmail(email);
        if (existingUserByEmail != null) {
            request.setAttribute("emailError", "Email already exists.");
            hasError = true;
        }

        // If there are errors, retain the form data and go back to register.jsp
        if (hasError) {
            request.setAttribute("username", username);
            request.setAttribute("email", email);
            request.setAttribute("role", role);
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        
        // Hash the password
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        
        // Register new user
        User newUser = new User(username, email, hashedPassword, role);
        userDAO.registerUser(newUser);

        // Redirect to a success page or login page
        response.sendRedirect("index.jsp");
    }
}