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
        Integer width = Integer.valueOf(request.getParameter("width"));
        Integer length = Integer.valueOf(request.getParameter("length"));
        Integer height = Integer.valueOf(request.getParameter("height"));
        Long id_address = Long.parseLong(request.getParameter("address"));
        String user = (String) request.getSession().getAttribute("userName");

        Address address=addressService.getAddressById(id_address);
        double volume=deliveryRequestService.calculateVolume(length,height,width);
          deliveryRequestService.saveDeliveryRequest(
                  new DeliveryRequest(
                          deliveryRequestService.newDateOfArrival(address.getDirection().getDistance()),
                          type_en,
                          type_en.equals("Cargo") ? "Вантаж" : "Палета",
                          volume,
                          weight,
                          address,
                          userService.getUserByUsername(user),
                          tariffService.chooseTariff(weight,volume,address.getDirection().getCityEn())));

        return "redirect:/user";
    }
}
