package com.example.final_project.repository;

import com.example.final_project.entity.DeliveryRequest;
import com.example.final_project.entity.Direction;
import com.example.final_project.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface DeliveryRequestRepository  {
    List<DeliveryRequest> findDeliveryRequestByUser(User user);
    Page<DeliveryRequest> findAll(Pageable pageable);
    List<DeliveryRequest> findAll();
    Page<DeliveryRequest> findAllByAddress_Direction(Direction direction, Pageable pageable);
    Page<DeliveryRequest> findAllByDateOfArrival(LocalDate date, Pageable pageable);
    @Query(value = "SELECT * FROM Request d LEFT JOIN Receipt r ON d.id_delivery_request=r.delivery_id WHERE r.delivery_id IS NULL ",nativeQuery = true)
    Page<DeliveryRequest> findDeliveryRequests(Pageable pageable);
    <S extends DeliveryRequest> S save(S s);
    void delete(DeliveryRequest deliveryRequest);
    Optional<DeliveryRequest> findById(Long aLong);
    DeliveryRequest getById(Long id);
}


