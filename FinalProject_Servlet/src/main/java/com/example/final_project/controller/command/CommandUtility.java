package com.example.final_project.controller.command;

import com.example.final_project.model.entity.Address;
import com.example.final_project.model.service.AddressService;

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

    static boolean validationForRegistration(String name, String surname, String username, String password) {
        return username.matches("[A-Za-z0-9_-]{3,20}") && password.matches("[A-Za-z0-9_-]{3,20}")
                && surname.matches("^[A-ZА-ЩЬЮЯҐІЇЄ][a-zа-щьюяґіїє']{1,30}$")
                && name.matches("^[A-ZА-ЩЬЮЯҐІЇЄ][a-zа-щьюяґіїє']{1,30}$");
    }

    static boolean validationForDeliveryRequest(double weight, int width, int length,int height, Address address) {
            return weight > 0 && width > 0 && length > 0 && height > 0 && address.getAddress_en() != null;
    }

    static boolean validationForDeliveryCost(double weight, int width, int length,int height) {
        return weight > 0 && width > 0 && length > 0 && height > 0;
    }
}