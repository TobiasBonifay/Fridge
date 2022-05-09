package edu.polytech.fridge;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import edu.polytech.fridge.databinding.ActivityFindFoodBinding;
import edu.polytech.fridge.fridge.data.FoodItemParcelable;
import edu.polytech.fridge.fridge.data.FridgeFindFoodsRecyclerViewInterface;
import edu.polytech.fridge.fridge.adapters.FindFoodAdapter;
import edu.polytech.fridge.fridge.viewmodel.FindFoodViewModel;

/**
 * Display a recyclerView from FireBase food items
 * And allow the user to add this food item in the user' fridge
 */
public class FindFoodsActivity extends AppCompatActivity implements FridgeFindFoodsRecyclerViewInterface {
    private ActivityFindFoodBinding binding;
    private RecyclerView recyclerViewToDisplayAvailableItems;
    private List<FindFoodViewModel> foodItemsAvailable;
    private List<FindFoodViewModel> filteredList = new ArrayList<>();
    Button btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFindFoodBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        btn4=(Button)findViewById(R.id.button4) ;


        setUp();
        setUpSearchView();

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ScanToAdd.class));

            }
        });
    }

    private void setUp() {
        foodItemsAvailable = foodItemsFakeDatabase();
        final List<FindFoodViewModel> emptyList = new ArrayList<>();
        FindFoodAdapter findFoodAdapter = new FindFoodAdapter(emptyList, this);
        recyclerViewToDisplayAvailableItems = findViewById(R.id.find_ingredient_recyclerview);
        recyclerViewToDisplayAvailableItems.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerViewToDisplayAvailableItems.setHasFixedSize(true);
        recyclerViewToDisplayAvailableItems.setAdapter(findFoodAdapter);
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

    private static List<FindFoodViewModel> foodItemsFakeDatabase() {
        List<FindFoodViewModel> items = new ArrayList<>();
        // fetch data from FireBase
        FindFoodViewModel aliment = new FindFoodViewModel("Carrot", R.drawable.ic_carrot,"vegetable");
        FindFoodViewModel aliment2 = new FindFoodViewModel("Pear", R.drawable.ic_pear,"fruit");
        FindFoodViewModel aliment3 = new FindFoodViewModel("Pasta", R.drawable.ic_spaghetti,"meal");
        FindFoodViewModel aliment6 = new FindFoodViewModel("Meat", R.drawable.meat_outline_filled,"meal");
        FindFoodViewModel aliment7 = new FindFoodViewModel("Canned", R.drawable.canned,"meal");
        FindFoodViewModel aliment8 = new FindFoodViewModel("drink", R.drawable.drink_martini_outline_filled_var,"drink");
        FindFoodViewModel aliment9 = new FindFoodViewModel("Milk", R.drawable._593736_farm_fresh_milk_product_icon,"dairy");
        FindFoodViewModel aliment10 = new FindFoodViewModel("Juice", R.drawable._07358_orange_juice_icon,"liquid");
        FindFoodViewModel aliment11 = new FindFoodViewModel("Tomato", R.drawable._6019_vegetable_tomato_icon,"vegetable");


        items.add(aliment);
        items.add(aliment2);
        items.add(aliment3);
        items.add(aliment6);
        items.add(aliment7);
        items.add(aliment8);
        items.add(aliment9);
        items.add(aliment10);
        items.add(aliment11);

        return items;
    }

    private void filterList(String text) {
        filteredList.clear();
        for (FindFoodViewModel food : foodItemsAvailable) {
            if (food.getFoodName().toLowerCase(Locale.ROOT).contains(text.toLowerCase(Locale.ROOT)))
                filteredList.add(food);
        }
        recyclerViewToDisplayAvailableItems.setAdapter(new FindFoodAdapter(filteredList, this));
    }

    public void addFoodOnFridge(FindFoodViewModel foodToAdd) {
        Intent addFoodIntent = new Intent(this, AddFoodItemActivity.class);
        addFoodIntent.putExtra("food", new FoodItemParcelable(foodToAdd.getFoodName(), foodToAdd.getFoodImage(),foodToAdd.getFoodType()));
        startActivity(addFoodIntent);
    }

    @Override
    public void OnItemClick(int position) {
        addFoodOnFridge(filteredList.get(position));
    }
}