package edu.polytech.fridge.factory.ingredientype;

import edu.polytech.fridge.factory.Ingredient;

public class CannedFood extends Ingredient {
    public CannedFood( String foodName, int foodImage, String expirationDate, int currentQuantity) {
        super(4, foodName, foodImage, expirationDate, currentQuantity);
    }
}
