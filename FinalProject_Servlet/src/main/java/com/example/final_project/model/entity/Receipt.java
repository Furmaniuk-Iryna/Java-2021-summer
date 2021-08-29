package com.example.final_project.model.entity;

public class Receipt {
    private long id;
    private DeliveryRequest deliveryRequest;
    private double price;
    private String status;

    public Receipt() {
    }

    public Receipt(long id, DeliveryRequest deliveryRequest, double price, String status) {
        this.id = id;
        this.deliveryRequest = deliveryRequest;
        this.price = price;
        this.status = status;
    }

    public Receipt( double price, String status,DeliveryRequest deliveryRequest) {
        this.deliveryRequest = deliveryRequest;
        this.price = price;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public DeliveryRequest getDeliveryRequest() {
        return deliveryRequest;
    }

    public void setDeliveryRequest(DeliveryRequest deliveryRequest) {
        this.deliveryRequest = deliveryRequest;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "id=" + id +
                ", deliveryRequest=" + deliveryRequest +
                ", price=" + price +
                ", status='" + status + '\'' +
                '}';
    }
}
