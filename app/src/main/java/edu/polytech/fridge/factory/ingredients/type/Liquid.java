package edu.polytech.fridge.factory.ingredients.type;

import edu.polytech.fridge.factory.Ingredient;

public class Liquid extends Ingredient {
    public Liquid( String foodName, int foodImage, String expirationDate, int currentQuantity) {
        super("liquid", foodName, foodImage, expirationDate, currentQuantity);
    }
}
