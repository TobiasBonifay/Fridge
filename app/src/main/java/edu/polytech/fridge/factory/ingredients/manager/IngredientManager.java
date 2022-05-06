package edu.polytech.fridge.factory.ingredients.manager;

import edu.polytech.fridge.factory.Ingredient;
import edu.polytech.fridge.factory.IngredientFactory;
import edu.polytech.fridge.factory.ingredients.type.CannedFood;
import edu.polytech.fridge.factory.ingredients.type.Dairy;
import edu.polytech.fridge.factory.ingredients.type.Fruit;
import edu.polytech.fridge.factory.ingredients.type.Liquid;
import edu.polytech.fridge.factory.ingredients.type.Meal;
import edu.polytech.fridge.factory.ingredients.type.Meat;
import edu.polytech.fridge.factory.ingredients.type.Vegetable;

public class IngredientManager extends IngredientFactory {
    @Override
    public Ingredient put(String type, String foodName, int foodImage, String expirationDate, int currentQuantity) throws Throwable {
        switch (type) {
            case "fruit":
                return new Fruit(foodName, foodImage, expirationDate, currentQuantity);
            case "vegetable":
                return new Vegetable(foodName, foodImage, expirationDate, currentQuantity);
            case "dairy":
                return new Dairy(foodName, foodImage, expirationDate, currentQuantity);
            case "liquid":
                return new Liquid(foodName, foodImage, expirationDate, currentQuantity);
            case "cannedfood":
                return new CannedFood(foodName, foodImage, expirationDate, currentQuantity);
            case "meat":
                return new Meat(foodName, foodImage, expirationDate, currentQuantity);
            case "meal":
                return new Meal(foodName, foodImage, expirationDate, currentQuantity);
            default:
                throw new Throwable("err");
        }
    }
}
