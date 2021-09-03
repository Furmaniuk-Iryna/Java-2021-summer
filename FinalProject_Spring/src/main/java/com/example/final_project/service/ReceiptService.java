package com.example.final_project.service;

import com.example.final_project.entity.DeliveryRequest;
import com.example.final_project.entity.Receipt;
import com.example.final_project.entity.User;
import com.example.final_project.repository.ReceiptRepository;
import com.example.final_project.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * ReceiptService is a service we'll be using to form response and
 * where there is all the business logic for the essence Receipt
 */
@Slf4j
@Service
public class ReceiptService {

    @Autowired
    private ReceiptRepository receiptRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private DeliveryRequestService deliveryRequestService;

    public void saveReceipt(DeliveryRequest newDeliveryRequest) {
        log.info("Service: Save receipt with delivery request {}", newDeliveryRequest);
        receiptRepository.save(Receipt.builder()
                .deliveryRequest(newDeliveryRequest)
                .price(deliveryRequestService.calculateDeliveryCost(newDeliveryRequest.getWeight(),
                        newDeliveryRequest.getVolume(), newDeliveryRequest.getAddress().getDirection().getCityEn()))
                .status("not paid").build());
    }

    public String checkPay(User user, Receipt receipt) {
        log.info("Service: CheckPay with user id {}, receipt {}", user.getIdUser(), receipt);
        return receipt.getStatus().equals("paid") ? "paid" : updatePaymentStatus(user,receipt);
    }

    public String updatePaymentStatus(User user, Receipt receipt){
        receipt.setStatus(userService.pay(user, receipt) ? "paid" : "not paid");
        receiptRepository.save(receipt);
       return receipt.getStatus().equals("paid") ? "successful" : "fail";
    }

}
