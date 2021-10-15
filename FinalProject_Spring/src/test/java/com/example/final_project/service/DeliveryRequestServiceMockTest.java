package com.example.final_project.service;

import com.example.final_project.entity.*;
import com.example.final_project.repository.DeliveryRequestRepository;
import com.example.final_project.repository.TariffRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

import static org.hamcrest.Matchers.is;

@RunWith(MockitoJUnitRunner.class)
public class
DeliveryRequestServiceMockTest {

    @Mock
    DeliveryRequestRepository deliveryRequestRepository;

    @Mock
    TariffRepository tariffRepository;

    @Mock
    DirectionServise directionServise;

    @InjectMocks
    DeliveryRequestService deliveryRequestService;


    @Test
    public void chooseTariff() {
        deliveryRequestService.chooseTariff(110.0, 1.2, "Lviv");
        Mockito.verify(tariffRepository, Mockito.times(1)).findTariffById(2L);
    }

    @Test
    public void saveDeliveryRequest() {
        final Direction direction = new Direction(1, "Lviv", "Львів", 450);
        final Address address = new Address(1, "Lviv, Viddilennia 1", "Львів, Відділення 1", direction);
        DeliveryRequest deliveryRequest = new DeliveryRequest();
        deliveryRequest.setType_en("Cargo");
        deliveryRequest.setHeight(100);
        deliveryRequest.setLength(120);
        deliveryRequest.setWeight(150);
        deliveryRequest.setWidth(100);
        deliveryRequest.setAddress(address);
        User user = User.builder().idUser(1L).username("username").password("12345").name("name").surname("surname").balance(0.0).active(true).roles(null).build();

        deliveryRequestService.saveDeliveryRequest(deliveryRequest, user);
        Mockito.verify(deliveryRequestRepository, Mockito.times(1)).save(Mockito.any(DeliveryRequest.class));

    }

    @Test
    public void calculateDeliveryCost() {
        Tariff tariff = new Tariff(1L, "first", "", 8, 1200, "desc", " ");
        Mockito.when(deliveryRequestService.chooseTariff(120.0, 1.2, "Lviv")).thenReturn(tariff);
        double result = deliveryRequestService.calculateDeliveryCost(120.0, 1.2, "Lviv");
        Assert.assertThat(result, is(1440.0));
    }

    @Test
    public void getDirectionReport() {
        Pageable pageable = PageRequest.of(1, 3);
        Direction direction = new Direction(1, "Lviv", "Львів", 450);
        Mockito.when(directionServise.getNeededDirection("Lviv")).thenReturn(direction);
        deliveryRequestService.getDirectionReport("Lviv", pageable);
        Mockito.verify(deliveryRequestRepository, Mockito.times(1))
                .findAllByAddress_Direction(direction, pageable);
    }

    @Test
    public void getReportByDays() {
        Pageable pageable = PageRequest.of(1, 3);
        deliveryRequestService.getReportByDays(LocalDate.of(2021, 8, 17), pageable);
        Mockito.verify(deliveryRequestRepository, Mockito.times(1))
                .findAllByDateOfArrival(LocalDate.of(2021, 8, 17), pageable);

    }

    @Test
    public void newDateOfArrival() {
        final Direction direction = new Direction(1, "Lviv", "Львів", 450);
        final Address address = new Address(1, "Lviv, Viddilennia 1", "Львів, Відділення 1", direction);
        DeliveryRequest deliveryRequest = new DeliveryRequest();
        deliveryRequest.setAddress(address);

        Assert.assertThat(deliveryRequestService.newDateOfArrival(deliveryRequest), is(LocalDate.of(2021, 10, 15)));
    }

    @Test
    public void calculateVolume() {
        double result = deliveryRequestService.calculateVolume(120, 100, 100);
        Assert.assertThat(result, is(1.2));
    }

    @Test
    public void save() {
        deliveryRequestService.save(new DeliveryRequest());
        Mockito.verify(deliveryRequestRepository, Mockito.times(1)).save(Mockito.any(DeliveryRequest.class));
    }

    @Test
    public void delete() {
        deliveryRequestService.delete(new DeliveryRequest());
        Mockito.verify(deliveryRequestRepository, Mockito.times(1)).delete(Mockito.any(DeliveryRequest.class));
    }

    @Test
    public void findById() {
        deliveryRequestService.findById(1L);
        Mockito.verify(deliveryRequestRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    public void findAll() {
        deliveryRequestService.findAll();
        Mockito.verify(deliveryRequestRepository, Mockito.times(1)).findAll();
    }
}
