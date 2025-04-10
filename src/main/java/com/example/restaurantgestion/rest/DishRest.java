package com.example.restaurantgestion.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class DishRest {
    private String id;
    private String name;
    private int unitPrice;
    private List<IngredientDishRest> ingredients;
    private Double availableQuantity;
}
