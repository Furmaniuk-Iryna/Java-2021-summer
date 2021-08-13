package com.example.final_project.controller;

import com.example.final_project.entity.Role;
import com.example.final_project.entity.User;
import com.example.final_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.Optional;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping()
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }


    @PostMapping()
    public String addUser(@ModelAttribute("user") User user, Model model) {
        Optional<User> userFromDB = Optional.ofNullable(userRepository.findByUsername(user.getUsername()));
        if (userFromDB.isPresent()) {
            model.addAttribute("message", "User " + user.getUsername() + " exists!");
            return "registration";
        } else {

            user.setBalance(0);
            user.setActive(true);
            user.setRoles(Collections.singleton(Role.USER));
            userRepository.save(user);
            return "redirect:/users";
        }
    }
}
