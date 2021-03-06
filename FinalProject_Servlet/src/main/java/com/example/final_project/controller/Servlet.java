package com.example.final_project.controller;

import com.example.final_project.controller.command.*;
import com.example.final_project.controller.command.ExceptionCommand;
import org.apache.logging.log4j.LogManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public class Servlet extends HttpServlet {
    private static Logger log = Logger.getLogger(String.valueOf(Servlet.class));

    private Map<String, Command> commands = new ConcurrentHashMap<>();

    public void init(){


        log.info("Before Starting application");
        commands.put("main", new Main());
        commands.put("logout", new LogOut());
        commands.put("login", new Login());
        commands.put("registration", new Registration());
        commands.put("exception" , new ExceptionCommand());
        commands.put("user", new User());
        commands.put("manager", new Manager());
        commands.put("delivery-request", new DeliveryRequestCommand());
        commands.put("receipt", new ReceiptCommand());
        commands.put("directionReport", new DirectionReport());
        commands.put("reportByDays", new ReportByDays());
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }


    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }


    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private synchronized void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        path = path.replaceAll(".*/cargo-delivery/pages/" , "");
        Command command = commands.getOrDefault(path ,
                (r)->"main");
        String page = command.execute(request);
        log.info(command.getClass().getName());
        if(page.contains("redirect:")){
            response.sendRedirect(page.replace("redirect:", "/cargo-delivery"));
        }else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
