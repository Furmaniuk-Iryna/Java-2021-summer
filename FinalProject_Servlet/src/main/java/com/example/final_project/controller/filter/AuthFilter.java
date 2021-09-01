package com.example.final_project.controller.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        String role = (String) session.getAttribute("role");

        if (session.isNew() || role == null) {
            session.setAttribute("role", "GUEST");

        }
        String path = req.getRequestURI().substring(req.getContextPath().length());

        if (path.startsWith("/img")) {
            filterChain.doFilter(request, response); // Goes to default servlet.
        }

        if ((path.equals("/main") || path.equals("/login"))
                && !session.getAttribute("role").equals("GUEST")) {
            path = "/logout";
        }

        if ((path.equals("/user") || path.equals("/delivery-request"))
                && !session.getAttribute("role").equals("USER")) {
            path = session.getAttribute("role").equals("MANAGER")
                    ? "logout"
                    : "main";
            ((HttpServletResponse) response).sendRedirect("/cargo-delivery/"+path);
            return;
        }

        if ((path.equals("/manager") || (path.equals("/directionReport")) || (path.equals("/reportByDays")) || (path.equals("/receipt")))
                && !session.getAttribute("role").equals("MANAGER")) {
            path = session.getAttribute("role").equals("USER")
                    ? "logout"
                    : "main";
            ((HttpServletResponse) response).sendRedirect("/cargo-delivery/"+path);
            return;
        }

        request.getRequestDispatcher("/pages" + path).forward(request, response);
    }


    @Override
    public void destroy() {

    }
}
