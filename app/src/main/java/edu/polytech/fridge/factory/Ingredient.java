package edu.polytech.fridge.factory;

public class Ingredient {
    private String foodName;
    private int type;
    private int foodImage;
    private String expirationDate;
    private int currentQuantity;
    public Ingredient(int type , String foodName,int foodImage,String expirationDate,int currentQuantity){
        this.foodImage = foodImage;
        this.foodImage=foodImage;
        this.expirationDate=expirationDate;
        this.currentQuantity=currentQuantity;
        this.type = type;
    }
    public String getFoodName(){
        return this.foodName;
    }
    public String getExpirationDate(){
        return this.expirationDate;
    }
    public  int getFoodImage(){
        return this.foodImage;
    }
    public  int getCurrentQuantity(){
        return this.foodImage;
    }
    public int getType(){
        return this.type;
    }
}
