package com.example.final_project.controller.filter;

import com.example.final_project.model.service.UserService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;
import java.io.IOException;

public class AuthFilter implements Filter {
    UserService userService= new UserService();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession();
        ServletContext context = request.getServletContext();
      //  System.out.println("logged Users---"+context.getAttribute("loggedUsers"));
     //   System.out.println("session for---"+session.getAttribute("userName"));

        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
