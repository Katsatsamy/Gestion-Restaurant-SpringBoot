package com.example.restaurantgestion.service;

import com.example.restaurantgestion.entity.Dish;
import com.example.restaurantgestion.entity.IngredientWithQuantity;
import com.example.restaurantgestion.repository.DishCrudOperation;
import com.example.restaurantgestion.rest.DishRest;
import com.example.restaurantgestion.rest.IngredientDishRest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class DishService {
    private final IngredientService ingredientService;
    private DishCrudOperation dishCrudOperation;

    public List<DishRest> getAllDish(int page, int size) {
        List<Dish> dishes = dishCrudOperation.getAll(page, size);
        List<DishRest> dishRests = new ArrayList<>();
        for (Dish dish : dishes) {
            List<IngredientDishRest> ingredientDishRests = new ArrayList<>();
            List<IngredientWithQuantity> ingredientWithQuantities = dish.getIngredients();
            for (IngredientWithQuantity ingredientWithQuantity : ingredientWithQuantities) {
                IngredientDishRest ingredientDishRest = new IngredientDishRest(
                        ingredientWithQuantity.getIngredient().getId(),
                        ingredientWithQuantity.getIngredient().getName(),
                        ingredientWithQuantity.getIngredient().getUnitPrice(),
                        ingredientWithQuantity.getUnity(),
                        ingredientWithQuantity.getIngredient().getAvalaibleQuantity().getQuantity(),
                        ingredientWithQuantity.getRequiredQuantity()
                );
                ingredientDishRests.add(ingredientDishRest);
            }
            DishRest dishRest = new DishRest(
                    dish.getId(),
                    dish.getName(),
                    dish.getUnitPrice(),
                    ingredientDishRests,
                    dish.getAvailableQuantity()
            );
            dishRests.add(dishRest);
        }
        return dishRests;
    }

    public ResponseEntity putIngredientWithQuantity(String dishId, List<IngredientWithQuantity> ingredientWithQuantity) {
        return dishCrudOperation.putIngrdientQuantity(dishId, ingredientWithQuantity);
    }
}
