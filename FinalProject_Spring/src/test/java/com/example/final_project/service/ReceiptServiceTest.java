package com.example.final_project.service;

import com.example.final_project.entity.Receipt;
import com.example.final_project.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.NoSuchElementException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReceiptServiceTest {

    @Autowired
    private ReceiptService receiptService;


    @Test
    public void checkPayWhenStatusIsPaid() {
        Receipt receipt = new Receipt();
        receipt.setStatus("paid");
        Assert.assertEquals("paid", receiptService.checkPay(new User(), receipt));
    }
}