package com.example.final_project.controller.command;

import javax.servlet.http.HttpServletRequest;

public class LogOut implements Command{

    @Override
    public synchronized String execute(HttpServletRequest request) {

        CommandUtility.deleteUserFromLoggedUsers(request);
        return "redirect:/main";
    }
}
