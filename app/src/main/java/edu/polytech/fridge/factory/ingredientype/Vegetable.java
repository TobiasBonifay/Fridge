package edu.polytech.fridge.factory.ingredientype;

import edu.polytech.fridge.factory.Ingredient;

public class Vegetable extends Ingredient {
    public Vegetable( String foodName, int foodImage, String expirationDate, int currentQuantity) {
        super(1, foodName, foodImage, expirationDate, currentQuantity);
    }
}
