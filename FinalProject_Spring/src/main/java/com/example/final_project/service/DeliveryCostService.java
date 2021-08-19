package com.example.final_project.service;

import com.example.final_project.entity.DeliveryCost;
import com.example.final_project.repository.TariffRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.final_project.constants.Constant.*;
@Slf4j
@Service
public class DeliveryCostService {
    private double forWeight;
    private double forVolume;

    @Autowired
    private TariffRepository tariffRepository;

    @Autowired
    private DirectionServise directionServise;

    public double calculateVolume(int length, int height, int width) {
        return (double) length * width * height / centimetersToCubicMeters;
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
        getTariffForWeightAndVolume(weight > maxWeight || volume > maxVolume
                || directionServise.getNeededDirection(city).getDistance() > maxDistance ? secondTariffId : firstTariffId);
    }
}
