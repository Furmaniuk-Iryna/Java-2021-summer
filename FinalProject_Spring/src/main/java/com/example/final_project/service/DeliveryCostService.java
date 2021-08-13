package com.example.final_project.service;

import com.example.final_project.entity.DeliveryCost;
import com.example.final_project.repository.TariffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public double calculateDeliveryCost(double weight, double volume, String city) {
        chooseTariff(weight, volume, city);
        return Math.max(weight * forWeight, volume * forVolume);
    }

    @Transactional
    public void getTariffForWeightAndVolume(Long id){
        forWeight = tariffRepository.findTariffById(id).getTariffForWeight();
        forVolume = tariffRepository.findTariffById(id).getTariffForVolume();
    }

    public void chooseTariff(double weight, double volume, String city) {
        //TODO optional, const
        getTariffForWeightAndVolume(weight > 50 || volume > 1
                || directionServise.getDistance(city) > 500 ? 2L : 1L);
    }
}
