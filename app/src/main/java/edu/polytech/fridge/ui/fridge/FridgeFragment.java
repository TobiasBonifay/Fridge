package edu.polytech.fridge.ui.fridge;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import edu.polytech.fridge.R;
import edu.polytech.fridge.databinding.FragmentFridgeBinding;
import edu.polytech.fridge.ui.fridge.view.FoodAdapter;
import edu.polytech.fridge.ui.fridge.view.FoodViewModel;

public class FridgeFragment extends Fragment {
    private FragmentFridgeBinding binding;
    private FoodAdapter foodAdapter;
    private RecyclerView recyclerView;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentFridgeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        FridgeContent.generateFridgeTemplateWithFakeFoods();

        setUpSearchView();
        setUpFridgeContent();
        setUpAddFoodButton();

        return root;
    }

    private void setUpAddFoodButton() {
        ImageButton imageButtonAddFood = binding.addFood;
        imageButtonAddFood.setOnClickListener(view -> addFoodOnFridge());
    }

    private void setUpFridgeContent() {
        foodAdapter = new FoodAdapter(FridgeContent.getInstance().getFoodList());
        recyclerView = binding.simpleRecyclerview;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(foodAdapter);
    }

    private void setUpSearchView() {
        SearchView searchView = binding.searchView;
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
        for (FoodViewModel food: FridgeContent.getInstance().getFoodList()) {
            if (food.getFoodName().toLowerCase(Locale.ROOT).contains(text.toLowerCase(Locale.ROOT))) {
                filteredList.add(food);
            }
        }
        if (filteredList.isEmpty()) {
            Toast.makeText(getContext(), "No food items", Toast.LENGTH_SHORT).show();
        } else {
            recyclerView.setAdapter(new FoodAdapter(filteredList));
        }
    }

    public void addFoodOnFridge() {
        FoodViewModel newFood = new FoodViewModel("Carrot", R.drawable.ic_carrot, "27/04/2022", 1);
        FridgeContent.getInstance().addFoodOnFridge(newFood, 1);
        foodAdapter.notifyItemInserted(FridgeContent.getInstance().getFoodList().size());
        recyclerView.setAdapter(new FoodAdapter(FridgeContent.getInstance().getFoodList()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}