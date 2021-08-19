package com.example.final_project.service;

import com.example.final_project.entity.DeliveryRequest;
import com.example.final_project.entity.User;
import com.example.final_project.repository.DeliveryRequestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

import static com.example.final_project.constants.Constant.*;

@Slf4j
@Service
public class DeliveryRequestService {

    @Autowired
    private DeliveryRequestRepository deliveryRequestRepository;
    @Autowired
    private DirectionServise directionServise;
    @Autowired
    private DeliveryCostService deliveryCostService;

    public LocalDate newDateOfArrival(DeliveryRequest deliveryRequest) {
        return deliveryRequest.getAddress().getDirection().getDistance() > maxDistance
                ? LocalDate.now().plusDays(numberDaysWhenDistanceMoreMaxDistance)
                : LocalDate.now().plusDays(numberDaysWhenDistanceLessMaxDistance);
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
                .volume(deliveryCostService.calculateVolume(deliveryRequest.getLength(),deliveryRequest.getHeight(),deliveryRequest.getWidth()))
                .type_uk(deliveryRequest.getType_en().equals("Cargo") ? "Вантаж" : "Палета")
                .user(user)
                .build());
    }

    public Page<DeliveryRequest> getDirectionReport(String city, Pageable pageable){
       return deliveryRequestRepository.findAllByAddress_Direction(directionServise.getNeededDirection(city),pageable);
    }

    public Page<DeliveryRequest> getReportByDays(LocalDate date, Pageable pageable){
        Optional.ofNullable(date).orElseThrow(() -> new RuntimeException("Incorrect date"));
        return deliveryRequestRepository.findAllByDateOfArrival(date,pageable);
    }
}
