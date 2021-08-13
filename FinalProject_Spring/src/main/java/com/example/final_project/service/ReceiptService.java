package com.example.final_project.service;

import com.example.final_project.entity.Receipt;
import com.example.final_project.entity.User;
import com.example.final_project.repository.DeliveryRequestRepository;
import com.example.final_project.repository.ReceiptRepository;
import com.example.final_project.repository.TariffRepository;
import com.example.final_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ReceiptService {

    @Autowired
    private ReceiptRepository receiptRepository;
    @Autowired
    private DirectionServise directionServise;
    @Autowired
    private DeliveryRequestRepository deliveryRequestRepository;
    @Autowired
    private TariffRepository tariffRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DeliveryCostService deliveryCostService;

   @Transactional
 public String pay(User user, Receipt receipt){
   if(userRepository.findByUsername(user.getUsername()).getBalance() >= receipt.getPrice())
   {userRepository.findByUsername(user.getUsername()).setBalance(user.getBalance()-receipt.getPrice());
 return "successful";}
   else return "fail";
   }

 public void recharge (User user,  double sum){
       userRepository.findByUsername(user.getUsername()).setBalance(user.getBalance()+sum);
 }

}
