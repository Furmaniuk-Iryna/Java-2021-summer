package com.example.final_project.controller;

import com.example.final_project.entity.DeliveryRequest;
import com.example.final_project.entity.User;
import com.example.final_project.repository.AddressRepository;
import com.example.final_project.repository.DeliveryRequestRepository;
import com.example.final_project.repository.ReceiptRepository;
import com.example.final_project.service.DeliveryRequestService;
import com.example.final_project.service.ReceiptService;
import com.example.final_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * UserController is a controller we'll be using to receive requests and send a response to the users
 */
@Controller
@RequestMapping("/users")
public class UserController {
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
    @Autowired
    private UserService userService;

    @GetMapping()
    public String userPage(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("isDeliveryRequests", deliveryRequestRepository.findDeliveryRequestByUser(user).size() > 0 ? "true" : "false");
        model.addAttribute("deliveryRequests", deliveryRequestRepository.findDeliveryRequestByUser(user));
        model.addAttribute("receipts", receiptRepository.findAll());
        model.addAttribute("balance", userService.findUserById(user.getIdUser()).getBalance());
        model.addAttribute("paid", "");
        return "user";
    }

    @GetMapping("/recharge")
    public String userPage(@AuthenticationPrincipal User user,
                           @RequestParam(value = "sum", required = true) Integer sum) {

        userService.recharge(user, sum > 0 ? sum : 0);
        return "redirect:/users";
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
        if (bindingResult.hasErrors()) {
            model.addAttribute("addresses", addressRepository.findAll());
            return "deliveryRequest";
        }
        deliveryRequestService.saveDeliveryRequest(deliveryRequest, user);
        return "redirect:/users";
    }

    @GetMapping("/deliveryRequests/{id}")
    public String paymentPage(@PathVariable("id") long id, @AuthenticationPrincipal User user, Model model) {
        model.addAttribute("isDeliveryRequests", deliveryRequestRepository.findDeliveryRequestByUser(user).size() > 0 ? "true" : "false");
        model.addAttribute("deliveryRequests", deliveryRequestRepository.findDeliveryRequestByUser(user));
        model.addAttribute("receipts", receiptRepository.findAll());
        model.addAttribute("balance", userService.findUserById(user.getIdUser()).getBalance());
        model.addAttribute("paid", receiptService.checkPay(user, receiptRepository.findReceiptByDeliveryRequest(deliveryRequestRepository.getById(id))));
        return "user";
    }

}
