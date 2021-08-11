package com.example.final_project.service;

import com.example.final_project.repository.DeliveryRequestRepository;
import com.example.final_project.repository.ReceiptRepository;
import com.example.final_project.repository.TariffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceiptService {
    private double forWeight;
    private double forVolume;
    @Autowired
    private ReceiptRepository receiptRepository;
    @Autowired
    private DirectionServise directionServise;
    @Autowired
    private DeliveryRequestRepository deliveryRequestRepository;
    @Autowired
    private TariffRepository tariffRepository;

    public double calculatePrice(double weight, double volume, String city) {
        //TODO validation
        chooseTariff(weight, volume, city);
        return Math.max(weight * forWeight, volume * forVolume);
    }

    public void chooseTariff(double weight, double volume, String city) {
        //TODO optional, refactoring
        if (weight > 50 || volume > 1                  // 50,1,500 винести в константи
                || directionServise.getDistance(city) > 500) {
            forWeight = tariffRepository.findTariffById(2L).getTariffForWeight();
            forVolume = tariffRepository.findTariffById(2L).getTariffForVolume();
        } else {
            forWeight = tariffRepository.findTariffById(1L).getTariffForWeight();
            forVolume = tariffRepository.findTariffById(1L).getTariffForVolume();
        }
    }

}
