package com.example.final_project.controller.command;

import com.example.final_project.model.entity.User;
import com.example.final_project.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class Login implements Command{
private  UserService userService=new UserService();
    @Override
    public synchronized String execute(HttpServletRequest request) {

        Optional<User> role = Optional.ofNullable(userService.getUserByUsername((String) request.getSession().getAttribute("userName")));

        if (role.get().getRole()!=null&&(role.get().getRole().equals("USER") || role.get().getRole().equals("MANAGER"))){
            CommandUtility.deleteUserFromLoggedUsers(request);
            return "redirect:/main";
        }


        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || username.equals("") || password == null || password.equals("")) {
            return "/login.jsp";
        }

        User user = userService.getUserByUsername(username);
        return user.getUsername() == null ? "/login.jsp" : getPageForRole(request, user.getRole(), username);
    }

    public String getPageForRole (HttpServletRequest request,
                                  String role, String username){
        if(CommandUtility.checkUserIsLogged(request, username)){
          throw new RuntimeException("User is logged");
        }


        CommandUtility.setUserRole(request,role,username);
        return role.equals("USER") ? "redirect:/user" :"redirect:/manager";
    }


}


