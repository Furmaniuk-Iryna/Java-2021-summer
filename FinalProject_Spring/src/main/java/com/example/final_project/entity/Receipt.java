package com.example.final_project.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "receipt")
public class Receipt {
    @Id
    @Column(name = "delivery_id")
    private Long id;
    @OneToOne
    @MapsId
    @JoinColumn(name = "delivery_id")
    private DeliveryRequest deliveryRequest;

    private double price;

}
