package com.example.restaurantgestion.rest;

import com.example.restaurantgestion.entity.Ingredient;
import com.example.restaurantgestion.entity.Movement;
import com.example.restaurantgestion.entity.Unity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class StockRest {
    private String id;
    private Movement movement;
    private Double quantity;
    private Unity unity;
    private LocalDateTime date;
}
