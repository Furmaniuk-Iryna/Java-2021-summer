package com.example.final_project.repository;

import com.example.final_project.entity.DeliveryRequest;
import com.example.final_project.entity.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt,Long> {
    Receipt findReceiptByDeliveryRequest(DeliveryRequest deliveryRequest);
    Optional<Receipt> findReceiptById (long id);
}
