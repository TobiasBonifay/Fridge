package edu.polytech.fridge.fridge.data;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import edu.polytech.fridge.R;
import edu.polytech.fridge.factory.Ingredient;
import edu.polytech.fridge.factory.ingredients.manager.IngredientManager;
import edu.polytech.fridge.factory.ingredients.type.Fruit;
import edu.polytech.fridge.factory.ingredients.type.Meal;
import edu.polytech.fridge.factory.ingredients.type.Vegetable;


/**
 * Singleton class to store fridge data/content
 */
public class Fridge {
    private static Fridge instance;
    private List<Ingredient> foodList = new ArrayList<>();

    private Fridge() {

    }

    public static synchronized Fridge getInstance() {
        if (instance == null) {
            instance = new Fridge();
        }
        return instance;
    }

    public static void generateFridgeTemplateWithFakeFoods() throws Throwable {
        List<Ingredient> foodList = new ArrayList<>();

        // fetch data from FireBase
        Ingredient aliment = IngredientManager.put("vegetable","Carrot", R.drawable.ic_carrot, "27/05/2022", 4);
        Ingredient aliment2 = IngredientManager.put("fruit","Pear", R.drawable.ic_pear, "24/05/2022", 2);
        Ingredient aliment3 = IngredientManager.put("meal","Pasta", R.drawable.ic_spaghetti, "29/05/2022", 1);
        Ingredient aliment4 = IngredientManager.put("meal","Leek", R.drawable.ic_leek_svgrepo_com, "09/05/2021", 6);

        foodList.add(aliment);
        foodList.add(aliment2);
        foodList.add(aliment3);
        foodList.add(aliment4);
        // return foodList;
        Fridge.getInstance().setFoodList(foodList);
    }

    public List<Ingredient> getFoodList() {
        return foodList;
    }

    public void setFoodList(final List<Ingredient> foodList) {
        this.foodList = foodList;
    }

    /**
     * stack same food item
     *
     * @param food Food to add
     */
    public void addFoodOnFridge(final Ingredient food) {
        Log.d("ITEMFOOD", "addFoodOnFridgeInit " + food.getFoodName());
        if (isAlreadyHere(food)) {
            Log.d("ITEMFOOD", "already HERE " + food.getFoodName());
            foodList.forEach(alreadyIn -> {
                if (isSameFood(food, alreadyIn)) {
                    alreadyIn.setCurrentQuantity(alreadyIn.getCurrentQuantity() + food.getCurrentQuantity());
                }
                foodList.remove(food); // if present remove it
            });
        } else {
            Log.d("ITEMFOOD", "not HERE " + food.getFoodName() + food.getFoodImage());
            foodList.add(food);
        }
    }

    public void removeFoodOnFridge(final Ingredient food) {
        foodList.remove(food);
    }

    private void clearFridge() {
        foodList.clear();
    }

    /**
     * If a food is already here in the fridge, and have the same expiration date, then stack it
     *
     * @param foodToAdd
     * @return The potential duplicated food
     */
    private boolean isAlreadyHere(Ingredient foodToAdd) {
        return foodList.stream().anyMatch(f -> isSameFood(f, foodToAdd));
    }

    public void changeFoodQuantity(Ingredient food, int increment) {
        if (food == null || increment == 0) return;
        if (food.getCurrentQuantity() <= 0 && increment < 0) return;
        foodList.forEach(f -> {
            if (f == food) {
                f.setCurrentQuantity(f.getCurrentQuantity() + increment);
            }
        });
        if (food.getCurrentQuantity() == 0) removeFoodOnFridge(food);
    }

    private boolean isSameFood(Ingredient f1, Ingredient f2) {
        if (f1 == f2) return true;
        return f1.getFoodName().equals(f2.getFoodName())
                && f1.getExpirationDate().equals(f2.getExpirationDate());
    }
}
