package com.example.final_project.controller.command;

import com.example.final_project.model.service.DeliveryRequestService;

import javax.servlet.http.HttpServletRequest;

public class DeliveryRequestCommand implements Command {
    private DeliveryRequestService deliveryRequestService;

    @Override
    public String execute(HttpServletRequest request) {

        String type_en = request.getParameter("typeEn");

        if (type_en == null || type_en.isEmpty()) {
            //TODO validation
            return "/WEB-INF/user/deliveryRequest.jsp";
        }

        Double weight = Double.valueOf(request.getParameter("weight"));
        Integer width = Integer.valueOf(request.getParameter("width"));
        Integer length = Integer.valueOf(request.getParameter("length"));
        Integer height = Integer.valueOf(request.getParameter("height"));
        String address = request.getParameter("address");
        String user = (String) request.getSession().getAttribute("user");
        //TODO service
        //   deliveryRequestService.saveDeliveryRequest(new DeliveryRequest());
        return "redirect:/user";
    }

}
