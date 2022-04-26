package edu.polytech.fridge;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFindFoodBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        setUp();
        setUpSearchView();
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
        FindFoodViewModel aliment = new FindFoodViewModel("Carrot", R.drawable.ic_carrot);
        FindFoodViewModel aliment2 = new FindFoodViewModel("Pear", R.drawable.ic_pear);
        FindFoodViewModel aliment3 = new FindFoodViewModel("Pasta", R.drawable.ic_spaghetti);
        FindFoodViewModel aliment4 = new FindFoodViewModel("Toxic Pasta", R.drawable.ic_spaghetti);
        FindFoodViewModel aliment5 = new FindFoodViewModel("Database item", R.drawable.ic_spaghetti);

        items.add(aliment);
        items.add(aliment2);
        items.add(aliment3);
        items.add(aliment4);
        items.add(aliment5);
        return items;
    }

    private void filterList(String text) {
        List<FindFoodViewModel> filteredList = new ArrayList<>();

        for (FindFoodViewModel food : foodItemsAvailable) {
            if (food.getFoodName().toLowerCase(Locale.ROOT).contains(text.toLowerCase(Locale.ROOT)))
                filteredList.add(food);
        }
        recyclerViewToDisplayAvailableItems.setAdapter(new FindFoodAdapter(filteredList, this));
    }

    public void addFoodOnFridge(FindFoodViewModel foodToAdd) {
        Intent addFoodIntent = new Intent(this, AddFoodItemActivity.class);
        addFoodIntent.putExtra("food", new FoodItemParcelable(foodToAdd.getFoodName(), foodToAdd.getFoodImage()));
        startActivity(addFoodIntent);
    }


    @Override
    public void OnItemClick(int position) {
        addFoodOnFridge(foodItemsAvailable.get(position));
    }
}