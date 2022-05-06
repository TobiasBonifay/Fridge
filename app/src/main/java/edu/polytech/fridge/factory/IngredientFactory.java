package edu.polytech.fridge.factory;

public abstract class IngredientFactory {
    public abstract Ingredient put(String type, String foodName, int foodImage, String expirationDate, int currentQuantity) throws Throwable;
}
