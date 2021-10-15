package com.example.final_project.controller;

import com.example.final_project.entity.User;
import com.example.final_project.repository.UserRepository;
import com.example.final_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Optional;

/**
 * RegistrationController we'll be using to receive request and send a page for registration new user
 */
@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;


    @GetMapping()
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }


    @PostMapping()
    public String addUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) return "registration";
        Optional<User> userFromDB = userRepository.findByUsername(user.getUsername());
        if (userFromDB.isPresent()) {
            model.addAttribute("message", "User " + user.getUsername() + " exists!");
            return "registration";
        } else {
            userService.saveUser(user);
            return "redirect:/users";
        }
    }
}
