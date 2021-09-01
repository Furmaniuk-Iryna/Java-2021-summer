package com.example.final_project.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Optional;

public class CommandUtility {
    static void setUserRole(HttpServletRequest request,
                            String role, String username) {

        HttpSession session = request.getSession();
        session.setAttribute("role", role);
        session.setAttribute("userName", username);
    }

    static void deleteUserFromLoggedUsers(HttpServletRequest request) {
        HttpSession session = request.getSession();
        @SuppressWarnings("unchecked")
        HashSet<String> loggedUsers = (HashSet<String>) request.getSession().getServletContext()
                .getAttribute("loggedUsers");
        loggedUsers.remove((String) request.getSession().getAttribute("userName"));
        request.getSession().getServletContext()
                .setAttribute("loggedUsers", loggedUsers);
        session.removeAttribute("role");
        session.removeAttribute("userName");
    }

    static boolean checkUserIsLogged(HttpServletRequest request, String userName) {
        @SuppressWarnings("unchecked")
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
