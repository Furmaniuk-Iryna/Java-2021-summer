package com.example.final_project.model.service;

import org.junit.Assert;
import org.junit.Test;
import java.time.LocalDate;
import static org.hamcrest.CoreMatchers.is;

public class DeliveryRequestServiceTest {
final DeliveryRequestService deliveryRequestService = new DeliveryRequestService();

    @Test
    public void newDateOfArrivalWhenDistanceLessThan500() {
        LocalDate resultDate = deliveryRequestService.newDateOfArrival(450);
        Assert.assertEquals(LocalDate.now().plusDays(1L), resultDate);
    }

    @Test
    public void newDateOfArrivalWhenDistanceMoreThan500() {
        LocalDate resultDate = deliveryRequestService.newDateOfArrival(650);
        Assert.assertEquals(LocalDate.now().plusDays(2L), resultDate);
    }

    @Test
    public void calculateVolume() {
        Assert.assertThat(deliveryRequestService.calculateVolume(50, 60, 70), is(0.21));
    }

    @Test
    public void calculateDeliveryCostWhenPriceForWeightIsMore() {
        Assert.assertThat(deliveryRequestService.calculateDeliveryCost(110, 0.5, "Dnipro"), is(880.0));
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
}