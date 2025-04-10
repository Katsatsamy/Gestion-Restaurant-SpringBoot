package com.example.restaurantgestion.entity;

import java.util.Objects;

public class IngredientWithQuantity {
    private Ingredient ingredient;
    private double requiredQuantity;
    private Unity unity;

    public IngredientWithQuantity(Ingredient ingredient, double requiredQuantity, Unity unity) {
        this.ingredient = ingredient;
        this.requiredQuantity = requiredQuantity;
        this.unity = unity;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public double getRequiredQuantity() {
        return requiredQuantity;
    }

    public Unity getUnity() {
        return unity;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public void setRequiredQuantity(double requiredQuantity) {
        this.requiredQuantity = requiredQuantity;
    }

    public void setUnity(Unity unity) {
        this.unity = unity;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        IngredientWithQuantity that = (IngredientWithQuantity) o;
        return requiredQuantity == that.requiredQuantity && Objects.equals(ingredient, that.ingredient) && unity == that.unity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredient, requiredQuantity, unity);
    }

    @Override
    public String toString() {
        return "IngredientWithQuantity{" +
                "ingredient=" + ingredient +
                ", requeredquantity=" + requiredQuantity +
                ", unity=" + unity +
                '}';
    }
}