package com.example.final_project.controller.command;

import com.example.final_project.model.entity.Address;
import com.example.final_project.model.entity.DeliveryRequest;
import com.example.final_project.model.service.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class DeliveryRequestSave implements Command{
    private DirectionService directionService= new DirectionService();
    private DeliveryRequestService deliveryRequestService= new DeliveryRequestService();
    private TariffService tariffService = new TariffService();
    private AddressService addressService = new AddressService();
    private UserService userService = new UserService();
    @Override
    public synchronized String execute(HttpServletRequest request) throws ServletException, IOException {
        String type_en = request.getParameter("typeEn");
        double weight = Double.parseDouble(request.getParameter("weight"));
        int width = Integer.parseInt(request.getParameter("width"));
        int length = Integer.parseInt(request.getParameter("length"));
        int height = Integer.parseInt(request.getParameter("height"));
        Address address=addressService.getAddressById( Long.parseLong(request.getParameter("address")));
        //TODO validation
        double volume=deliveryRequestService.calculateVolume(length,height,width);

          deliveryRequestService.saveDeliveryRequest(
                  new DeliveryRequest(
                          deliveryRequestService.newDateOfArrival(address.getDirection().getDistance()),
                          type_en,
                          type_en.equals("Cargo") ? "Вантаж" : "Палета",
                          volume,
                          weight,
                          address,
                          userService.getUserByUsername((String) request.getSession().getAttribute("userName")),
                          tariffService.chooseTariff(weight,volume,address.getDirection().getCityEn())));

        return "redirect:/user";
    }
}
