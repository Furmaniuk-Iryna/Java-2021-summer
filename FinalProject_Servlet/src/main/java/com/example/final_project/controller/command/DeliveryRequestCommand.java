package com.example.final_project.controller.command;

import com.example.final_project.model.service.AddressService;
import com.example.final_project.model.service.DirectionService;

import javax.servlet.http.HttpServletRequest;

public class DeliveryRequestCommand implements Command {
    private AddressService addressService = new AddressService();

    @Override
    public String execute(HttpServletRequest request) {

        request.setAttribute("addresses", addressService.getAllAddress());
        return "/WEB-INF/user/deliveryRequest.jsp";
    }

}
