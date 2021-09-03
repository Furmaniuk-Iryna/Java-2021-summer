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
/**
 * Manager is a controller we'll be using to receive requests and send a response to the main page
 * Please see the {@link com.example.final_project.controller.command.Command} class for true identity
 */
public class Main implements Command {
    private final TariffService tariffService = new TariffService();
    private final DirectionService directionService = new DirectionService();
    private final DeliveryRequestService deliveryRequestService = new DeliveryRequestService();
    List<Tariff> tariffList = tariffService.getAllTariffs();
    List<Direction> directionList = directionService.getAllDirections();

    @Override
    public String execute(HttpServletRequest request) throws UnsupportedEncodingException {
        request.getSession().setAttribute("error", false);

        request.getSession().setAttribute("cost", 0.0);

        if (Boolean.parseBoolean(request.getParameter("form"))) {

            double weight = Double.parseDouble(request.getParameter("weight"));
            int width = Integer.parseInt(request.getParameter("width"));
            int length = Integer.parseInt(request.getParameter("length"));
            int height = Integer.parseInt(request.getParameter("height"));
            if (CommandUtility.validationForDeliveryCost(weight, width, length, height)) {
                double volume = deliveryRequestService.calculateVolume(length, height, width);
                double cost = deliveryRequestService.calculateDeliveryCost(weight, volume, request.getParameter("route"));

                request.getSession().setAttribute("cost", cost);
            } else {
                request.getSession().setAttribute("error", true);
            }
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
