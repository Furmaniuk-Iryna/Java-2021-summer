package com.company.ImmutableObject;

/**
 * Created by Iryna Furmaniuk on 11.07.2021.
 */
public class Course {
    private String name;
    private double price;
    private String duration;

    public Course() {
    }

    public Course(String name, double price, String duration) {
        this.name = name;
        this.price = price;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", duration='" + duration + '\'' +
                '}';
    }
}
