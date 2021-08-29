package com.example.final_project.model.service;

import com.example.final_project.model.dao.DaoFactory;
import com.example.final_project.model.dao.DeliveryRequestDao;
import com.example.final_project.model.entity.DeliveryRequest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DeliveryRequestService {
    DaoFactory daoFactory = DaoFactory.getInstance();
    private DirectionService directionService = new DirectionService();
    private TariffService tariffService = new TariffService();
    private double forWeight;
    private double forVolume;

    public List<DeliveryRequest> getAllDeliveryRequests() {
        try (DeliveryRequestDao dao = daoFactory.createDeliveryRequestDao()) {
            return Optional.ofNullable(dao.findAll()).orElseThrow(RuntimeException::new);
        }
    }
    public DeliveryRequest getDeliveryRequestById(long id){
        try (DeliveryRequestDao dao = daoFactory.createDeliveryRequestDao()) {
            return Optional.ofNullable(dao.findById(id)).orElseThrow(RuntimeException::new);
        }
    }

    public synchronized List<DeliveryRequest> getPagesDeliveryRequests(int firstRequest, int lastRequest) {

        List<DeliveryRequest> deliveryRequestList = new ArrayList<>();
        for (int request =firstRequest; request < lastRequest; request++)
          deliveryRequestList.add(getAllDeliveryRequests().get(request));

        return deliveryRequestList;
    }
    public synchronized List<DeliveryRequest> getDirectionReports(int firstRequest, int lastRequest, String city) {

        List<DeliveryRequest> deliveryRequestList = new ArrayList<>();
        for (int request =firstRequest; request < lastRequest; request++)
        {            deliveryRequestList.add(getDirectionReport(city).get(request));}
        return deliveryRequestList;
    }
    public synchronized List<DeliveryRequest> getReportByDays(int firstRequest, int lastRequest, LocalDate dayFromForm) {
        List<DeliveryRequest> deliveryRequestList = new ArrayList<>();
        for (int request =firstRequest; request < lastRequest; request++)
        {deliveryRequestList.add(getReportByDay(dayFromForm).get(request));}
        return deliveryRequestList;
    }

    public void saveDeliveryRequest(DeliveryRequest deliveryRequest) {
        try (DeliveryRequestDao dao = daoFactory.createDeliveryRequestDao()) {
            dao.save(deliveryRequest);
        }
    }


    public LocalDate newDateOfArrival(double distance) { //synchronized?
        return distance > 500
                ? LocalDate.now().plusDays(2)
                : LocalDate.now().plusDays(1);
    }

    public double calculateVolume(int length, int height, int width) {//synchronized?
        return (double) length * width * height / 1000000;
    }
    public double calculateDeliveryCost(double weight, double volume, String city) {
        chooseTariff(weight, volume, city);
        return Math.max(weight * forWeight, volume * forVolume);
    }

   //TODO Transactional
    public void getTariffForWeightAndVolume(Long id){
        forWeight = tariffService.findTariffById(id).getTariffForWeight();
        forVolume = tariffService.findTariffById(id).getTariffForVolume();
    }

    public void chooseTariff(double weight, double volume, String city) {
        getTariffForWeightAndVolume(weight > 100 || volume > 1
                || directionService.getNeededDirection(city).getDistance() > 500 ? 2L : 1L);
    }

    public List<DeliveryRequest> getReportByDay(LocalDate dayFromForm) {
        try (DeliveryRequestDao dao = daoFactory.createDeliveryRequestDao()) {
        return dao.findAll()
                .stream()
                .filter(deliveryRequest -> (deliveryRequest.getDateOfArrival()).isEqual(dayFromForm))
                .collect(Collectors.toList());}}

        public List<DeliveryRequest> getDirectionReport(String city) {
            try (DeliveryRequestDao dao = daoFactory.createDeliveryRequestDao()) {
            return dao.findAll()
                    .stream()
                    .filter(deliveryRequest -> (deliveryRequest.getAddress().getDirection().getCityEn().toLowerCase().equals(city.toLowerCase())
                            || deliveryRequest.getAddress().getDirection().getCityUk().toLowerCase().equals(city.toLowerCase())))
                    .collect(Collectors.toList());}}
}
