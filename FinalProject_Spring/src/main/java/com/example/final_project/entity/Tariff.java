package com.example.final_project.entity;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "tariff")
public class Tariff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tariff",nullable = false)
    private long id;
    @Column(name = "name_en")
    private   String tariffNameEn;
    @Column(name = "name_uk")
    private   String tariffNameUk;
    @Column(name = "for_weight")
    private double tariffForWeight;
    @Column(name = "for_volume")
    private double tariffForVolume;
    @Column(name = "description_en")
    private String descriptionEn;
    @Column(name = "description_uk")
    private String descriptionUk;
}
