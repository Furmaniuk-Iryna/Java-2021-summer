package com.example.final_project.controller;

import com.example.final_project.repository.UserRepository;
import com.example.final_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequestMapping("/manager")
//@PreAuthorize("hasAuthority('MANAGER')")
public class ManagerController {
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private UserRepository userRepository;

//    @GetMapping("/manager")
//    public String userList(Model model) {
//        model.addAttribute("allUsers", userService.allUsers());
//        return "manager";
//    }
//
//    @PostMapping("/manager")
//    public String  deleteUser(@RequestParam(required = true, defaultValue = "" ) Long userId,
//                              @RequestParam(required = true, defaultValue = "" ) String action,
//                              Model model) {
//        if (action.equals("delete")){
//            userService.deleteUser(userId);
//        }
//        return "/manager";
//    }
//
//
}
