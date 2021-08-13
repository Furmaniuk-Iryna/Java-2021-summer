package com.example.final_project.controller;

import com.example.final_project.entity.DeliveryRequest;
import com.example.final_project.entity.Direction;
import com.example.final_project.entity.Receipt;
import com.example.final_project.repository.DeliveryRequestRepository;
import com.example.final_project.repository.ReceiptRepository;
import com.example.final_project.service.DeliveryCostService;
import com.example.final_project.service.DeliveryRequestService;
import com.example.final_project.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/deliveryRequests")
public class DeliveryRequestController {

    @Autowired
    private DeliveryRequestRepository deliveryRequestRepository;
    @Autowired
    private DeliveryRequestService deliveryRequestService;
    @Autowired
    private DeliveryCostService deliveryCostService;
    @Autowired
    private ReceiptRepository receiptRepository;
    @Autowired
    private ReceiptService receiptService;

    @GetMapping()
    public String createManagerPage(Model model) {
        model.addAttribute("deliveryRequests", deliveryRequestRepository.findAll());
        model.addAttribute("deliveryRequest", new DeliveryRequest());
        model.addAttribute("direction", new Direction());
        return "manager";
    }

    @PostMapping("/directionReports")
    public String createDirectionReport(@ModelAttribute("direction") Direction direction, Model model) {
        model.addAttribute("directionReports",deliveryRequestService.getDirectionReport(direction.getCity_en()));
        return "directionReport";
    }

    @GetMapping("/directionReports")
    public String DirectionReport(Model model) {
        return "directionReport";
    }

    @GetMapping("/reportsByDays")
    public String createReportByDays(Model model) {
        return "reportByDays";
    }


    @PostMapping("/reportsByDays")
    public String ReportByDays(@ModelAttribute("deliveryRequest") DeliveryRequest deliveryRequest, Model model) {
        model.addAttribute("deliveryRequestsByDay", deliveryRequestService.getReportByDay(deliveryRequest.getDateOfArrival()));
        return "reportByDays";
    }

    @GetMapping("/{id}")
    public String createReceipt(@PathVariable("id") long id, Receipt receipt, Model model) {
        Optional<DeliveryRequest> newDeliveryRequest = deliveryRequestRepository.findById(id);
        model.addAttribute("deliveryRequest", newDeliveryRequest);
        double price = deliveryCostService.calculateDeliveryCost(newDeliveryRequest.get().getWeight(),
                newDeliveryRequest.get().getVolume(), newDeliveryRequest.get().getAddress().getDirection().getCity_en());
        model.addAttribute("price", price);
        receipt.setDeliveryRequest(newDeliveryRequest.get());
        receipt.setPrice(price);
        receipt.setStatus("not paid");
        receiptRepository.save(receipt);
        return "receipt";
    }

}
