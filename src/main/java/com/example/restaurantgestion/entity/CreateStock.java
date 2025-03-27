package com.example.restaurantgestion.entity;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CreateStock {
    private String id;
    private Movement movement;
    private Double quantity;
    private Unity unity;
}
