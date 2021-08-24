package com.example.final_project.controller.command;

import javax.servlet.http.HttpServletRequest;

public class User implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/user/user.jsp";
    }
}
