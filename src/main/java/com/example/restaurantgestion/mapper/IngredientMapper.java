package com.example.restaurantgestion.mapper;

import com.example.restaurantgestion.entity.IngredientPrice;
import com.example.restaurantgestion.entity.Stock;
import com.example.restaurantgestion.entity.Unity;
import com.example.restaurantgestion.rest.StockRest;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class IngredientMapper {
    private String id;
    private String name;
    private Double unitPrice;
    private List<IngredientPrice> ingredientPrices;
    private List<StockRest> stockRests;
    private Unity unity;
}
