package com.example.filterdemo;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName = "AuthenticateFilter")
public class AuthenticateFilter implements Filter {
    FilterConfig filterConfig = null;
    public void init(FilterConfig config) throws ServletException {
        filterConfig = config;
    }

    public void destroy() {
        filterConfig = null;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException
    {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        String url = req.getRequestURL().toString();

        if (url.endsWith(".html"))
        {
            if (session==null || session.getAttribute("user")==null)
            {
                RequestDispatcher rd = req.getRequestDispatcher("login.html");
                PrintWriter out = res.getWriter();
                out.println("<h1> Invalid session. Please log in again. " +
                        "</h1>");
                rd.include(req, response);
            }
        }
        else chain.doFilter(request, response);
    }
}
