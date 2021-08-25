package com.example.final_project.controller.command;

import com.example.final_project.model.entity.User;
import com.example.final_project.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class Login implements Command{

    @Override
    public synchronized String execute(HttpServletRequest request) {
        UserService userService = new UserService();
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || username.equals("") || password == null || password.equals("")) {
            return "/login.jsp";
        }

        User user = userService.getUserByUsername(username);
        return user.getUsername() == null ? "/login.jsp" : getPageForRole(request, user.getRole(), username);
    }

    public String getPageForRole (HttpServletRequest request,
                                  String role, String username){
        if(CommandUtility.checkUserIsLogged(request, username)){
            new RuntimeException("user is logged");
            return "/WEB-INF/error.jsp";
        }


        CommandUtility.setUserRole(request,role,username);
        return role.equals("USER") ? "redirect:/user" :"redirect:/manager";
    }


}


