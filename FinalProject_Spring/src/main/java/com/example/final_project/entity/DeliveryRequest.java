package com.example.final_project.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "delivery_request")

public class DeliveryRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id_delivery_request")
    private long id;
    private String type_en;
    private String type_uk;
    private double weight;
    private double volume;
    private double length;
    private double width;
    private double height;
    private String address;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfArrival;
    @ManyToOne
    @JoinColumn(name="direction_id", nullable=false)
    private Direction direction;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;



}
