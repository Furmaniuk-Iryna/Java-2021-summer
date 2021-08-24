package com.example.final_project.controller.command;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Optional;

public class CommandUtility {
    static void setUserRole(HttpServletRequest request,
                            String role, String username) {

        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        context.setAttribute("userName", username);
        session.setAttribute("role", role);

    }

    static boolean checkUserIsLogged(HttpServletRequest request, String userName) {
        HashSet<String> loggedUsers = (HashSet<String>) Optional.ofNullable(request.getSession().getServletContext()
                .getAttribute("loggedUsers")).orElse(new HashSet<String>());

        if (!loggedUsers.isEmpty() && loggedUsers.stream().anyMatch(userName::equals)) {
            return true;
        }

        loggedUsers.add(userName);
        request.getSession().getServletContext()
                .setAttribute("loggedUsers", loggedUsers);
        return false;
    }
}
