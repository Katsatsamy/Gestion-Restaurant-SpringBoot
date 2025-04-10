package com.example.restaurantgestion.rest;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class BestSalesDish {
    private String dishName;
    private Double quantitySale;
    private Double priceTotal;
}
