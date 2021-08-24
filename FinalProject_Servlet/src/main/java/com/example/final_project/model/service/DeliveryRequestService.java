package com.example.final_project.model.service;

import com.example.final_project.model.dao.DaoFactory;
import com.example.final_project.model.dao.DeliveryRequestDao;
import com.example.final_project.model.entity.DeliveryRequest;

import java.time.LocalDate;
import java.util.List;

public class DeliveryRequestService {
    DaoFactory daoFactory = DaoFactory.getInstance();

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


//    public LocalDate newDateOfArrival(DeliveryRequest deliveryRequest) {
//        return deliveryRequest.getAddress()          > 500
//                ? LocalDate.now().plusDays(2)
//                : LocalDate.now().plusDays(1);
//    }

}
