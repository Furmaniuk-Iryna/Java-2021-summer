package com.example.final_project.entity;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Component
public class UserCard {
    private long cardNumber;
    private double balance;
    private User user;
}
