package com.example.final_project.model.service;

import com.example.final_project.model.dao.DaoFactory;
import com.example.final_project.model.dao.ReceiptDao;
import com.example.final_project.model.entity.Receipt;
import com.example.final_project.model.entity.User;

import java.util.List;
import java.util.Optional;

public class ReceiptService {
    DaoFactory daoFactory = DaoFactory.getInstance();
    public void saveReceipt(Receipt receipt){
        try (ReceiptDao dao = daoFactory.createReceiptDao()) {
            dao.save(receipt);
        }
    }

    public Receipt findReceiptByRequest(long idRequest){
        try (ReceiptDao dao = daoFactory.createReceiptDao()) {
            return Optional.ofNullable(dao.findById(idRequest)).orElseThrow(RuntimeException::new);
        }
    }

    public  String checkPay(User user, Receipt receipt) {
        return receipt.getStatus().equals("paid") ? "paid" : updatePaymentStatus(user,receipt);
    }

    public String updatePaymentStatus(User user, Receipt receipt){
        UserService userService= new UserService();
        try (ReceiptDao dao = daoFactory.createReceiptDao()) {
        receipt.setStatus(userService.pay(user, receipt) ? "paid" : "not paid");
        dao.update(receipt);
        return receipt.getStatus().equals("paid") ? "successful" : "fail";
    }}

    public List<Receipt> findAllReceipts(){
        try (ReceiptDao dao = daoFactory.createReceiptDao()) {
         return Optional.ofNullable(dao.findAll()).orElseThrow(RuntimeException::new);
        }
    }
}
