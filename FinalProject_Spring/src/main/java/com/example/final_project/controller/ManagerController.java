package com.example.final_project.controller;

import com.example.final_project.entity.DeliveryRequest;
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

@Controller
public class ManagerController {
    private ArrayList<DeliveryRequest> deliveryRequestsByDay;
    private DeliveryRequest newDeliveryRequest;
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

    @RequestMapping("/reportByDays")
    public String reportByDays() {
        return "reportByDays";
    }

    @RequestMapping("/directionReport")
    public String directionReport() {
        return "directionReport";
    }

    @RequestMapping("/manager")
    public String admin() {
        return "manager";
    }

    @GetMapping("/manager")
    public String createManagerPage(Model model) {
        model.addAttribute("deliveryRequests", deliveryRequestRepository.findAll());
        model.addAttribute("deliveryRequest", new DeliveryRequest());
        return "manager";
    }

    @PostMapping("/manager")
    public String managerPage(Model model) {
        return "manager";
    }

    @GetMapping("/manager/directionReport")
    public String createDirectionReport(Model model) {
        //   model.addAttribute("deliveryRequests", deliveryRequestRepository.findAll());
        return "directionReport";
    }

    @GetMapping("/manager/reportByDays")
    public String createReportByDays(Model model) {
//        Integer dayForReport = 1;
//        model.addAttribute("dayForReport", dayForReport);
//        deliveryRequestsByDay = deliveryRequestService.getReportByDay(dayForReport);
//        System.out.println(deliveryRequestsByDay);
//        model.addAttribute("deliveryRequestsByDay", deliveryRequestsByDay);
        return "reportByDays";
    }
    //    @PostMapping("/manager/reportByDays")
//    public String ReportByDays (@RequestParam int dayForReport, Model model) {
//        deliveryRequestsByDay = deliveryRequestService.getReportByDay(dayForReport);
//        return "/manager";
//    }

    @GetMapping("/manager/receipt={id}")
    public String createReceipt(@PathVariable("id") long id, Model model) {
        model.addAttribute("deliveryRequest", deliveryRequestRepository.findById(id));
        return "receipt";
    }
}
