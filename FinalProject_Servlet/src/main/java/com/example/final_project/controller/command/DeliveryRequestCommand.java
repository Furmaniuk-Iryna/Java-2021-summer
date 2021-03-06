package com.example.final_project.controller.command;

import com.example.final_project.model.entity.Address;
import com.example.final_project.model.entity.DeliveryRequest;
import com.example.final_project.model.service.AddressService;
import com.example.final_project.model.service.DeliveryRequestService;
import com.example.final_project.model.service.TariffService;
import com.example.final_project.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
/**
 * DeliveryRequestCommand is a controller we'll be using to receive requests and send a response for create delivery request
 * Please see the {@link com.example.final_project.controller.command.Command} class for true identity
 */
public class DeliveryRequestCommand implements Command {
    private final AddressService addressService = new AddressService();
    private final DeliveryRequestService deliveryRequestService = new DeliveryRequestService();
    private final TariffService tariffService = new TariffService();
    private final UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().setAttribute("error", false);
        request.getSession().setAttribute("save", false);
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

        if (!CommandUtility.validationForDeliveryRequest(weight, width, length, height, address)) {
            request.getSession().setAttribute("error", true);
        } else {

            double volume = deliveryRequestService.calculateVolume(length, height, width);

            deliveryRequestService.saveDeliveryRequest(
                    new DeliveryRequest(
                            deliveryRequestService.newDateOfArrival(address.getDirection().getDistance()),
                            type_en,
                            type_en.equals("Cargo") ? "????????????" : "????????????",
                            volume,
                            weight,
                            address,
                            userService.getUserByUsername((String) request.getSession().getAttribute("userName")),
                            deliveryRequestService.chooseTariff(weight, volume, address.getDirection().getCityEn())
                    ));
            request.getSession().setAttribute("save", true);

        }
        return "/WEB-INF/user/deliveryRequest.jsp";
    }
}