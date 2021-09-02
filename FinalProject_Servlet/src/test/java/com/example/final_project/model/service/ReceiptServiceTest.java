package com.example.final_project.model.service;

import com.example.final_project.model.entity.Receipt;
import com.example.final_project.model.entity.User;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReceiptServiceTest {
    private final ReceiptService receiptService=new ReceiptService();


    @Test
    public void checkPayWhenStatusIsPaid() {
        Receipt receipt = new Receipt();
        receipt.setStatus("paid");
        Assert.assertEquals("paid", receiptService.checkPay(new User(), receipt));
    }

}