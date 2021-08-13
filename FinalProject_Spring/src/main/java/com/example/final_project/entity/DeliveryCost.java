package com.example.final_project.entity;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Component
public class DeliveryCost {
    @Min(1)
    private double weight;
    private double volume;
    @Min(1)
    private int length;
    @Min(1)
    private int width;
    @Min(1)
    private int height;
    private String city;

}
