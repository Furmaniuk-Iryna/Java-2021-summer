package com.example.final_project.controller;

import com.example.final_project.entity.Role;
import com.example.final_project.entity.User;
import com.example.final_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;



    @GetMapping()
    public String registration() {
        return "registration";
    }


    @PostMapping()
    public String addUser( User user, Map<String, Object> model){
        //TODO ref. optional
        User userFromDB= userRepository.findByUsername(user.getUsername());
        if (userFromDB != null){
            model.put("message", "User "+user.getUsername()+" exists!");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        return "redirect:/users";
    }
}
