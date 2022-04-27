package edu.polytech.fridge.factory.ingredients.manager;

import edu.polytech.fridge.factory.Ingredient;
import edu.polytech.fridge.factory.IngredientFactory;
import edu.polytech.fridge.factory.ingredients.type.CannedFood;
import edu.polytech.fridge.factory.ingredients.type.Dairy;
import edu.polytech.fridge.factory.ingredients.type.Fruit;
import edu.polytech.fridge.factory.ingredients.type.Liquid;
import edu.polytech.fridge.factory.ingredients.type.Meat;
import edu.polytech.fridge.factory.ingredients.type.Vegetable;

public class IngredientManager extends IngredientFactory {
    @Override
    public Ingredient put(int type, String foodName, int foodImage, String expirationDate, int currentQuantity) throws Throwable {
        switch (type) {
            case 0:
                return new Fruit(foodName, foodImage, expirationDate, currentQuantity);
            case 1:
                return new Vegetable(foodName, foodImage, expirationDate, currentQuantity);
            case 2:
                return new Dairy(foodName, foodImage, expirationDate, currentQuantity);
            case 3:
                return new Liquid(foodName, foodImage, expirationDate, currentQuantity);
            case 4:
                return new CannedFood(foodName, foodImage, expirationDate, currentQuantity);
            case 5:
                return new Meat(foodName, foodImage, expirationDate, currentQuantity);
            default:
                throw new Throwable("err");
        }
    }
}
