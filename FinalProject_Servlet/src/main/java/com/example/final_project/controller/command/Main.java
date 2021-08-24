package com.example.final_project.controller.command;

import com.example.final_project.model.entity.Tariff;
import com.example.final_project.model.service.TariffService;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.CopyOnWriteArrayList;

public class Main implements Command {
    TariffService tariffService = new TariffService();


    @Override
    public String execute(HttpServletRequest request) {
        return "/index.jsp";
    }
}
