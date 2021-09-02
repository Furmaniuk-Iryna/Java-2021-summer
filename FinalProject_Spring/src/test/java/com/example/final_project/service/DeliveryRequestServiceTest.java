package com.example.final_project.service;

import com.example.final_project.entity.Address;
import com.example.final_project.entity.DeliveryRequest;
import com.example.final_project.entity.Direction;
import com.example.final_project.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;

import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeliveryRequestServiceTest {

    @Autowired
    private DeliveryRequestService deliveryRequestService;

    @Test
    public void newDateOfArrivalWhenDistanceLessThan500() {
        final Direction direction = new Direction(1, "Lviv", "Львів", 450);
        final Address address = new Address(1, "Lviv, Viddilennia 1", "Львів, Відділення 1", direction);
        DeliveryRequest deliveryRequest = new DeliveryRequest(1, "Cargo", "Вантаж", 100, 0.9,
                90, 100, 100, address, null, new User());
        LocalDate resultDate = deliveryRequestService.newDateOfArrival(deliveryRequest);
        Assert.assertEquals(LocalDate.now().plusDays(1L), resultDate);
    }

    @Test
    public void newDateOfArrivalWhenDistanceMoreThan500() {
        final Direction direction = new Direction(1, "Lviv", "", 650);
        final Address address = new Address(1, "Lviv, Viddilennia 1", "Львів, Відділення 1", direction);
        DeliveryRequest deliveryRequest = new DeliveryRequest(1, "Cargo", "Вантаж", 100, 0.9,
                90, 100, 100, address, null, new User());
        LocalDate resultDate = deliveryRequestService.newDateOfArrival(deliveryRequest);
        Assert.assertEquals(LocalDate.now().plusDays(2L), resultDate);
    }

    @Test
    public void calculateVolume() {
        Assert.assertThat(deliveryRequestService.calculateVolume(50, 60, 70), is(0.21));
    }

    @Test
    public void calculateDeliveryCostWhenPriceForWeightIsMore() {
        Assert.assertThat(deliveryRequestService.calculateDeliveryCost(100, 0.5, "Dnipro"), is(800.0));
    }

    @Test
    public void calculateDeliveryCostWhenPriceForVolumeIsMore() {
        Assert.assertThat(deliveryRequestService.calculateDeliveryCost(45, 0.5, "Dnipro"), is(500.0));
    }

    @Test
    public void chooseSecondTariffIfDistanceMoreThan500() {
        Assert.assertEquals(2L, deliveryRequestService.chooseTariff(45, 0.7, "Lviv").getId());
    }

    @Test
    public void chooseSecondTariffIfWeightMoreThan100() {
        Assert.assertEquals(2L, deliveryRequestService.chooseTariff(120, 0.5, "Dnipro").getId());
    }

    @Test
    public void chooseSecondTariffIfVolumeMoreThan1() {
        Assert.assertEquals(2L, deliveryRequestService.chooseTariff(49, 1.2, "Odessa").getId());
    }

    @Test
    public void chooseFirstTariff() {
        Assert.assertEquals(1L, deliveryRequestService.chooseTariff(40, 0.5, "Dnipro").getId());
    }

    @Test(expected = RuntimeException.class)
    public void getReportByDays() {
       deliveryRequestService.getReportByDays(null,PageRequest.of(0,1));
    }
}