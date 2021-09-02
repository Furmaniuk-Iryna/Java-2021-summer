package com.example.final_project.controller.command;

import com.example.final_project.model.entity.Role;
import com.example.final_project.model.entity.User;
import com.example.final_project.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class Registration implements Command{

    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().setAttribute("error", false);
        request.getSession().setAttribute("userPresent", false);

        UserService userService = new UserService();
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String username = request.getParameter("username");
        String password = request.getParameter("password");


        if (username != null && !username.isEmpty()) {
            if (userService.getUserByUsername(username).getUsername() == null) {
                if (CommandUtility.validationForRegistration(name, surname, username, password)) {
                    userService.saveUser(new User(0, name, surname, username, password, Role.USER.name()));
                } else {
                    request.getSession().setAttribute("error", true);
                    return "/registration.jsp";
                }
            } else {
                request.getSession().setAttribute("userPresent", true);
                return "/registration.jsp";
            }
            return "redirect:/login";
        }

        return "/registration.jsp";
    }

}
