package edu.polytech.fridge.factory.ingredients.type;

import edu.polytech.fridge.factory.Ingredient;

public class Meal extends Ingredient {
    public Meal( String foodName, int foodImage, String expirationDate, int currentQuantity) {
        super("meal", foodName, foodImage, expirationDate, currentQuantity);
    }
}
