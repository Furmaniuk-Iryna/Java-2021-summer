package com.example.final_project.model.service;

import com.example.final_project.model.dao.DaoFactory;
import com.example.final_project.model.dao.DeliveryRequestDao;
import com.example.final_project.model.dao.TariffDao;
import com.example.final_project.model.entity.DeliveryRequest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

public class DeliveryRequestService {
    DaoFactory daoFactory = DaoFactory.getInstance();
    private DirectionService directionService = new DirectionService();
    private TariffService tariffService = new TariffService();
    private double forWeight;
    private  double forVolume;

    public List<DeliveryRequest> getAllDeliveryRequests(){
        try (DeliveryRequestDao dao = daoFactory.createDeliveryRequestDao()) {
            return dao.findAll();
        }
    }
    public void saveDeliveryRequest(DeliveryRequest deliveryRequest){
        try (DeliveryRequestDao dao = daoFactory.createDeliveryRequestDao()) {
            dao.save(deliveryRequest);
        }
    }


    public LocalDate newDateOfArrival(double distance) {
        return distance > 500
                ? LocalDate.now().plusDays(2)
                : LocalDate.now().plusDays(1);
    }
    public double calculateVolume(int length, int height, int width) {
        return (double) length * width * height / 1000000;
    }

//    @Transactional
//    public void getTariffForWeightAndVolume(long id){
//            forWeight = tariffService.findTariffById(id).getTariffForWeight();
//            forVolume = tariffService.findTariffById(id).getTariffForVolume();
//    }
//
//    public void chooseTariff(double weight, double volume, String city) {
//        getTariffForWeightAndVolume(weight > 100 || volume > 1
//                || directionService.getNeededDirection(city).getDistance() > 500 ? 2L : 1L);
//    }
}
