package com.example.final_project.entity;


import lombok.*;

import javax.persistence.*;
import java.util.List;
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
    @Column(nullable = false, name = "id_direction")
    private long id;
    @Column(nullable = false, unique = true)
    private String city_en;
    @Column(nullable = false, unique = true)
    private String city_uk;
    private double distance;
//    @OneToMany(mappedBy="direction")
//    private List<DeliveryRequest> deliveryRequestSet;
}
