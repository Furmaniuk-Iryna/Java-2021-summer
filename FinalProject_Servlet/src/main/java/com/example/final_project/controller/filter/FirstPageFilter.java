package com.example.final_project.controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FirstPageFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        String path = ((HttpServletRequest) request).getRequestURI();
        path= path.replaceAll(".*/cargo-delivery" , "");
        if (path.equals("/pages")){
            path= "/cargo-delivery/pages/main";
            ( (HttpServletResponse) response).sendRedirect(path);
        }
               filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
