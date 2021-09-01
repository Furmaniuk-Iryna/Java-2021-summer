package com.example.final_project.model.dao;

import com.example.final_project.model.entity.DeliveryRequest;

import java.util.List;

public interface DeliveryRequestDao extends GenericDao<DeliveryRequest>{
    List<DeliveryRequest> findByUser(long idUser);
    List<DeliveryRequest> findAllForCreateReceipt();
}
