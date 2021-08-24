package com.example.final_project.model.entity;

public class Direction {
    private long id;
    private String cityEn;
    private String cityUk;
    private double distance;

    public Direction() {
    }

    public Direction(long id, String cityEn, String cityUk, double distance) {
        this.id = id;
        this.cityEn = cityEn;
        this.cityUk = cityUk;
        this.distance = distance;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCityEn() {
        return cityEn;
    }

    public void setCityEn(String cityEn) {
        this.cityEn = cityEn;
    }

    public String getCityUk() {
        return cityUk;
    }

    public void setCityUk(String cityUk) {
        this.cityUk = cityUk;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Direction{" +
                "id=" + id +
                ", cityEn='" + cityEn + '\'' +
                ", cityUk='" + cityUk + '\'' +
                ", distance=" + distance +
                '}';
    }
}
