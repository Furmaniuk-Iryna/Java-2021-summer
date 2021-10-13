package com.example.final_project.repository;

import com.example.final_project.entity.DeliveryRequest;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
@Profile("nocache")
public interface DeliveryRequestRepository1 extends DeliveryRequestRepository, JpaRepository<DeliveryRequest,Long> {

}
