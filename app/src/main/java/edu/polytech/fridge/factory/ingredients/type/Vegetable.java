package edu.polytech.fridge.factory.ingredients.type;

import edu.polytech.fridge.factory.Ingredient;

public class Vegetable extends Ingredient {
    public Vegetable( String foodName, int foodImage, String expirationDate, int currentQuantity) {
        super("vegetable", foodName, foodImage, expirationDate, currentQuantity);
    }
}
