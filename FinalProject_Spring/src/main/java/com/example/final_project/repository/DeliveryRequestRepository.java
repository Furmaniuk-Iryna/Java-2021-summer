package com.example.final_project.repository;

import com.example.final_project.entity.DeliveryRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DeliveryRequestRepository extends JpaRepository<DeliveryRequest, Long> {
    @Override
    List<DeliveryRequest> findAll();
}
