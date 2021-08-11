package com.example.final_project.controller;

import com.example.final_project.entity.DeliveryRequest;
import com.example.final_project.entity.User;
import com.example.final_project.entity.UserCard;
import com.example.final_project.repository.DeliveryRequestRepository;
import com.example.final_project.repository.ReceiptRepository;
import com.example.final_project.service.DeliveryRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private DeliveryRequestService deliveryRequestService;

    @Autowired
    private DeliveryRequestRepository deliveryRequestRepository;
    @Autowired
    private ReceiptRepository receiptRepository;

    @GetMapping()
    public String userPage(@AuthenticationPrincipal User user, Model model) {
        ArrayList<DeliveryRequest> deliveryRequests = deliveryRequestRepository.findDeliveryRequestByUser(user);
        model.addAttribute("isDeliveryRequests", deliveryRequests.size() > 0 ? "true" : "false");
        model.addAttribute("deliveryRequests", deliveryRequests);
        model.addAttribute("receipts", receiptRepository.findAll());
        return "user";
    }

    @GetMapping("/deliveryRequests")
    public String newDeliveryRequest(Model model) {
        model.addAttribute("deliveryRequest", new DeliveryRequest());
        return "deliveryRequest";
    }

    @PostMapping("/deliveryRequests")
    public String readDeliveryRequest(@ModelAttribute("deliveryRequest") DeliveryRequest deliveryRequest,
                                      @AuthenticationPrincipal User user, Model model) {
        deliveryRequestRepository.save(deliveryRequestService.fillDeliveryRequest(deliveryRequest, user));
        return "redirect:/users";
    }

    @GetMapping("/deliveryRequests/{id}")
    public String paymentPage(@PathVariable("id") long id, Model model) {
        Optional<DeliveryRequest> newDeliveryRequest = deliveryRequestRepository.findById(id);
        model.addAttribute("deliveryRequest", newDeliveryRequest);
        model.addAttribute("price", receiptRepository.findReceiptByDeliveryRequest(deliveryRequestRepository.getById(id)));
        model.addAttribute("userCard", new UserCard());
        return "payment";
    }

    @PostMapping("/deliveryRequests/{id}")
    public String payReceipt(@PathVariable("id") long id, @AuthenticationPrincipal User user, @ModelAttribute("userCard") UserCard userCard, Model model) {
        userCard.setUser(user);
        userCard.setBalance(1000);
        Optional<DeliveryRequest> newDeliveryRequest = deliveryRequestRepository.findById(id);
        model.addAttribute("deliveryRequest", newDeliveryRequest);
        model.addAttribute("price", receiptRepository.findReceiptByDeliveryRequest(deliveryRequestRepository.getById(id)));
        model.addAttribute("userCard", new UserCard());
        return "redirect:/users";
    }
}
