package edu.polytech.fridge.fridge.data;

import android.os.Parcel;
import android.os.Parcelable;

public class FoodItemParcelable implements Parcelable {
    private final String foodName;
    private final int foodImage;
    private  String type;

    public FoodItemParcelable(String foodName, int foodImage,String type) {
        this.foodName = foodName;
        this.foodImage = foodImage;
        this.type=type;

    }

    public String getFoodName() {
        return foodName;
    }

    public int getFoodImage() {
        return foodImage;
    }
    public String getFoodType() {
        return type;
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