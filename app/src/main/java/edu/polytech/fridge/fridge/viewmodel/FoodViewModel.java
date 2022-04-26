package edu.polytech.fridge.fridge.viewmodel;

import androidx.annotation.NonNull;

/**
 * Represent a food item
 * Defined by name, expirationDate, picture and currentQuantity
 */
public class FoodViewModel {
    private String foodName;
    private int foodImage;
    private String expirationDate;
    private int currentQuantity;

    public FoodViewModel(@NonNull final String foodName, final int foodImage, @NonNull final String expirationDate, int currentQuantity) {
        setFoodName(foodName);
        setFoodImage(foodImage);
        setExpirationDate(expirationDate);
        setCurrentQuantity(currentQuantity);
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

    @NonNull
    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(@NonNull final String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getCurrentQuantity() {
        return currentQuantity;
    }

    public void setCurrentQuantity(int currentQuantity) {
        this.currentQuantity = currentQuantity;
    }
}