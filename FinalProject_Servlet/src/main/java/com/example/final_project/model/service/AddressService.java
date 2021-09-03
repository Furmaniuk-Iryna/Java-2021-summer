package com.example.final_project.model.service;

import com.example.final_project.model.dao.AddressDao;
import com.example.final_project.model.dao.DaoFactory;
import com.example.final_project.model.dao.DirectionDao;
import com.example.final_project.model.entity.Address;
import com.example.final_project.model.entity.Direction;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;

/**
 * AddressService is a service we'll be using to form response and
 * where there is all the business logic for the essence Address
 */
public class AddressService {
    DaoFactory daoFactory = DaoFactory.getInstance();
    private static Logger log = Logger.getLogger(String.valueOf(AddressService.class));
    public List<Address> getAllAddress(){
        log.info("Service: started getAllAddress");
        try (AddressDao dao = daoFactory.createAddressDao()) {
            return Optional.ofNullable(dao.findAll()).orElseThrow(RuntimeException::new);
        }
    }

    public Address getAddressById(long id){
        log.info("Service: started getAddressById");
        try (AddressDao dao = daoFactory.createAddressDao()) {
            return Optional.ofNullable(dao.findById(id)).orElseThrow(RuntimeException::new);
        }
    }
}
