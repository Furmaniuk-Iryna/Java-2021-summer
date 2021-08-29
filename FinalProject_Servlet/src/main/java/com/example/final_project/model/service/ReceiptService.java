package com.example.final_project.model.service;

import com.example.final_project.model.dao.DaoFactory;
import com.example.final_project.model.dao.ReceiptDao;
import com.example.final_project.model.entity.Receipt;
import com.example.final_project.model.entity.User;

public class ReceiptService {
    DaoFactory daoFactory = DaoFactory.getInstance();
    public void saveReceipt(Receipt receipt){
        try (ReceiptDao dao = daoFactory.createReceiptDao()) {
            dao.save(receipt);
        }
    }

    public String checkPay(User user, Receipt receipt) {
        return receipt.getStatus().equals("paid") ? "paid" : updatePaymentStatus(user,receipt);
    }

    public String updatePaymentStatus(User user, Receipt receipt){
        UserService userService= new UserService();
        try (ReceiptDao dao = daoFactory.createReceiptDao()) {
        receipt.setStatus(userService.pay(user, receipt) ? "paid" : "not paid");
        dao.save(receipt);
        return receipt.getStatus().equals("paid") ? "successful" : "fail";
    }}
}
