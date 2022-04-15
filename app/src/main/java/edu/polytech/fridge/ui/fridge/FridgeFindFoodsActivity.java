package edu.polytech.fridge.ui.fridge;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import edu.polytech.fridge.MainActivity;
import edu.polytech.fridge.R;
import edu.polytech.fridge.ui.fridge.view.FoodAdapter;
import edu.polytech.fridge.ui.fridge.view.FoodViewModel;

/**
 * Display a recyclerView from FireBase food items
 * And allow the user to add this food item in the user' fridge
 */
public class FridgeFindFoodsActivity extends AppCompatActivity {
    private FridgeFindFoodsActivity binding;
    private FoodAdapter foodAdapter;
    private RecyclerView foodItemFromDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_food);
        setUpSearchView();
    }


    private void setUpSearchView() {
        SearchView searchView = findViewById(R.id.searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filterList(s);
                return true;
            }
        });
    }

    private void filterList(String text) {
        List<FoodViewModel> filteredList = new ArrayList<>();
        for (FoodViewModel food: Fridge.getInstance().getFoodList()) {
            if (food.getFoodName().toLowerCase(Locale.ROOT).contains(text.toLowerCase(Locale.ROOT))) {
                filteredList.add(food);
            }
        }
        if (filteredList.isEmpty()) {
            Toast.makeText(this, "No food items", Toast.LENGTH_SHORT).show();
        } else {
            foodItemFromDatabase.setAdapter(new FoodAdapter(filteredList));
        }
    }

    public void addFoodOnFridge() {
        FoodViewModel newFood = new FoodViewModel("Carrot", R.drawable.ic_carrot, "27/04/2022", 1);
        Fridge.getInstance().addFoodOnFridge(newFood, 1);
        foodAdapter.notifyItemInserted(Fridge.getInstance().getFoodList().size());
        foodItemFromDatabase.setAdapter(new FoodAdapter(Fridge.getInstance().getFoodList()));
    }


}