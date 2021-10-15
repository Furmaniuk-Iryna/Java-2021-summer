package com.example.final_project.service;

import com.example.final_project.repository.AddressRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AddressServiceMockTest {

    @Mock
    AddressRepository addressRepository;

    @InjectMocks
    AddressService addressService;

    @Test
    public void getById() {
        addressService.getById(1L);
        Mockito.verify(addressRepository, Mockito.times(1)).getById(1L);
    }
}