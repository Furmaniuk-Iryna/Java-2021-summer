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
    private Tariff tariff;

    public DeliveryRequest() {
    }

    public DeliveryRequest(long id, String type_en, String type_uk,
                           double weight, double volume, int length,
                           int width, int height, Address address,
                           LocalDate dateOfArrival, User user,Tariff tariff) {
        this.id = id;
        this.type_en = type_en;
        this.type_uk = type_uk;
        this.weight = weight;
        this.volume = volume;
        this.length = length;
        this.width = width;
        this.height = height;
        this.address = address;
        this.dateOfArrival = dateOfArrival;
        this.user = user;
        this.tariff = tariff;
    }

    public Tariff getTariff() {
        return tariff;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType_en() {
        return type_en;
    }

    public void setType_en(String type_en) {
        this.type_en = type_en;
    }

    public String getType_uk() {
        return type_uk;
    }

    public void setType_uk(String type_uk) {
        this.type_uk = type_uk;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public LocalDate getDateOfArrival() {
        return dateOfArrival;
    }

    public void setDateOfArrival(LocalDate dateOfArrival) {
        this.dateOfArrival = dateOfArrival;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "DeliveryRequest{" +
                "id=" + id +
                ", type_en='" + type_en + '\'' +
                ", type_uk='" + type_uk + '\'' +
                ", weight=" + weight +
                ", volume=" + volume +
                ", length=" + length +
                ", width=" + width +
                ", height=" + height +
                ", address=" + address +
                ", dateOfArrival=" + dateOfArrival +
                ", user=" + user +
                ", tariff=" + tariff +
                '}';
    }
}
