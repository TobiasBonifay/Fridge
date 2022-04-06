package edu.polytech.fridge.ui.fridge.view;

import androidx.annotation.NonNull;

public class FoodViewModel {
    private String foodName;
    private String expirationDate;

    public FoodViewModel(
            @NonNull final String foodName,
            @NonNull final String expirationDate
    ) {
        setFoodName(foodName);
        setExpirationDate(expirationDate);
    }

    @NonNull
    public String getFoodName() {
        return foodName;
    }

    @NonNull
    public String getExpirationDate() {
        return expirationDate;
    }

    public void setFoodName(@NonNull final String foodName) {
        this.foodName = foodName;
    }

    public void setExpirationDate(@NonNull final String expirationDate) {
        this.expirationDate = expirationDate;
    }
}