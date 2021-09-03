package com.example.final_project.controller.command;

import javax.servlet.http.HttpServletRequest;
/**
 * LogOut is a controller we'll be using to receive requests and send a response for log out
 * Please see the {@link com.example.final_project.controller.command.Command} class for true identity
 */
public class LogOut implements Command{

    @Override
    public synchronized String execute(HttpServletRequest request) {

        CommandUtility.deleteUserFromLoggedUsers(request);
        return "redirect:/main";
    }
}
