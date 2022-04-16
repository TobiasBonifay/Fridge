package edu.polytech.fridge.ui.fridge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import edu.polytech.fridge.MainActivity;
import edu.polytech.fridge.R;
import edu.polytech.fridge.ui.fridge.data.Fridge;
import edu.polytech.fridge.ui.fridge.findfoods.FindFoodViewModel;
import edu.polytech.fridge.ui.fridge.view.FoodViewModel;

public class FridgeAddFoodItemActivity extends AppCompatActivity implements Parcelable {
    private final String foodName;
    private final int foodImage;

    public FridgeAddFoodItemActivity(String foodName, int foodImage) {
        // should never be called
        this.foodName = foodName;
        this.foodImage = foodImage;
    }

    protected FridgeAddFoodItemActivity(Parcel in) {
        foodName = in.readString();
        foodImage = in.readInt();
    }

    public static final Creator<FridgeAddFoodItemActivity> CREATOR = new Creator<FridgeAddFoodItemActivity>() {
        @Override
        public FridgeAddFoodItemActivity createFromParcel(Parcel in) {
            return new FridgeAddFoodItemActivity(in);
        }

        @Override
        public FridgeAddFoodItemActivity[] newArray(int size) {
            return new FridgeAddFoodItemActivity[size];
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food_item);

        FindFoodViewModel foodToAdd = getIntent().getExtras().getParcelable("food");
        String expirationDate = "27/08/2025";
        int quantity = 1;

        FoodViewModel foodToAddCompleted = new FoodViewModel(
                foodToAdd.getFoodName(),
                foodToAdd.getFoodImage(),
                expirationDate,
                quantity
        );
        addFoodInFridge(foodToAddCompleted);
    }

    private void addFoodInFridge(FoodViewModel foodToAdd) {
        FoodViewModel newFood = new FoodViewModel(
                foodToAdd.getFoodName(),
                foodToAdd.getFoodImage(),
                foodToAdd.getExpirationDate(),
                foodToAdd.getCurrentQuantity());
        Fridge.getInstance().addFoodOnFridge(newFood);
        startActivity(new Intent(this, MainActivity.class));
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