package com.example.final_project.repository;

import com.example.final_project.config.QueryConfig;
import com.example.final_project.entity.DeliveryRequest;
import com.example.final_project.entity.Direction;
import com.example.final_project.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CachedDeliveryRequestsRepositoryImpl implements DeliveryRequestRepository {
    private final ConcurrentHashMap<Long, DeliveryRequest> cache = new ConcurrentHashMap<>();

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private QueryConfig queryConfig;

    @Override
    public List<DeliveryRequest> findDeliveryRequestByUser(User user) {
        Query req = entityManager.createQuery(queryConfig.getFindByUser()).
                setParameter("id", user.getIdUser());
        List<DeliveryRequest> deliveryRequestList = req.getResultList();
        deliveryRequestList.forEach(deliveryRequest -> {
            if (!cache.containsKey(deliveryRequest.getId())) cache.put(deliveryRequest.getId(), deliveryRequest);
        });

        return deliveryRequestList;
    }

    @Override
    public Page<DeliveryRequest> findAll(Pageable pageable) {
        Query query = entityManager.createQuery(queryConfig.getFindAll());
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        query.setFirstResult((pageNumber) * pageSize);
        query.setMaxResults(pageSize);
        List<DeliveryRequest> deliveryRequestList = query.getResultList();
        Query queryCount = entityManager.createQuery(queryConfig.getCountFindAll());
        long count = (long) queryCount.getSingleResult();
        Page<DeliveryRequest> page = new PageImpl<DeliveryRequest>(deliveryRequestList, pageable, count);
        return page;
    }

    @Override
    public List<DeliveryRequest> findAll() {
        return entityManager.createQuery(queryConfig.getFindAll()).getResultList();

    }

    @Override
    public Page<DeliveryRequest> findAllByAddress_Direction(Direction direction, Pageable pageable) {
        Query query = entityManager.createQuery(queryConfig.getFindByDirection())
                .setParameter("id", direction.getId());
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        query.setFirstResult((pageNumber) * pageSize);
        query.setMaxResults(pageSize);
        List<DeliveryRequest> deliveryRequestList = query.getResultList();
        Query queryCount = entityManager.createQuery(queryConfig.getCountFindByDirection());
        queryCount.setParameter("id", direction.getId());
        long count = (long) queryCount.getSingleResult();
        deliveryRequestList.forEach(deliveryRequest -> {
            if (!cache.containsKey(deliveryRequest.getId())) cache.put(deliveryRequest.getId(), deliveryRequest);
        });
        return new PageImpl<DeliveryRequest>(deliveryRequestList, pageable, count);
    }

    @Override
    public Page<DeliveryRequest> findAllByDateOfArrival(LocalDate date, Pageable pageable) {
        Query query = entityManager.createQuery(queryConfig.getFindByDate())
                .setParameter("date", date);
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        query.setFirstResult((pageNumber) * pageSize);
        query.setMaxResults(pageSize);
        List<DeliveryRequest> deliveryRequestList = query.getResultList();
        Query queryCount = entityManager.createQuery(queryConfig.getCountFindByDate());
        queryCount.setParameter("date", date);
        long count = (long) queryCount.getSingleResult();
        deliveryRequestList.forEach(deliveryRequest -> {
            if (!cache.containsKey(deliveryRequest.getId())) cache.put(deliveryRequest.getId(), deliveryRequest);
        });
        return new PageImpl<DeliveryRequest>(deliveryRequestList, pageable, count);
    }

    @Override
    public Page<DeliveryRequest> findDeliveryRequests(Pageable pageable) {
        Query query = entityManager.createQuery(queryConfig.getFindForReceipt());
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        query.setFirstResult((pageNumber) * pageSize);
        query.setMaxResults(pageSize);
        List<DeliveryRequest> deliveryRequestList = query.getResultList();
        Query queryCount = entityManager.createQuery(queryConfig.getCountFindForReceipt());
        long count = (long) queryCount.getSingleResult();
        Page<DeliveryRequest> page = new PageImpl<DeliveryRequest>(deliveryRequestList, pageable, count);
        return page;
    }

    @Override
    public DeliveryRequest getById(Long id) {
        return cache.containsKey(id)
                ? cache.get(id)
                : getFromDB(id);
    }

    private DeliveryRequest getFromDB(Long id) {
        DeliveryRequest deliveryRequest = (DeliveryRequest) entityManager.createQuery(queryConfig.getGetById())
                .setParameter("id", id).getSingleResult();
        cache.put(id, deliveryRequest);
        return deliveryRequest;
    }

    @Override
    public Optional<DeliveryRequest> findById(Long aLong) {
        return cache.containsKey(aLong)
                ? Optional.ofNullable(cache.get(aLong))
                : Optional.ofNullable(getFromDB(aLong));
    }

    @Override
    @Transactional
    public void delete(DeliveryRequest deliveryRequest) {
        entityManager.createNativeQuery(queryConfig.getDelete(), DeliveryRequest.class).
                setParameter("id", deliveryRequest.getId()).
                executeUpdate();
        cache.remove(deliveryRequest.getId());
    }

    @Override
    @Transactional
    public <S extends DeliveryRequest> S save(S s) {
        S deliveryRequest = (s.getId() == 0) ? saveDeliveryRequest(s) : updateDeliveryRequest(s);
        return deliveryRequest;

    }

    private <S extends DeliveryRequest> S saveDeliveryRequest(S s) {
        entityManager.persist(s);
        Long id = (Long) entityManager.createQuery(queryConfig.getFindForCache())
                .setParameter("weight", s.getWeight())
                .setParameter("height", s.getHeight())
                .setParameter("width", s.getWidth())
                .setParameter("id", s.getUser().getIdUser()).getSingleResult();
        cache.put(id, getById(id));
        return s;
    }

    private <S extends DeliveryRequest> S updateDeliveryRequest(S s){
        cache.put(s.getId(), s);
       return entityManager.merge(s);
    }

}