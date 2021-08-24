package com.example.final_project.model.entity;

public class Tariff {
    private long id;
    private String descriptionEn;
    private String descriptionUk;
    private double tariffForVolume;
    private double tariffForWeight;
    private   String tariffNameEn;
    private   String tariffNameUk;


    public Tariff() {
    }

    public Tariff(long id, String descriptionEn, String descriptionUk, double tariffForVolume, double tariffForWeight, String tariffNameEn, String tariffNameUk) {
        this.id = id;
        this.descriptionEn = descriptionEn;
        this.descriptionUk = descriptionUk;
        this.tariffForVolume = tariffForVolume;
        this.tariffForWeight = tariffForWeight;
        this.tariffNameEn = tariffNameEn;
        this.tariffNameUk = tariffNameUk;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    public String getDescriptionUk() {
        return descriptionUk;
    }

    public void setDescriptionUk(String descriptionUk) {
        this.descriptionUk = descriptionUk;
    }

    public double getTariffForVolume() {
        return tariffForVolume;
    }

    public void setTariffForVolume(double tariffForVolume) {
        this.tariffForVolume = tariffForVolume;
    }

    public double getTariffForWeight() {
        return tariffForWeight;
    }

    public void setTariffForWeight(double tariffForWeight) {
        this.tariffForWeight = tariffForWeight;
    }

    public String getTariffNameEn() {
        return tariffNameEn;
    }

    public void setTariffNameEn(String tariffNameEn) {
        this.tariffNameEn = tariffNameEn;
    }

    public String getTariffNameUk() {
        return tariffNameUk;
    }

    public void setTariffNameUk(String tariffNameUk) {
        this.tariffNameUk = tariffNameUk;
    }

    @Override
    public String toString() {
        return "Tariff{" +
                "id=" + id +
                ", descriptionEn='" + descriptionEn + '\'' +
                ", descriptionUk='" + descriptionUk + '\'' +
                ", tariffForVolume=" + tariffForVolume +
                ", tariffForWeight=" + tariffForWeight +
                ", tariffNameEn='" + tariffNameEn + '\'' +
                ", tariffNameUk='" + tariffNameUk + '\'' +
                '}';
    }
}
