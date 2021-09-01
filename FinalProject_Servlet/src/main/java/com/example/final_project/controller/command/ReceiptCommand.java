package com.example.final_project.controller.command;

import com.example.final_project.model.entity.DeliveryRequest;
import com.example.final_project.model.entity.Receipt;
import com.example.final_project.model.service.DeliveryRequestService;
import com.example.final_project.model.service.ReceiptService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ReceiptCommand implements Command {
    private final DeliveryRequestService deliveryRequestService = new DeliveryRequestService();
    private final ReceiptService receiptService = new ReceiptService();

    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        DeliveryRequest deliveryRequest = deliveryRequestService.getDeliveryRequestById(id);

        request.setAttribute("receipts", deliveryRequest);

        double volume = deliveryRequestService.calculateVolume(deliveryRequest.getLength(), deliveryRequest.getHeight(),
                deliveryRequest.getWidth());
        double price= deliveryRequestService.calculateDeliveryCost(deliveryRequest.getWeight(),volume,deliveryRequest.getAddress().getDirection().getCityEn());

        request.setAttribute("price", price);
        receiptService.saveReceipt(new Receipt(price,"not paid",deliveryRequest));

        return "/WEB-INF/manager/receipt.jsp";
    }
}
