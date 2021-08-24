package com.example.final_project.controller.command;

import javax.servlet.http.HttpServletRequest;

public class Manager implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/manager/manager.jsp";
    }
}
