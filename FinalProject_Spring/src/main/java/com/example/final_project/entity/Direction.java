package com.example.final_project.entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "direction")
public class Direction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_direction")
    private long id;
    @Column(unique = true)
    private String cityEn;
    @Column(unique = true)
    private String cityUk;
    private double distance;

}
