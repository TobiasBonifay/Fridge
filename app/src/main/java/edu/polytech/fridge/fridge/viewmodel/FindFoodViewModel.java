package edu.polytech.fridge.fridge.viewmodel;

import androidx.annotation.NonNull;

/**
 * Represent a food item fetched from database
 * Used by the user to add a new item on the fridge
 */
public class FindFoodViewModel {
    private String foodName;
    private int foodImage;
    private String foodType;

    public FindFoodViewModel(@NonNull final String foodName, final int foodImage,final String foodType) {
        setFoodName(foodName);
        setFoodImage(foodImage);
        setFoodType(foodType);
    }

    public int getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(int foodImage) {
        this.foodImage = foodImage;
    }

    @NonNull
    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(@NonNull final String foodName) {
        this.foodName = foodName;
    }
    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(@NonNull final String foodType) {
        this.foodType = foodType;
    }
}