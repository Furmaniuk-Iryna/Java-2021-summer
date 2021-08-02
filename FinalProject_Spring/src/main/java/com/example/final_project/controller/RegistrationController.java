package com.example.final_project.controller;

import com.example.final_project.entity.Role;
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
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {

@Autowired
   private UserRepository userRepository;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }


    @PostMapping("/registration")
    public String addUser( User user, Map<String, Object> model){
 User userFromDB= userRepository.findByUsername(user.getUsername());
 if (userFromDB != null){
     model.put("message", "User exists!");
     return "registration";
 }
 user.setActive(true);
 user.setRoles(Collections.singleton(Role.USER));
 userRepository.save(user);
        return "redirect:/user";
    }
}
