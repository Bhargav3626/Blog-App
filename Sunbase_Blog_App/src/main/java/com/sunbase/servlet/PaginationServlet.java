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

@WebServlet("/PaginationServlet")
public class PaginationServlet extends HttpServlet {
    private BlogDAO blogDAO;

    @Override
    public void init() throws ServletException {
        blogDAO = new BlogDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNumber = Integer.parseInt(request.getParameter("page"));
        HttpSession session = request.getSession();
        session.setAttribute("currentPage", pageNumber);

        User user = (User) session.getAttribute("user");

        if (user != null && user.getRole().equals("Admin")) {
            response.sendRedirect("admindashboard.jsp");
        } else {
            response.sendRedirect("viewer.jsp");
        }
    }
}
