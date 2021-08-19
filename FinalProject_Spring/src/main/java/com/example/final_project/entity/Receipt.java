package com.example.final_project.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "receipt")
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private long id;

    @OneToOne
    @JoinColumn(name = "delivery_id", unique = true)
    private DeliveryRequest deliveryRequest;
    @Min(0)
    private double price;

    private String status;
}
