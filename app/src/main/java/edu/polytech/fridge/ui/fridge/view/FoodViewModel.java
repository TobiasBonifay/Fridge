package edu.polytech.fridge.ui.fridge.view;

import androidx.annotation.NonNull;

public class FoodViewModel {
    private String foodName;
    private String expirationDate;
    private int currentQuantity;

    public FoodViewModel(
            @NonNull final String foodName,
            @NonNull final String expirationDate,
            int currentQuantity
    ) {
        setFoodName(foodName);
        setExpirationDate(expirationDate);
        setCurrentQuantity(currentQuantity);
    }

    @NonNull
    public String getFoodName() {
        return foodName;
    }

    @NonNull
    public String getExpirationDate() {
        return expirationDate;
    }

    public int getCurrentQuantity() {
        return currentQuantity;
    }

    public void setFoodName(@NonNull final String foodName) {
        this.foodName = foodName;
    }

    public void setExpirationDate(@NonNull final String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setCurrentQuantity(int currentQuantity) {
        this.currentQuantity = currentQuantity;
    }
}