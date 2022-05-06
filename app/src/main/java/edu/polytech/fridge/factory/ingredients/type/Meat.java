package edu.polytech.fridge.factory.ingredients.type;

import edu.polytech.fridge.factory.Ingredient;

public class Meat extends Ingredient {

    public Meat(String foodName, int foodImage, String expirationDate, int currentQuantity) {
        super("meat", foodName, foodImage, expirationDate, currentQuantity);
    }
}
