package com.example.final_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @RequestMapping("/")
    public String mainPage() {
        return "main";
    }

    @RequestMapping("/registration")
    public String registrationPage() {
        return "registrationPage";
    }

    @RequestMapping("/login")
    public String logInPage() {
        return "login";
    }

    @RequestMapping("/reportByDays")
    public String reportByDays() {
        return "reportByDays";
    }

    @RequestMapping("/directionReport")
    public String directionReport() {
        return "directionReport";
    }

    @RequestMapping("/manager")
    public String admin() {
        return "manager";
    }

    @RequestMapping("/user")
    public String user() {
        return "user";
    }

}
