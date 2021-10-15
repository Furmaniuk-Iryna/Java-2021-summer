package com.example.final_project.service;

import com.example.final_project.entity.Address;
import com.example.final_project.repository.AddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public Address getById(Long id){
     return addressRepository.getById(id);
    }
}
