package com.example.final_project.model.service;

import com.example.final_project.model.dao.DaoFactory;
import com.example.final_project.model.dao.TariffDao;
import com.example.final_project.model.dao.UserDao;
import com.example.final_project.model.entity.Tariff;
import com.example.final_project.model.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;

/**
 * TariffService is a service we'll be using to form response and
 * where there is all the business logic for the essence Tariff
 */
public class TariffService {
    DaoFactory daoFactory = DaoFactory.getInstance();
    DirectionService directionService = new DirectionService();
    private static Logger log = Logger.getLogger(String.valueOf(TariffService.class));

    public List<Tariff> getAllTariffs(){
        try (TariffDao dao = daoFactory.createTariffDao()) {
            return Optional.ofNullable(dao.findAll()).orElseThrow(RuntimeException::new);
        }
    }

    public Tariff findTariffById(long id){
        try (TariffDao dao = daoFactory.createTariffDao()) {
            return Optional.ofNullable(dao.findById(id)).orElseThrow(RuntimeException::new);
        }
    }
}
