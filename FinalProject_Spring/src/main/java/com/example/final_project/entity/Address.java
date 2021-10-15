package com.example.final_project.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_address")
    private long id;
    @Column(unique = true)
    private String address_en;
    @Column(unique = true)
    private String address_uk;
    @ManyToOne
    @JoinColumn(name = "direction_id", nullable = false)
    private Direction direction;
}
