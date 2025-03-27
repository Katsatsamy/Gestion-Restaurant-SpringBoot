package com.example.restaurantgestion.service;

import com.example.restaurantgestion.entity.CreatePrice;
import com.example.restaurantgestion.entity.CreateStock;
import com.example.restaurantgestion.entity.Ingredient;
import com.example.restaurantgestion.entity.Stock;
import com.example.restaurantgestion.mapper.IngredientMapper;
import com.example.restaurantgestion.repository.IngredientCrudOperation;
import com.example.restaurantgestion.rest.StockRest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class IngredientService {
    private IngredientCrudOperation ingredientCrudOperation;

    public List<IngredientMapper> getAll(int page, int size) {
        List<Ingredient> ingredients = ingredientCrudOperation.getAll(page, size);
        return mappingIngredients(ingredients);
    }

    public List<IngredientMapper> getIngredientsFilterByDate(Double priceMiniFilter, Double priceMaxFilter) {
        if (priceMiniFilter != null && priceMaxFilter != null && priceMaxFilter < priceMiniFilter) {
            throw new IllegalArgumentException("priceMaxFilter must be greater than priceMiniFilter");
        }if(priceMaxFilter < 0 || priceMiniFilter < 0){
            throw new IllegalArgumentException("price must be greater than 0");
        }
        List<Ingredient> ingredients = ingredientCrudOperation.getAll();
        if(priceMiniFilter == null){
            return mappingIngredients(ingredients.stream().filter(ingredient -> ingredient.getUnitPrice() <= priceMaxFilter).collect(Collectors.toList()));
        }if(priceMaxFilter == null){
            return mappingIngredients(ingredients.stream().filter(ingredient -> ingredient.getUnitPrice() >= priceMiniFilter).collect(Collectors.toList()));
        }
        return mappingIngredients(ingredients.stream().filter(ingredient -> ingredient.getUnitPrice() >= priceMiniFilter && ingredient.getUnitPrice() <= priceMaxFilter).collect(Collectors.toList()));
    }

    public ResponseEntity<?> findById(String id) {
         Ingredient ingredient = ingredientCrudOperation.findById(id);
         if(ingredient == null) {
             return ResponseEntity.status(404).body("Ingredient = <" + id + "> is not found");
         }
         return ResponseEntity.ok(ingredient);
    }

     public ResponseEntity<?> saveAll(List<Ingredient> ingredients) {
        return ResponseEntity.ok(ingredientCrudOperation.saveAll(ingredients));
     }

     public ResponseEntity addPrice(String ingredient_id, List<CreatePrice> prices) {
        return ingredientCrudOperation.addPrice(ingredient_id, prices);
     }

     public ResponseEntity addStock(String ingredient_id, List<CreateStock> stocks) {
        return ingredientCrudOperation.addStock(ingredient_id, stocks);
     }

     public List<IngredientMapper> mappingIngredients(List<Ingredient> ingredients) {
        List<IngredientMapper> ingredientMappers = new ArrayList<>();
        for (Ingredient ingredient : ingredients) {
            List<Stock> stocks = ingredientCrudOperation.getAvailableStocks(ingredient.getId());
            List<StockRest> stockRests = new ArrayList<>();
            for (Stock stock : stocks) {
                stockRests.add(
                        new StockRest(
                                stock.getId(),
                                stock.getMovement(),
                                stock.getQuantity(),
                                stock.getUnity(),
                                stock.getDate()
                        )
                );
            }
            ingredientMappers.add(
                    new IngredientMapper(
                            ingredient.getId(),
                            ingredient.getName(),
                            ingredientCrudOperation.getIngredientPrice(ingredient.getId()).getUnit_price(),
                            ingredientCrudOperation.getAllIngredientPrice(ingredient.getId()),
                            stockRests,
                            ingredient.getUnity()
                    )
            );
        }
        return ingredientMappers;
     }
}
