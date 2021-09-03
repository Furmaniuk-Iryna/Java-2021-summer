package com.example.final_project.controller.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashSet;
/**
 * SessionListener is the listener we'll be using to delete session
 */
public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

    }

    @Override
    public synchronized void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        @SuppressWarnings("unchecked")
        HashSet<String> loggedUsers = (HashSet<String>) httpSessionEvent
                .getSession().getServletContext()
                .getAttribute("loggedUsers");
        String userName = (String) httpSessionEvent.getSession()
                .getAttribute("userName");
        loggedUsers.remove(userName);
        httpSessionEvent.getSession().setAttribute("loggedUsers", loggedUsers);
        httpSessionEvent.getSession().removeAttribute("role");
        httpSessionEvent.getSession().removeAttribute("userName");
    }
}
