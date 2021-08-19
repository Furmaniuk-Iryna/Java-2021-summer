package com.example.final_project.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.LocalDate;
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "request")

public class DeliveryRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id_delivery_request")
    private long id;
    private String type_en;
    private String type_uk;
    @Min(1)
    private double weight;
    private double volume;
    @Min(1)
    private int length;
    @Min(1)
    private int width;
    @Min(1)
    private int height;

    @ManyToOne
    @JoinColumn(name="address_id", nullable=false)
    private Address address;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfArrival;

//    @ManyToOne
//    @JoinColumn(name="direction_id", nullable=false)
//    private Direction direction;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;



}
