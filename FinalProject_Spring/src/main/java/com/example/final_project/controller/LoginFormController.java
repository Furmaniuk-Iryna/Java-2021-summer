package com.example.final_project.controller;
import com.example.final_project.entity.User;
import com.example.final_project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/")
public class LoginFormController {

    private final UserService userService;

    @Autowired
    public LoginFormController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    //@RequestMapping(value = "login", method = RequestMethod.POST)
    @PostMapping(value = "login")
    public void loginFormController(User user){
        log.info("{}",userService.loadUserByUsername(user.getUsername()));
        log.info("{}", user);
/*       userService.saveNewUser(User.builder()
                .firstName("Ann")
                .lastName("Reizer")
                .email("AnnReizer@testing.ua")
                .role(RoleType.ROLE_USER)
                .build());*/
    }

//    @RequestMapping(value = "user", method = RequestMethod.GET)
//    public User getAllUser(){
//        log.info("{}",userService.allUsers());
//        return userService.allUsers();
//    }
}
