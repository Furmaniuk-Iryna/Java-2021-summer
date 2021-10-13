package com.example.final_project.repository.Impl;

import com.example.final_project.entity.DeliveryRequest;
import com.example.final_project.entity.Direction;
import com.example.final_project.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class DeliveryRequestsRepositoryImpl {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private EntityManagerFactory entityManagerFactory;



    public List<DeliveryRequest> findDeliveryRequestByUser(User user) {
        System.out.println("-----------findDeliveryRequestByUser---------------------Cache");
        Query req = entityManager.createQuery("SELECT d FROM DeliveryRequest d WHERE d.user.idUser=:id").
        setParameter("id", user.getIdUser());

        return req.getResultList();
    }


    public Page<DeliveryRequest> findAll(Pageable pageable) {
        System.out.println("-------findAll-------------------------Cache");
        Query query = entityManager.createQuery("SELECT d FROM DeliveryRequest d");
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        query.setFirstResult((pageNumber) * pageSize);
        query.setMaxResults(pageSize);
        List<DeliveryRequest> deliveryRequestList = query.getResultList();
        System.out.println("----------------------------------" + deliveryRequestList);
        Query queryCount = entityManager.createQuery("select count(d.id) from DeliveryRequest d");
        long count =(long) queryCount.getSingleResult();
        Page<DeliveryRequest> page = new PageImpl<DeliveryRequest>(deliveryRequestList, pageable, count);
        System.out.println("----------------------------------" + page);
        return page;
    }

    public List<DeliveryRequest> findAll() {
        System.out.println("-------findAll()-------------------------Cache");
       return entityManager.createQuery("SELECT d FROM DeliveryRequest d").getResultList();

    }


    public Page<DeliveryRequest> findAllByAddress_Direction(Direction direction, Pageable pageable) {
        System.out.println("------------------------------findAllByAddress_Direction--Cache");
        Query query = entityManager.createQuery("SELECT d FROM DeliveryRequest d JOIN Address a ON a.direction.id=d.address.direction.id WHERE a.direction.id=:id")
                .setParameter("id", direction.getId());
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        query.setFirstResult((pageNumber) * pageSize);
        query.setMaxResults(pageSize);
        List<DeliveryRequest> deliveryRequestList = query.getResultList();
        Query queryCount = entityManager.createQuery("SELECT COUNT(d.id) FROM DeliveryRequest d JOIN Address a ON a.direction.id=d.address.direction.id WHERE a.direction.id=:id");
        queryCount.setParameter("id", direction.getId());
        long count = (long) queryCount.getSingleResult();
        System.out.println("page-----------------------------" + count);

        return new PageImpl<DeliveryRequest>(deliveryRequestList, pageable, count);
    }


    public Page<DeliveryRequest> findAllByDateOfArrival(LocalDate date, Pageable pageable) {

        System.out.println("------------------------------findAllByDateOfArrival--Cache");
        Query query = entityManager.createQuery("SELECT d FROM DeliveryRequest d WHERE d.dateOfArrival=:date")
        .setParameter("date", date);
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        query.setFirstResult((pageNumber) * pageSize);
        query.setMaxResults(pageSize);
        List<DeliveryRequest> deliveryRequestList = query.getResultList();
        Query queryCount = entityManager.createQuery("select count(d.id) from DeliveryRequest d WHERE d.dateOfArrival=:date");
        queryCount.setParameter("date", date);
        long count = (long) queryCount.getSingleResult();
        return new PageImpl<DeliveryRequest>(deliveryRequestList, pageable, count);
    }


    public Page<DeliveryRequest> findDeliveryRequests(Pageable pageable) {
        System.out.println("-------findDeliveryRequests-------------------------Cache");
        Query query = entityManager.createQuery("SELECT d FROM DeliveryRequest d LEFT JOIN Receipt r ON d.id=r.deliveryRequest.id WHERE r.deliveryRequest.id IS NULL");
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        query.setFirstResult((pageNumber) * pageSize);
        query.setMaxResults(pageSize);
        List<DeliveryRequest> deliveryRequestList = query.getResultList();
        System.out.println("----------------------------------" + deliveryRequestList);
        Query queryCount = entityManager.createQuery("SELECT COUNT (d.id) FROM DeliveryRequest d LEFT JOIN Receipt r ON d.id=r.deliveryRequest.id WHERE r.deliveryRequest.id IS NULL");
        long count = (long) queryCount.getSingleResult();
        Page<DeliveryRequest> page = new PageImpl<DeliveryRequest>(deliveryRequestList, pageable, count);
        System.out.println("----------------------------------" + page);
        return page;
    }

    public DeliveryRequest getById(Long id) {
        System.out.println("----------------getById----------------Cache");
        return (DeliveryRequest) entityManager.createQuery("SELECT d FROM DeliveryRequest d WHERE d.id=:id").setParameter("id",id).getSingleResult();
    }

    public Optional<DeliveryRequest> findById(Long aLong) {
        System.out.println("------------findById--------------------Cache");
        return Optional.ofNullable((DeliveryRequest) entityManager.createQuery("SELECT d FROM DeliveryRequest d WHERE d.id=:id").setParameter("id",aLong).getSingleResult());
    }

    public void delete(DeliveryRequest deliveryRequest) {
        EntityManager em= entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.createNativeQuery("delete from `request` where id_delivery_request=:id", DeliveryRequest.class).
                setParameter("id", deliveryRequest.getId()).
                executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public <S extends DeliveryRequest> S save(S s) {
        System.out.println("Save------------------------");
     EntityManager em= entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(s);
        em.getTransaction().commit();
        em.close();
        return s;
    }
}
