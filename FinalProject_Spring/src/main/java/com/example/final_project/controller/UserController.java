package com.example.final_project.controller;

import com.example.final_project.entity.DeliveryRequest;
import com.example.final_project.entity.User;
import com.example.final_project.repository.DeliveryRequestRepository;
import com.example.final_project.service.DeliveryRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private DeliveryRequestService deliveryRequestService;

    @Autowired
    private DeliveryRequestRepository deliveryRequestRepository;

    @GetMapping()
    public String userPage() {
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
}
