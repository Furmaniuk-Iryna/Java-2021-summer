package com.example.final_project.model.entity;

import java.time.LocalDate;

public class DeliveryRequest {
    private long id;
    private String type_en;
    private String type_uk;
    private double weight;
    private double volume;
    private int length;
    private int width;
    private int height;
    private Address address;
    private LocalDate dateOfArrival;
    private User user;



}
