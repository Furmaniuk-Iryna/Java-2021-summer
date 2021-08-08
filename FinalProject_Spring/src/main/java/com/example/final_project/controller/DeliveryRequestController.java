package com.example.final_project.controller;

import com.example.final_project.entity.DeliveryRequest;
import com.example.final_project.entity.Direction;
import com.example.final_project.repository.DeliveryRequestRepository;
import com.example.final_project.repository.ReceiptRepository;
import com.example.final_project.service.DeliveryCostService;
import com.example.final_project.service.DeliveryRequestService;
import com.example.final_project.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;

@Controller
@RequestMapping("/deliveryRequests")
public class DeliveryRequestController {
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

    @GetMapping()
    public String createManagerPage(Model model) {
        model.addAttribute("deliveryRequests", deliveryRequestRepository.findAll());
        model.addAttribute("deliveryRequest", new DeliveryRequest());
        model.addAttribute("direction", new Direction());
        return "manager";
    }

//    @PostMapping()
//    public String managerPage(Model model) {
//        return "manager";
//    }

    @PostMapping("/directionReports")
    public String createDirectionReport(@ModelAttribute("direction") Direction direction, Model model) {
        //   model.addAttribute("deliveryRequests", deliveryRequestRepository.findAll());
        model.addAttribute("directionReports",deliveryRequestService.getDirectionReport(direction.getCity_en()));
        return "directionReport";
    }
    @GetMapping("/directionReports")
    public String DirectionReport( Model model) {
        //   model.addAttribute("deliveryRequests", deliveryRequestRepository.findAll());
        return "directionReport";
    }

    @GetMapping("/reportsByDays")
    public String createReportByDays( Model model) {

        //        Integer dayForReport = 1;
//        model.addAttribute("dayForReport", dayForReport);
//        deliveryRequestsByDay = deliveryRequestService.getReportByDay(dayForReport);
//        System.out.println(deliveryRequestsByDay);
//        model.addAttribute("deliveryRequestsByDay", deliveryRequestsByDay);
        return "reportByDays";
    }
//    @GetMapping("/reportsByDays/{day}")
//    public String ReportByDays(Model model) {
////        Integer dayForReport = 1;
////        model.addAttribute("dayForReport", dayForReport);
////        deliveryRequestsByDay = deliveryRequestService.getReportByDay(dayForReport);
////        System.out.println(deliveryRequestsByDay);
////        model.addAttribute("deliveryRequestsByDay", deliveryRequestsByDay);
//        return "reportByDays";
//    }

//        @PostMapping("/reportsByDays")
//    public String ReportByDays (@ModelAttribute D, Model model) {
//            System.out.println("----------------------"+date);
//      //  deliveryRequestsByDay = deliveryRequestService.getReportByDay(dayForReport);
//        return "/manager";
//    }

    @GetMapping("/{id}")
    public String createReceipt(@PathVariable("id") long id, Model model) {
        model.addAttribute("deliveryRequest", deliveryRequestRepository.findById(id));
        return "receipt";
    }
}
