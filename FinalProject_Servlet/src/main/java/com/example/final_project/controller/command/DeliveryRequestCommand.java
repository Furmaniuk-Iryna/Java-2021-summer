package com.example.final_project.controller.command;

import com.example.final_project.model.entity.Address;
import com.example.final_project.model.entity.DeliveryRequest;
import com.example.final_project.model.service.AddressService;
import com.example.final_project.model.service.DeliveryRequestService;
import com.example.final_project.model.service.TariffService;
import com.example.final_project.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class DeliveryRequestCommand implements Command {
    private final AddressService addressService = new AddressService();
    private final DeliveryRequestService deliveryRequestService = new DeliveryRequestService();
    private final TariffService tariffService = new TariffService();
    private final UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request) {

        String type_en = request.getParameter("typeEn");
        if (type_en == null) {
            request.setAttribute("addresses", addressService.getAllAddress());
            return "/WEB-INF/user/deliveryRequest.jsp";
        }

        double weight = Double.parseDouble(request.getParameter("weight"));
        int width = Integer.parseInt(request.getParameter("width"));
        int length = Integer.parseInt(request.getParameter("length"));
        int height = Integer.parseInt(request.getParameter("height"));
        Address address = addressService.getAddressById(Long.parseLong(request.getParameter("address")));
        //TODO validation
        double volume = deliveryRequestService.calculateVolume(length, height, width);

        deliveryRequestService.saveDeliveryRequest(
                new DeliveryRequest(
                        deliveryRequestService.newDateOfArrival(address.getDirection().getDistance()),
                        type_en,
                        type_en.equals("Cargo") ? "Вантаж" : "Палета",
                        volume,
                        weight,
                        address,
                        userService.getUserByUsername((String) request.getSession().getAttribute("userName")),
                        tariffService.chooseTariff(weight, volume, address.getDirection().getCityEn())
                ));

        return "redirect:/user";
    }

}
