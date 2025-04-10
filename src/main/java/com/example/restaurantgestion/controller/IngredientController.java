package com.example.restaurantgestion.controller;

import com.example.restaurantgestion.entity.CreatePrice;
import com.example.restaurantgestion.entity.CreateStock;
import com.example.restaurantgestion.entity.Ingredient;
import com.example.restaurantgestion.repository.IngredientCrudOperation;
import com.example.restaurantgestion.service.IngredientService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class IngredientController {
    private final IngredientService ingredientService;

    @GetMapping("/ingredients")
    public ResponseEntity<?> getAllIngredients(
            @RequestParam(required = false) Double priceMiniFilter,
            @RequestParam(required = false) Double priceMaxFilter){

        if (priceMiniFilter != null && priceMaxFilter != null && priceMaxFilter < priceMiniFilter) {
            return ResponseEntity.badRequest()
                    .body("priceMinFilter (" + priceMiniFilter + ") ne peut pas être supérieur à priceMaxFilter (" + priceMaxFilter + ").");
        }

        if ((priceMiniFilter != null && priceMiniFilter < 0) || (priceMaxFilter != null && priceMaxFilter < 0)) {
            return ResponseEntity.badRequest()
                    .body("Les prix doivent être supérieurs ou égaux à 0.");
        }

        if (priceMiniFilter != null || priceMaxFilter != null) {
            return ResponseEntity.ok(ingredientService.getIngredientsFilterByDate(priceMiniFilter, priceMaxFilter));
        }

        return ResponseEntity.ok(ingredientService.getAll(1, 10));
    }

    @GetMapping("/ingredients/{id}")
    public ResponseEntity<?> getIngredientById(@PathVariable String id) {
        ResponseEntity<?> ingredient = ingredientService.findById(id);
        if (ingredient == null) {
            return ResponseEntity.status(404).body("Ingrédient = <" + id + "> is not found");
        }
        return ResponseEntity.ok(ingredient);
    }

    @PutMapping("/ingredients")
    public ResponseEntity<?> putIngredients(@RequestBody List<Ingredient> ingredients) {
        return ResponseEntity.ok(ingredientService.saveAll(ingredients));
    }

    @PostMapping("/ingredients")
    public ResponseEntity<?> postIngredient(@RequestBody List<Ingredient> ingredients) {
        return ResponseEntity.ok(ingredientService.saveAll(ingredients));
    }

    @PutMapping("/ingredients/{ingredientId}/price")
    public ResponseEntity putPrice(@PathVariable String ingredientId, @RequestBody List<CreatePrice> prices ){
        return ResponseEntity.ok(ingredientService.addPrice(ingredientId, prices));
    }

    @PutMapping("/ingredients/{ingredientId}/stockMouvement")
    public ResponseEntity putStockMouvement(@PathVariable String ingredientId, @RequestBody List<CreateStock> stocks) {
        return ResponseEntity.ok(ingredientService.addStock(ingredientId, stocks));
    }
}

