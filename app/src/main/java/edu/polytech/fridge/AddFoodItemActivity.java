package edu.polytech.fridge;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import edu.polytech.fridge.databinding.ActivityAddFoodItemBinding;
import edu.polytech.fridge.factory.Ingredient;
import edu.polytech.fridge.fridge.data.FoodItemParcelable;
import edu.polytech.fridge.fridge.data.Fridge;

public class AddFoodItemActivity extends AppCompatActivity {
    private ActivityAddFoodItemBinding binding;
    private Button cancel, add;
    private ImageView foodImage;
    private TextView foodName;

    public AddFoodItemActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddFoodItemBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Ingredient foodToAddCompleted = getFoodItemToAdd();
        cancel = view.findViewById(R.id.Cancel);
        add = view.findViewById(R.id.addIngredient);
        foodImage = view.findViewById(R.id.food_image_add);
        foodName = view.findViewById(R.id.food_name_add);
        foodImage.setImageResource(foodToAddCompleted.getFoodImage());
        foodName.setText(foodToAddCompleted.getFoodName());
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFoodInFridge(foodToAddCompleted);
            }
        });




    }

    @NonNull
    private Ingredient getFoodItemToAdd() {
        FoodItemParcelable foodItemParcelableToAdd = getIntent().getParcelableExtra("food");
        String expirationDate = "27/04/2022";
        int quantity = 1;
        return new Ingredient(
                foodItemParcelableToAdd.getFoodType(),
                foodItemParcelableToAdd.getFoodName(),
                foodItemParcelableToAdd.getFoodImage(),
                expirationDate,
                quantity

        );
    }

    private void addFoodInFridge(Ingredient foodToAdd) {
        // add to json
        Fridge.getInstance().addFoodOnFridge(foodToAdd);
        Log.d("ITEMFOOD", "addFoodOnFridge " + Fridge.getInstance().getFoodList().toString());
        startActivity(new Intent(this, MainActivity.class));
    }
}