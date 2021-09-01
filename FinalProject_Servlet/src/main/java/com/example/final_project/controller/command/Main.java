package com.example.final_project.controller.command;

import com.example.final_project.model.entity.Direction;
import com.example.final_project.model.entity.Tariff;
import com.example.final_project.model.service.DeliveryRequestService;
import com.example.final_project.model.service.DirectionService;
import com.example.final_project.model.service.TariffService;
import com.example.final_project.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class Main implements Command {
    private final TariffService tariffService = new TariffService();
    private final DirectionService directionService = new DirectionService();
    private final DeliveryRequestService deliveryRequestService = new DeliveryRequestService();
    private final UserService userService = new UserService();
    List<Tariff> tariffList = tariffService.getAllTariffs();
    List<Direction> directionList = directionService.getAllDirections();

    @Override
    public String execute(HttpServletRequest request) throws UnsupportedEncodingException {


        request.getSession().setAttribute("cost", 0);

        if (Boolean.parseBoolean(request.getParameter("form"))) {
            //TODO validation
            double weight = Double.parseDouble(request.getParameter("weight"));
            int width = Integer.parseInt(request.getParameter("width"));
            int length = Integer.parseInt(request.getParameter("length"));
            int height = Integer.parseInt(request.getParameter("height"));

            double volume = deliveryRequestService.calculateVolume(length, height, width);
            double cost = deliveryRequestService.calculateDeliveryCost(weight, volume, request.getParameter("route"));

            request.getSession().setAttribute("cost", cost);
        }

        request.setAttribute("sort", Boolean.parseBoolean(request.getParameter("sort")));
        request.setAttribute("filter", Boolean.parseBoolean(request.getParameter("filter")));

        if (Boolean.parseBoolean(request.getParameter("filter"))) {
            request.getSession().setAttribute("filteredDirection", directionService.getNeededDirection(
                    new String(request.getParameter("city").getBytes("ISO-8859-1"), "UTF-8")));
        }

        request.setAttribute("sortedDirectionsEnLocale", directionService.sortedDirectionsForEnLocale());
        request.setAttribute("sortedDirectionsUkLocale", directionService.sortedDirectionsForUkLocale());
        request.setAttribute("tariffList", tariffList);
        request.setAttribute("directions", directionList);
        return "/main.jsp";
    }
}
