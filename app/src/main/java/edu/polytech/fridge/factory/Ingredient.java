package edu.polytech.fridge.factory;

import androidx.annotation.NonNull;

public class Ingredient {
    private String foodName;
    private  String type;
    private  int foodImage;
    private String expirationDate;
    private  int currentQuantity;

    public Ingredient(String type, String foodName, int foodImage, String expirationDate, int currentQuantity) {
        this.foodName = foodName;
        this.foodImage = foodImage;
        this.expirationDate = expirationDate;
        this.currentQuantity = currentQuantity;
        this.type = type;
    }

    public String getFoodName() {
        return this.foodName;
    }

    public String getExpirationDate() {
        return this.expirationDate;
    }

    public int getFoodImage() {
        return this.foodImage;
    }

    public int getCurrentQuantity() {
        return this.currentQuantity;
    }

    public String getType() {
        return this.type;
    }

    public void setFoodImage(int foodImage) {
        this.foodImage = foodImage;
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
