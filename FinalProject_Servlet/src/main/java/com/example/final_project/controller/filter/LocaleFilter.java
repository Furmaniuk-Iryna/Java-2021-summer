package com.example.final_project.controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LocaleFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;

        if (request.getParameter("locale") != null)
            req.getSession().setAttribute("locale", request.getParameter("locale"));

        req.getSession().setAttribute("locale", req.getSession().getAttribute("locale") == null
                ? "en"
                : (String) req.getSession().getAttribute("locale"));

        filterChain.doFilter(req, res);
    }

    @Override
    public void destroy() {

    }
}
