package com.example.final_project.controller.command;

import com.example.final_project.model.entity.Role;
import com.example.final_project.model.entity.User;
import com.example.final_project.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class Registration implements Command{

    @Override
    public String execute(HttpServletRequest request) {


        UserService userService = new UserService();
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //TODO validation

        if (username != null && !username.isEmpty() && userService.getUserByUsername(username).getUsername() == null) {
            userService.saveUser(new User(0, name, surname, username, password, Role.USER.name()));
            return "/login";
        }

        return "/registration.jsp";
    }

}
