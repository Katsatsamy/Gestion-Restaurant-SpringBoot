package com.example.restaurantgestion.entity;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class DishOrderRest {
    private String id;
    private String name;
    private int unitPrice;
    private Double quantity;
    private OrderStatus status;
}
