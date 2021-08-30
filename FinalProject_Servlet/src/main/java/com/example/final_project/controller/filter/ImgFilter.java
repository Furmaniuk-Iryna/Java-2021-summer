package com.example.final_project.controller.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ImgFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String path = req.getRequestURI().substring(req.getContextPath().length());

        if (path.startsWith("/img")) {
            filterChain.doFilter(request, response); // Goes to default servlet.
        } else {
            request.getRequestDispatcher("/pages" + path).forward(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
