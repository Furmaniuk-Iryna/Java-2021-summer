package com.example.final_project.controller.command;

import javax.servlet.http.HttpServletRequest;

public class ExceptionCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/error.jsp";
    }
}
