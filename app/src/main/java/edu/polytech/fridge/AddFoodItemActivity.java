package edu.polytech.fridge;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import edu.polytech.fridge.databinding.ActivityAddFoodItemBinding;
import edu.polytech.fridge.fridge.data.FoodItemParcelable;
import edu.polytech.fridge.fridge.data.Fridge;
import edu.polytech.fridge.fridge.viewmodel.FoodViewModel;

public class AddFoodItemActivity extends AppCompatActivity {
    private ActivityAddFoodItemBinding binding;

    public AddFoodItemActivity(){}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddFoodItemBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


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
        // startActivity(new Intent(this, MainActivity.class));
    }
}