package com.example.final_project.service;

import com.example.final_project.entity.DeliveryRequest;
import com.example.final_project.entity.User;
import com.example.final_project.repository.DeliveryRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class DeliveryRequestService {
    private final int centimetersToCubicMeters = 1000000; // винести в константи + 500, 2,1

    @Autowired
    private DeliveryRequestRepository deliveryRequestRepository;

    public LocalDate newDateOfArrival(DeliveryRequest deliveryRequest) {
        return deliveryRequest.getDirection().getDistance() > 500
                ? LocalDate.now().plusDays(2)
                : LocalDate.now().plusDays(1);
    }

    public double calculateVolumeForDeliveryRequest(DeliveryRequest deliveryRequest) {
        return deliveryRequest.getLength() * deliveryRequest.getWidth() * deliveryRequest.getHeight() / centimetersToCubicMeters;
    }

    public DeliveryRequest fillDeliveryRequest(DeliveryRequest deliveryRequest, User user) {
        deliveryRequest.setDateOfArrival(newDateOfArrival(deliveryRequest));
        deliveryRequest.setVolume(calculateVolumeForDeliveryRequest(deliveryRequest));
        deliveryRequest.setType_uk(deliveryRequest.getType_en().equals("Cargo") ? "Вантаж" : "Палета");
        deliveryRequest.setUser(user);
        return deliveryRequest;
    }

    public ArrayList<DeliveryRequest> getReportByDay(LocalDate dayFromForm) {
        return (ArrayList<DeliveryRequest>) deliveryRequestRepository.findAll()
                .stream()
                .filter(deliveryRequest -> (deliveryRequest.getDateOfArrival()).isEqual(dayFromForm))
                .collect(Collectors.toList());
    }
    public ArrayList<DeliveryRequest> getDirectionReport(String city) {
        return (ArrayList<DeliveryRequest>) deliveryRequestRepository.findAll()
                .stream()
                .filter(deliveryRequest -> (deliveryRequest.getDirection().getCity_en().toLowerCase().equals(city.toLowerCase())
                        || deliveryRequest.getDirection().getCity_uk().toLowerCase().equals(city.toLowerCase())))
                .collect(Collectors.toList());
    }
}
