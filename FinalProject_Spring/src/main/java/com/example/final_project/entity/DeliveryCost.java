package com.example.final_project.entity;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Component
public class DeliveryCost {
    private double weight;
    private double volume;
    private int length;
    private int width;
    private int height;
    private String city;

}
