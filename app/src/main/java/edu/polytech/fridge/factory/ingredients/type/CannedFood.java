package edu.polytech.fridge.factory.ingredients.type;

import edu.polytech.fridge.factory.Ingredient;

public class CannedFood extends Ingredient {
    public CannedFood( String foodName, int foodImage, String expirationDate, int currentQuantity) {
        super("cannedfood", foodName, foodImage, expirationDate, currentQuantity);
    }
}
