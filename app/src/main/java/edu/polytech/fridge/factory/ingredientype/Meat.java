package edu.polytech.fridge.factory.ingredientype;

import edu.polytech.fridge.factory.Ingredient;

public class Meat extends Ingredient {

    public Meat(String foodName, int foodImage, String expirationDate, int currentQuantity) {
        super(5, foodName, foodImage, expirationDate, currentQuantity);
    }
}
