package edu.polytech.fridge.fridge.data;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import edu.polytech.fridge.R;
import edu.polytech.fridge.fridge.viewmodel.FoodViewModel;


/**
 * Singleton class to store fridge data/content
 */
public class Fridge {
    private static Fridge instance;
    private List<FoodViewModel> foodList = new ArrayList<>();

    private Fridge() {

    }

    public static synchronized Fridge getInstance() {
        if (instance == null) {
            instance = new Fridge();
        }
        return instance;
    }

    public static void generateFridgeTemplateWithFakeFoods() {
        List<FoodViewModel> foodViewModelList = new ArrayList<>();

        // fetch data from FireBase
        FoodViewModel aliment = new FoodViewModel("Carrot", R.drawable.ic_carrot, "27/04/2022", 4);
        FoodViewModel aliment2 = new FoodViewModel("Pear", R.drawable.ic_pear, "24/04/2022", 2);
        FoodViewModel aliment3 = new FoodViewModel("Pasta", R.drawable.ic_spaghetti, "29/04/2022", 1);
        FoodViewModel aliment4 = new FoodViewModel("Toxic Pasta", R.drawable.ic_spaghetti, "09/04/2017", 1);

        foodViewModelList.add(aliment);
        foodViewModelList.add(aliment2);
        foodViewModelList.add(aliment3);
        foodViewModelList.add(aliment4);
        // return foodViewModelList;
        Fridge.getInstance().setFoodList(foodViewModelList);
    }

    public List<FoodViewModel> getFoodList() {
        return foodList;
    }

    public void setFoodList(final List<FoodViewModel> foodList) {
        this.foodList = foodList;
    }

    /**
     * stack same food item
     *
     * @param food Food to add
     */
    public void addFoodOnFridge(final FoodViewModel food) {
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

    public void removeFoodOnFridge(final FoodViewModel food) {
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
    private boolean isAlreadyHere(FoodViewModel foodToAdd) {
        return foodList.stream().anyMatch(f -> isSameFood(f, foodToAdd));
    }

    public void changeFoodQuantity(FoodViewModel food, int increment) {
        if (food == null || increment == 0) return;
        if (food.getCurrentQuantity() <= 0 && increment < 0) return;
        foodList.forEach(f -> {
            if (f == food) {
                f.setCurrentQuantity(f.getCurrentQuantity() + increment);
            }
        });
        if (food.getCurrentQuantity() == 0) removeFoodOnFridge(food);
    }

    private boolean isSameFood(FoodViewModel f1, FoodViewModel f2) {
        if (f1 == f2) return true;
        return f1.getFoodName().equals(f2.getFoodName())
                && f1.getExpirationDate().equals(f2.getExpirationDate());
    }
}
