package com.example.final_project.controller.command;

import com.example.final_project.model.entity.Direction;
import com.example.final_project.model.entity.Tariff;
import com.example.final_project.model.service.DirectionService;
import com.example.final_project.model.service.TariffService;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

public class Main implements Command {
  private TariffService tariffService= new TariffService();
  private DirectionService directionService= new DirectionService();
    List<Tariff> tariffList = tariffService.getAllTariffs();
    List<Direction> directionList = directionService.getAllDirections();

    @Override
    public String execute(HttpServletRequest request)  {
        String language=request.getParameter("locale");
        Locale locale = new Locale(language==null ? "en" : language);

        request.setAttribute("locale", locale);
        request.setAttribute("tariffList", tariffList);
        request.setAttribute("directions", directionList);
        return "/main.jsp";
    }
}
