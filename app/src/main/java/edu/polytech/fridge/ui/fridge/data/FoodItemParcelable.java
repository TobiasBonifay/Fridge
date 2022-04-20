package edu.polytech.fridge.ui.fridge.data;

import android.os.Parcel;
import android.os.Parcelable;

public class FoodItemParcelable implements Parcelable {
    private final String foodName;
    private final int foodImage;

    public FoodItemParcelable(String foodName, int foodImage) {
        this.foodName = foodName;
        this.foodImage = foodImage;
    }

    public String getFoodName() {
        return foodName;
    }

    public int getFoodImage() {
        return foodImage;
    }

    public static final Creator<FoodItemParcelable> CREATOR = new Creator<FoodItemParcelable>() {
        @Override
        public FoodItemParcelable createFromParcel(Parcel in) {
            return new FoodItemParcelable(in);
        }

        @Override
        public FoodItemParcelable[] newArray(int size) {
            return new FoodItemParcelable[size];
        }
    };
    protected FoodItemParcelable(Parcel in) {
        foodName = in.readString();
        foodImage = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(foodName);
        parcel.writeInt(foodImage);
    }
}