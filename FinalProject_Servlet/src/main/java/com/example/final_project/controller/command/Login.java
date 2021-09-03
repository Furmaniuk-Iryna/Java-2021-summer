package com.example.final_project.controller.command;

import com.example.final_project.model.entity.User;
import com.example.final_project.model.service.UserService;
import javax.servlet.http.HttpServletRequest;

/**
 * Login is a controller we'll be using to receive requests and send a response for log in
 * Please see the {@link com.example.final_project.controller.command.Command} class for true identity
 */
public class Login implements Command{
private final UserService userService=new UserService();
    @Override
    public String execute(HttpServletRequest request) {


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


