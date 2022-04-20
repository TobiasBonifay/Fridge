package edu.polytech.fridge.ui.fridge;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import edu.polytech.fridge.MainActivity;
import edu.polytech.fridge.R;
import edu.polytech.fridge.ui.fridge.data.FoodItemParcelable;
import edu.polytech.fridge.ui.fridge.data.Fridge;
import edu.polytech.fridge.ui.fridge.view.FoodViewModel;

public class FridgeAddFoodItemActivity extends AppCompatActivity {

    public FridgeAddFoodItemActivity(){}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food_item);

        FoodViewModel foodToAddCompleted = getFoodItemToAdd();
        addFoodInFridge(foodToAddCompleted);
    }

    @NonNull
    private FoodViewModel getFoodItemToAdd() {
        FoodItemParcelable foodItemParcelableToAdd = getIntent().getParcelableExtra("food");
        String expirationDate = "27/04/2022";
        int quantity = 2;
        return new FoodViewModel(
                foodItemParcelableToAdd.getFoodName(),
                foodItemParcelableToAdd.getFoodImage(),
                expirationDate,
                quantity
        );
    }

    private void addFoodInFridge(FoodViewModel foodToAdd) {
        // add to json
        Fridge.getInstance().addFoodOnFridge(foodToAdd);
        Log.d("ITEMFOOD", "addFoodOnFridge " + Fridge.getInstance().getFoodList().toString());
        startActivity(new Intent(this, MainActivity.class));
    }
}