package com.example.final_project.repository;

import com.example.final_project.entity.DeliveryRequest;
import com.example.final_project.entity.Direction;
import com.example.final_project.entity.User;
import com.example.final_project.repository.Impl.DeliveryRequestsRepositoryImpl;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Profile("cache")
public  class DeliveryRequestRepository2  extends DeliveryRequestsRepositoryImpl implements DeliveryRequestRepository {

    public List<DeliveryRequest> findDeliveryRequestByUser(User user) {
        System.out.println("--------------------------------Cache");
        return super.findDeliveryRequestByUser(user);
    }


    public Page<DeliveryRequest> findAll(Pageable pageable) {
        System.out.println("--------------------------------Cache");
        return super.findAll(pageable);
    }


    public List<DeliveryRequest> findAll() {
        return super.findAll();
    }


    public Page<DeliveryRequest> findAllByAddress_Direction(Direction direction, Pageable pageable) {
        System.out.println("--------------------------------Cache");
        return super.findAllByAddress_Direction(direction,pageable);
    }


    public Page<DeliveryRequest> findAllByDateOfArrival(LocalDate date, Pageable pageable) {
        System.out.println("--------------------------------Cache");
        return super.findAllByDateOfArrival(date, pageable);
    }


    public Page<DeliveryRequest> findDeliveryRequests(Pageable pageable) {
        System.out.println("--------------------------------Cache");
        return super.findDeliveryRequests(pageable);
    }


    public DeliveryRequest getById(Long id) {
        return super.getById(id);
    }

    @Override
    public <S extends DeliveryRequest> S save(S s) {
       return super.save(s);
    }

    @Override
    public void delete(DeliveryRequest deliveryRequest) {
super.delete(deliveryRequest);
    }

    @Override
    public Optional<DeliveryRequest> findById(Long aLong) {
        return super.findById(aLong);
    }
}
