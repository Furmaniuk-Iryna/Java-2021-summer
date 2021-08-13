package com.example.final_project.controller;

import com.example.final_project.entity.DeliveryRequest;
import com.example.final_project.entity.Receipt;
import com.example.final_project.entity.User;
import com.example.final_project.repository.AddressRepository;
import com.example.final_project.repository.DeliveryRequestRepository;
import com.example.final_project.repository.ReceiptRepository;
import com.example.final_project.service.DeliveryRequestService;
import com.example.final_project.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {
    private String paid;
    @Autowired
    private DeliveryRequestService deliveryRequestService;

    @Autowired
    private DeliveryRequestRepository deliveryRequestRepository;
    @Autowired
    private ReceiptRepository receiptRepository;
    @Autowired
    private ReceiptService receiptService;
    @Autowired
    private AddressRepository addressRepository;

    @GetMapping()
    public String userPage(@AuthenticationPrincipal User user, Model model) {
        ArrayList<DeliveryRequest> deliveryRequests = deliveryRequestRepository.findDeliveryRequestByUser(user);
        model.addAttribute("isDeliveryRequests", deliveryRequests.size() > 0 ? "true" : "false");
        model.addAttribute("deliveryRequests", deliveryRequests);
        model.addAttribute("receipts", receiptRepository.findAll());
        model.addAttribute("paid","");
        return "user";
    }

    @GetMapping("/deliveryRequests")
    public String newDeliveryRequest(Model model) {
        model.addAttribute("deliveryRequest", new DeliveryRequest());
        model.addAttribute("addresses", addressRepository.findAll());
        model.addAttribute("locale", LocaleContextHolder.getLocale().getLanguage());
        return "deliveryRequest";
    }

    @PostMapping("/deliveryRequests")
    public String readDeliveryRequest(@ModelAttribute("deliveryRequest") @Valid DeliveryRequest deliveryRequest, BindingResult bindingResult,
                                      @AuthenticationPrincipal User user, Model model) {
        model.addAttribute("addresses", addressRepository.findAll());
        if(bindingResult.hasErrors()){ return "deliveryRequest";}
        deliveryRequestService.saveDeliveryRequest(deliveryRequest, user);
        return "redirect:/users";
    }

    @GetMapping("/deliveryRequests/{id}")
    public String paymentPage(@PathVariable("id") long id,@AuthenticationPrincipal User user, Model model) {
        ArrayList<DeliveryRequest> deliveryRequests = deliveryRequestRepository.findDeliveryRequestByUser(user);
        model.addAttribute("isDeliveryRequests", deliveryRequests.size() > 0 ? "true" : "false");
        model.addAttribute("deliveryRequests", deliveryRequests);
        model.addAttribute("receipts", receiptRepository.findAll());
        paid = receiptService.pay(user, receiptRepository.findReceiptByDeliveryRequest(deliveryRequestRepository.getById(id)));
        model.addAttribute("paid",paid);
        return "user";
    }

}
