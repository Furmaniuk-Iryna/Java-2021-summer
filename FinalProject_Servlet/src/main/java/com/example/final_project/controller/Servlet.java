package com.example.final_project.controller;

import com.example.final_project.controller.command.*;
import com.example.final_project.controller.command.Exception;
import com.example.final_project.model.service.TariffService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Servlet extends HttpServlet {
TariffService tariffService = new TariffService();
    private Map<String, Command> commands = new ConcurrentHashMap<>();

    public void init(){
        commands.put("main", new Main());
        commands.put("logout", new LogOut());
        commands.put("login", new Login());
        commands.put("registration", new Registration());
        commands.put("exception" , new Exception());
        commands.put("user", new User());
        commands.put("manager", new Manager());
        commands.put("delivery-request", new DeliveryRequestCommand());
    }


    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {

        processRequest(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      //  request.setAttribute("tariffs",tariffService.getAllTariffs());
        String path = request.getRequestURI();
        path = path.replaceAll(".*/cargo-delivery/" , "");
        Command command = commands.getOrDefault(path ,
                (r)->"/main");
        System.out.println(command.getClass().getName());
        String page = command.execute(request);
        if(page.contains("redirect:")){
            response.sendRedirect(page.replace("redirect:", "/cargo-delivery"));
        }else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
