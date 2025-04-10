package com.example.restaurantgestion.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


public class Dish {
    private String id;
    private String name;
    private final int unitPrice;
    private List<IngredientWithQuantity> ingredients;

    public Dish(int unitPrice, String id, String name, List<IngredientWithQuantity> ingredients) {
        this.unitPrice = unitPrice;
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public List<IngredientWithQuantity> getIngredients() {
        return ingredients;
    }


    public double getIngredientsPriceTotal() {
        return getIngredients().stream()
                .mapToDouble(ingredient -> ingredient.getIngredient().getUnitPrice() * ingredient.getRequiredQuantity())
                .sum();
    }

    public double getIngredientsPriceTotal(LocalDateTime date) {
        return getIngredients().stream()
                .mapToDouble(ingredient -> ingredient.getIngredient().getUnitPrice(date) * ingredient.getRequiredQuantity())
                .sum();
    }

    public double getGrossMargin(){
        return getUnitPrice() - getIngredientsPriceTotal(LocalDateTime.now());
    }

    public double getGrossMargin(LocalDateTime date){
        return getUnitPrice() - getIngredientsPriceTotal(date);
    }

    public double getAvailableQuantity() {
        List<Double> resultList = new ArrayList<>();

        for (IngredientWithQuantity ingredient : getIngredients()) {
            resultList.add(
                    Double.valueOf(ingredient.getIngredient().getAvalaibleQuantity().getQuantity() / ingredient.getRequiredQuantity())
            );
        }
        Collections.sort(resultList);
        if(resultList.isEmpty()) return 0.0;
        return resultList.get(0);
    }

    public double getAvailableQuantity(LocalDateTime date) {
        List<Double> resultList = new ArrayList<>();

        for (IngredientWithQuantity ingredient : getIngredients()) {
            resultList.add(
                    Double.valueOf(ingredient.getIngredient().getAvalaibleQuantity(date).getQuantity() / ingredient.getRequiredQuantity())
            );
        }
        Collections.sort(resultList);
        if(resultList.isEmpty()) return 0.0;
        return resultList.get(0);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return unitPrice == dish.unitPrice && Objects.equals(id, dish.id) && Objects.equals(name, dish.name) && Objects.equals(ingredients, dish.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, unitPrice, ingredients);
    }

    @Override
    public String
    toString() {
        return "Dish{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", unitPrice=" + unitPrice +
                ", ingredients=" + ingredients +
                '}';
    }
}
