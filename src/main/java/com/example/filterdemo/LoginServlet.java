package com.example.filterdemo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    String _username = "anas";
    String _password = "jawad";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username-field");
        String password = request.getParameter("pw-field");

        if (username.equals(_username) && password.equals(_password)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", username);

            PrintWriter out = response.getWriter();
            out.println("<h1> Successfully logged in. </h1>");

            RequestDispatcher rd = request.getRequestDispatcher(
                    "homepage.html");
            rd.include(request, response);
        }
    }
}
