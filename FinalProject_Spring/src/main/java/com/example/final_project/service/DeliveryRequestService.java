package com.example.final_project.service;

import com.example.final_project.entity.DeliveryRequest;
import com.example.final_project.entity.Tariff;
import com.example.final_project.entity.User;
import com.example.final_project.repository.DeliveryRequestRepository;
import com.example.final_project.repository.TariffRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.example.final_project.constants.Constant.*;

/**
 * DeliveryRequestService is service we'll be using to form response and
 * where there is all the business logic for the essence Delivery Request
 */
@Slf4j
@Service
public class DeliveryRequestService {
    @Autowired
    private DeliveryRequestRepository deliveryRequestRepository;
    @Autowired
    private DirectionServise directionServise;
    @Autowired
    private TariffRepository tariffRepository;


    public LocalDate newDateOfArrival(DeliveryRequest deliveryRequest) {
        log.info("Service: newDateOfArrival with request {}", deliveryRequest);
        return deliveryRequest.getAddress().getDirection().getDistance() > maxDistance
                ? LocalDate.now().plusDays(numberDaysWhenDistanceMoreMaxDistance)
                : LocalDate.now().plusDays(numberDaysWhenDistanceLessMaxDistance);
    }

    public void saveDeliveryRequest(DeliveryRequest deliveryRequest, User user) {
        log.info("Service: saveDeliveryRequest with request - {}, user id -  {}", deliveryRequest, user.getIdUser());
        deliveryRequestRepository.save(DeliveryRequest.builder()
                .type_en(deliveryRequest.getType_en())
                .weight(deliveryRequest.getWeight())
                .length(deliveryRequest.getLength())
                .width(deliveryRequest.getWidth())
                .height(deliveryRequest.getHeight())
                .address(deliveryRequest.getAddress())
                .dateOfArrival(newDateOfArrival(deliveryRequest))
                .volume(calculateVolume(deliveryRequest.getLength(), deliveryRequest.getHeight(), deliveryRequest.getWidth()))
                .type_uk(deliveryRequest.getType_en().equals("Cargo") ? "Вантаж" : "Палета")
                .user(user)
                .build());
    }

    public Page<DeliveryRequest> getDirectionReport(String city, Pageable pageable) {
        log.info("Service: getDirectionReport with city {}", city);
        return deliveryRequestRepository.findAllByAddress_Direction(directionServise.getNeededDirection(city), pageable);
    }

    public Page<DeliveryRequest> getReportByDays(LocalDate date, Pageable pageable) {
        log.info("Service: getReportByDays with date {}", date);
        Optional.ofNullable(date).orElseThrow(() -> new RuntimeException("Incorrect date"));
        return deliveryRequestRepository.findAllByDateOfArrival(date, pageable);
    }

    public double calculateVolume(int length, int height, int width) {
        log.info("Service: getDirectionReport with length {}, height {}, width{}", length,height,width);
        return (double) length * width * height / centimetersToCubicMeters;
    }

    public double calculateDeliveryCost(double weight, double volume, String city) {
        log.info("Service: calculateDeliveryCost with weight {}, volume {}, city{}", weight, volume,city);
      Tariff tariff = chooseTariff(weight, volume, city);
        return Math.max(weight * tariff.getTariffForWeight(), volume * tariff.getTariffForVolume());
    }


    public Tariff chooseTariff(double weight, double volume, String city) {
     return tariffRepository.findTariffById(weight > maxWeight || volume > maxVolume
                || directionServise.getNeededDirection(city).getDistance() > maxDistance ? secondTariffId : firstTariffId);
    }

    public List<DeliveryRequest> findAll (){
      return deliveryRequestRepository.findAll();
    }

    public Optional<DeliveryRequest> findById(Long id){
      return deliveryRequestRepository.findById(id);
    }

    public void delete(DeliveryRequest deliveryRequest){
        deliveryRequestRepository.delete(deliveryRequest);
    }

    public <S extends DeliveryRequest> S save(S s){
        return deliveryRequestRepository.save(s);
    }
}
