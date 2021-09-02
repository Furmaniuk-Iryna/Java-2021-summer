package com.example.final_project.model.service;

import com.example.final_project.model.dao.DaoFactory;
import com.example.final_project.model.dao.DeliveryRequestDao;
import com.example.final_project.model.entity.DeliveryRequest;
import com.example.final_project.model.entity.Tariff;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DeliveryRequestService {
    DaoFactory daoFactory = DaoFactory.getInstance();
    private final DirectionService directionService = new DirectionService();
    private final TariffService tariffService = new TariffService();


    public List<DeliveryRequest> getAllDeliveryRequests() {
        try (DeliveryRequestDao dao = daoFactory.createDeliveryRequestDao()) {
            return Optional.ofNullable(dao.findAllForCreateReceipt()).orElseThrow(RuntimeException::new);
        }
    }

    public DeliveryRequest getDeliveryRequestById(long id){
        try (DeliveryRequestDao dao = daoFactory.createDeliveryRequestDao()) {
            return Optional.ofNullable(dao.findById(id)).orElseThrow(RuntimeException::new);
        }
    }

    public  List<DeliveryRequest> getPagesDeliveryRequests(int firstRequest, int lastRequest) {
        List<DeliveryRequest> deliveryRequestList = new ArrayList<>();
        for (int request =firstRequest; request < lastRequest; request++)
          deliveryRequestList.add(getAllDeliveryRequests().get(request));
        return deliveryRequestList;
    }

    public  List<DeliveryRequest> getDirectionReports(int firstRequest, int lastRequest, String city) {
        List<DeliveryRequest> deliveryRequestList = new ArrayList<>();
        for (int request =firstRequest; request < lastRequest; request++)
        {            deliveryRequestList.add(getDirectionReport(city).get(request));}
        return deliveryRequestList;
    }

    public  List<DeliveryRequest> getReportByDays(int firstRequest, int lastRequest, LocalDate dayFromForm) {
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
      Tariff tariff=chooseTariff(weight, volume, city);
        return Math.max(weight * tariff.getTariffForWeight(), volume * tariff.getTariffForVolume());
    }


    public Tariff chooseTariff(double weight, double volume, String city) {
        return tariffService.findTariffById(weight > 100 || volume > 1
                || directionService.getNeededDirection(city).getDistance() > 500 ? 2L : 1L);
    }

    public List<DeliveryRequest> getReportByDay(LocalDate dayFromForm) {
        try (DeliveryRequestDao dao = daoFactory.createDeliveryRequestDao()) {
        return dao.findAll()
                .stream()
                .filter(deliveryRequest -> (deliveryRequest.getDateOfArrival()).isEqual(dayFromForm))
                .collect(Collectors.toList());
        }
    }

    public List<DeliveryRequest> getDirectionReport(String city) {
        try (DeliveryRequestDao dao = daoFactory.createDeliveryRequestDao()) {
            return dao.findAll()
                    .stream()
                    .filter(deliveryRequest -> (deliveryRequest.getAddress().getDirection().getCityEn().toLowerCase().equals(city.toLowerCase())
                            || deliveryRequest.getAddress().getDirection().getCityUk().toLowerCase().equals(city.toLowerCase())))
                    .collect(Collectors.toList());
        }
    }

    public List<DeliveryRequest> findByUser(long idUser) {
        try (DeliveryRequestDao dao = daoFactory.createDeliveryRequestDao()) {
            return Optional.ofNullable(dao.findByUser(idUser)).orElseThrow(RuntimeException::new);
        }
    }

    public  List<DeliveryRequest> getReportsForUser(int firstRequest, int lastRequest, long idUser) {
        List<DeliveryRequest> deliveryRequestList = new ArrayList<>();
        for (int request =firstRequest; request < lastRequest; request++)
        {            deliveryRequestList.add(findByUser(idUser).get(request));}
        return deliveryRequestList;
    }
}
