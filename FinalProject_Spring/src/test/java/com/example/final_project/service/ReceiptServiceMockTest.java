package com.example.final_project.service;

import com.example.final_project.entity.*;
import com.example.final_project.repository.ReceiptRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.any;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ReceiptServiceMockTest {
    @Mock
    private ReceiptRepository receiptRepository;
    @Mock
    private UserService userService;
    @Mock
    private DeliveryRequestService deliveryRequestService;
    @InjectMocks
    ReceiptService receiptService;

    @Test
    public void saveReceipt() {
        final Direction direction = new Direction(1, "Lviv", "Львів", 450);
        final Address address = new Address(1, "Lviv, Viddilennia 1", "Львів, Відділення 1", direction);
        DeliveryRequest deliveryRequest = new DeliveryRequest(0, "Cargo", "", 150, 1.5, 100, 150, 100, address, null, new User());
        Mockito.when(deliveryRequestService.calculateDeliveryCost(150, 1.5, "Lviv")).thenReturn(1000.0);
        receiptService.saveReceipt(deliveryRequest);
        Mockito.verify(receiptRepository, Mockito.times(1)).save(Mockito.any(Receipt.class));

    }

    @Test
    public void updatePaymentStatus() {
        Mockito.when(userService.pay(Mockito.any(User.class), Mockito.any(Receipt.class))).thenReturn(true);
        String result = receiptService.updatePaymentStatus(new User(), new Receipt());
        Mockito.verify(receiptRepository, Mockito.times(1)).save(Mockito.any(Receipt.class));
        Assert.assertEquals("successful", result);
    }


    @Test
    public void checkPay() {
        Receipt receipt = new Receipt();
        receipt.setStatus("paid");
        String result = receiptService.checkPay(new User(), receipt);
        Assert.assertEquals("paid", result);

    }
}