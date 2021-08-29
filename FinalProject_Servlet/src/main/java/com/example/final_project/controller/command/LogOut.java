package com.example.final_project.controller.command;

import com.example.final_project.model.entity.Role;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogOut implements Command{

    @Override
    public synchronized String execute(HttpServletRequest request) {

        CommandUtility.deleteUserFromLoggedUsers(request);
        return "redirect:/main";
    }
}
