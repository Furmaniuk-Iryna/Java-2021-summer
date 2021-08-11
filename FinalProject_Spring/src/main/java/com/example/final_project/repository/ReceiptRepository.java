package com.example.final_project.repository;

import com.example.final_project.entity.DeliveryRequest;
import com.example.final_project.entity.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface ReceiptRepository extends JpaRepository<Receipt,Long> {
    Receipt findReceiptByDeliveryRequest(DeliveryRequest deliveryRequest);
}
