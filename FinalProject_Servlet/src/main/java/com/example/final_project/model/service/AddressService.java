package com.example.final_project.model.service;

import com.example.final_project.model.dao.AddressDao;
import com.example.final_project.model.dao.DaoFactory;
import com.example.final_project.model.dao.DirectionDao;
import com.example.final_project.model.entity.Address;
import com.example.final_project.model.entity.Direction;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

public class AddressService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Address> getAllAddress(){
        try (AddressDao dao = daoFactory.createAddressDao()) {
            return Optional.ofNullable(dao.findAll()).orElseThrow(RuntimeException::new);
        }
    }

    public Address getAddressById(long id){
        try (AddressDao dao = daoFactory.createAddressDao()) {
            return Optional.ofNullable(dao.findById(id)).orElseThrow(RuntimeException::new);
        }
    }
}
