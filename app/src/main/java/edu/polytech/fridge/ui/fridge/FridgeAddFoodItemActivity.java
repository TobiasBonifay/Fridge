package edu.polytech.fridge.ui.fridge;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import edu.polytech.fridge.MainActivity;
import edu.polytech.fridge.R;
import edu.polytech.fridge.ui.fridge.data.FoodItemParcelable;
import edu.polytech.fridge.ui.fridge.data.Fridge;
import edu.polytech.fridge.ui.fridge.view.FoodViewModel;

public class FridgeAddFoodItemActivity extends AppCompatActivity {
    Button add,cancel;
    Intent intent;


    public FridgeAddFoodItemActivity(){}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food_item);
        intent = new Intent(this, MainActivity.class);
        FoodViewModel foodToAddCompleted = getFoodItemToAdd();
        add.findViewById(R.id.add_food);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFoodInFridge(foodToAddCompleted);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
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

    public void addFoodInFridge(FoodViewModel foodToAdd) {
        // add to json
        Fridge.getInstance().addFoodOnFridge(foodToAdd);
        Log.d("ITEMFOOD", "addFoodOnFridge " + Fridge.getInstance().getFoodList().toString());
        startActivity(new Intent(this, MainActivity.class));
    }
}