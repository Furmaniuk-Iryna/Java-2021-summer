package com.example.final_project.service;

import com.example.final_project.entity.DeliveryRequest;
import com.example.final_project.entity.Direction;
import com.example.final_project.entity.User;
import com.example.final_project.repository.DeliveryRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeliveryRequestService {
    private final int centimetersToCubicMeters = 1000000; // винести в константи + 500, 2,1

    @Autowired
    private DeliveryRequestRepository deliveryRequestRepository;
    @Autowired
    private DirectionServise directionServise;

    public LocalDate newDateOfArrival(DeliveryRequest deliveryRequest) {
        return deliveryRequest.getAddress().getDirection().getDistance() > 500
                ? LocalDate.now().plusDays(2)
                : LocalDate.now().plusDays(1);
    }

    public double calculateVolumeForDeliveryRequest(DeliveryRequest deliveryRequest) {
        return (double) deliveryRequest.getLength() * deliveryRequest.getWidth() * deliveryRequest.getHeight() / centimetersToCubicMeters;
    }

    public void saveDeliveryRequest(DeliveryRequest deliveryRequest, User user) {
        deliveryRequestRepository.save(DeliveryRequest.builder()
                .type_en(deliveryRequest.getType_en())
                .weight(deliveryRequest.getWeight())
                .length(deliveryRequest.getLength())
                .width(deliveryRequest.getWidth())
                .height(deliveryRequest.getHeight())
                .address(deliveryRequest.getAddress())
                .dateOfArrival(newDateOfArrival(deliveryRequest))
                .volume(calculateVolumeForDeliveryRequest(deliveryRequest))
                .type_uk(deliveryRequest.getType_en().equals("Cargo") ? "Вантаж" : "Палета")
                .user(user)
                .build());
    }

    public List<DeliveryRequest> getReportByDay(LocalDate dayFromForm) {
        return deliveryRequestRepository.findAll()
                .stream()
                .filter(deliveryRequest -> (deliveryRequest.getDateOfArrival()).isEqual(dayFromForm))
                .collect(Collectors.toList());
    }


public Page<DeliveryRequest> getDirectionReport(String city, Pageable pageable){
   return deliveryRequestRepository.findAllByAddress_Direction(directionServise.getNeededDirection(city),pageable);
}
    public Page<DeliveryRequest> getReportByDays(LocalDate date, Pageable pageable){
        return deliveryRequestRepository.findAllByDateOfArrival(date,pageable);
    }
}
