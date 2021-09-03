package com.example.final_project.controller;

import com.example.final_project.entity.DeliveryRequest;
import com.example.final_project.entity.Direction;
import com.example.final_project.repository.DeliveryRequestRepository;
import com.example.final_project.repository.ReceiptRepository;
import com.example.final_project.service.DeliveryRequestService;
import com.example.final_project.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.stream.IntStream;

/**
 * DeliveryRequestController is a controller we'll be using to receive requests and send a response to the manager
 */
@Controller
@RequestMapping("/deliveryRequests")
public class DeliveryRequestController {

    @Autowired
    private DeliveryRequestRepository deliveryRequestRepository;
    @Autowired
    private DeliveryRequestService deliveryRequestService;
    @Autowired
    private ReceiptRepository receiptRepository;
    @Autowired
    private ReceiptService receiptService;

    @GetMapping()
    public String createManagerPage(@RequestParam(value = "page",required = false, defaultValue = "0") Integer page,
                                    Model model) {
        Page<DeliveryRequest> deliveryRequestPage=deliveryRequestRepository.findDeliveryRequests(PageRequest.of(page, 3));
        model.addAttribute("deliveryRequests", deliveryRequestPage.getContent());
        model.addAttribute("pages", deliveryRequestPage);
        model.addAttribute("numbers", IntStream.range(0,deliveryRequestPage.getTotalPages()).toArray());
        model.addAttribute("deliveryRequest", new DeliveryRequest());
        model.addAttribute("direction", new Direction());
        return "manager";
    }


    @GetMapping("/directionReports")
    public String DirectionReport(Model model,
                                  @RequestParam(value = "city", required = false) String city,
                                  @RequestParam(value = "page", required = false, defaultValue = "0") Integer page) {
        Page<DeliveryRequest> deliveryRequestPage = deliveryRequestService.getDirectionReport(city, PageRequest.of(page, 2));
        model.addAttribute("directionReports", deliveryRequestPage.getContent());
        model.addAttribute("pages", deliveryRequestPage);
        model.addAttribute("numbers", IntStream.range(0, deliveryRequestPage.getTotalPages()).toArray());
        model.addAttribute("city", city);
        return "directionReport";
    }

    @GetMapping("/reportsByDays")
    public String createReportByDays(Model model,
                                     @RequestParam(value = "day", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate day,
                                     @RequestParam(value = "page", required = false, defaultValue = "0") Integer page) {
        Page<DeliveryRequest> deliveryRequestPage = deliveryRequestService.getReportByDays(day, PageRequest.of(page, 2));
        model.addAttribute("deliveryRequestsByDay", deliveryRequestPage.getContent());
        model.addAttribute("pages", deliveryRequestPage);
        model.addAttribute("numbers", IntStream.range(0, deliveryRequestPage.getTotalPages()).toArray());
        model.addAttribute("day", day);
        return "reportByDays";
    }

    @GetMapping("/{id}")
    public String createReceipt(@PathVariable("id") long id,Model model) {
        DeliveryRequest newDeliveryRequest = deliveryRequestRepository.findById(id).orElseThrow(RuntimeException::new);
        model.addAttribute("deliveryRequest", newDeliveryRequest);
        receiptService.saveReceipt(newDeliveryRequest);
        model.addAttribute("price", receiptRepository.findReceiptByDeliveryRequest(newDeliveryRequest).getPrice());
        return "receipt";
    }

}
