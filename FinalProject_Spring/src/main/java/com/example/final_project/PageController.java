package com.example.final_project;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @RequestMapping("/")
    public String mainPage() {
        return "index.html";
    }

    @RequestMapping("/registration")
    public String registrationPage() {
        return "registrationPage.html";
    }

    @RequestMapping("/log-in")
    public String logInPage() {
        return "logInPage";
    }


}
