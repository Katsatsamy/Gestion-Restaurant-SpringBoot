package com.example.restaurantgestion.controller;

import com.example.restaurantgestion.entity.IngredientWithQuantity;
import com.example.restaurantgestion.rest.DishRest;
import com.example.restaurantgestion.service.DishService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class DishController {
    private final DishService dishService;

    @GetMapping("/dishes")
    private List<DishRest> getAllDishes(){
        return dishService.getAllDish(1,10);
    }

    @PutMapping("/dishes/{id}/ingredients")
    private ResponseEntity putIngredientWithQuantity(List<IngredientWithQuantity> ingredientWithQuantity, @PathVariable String id) {
        return dishService.putIngredientWithQuantity(id, ingredientWithQuantity);
    }
}
