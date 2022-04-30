package edu.polytech.fridge.factory.ingredients.type;

import edu.polytech.fridge.factory.Ingredient;

public class Dairy extends Ingredient {
    public Dairy( String foodName, int foodImage, String expirationDate, int currentQuantity) {
        super(2, foodName, foodImage, expirationDate, currentQuantity);
    }
}