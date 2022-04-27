package edu.polytech.fridge.factory;

public class Ingredient {
    private final String foodName;
    private final int type;
    private final int foodImage;
    private final String expirationDate;
    private final int currentQuantity;

    public Ingredient(int type, String foodName, int foodImage, String expirationDate, int currentQuantity) {
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

    public int getType() {
        return this.type;
    }
}
