package com.example.final_project.service;

import com.example.final_project.entity.DeliveryCost;
import com.example.final_project.repository.TariffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryCostService {
    private final int centimetersToCubicMeters = 1000000; // винести в константи
    private double forWeight;
    private double forVolume;

    @Autowired
    private TariffRepository tariffRepository;

    @Autowired
    private DirectionServise directionServise;

    public double calculateVolumeForDeliveryCosts(DeliveryCost deliveryCost) {
        return (double) deliveryCost.getLength() * deliveryCost.getWidth() * deliveryCost.getHeight() / centimetersToCubicMeters;
    }

    public double calculateDeliveryCost(DeliveryCost deliveryCost) {
        //TODO validation
        deliveryCost.setVolume(calculateVolumeForDeliveryCosts(deliveryCost));
        chooseTariff(deliveryCost);
        return Math.max(deliveryCost.getWeight() * forWeight, deliveryCost.getVolume() * forVolume);
    }

    public void chooseTariff(DeliveryCost deliveryCost) {
        //TODO optional, refactoring
        if (deliveryCost.getWeight() > 50 || deliveryCost.getVolume() > 1  // 50,1,500 винести в константи
                || directionServise.getDistance(deliveryCost.getCity()) > 500) {
            forWeight = tariffRepository.findTariffById(2L).getTariffForWeight();
            forVolume = tariffRepository.findTariffById(2L).getTariffForVolume();
        } else {
            forWeight = tariffRepository.findTariffById(1L).getTariffForWeight();
            forVolume = tariffRepository.findTariffById(1L).getTariffForVolume();
        }
    }


}
