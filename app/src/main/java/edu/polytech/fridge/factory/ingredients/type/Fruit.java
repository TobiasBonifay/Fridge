package edu.polytech.fridge.factory.ingredients.type;

import edu.polytech.fridge.factory.Ingredient;

public class Fruit extends Ingredient {
    public Fruit(String foodName, int foodImage, String expirationDate, int currentQuantity) {
        super(0, foodName, foodImage, expirationDate, currentQuantity);
    }
}
