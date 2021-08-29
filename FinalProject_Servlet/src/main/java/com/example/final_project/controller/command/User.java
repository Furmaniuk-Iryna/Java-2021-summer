package com.example.final_project.controller.command;

import com.example.final_project.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class User implements Command{
    UserService userService = new UserService();
    @Override
    public String execute(HttpServletRequest request) {
     String role = Optional.ofNullable(userService.getUserByUsername((String) request.getSession().getAttribute("userName")).getRole())
             .orElseThrow(()-> new RuntimeException("FORBIDDEN"));
           if (!role.equals("USER")){
               return "redirect:/logout";
           }

        return "/WEB-INF/user/user.jsp";
    }
}
