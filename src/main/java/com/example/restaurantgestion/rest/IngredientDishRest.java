package com.example.restaurantgestion.rest;

import com.example.restaurantgestion.entity.Unity;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class IngredientDishRest {
    private String id;
    private String name;
    private Double unitPrice;
    private Unity unity;
    private double stockActual;
    private double requiredQuantity;
}
