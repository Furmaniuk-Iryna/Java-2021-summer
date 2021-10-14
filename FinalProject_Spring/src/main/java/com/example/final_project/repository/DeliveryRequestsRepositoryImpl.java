package com.example.final_project.repository;

import com.example.final_project.entity.DeliveryRequest;
import com.example.final_project.entity.Direction;
import com.example.final_project.entity.User;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@Profile("cache")
public class DeliveryRequestsRepositoryImpl implements DeliveryRequestRepository {
    private final ConcurrentHashMap<Long, DeliveryRequest> cache = new ConcurrentHashMap<>();


    @PersistenceContext
    private EntityManager entityManager;

    public List<DeliveryRequest> findDeliveryRequestByUser(User user) {
        Query req = entityManager.createQuery("SELECT d FROM DeliveryRequest d WHERE d.user.idUser=:id").
                setParameter("id", user.getIdUser());
        List<DeliveryRequest> deliveryRequestList = req.getResultList();
        deliveryRequestList.forEach(deliveryRequest -> {
            if (!cache.containsKey(deliveryRequest.getId())) cache.put(deliveryRequest.getId(), deliveryRequest);
        });

        return deliveryRequestList;
    }


    public Page<DeliveryRequest> findAll(Pageable pageable) {
        Query query = entityManager.createQuery("SELECT d FROM DeliveryRequest d");
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        query.setFirstResult((pageNumber) * pageSize);
        query.setMaxResults(pageSize);
        List<DeliveryRequest> deliveryRequestList = query.getResultList();
        Query queryCount = entityManager.createQuery("select count(d.id) from DeliveryRequest d");
        long count = (long) queryCount.getSingleResult();
        Page<DeliveryRequest> page = new PageImpl<DeliveryRequest>(deliveryRequestList, pageable, count);
        return page;
    }

    public List<DeliveryRequest> findAll() {
        return entityManager.createQuery("SELECT d FROM DeliveryRequest d").getResultList();

    }


    public Page<DeliveryRequest> findAllByAddress_Direction(Direction direction, Pageable pageable) {
        Query query = entityManager.createQuery("SELECT d FROM DeliveryRequest d JOIN Address a ON d.address.direction.id=a.direction.id WHERE a.direction.id=:id")
                .setParameter("id", direction.getId());
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        query.setFirstResult((pageNumber) * pageSize);
        query.setMaxResults(pageSize);
        List<DeliveryRequest> deliveryRequestList = query.getResultList();
        Query queryCount = entityManager.createQuery("SELECT COUNT(d.id) FROM DeliveryRequest d JOIN Address a ON d.address.direction.id=a.direction.id WHERE a.direction.id=:id");
        queryCount.setParameter("id", direction.getId());
        long count = (long) queryCount.getSingleResult();

        deliveryRequestList.forEach(deliveryRequest -> {
            if (!cache.containsKey(deliveryRequest.getId())) cache.put(deliveryRequest.getId(), deliveryRequest);
        });
        return new PageImpl<DeliveryRequest>(deliveryRequestList, pageable, count);
    }


    public Page<DeliveryRequest> findAllByDateOfArrival(LocalDate date, Pageable pageable) {
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
        deliveryRequestList.forEach(deliveryRequest -> {
            if (!cache.containsKey(deliveryRequest.getId())) cache.put(deliveryRequest.getId(), deliveryRequest);
        });
        return new PageImpl<DeliveryRequest>(deliveryRequestList, pageable, count);
    }


    public Page<DeliveryRequest> findDeliveryRequests(Pageable pageable) {
        Query query = entityManager.createQuery("SELECT d FROM DeliveryRequest d LEFT JOIN Receipt r ON d.id=r.deliveryRequest.id WHERE r.deliveryRequest.id IS NULL");
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        query.setFirstResult((pageNumber) * pageSize);
        query.setMaxResults(pageSize);
        List<DeliveryRequest> deliveryRequestList = query.getResultList();
        Query queryCount = entityManager.createQuery("SELECT COUNT (d.id) FROM DeliveryRequest d LEFT JOIN Receipt r ON d.id=r.deliveryRequest.id WHERE r.deliveryRequest.id IS NULL");
        long count = (long) queryCount.getSingleResult();
        Page<DeliveryRequest> page = new PageImpl<DeliveryRequest>(deliveryRequestList, pageable, count);
        return page;
    }

    public DeliveryRequest getById(Long id) {
        return cache.containsKey(id)
                ? cache.get(id)
                : getFromDB(id);
    }

    private DeliveryRequest getFromDB(Long id) {
        DeliveryRequest deliveryRequest = (DeliveryRequest) entityManager.createQuery("SELECT d FROM DeliveryRequest d WHERE d.id=:id")
                .setParameter("id", id).getSingleResult();
        cache.put(id, deliveryRequest);
        return deliveryRequest;
    }

    public Optional<DeliveryRequest> findById(Long aLong) {
        return cache.containsKey(aLong)
                ? Optional.ofNullable(cache.get(aLong))
                : Optional.ofNullable(getFromDB(aLong));
    }

    @Transactional
    public void delete(DeliveryRequest deliveryRequest) {
        entityManager.createNativeQuery("delete from `request` where id_delivery_request=:id", DeliveryRequest.class).
                setParameter("id", deliveryRequest.getId()).
                executeUpdate();
        cache.remove(deliveryRequest.getId());
    }

    @Transactional
    public <S extends DeliveryRequest> S save(S s) {
       S deliveryRequest = (s.getId() == 0)? saveDeliveryRequest(s) : updateDeliveryRequest(s);
            return deliveryRequest;

    }
    private <S extends DeliveryRequest> S saveDeliveryRequest(S s){
        entityManager.persist(s);
        Long id = (Long) entityManager.createQuery("select d.id from DeliveryRequest d where d.weight=:weight and d.height=:height and d.width=:width and d.user.idUser=:id")
                .setParameter("weight",s.getWeight())
                .setParameter("height",s.getHeight())
                .setParameter("width",s.getWidth())
                .setParameter("id",s.getUser().getIdUser()).getSingleResult();
        cache.put(id, getById(id));
        return s;
    }
    private <S extends DeliveryRequest> S updateDeliveryRequest(S s){
        cache.put(s.getId(), s);
       return entityManager.merge(s);
    }

}