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

@Slf4j
@Service
public class ReceiptService {

    @Autowired
    private ReceiptRepository receiptRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DeliveryCostService deliveryCostService;

    //TODO refactoring!
    @Transactional
    public Boolean pay(User user, Receipt receipt) {
        if (userRepository.findByUsername(user.getUsername()).get().getBalance() <= receipt.getPrice()) return false;

            User userFromDB = userRepository.findByUsername(user.getUsername()).get();
            userFromDB.setBalance(userFromDB.getBalance() - receipt.getPrice());
            userRepository.save(userFromDB);
            return true;
    }

    public void recharge(User user, int sum) {
        User userFromDB = userRepository.findByUsername(user.getUsername()).orElseThrow(RuntimeException::new);
        userFromDB.setBalance(userFromDB.getBalance() + sum);
        userRepository.save(userFromDB);
    }

    public void saveReceipt(DeliveryRequest newDeliveryRequest) {
        receiptRepository.save(Receipt.builder()
                .deliveryRequest(newDeliveryRequest)
                .price(deliveryCostService.calculateDeliveryCost(newDeliveryRequest.getWeight(),
                        newDeliveryRequest.getVolume(), newDeliveryRequest.getAddress().getDirection().getCityEn()))
                .status("not paid").build());
    }

    public String checkPay(User user, Receipt receipt) {
        return receipt.getStatus().equals("paid") ? "paid" : updatePaymentStatus(user,receipt);
    }

    public String updatePaymentStatus(User user, Receipt receipt){
        receipt.setStatus(pay(user, receipt) ? "paid" : "not paid");
        receiptRepository.save(receipt);
       return receipt.getStatus().equals("paid") ? "successful" : "fail";
    }

    public Receipt findReceiptById(long id){
        return receiptRepository.findReceiptById(id).orElseThrow(RuntimeException::new);
    }
}
