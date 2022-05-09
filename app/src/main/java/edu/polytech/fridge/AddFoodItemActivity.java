package edu.polytech.fridge;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import edu.polytech.fridge.databinding.ActivityAddFoodItemBinding;
import edu.polytech.fridge.factory.Ingredient;
import edu.polytech.fridge.fridge.data.FoodItemParcelable;
import edu.polytech.fridge.fridge.data.Fridge;

public class AddFoodItemActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityAddFoodItemBinding binding;
    private Button cancel, add;
    private ImageView foodImage;
    private TextView foodName;
    private EditText quantity;
    private EditText purchase;
    private EditText expiration;
    private EditText weight;
    private int mYear, mMonth, mDay;


    public AddFoodItemActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddFoodItemBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        cancel = view.findViewById(R.id.Cancel);
        add = view.findViewById(R.id.addIngredient);
        foodImage = view.findViewById(R.id.food_image_add);
        foodName = view.findViewById(R.id.food_name_add);
        quantity = view.findViewById(R.id.Quantity_Input);
        purchase = view.findViewById(R.id.purchase_date_Input);
        expiration = view.findViewById(R.id.expiration_date_input);
        weight = view.findViewById(R.id.weight_input);

        Ingredient foodToAdd = getFoodItemToAdd();
        if (foodToAdd == null) {
            Toast.makeText(getApplicationContext(), "Err", Toast.LENGTH_SHORT).show();
        }
        foodImage.setImageResource(foodToAdd.getFoodImage());
        foodName.setText(foodToAdd.getFoodName());
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        add.setOnClickListener(view1 -> {
            String expirationDate = expiration.getText().toString();
            int itemsQuantity = Math.max(Integer.parseInt(quantity.getText().toString()), 1);
            foodToAdd.setCurrentQuantity(itemsQuantity);
            foodToAdd.setExpirationDate(expirationDate);
            addFoodInFridge(foodToAdd);
        });
        expiration.setOnClickListener(this);
        purchase.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == expiration || v == purchase) {
            final Calendar calendar = Calendar.getInstance();
            mYear = calendar.get(Calendar.YEAR);
            mMonth = calendar.get(Calendar.MONTH);
            mDay = calendar.get(Calendar.DAY_OF_MONTH);
            //show dialog
            if (v == expiration) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        this,
                        (view, year, month, dayOfMonth) -> expiration.setText(dayOfMonth + "/" + (month + 1) + "/" + year),
                        mYear, mMonth, mDay);
                datePickerDialog.show();

            }
            if (v == purchase) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        this,
                        (view, year, month, dayOfMonth) -> purchase.setText(dayOfMonth + "/" + (month + 1) + "/" + year),
                        mYear, mMonth, mDay);
                datePickerDialog.show();

            }

        }
    }

    @NonNull
    private Ingredient getFoodItemToAdd() {
        FoodItemParcelable foodItemParcelableToAdd = getIntent().getParcelableExtra("food");
        // on ne connait pas encore les infos je crois
        String expirationDate = "DD/MM/YYYY";
        int itemsQuantity = 1;
        return new Ingredient(
                foodItemParcelableToAdd.getFoodType(),
                foodItemParcelableToAdd.getFoodName(),
                foodItemParcelableToAdd.getFoodImage(),
                expirationDate,
                itemsQuantity

        );
    }

    private void addFoodInFridge(Ingredient foodToAdd) {
        // add to json
        Fridge.getInstance().addFoodOnFridge(foodToAdd);
        Log.d("ITEMFOOD", "addFoodOnFridge " + Fridge.getInstance().getFoodList().toString());
        startActivity(new Intent(this, MainActivity.class));
    }
}