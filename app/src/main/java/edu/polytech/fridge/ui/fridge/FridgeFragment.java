package edu.polytech.fridge.ui.fridge;

import android.content.ClipData;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
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
    private SearchView searchView;

    private List<FoodViewModel> foods;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FridgeViewModel fridgeViewModel = new ViewModelProvider(this).get(FridgeViewModel.class);

        binding = FragmentFridgeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        searchView = binding.searchView;
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

        foods = generateFridgeTemplateWithFakeFoods();
        instantiateSimpleView(foods);

        ImageButton imageButtonAddFood = binding.addFood;
        imageButtonAddFood.setOnClickListener(view -> addFoodOnFridge());

        return root;
    }

    private void filterList(String text) {
        List<FoodViewModel> filteredList = new ArrayList<>();
        for (FoodViewModel food: foods) {
            if (food.getFoodName().toLowerCase(Locale.ROOT).contains(text.toLowerCase(Locale.ROOT))) {
                filteredList.add(food);
            }
        }
        if (filteredList.isEmpty()) {
            Toast.makeText(getContext(), "No food items", Toast.LENGTH_SHORT).show();
        } else {
            FoodAdapter foodAdapterFiltered = new FoodAdapter(filteredList);
            recyclerView.setAdapter(foodAdapterFiltered);
        }
    }

    private void addFoodOnFridge() {
        // YEAH IT'S TRASH
        FoodViewModel newFood = new FoodViewModel("new", R.drawable.ic_carrot, "27/04/2022", 1);
        foods.add(newFood);
        instantiateSimpleView(foods);
    }

    private void instantiateSimpleView(List<FoodViewModel> foods) {
        foodAdapter = new FoodAdapter(foods);
        recyclerView = binding.simpleRecyclerview;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(foodAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private List<FoodViewModel> generateFridgeTemplateWithFakeFoods() {
        List<FoodViewModel> foodViewModelList = new ArrayList<>();

        // fetch data from FireBase
        FoodViewModel aliment = new FoodViewModel("Carrot", R.drawable.ic_carrot , "27/04/2022", 4);
        FoodViewModel aliment2 = new FoodViewModel("Pear", R.drawable.ic_pear, "24/04/2022", 2);
        FoodViewModel aliment3 = new FoodViewModel("Pasta", R.drawable.ic_spaghetti, "29/04/2022", 1);
        FoodViewModel aliment4 = new FoodViewModel("Toxic Pasta", R.drawable.ic_spaghetti, "09/04/2017", 1);

        foodViewModelList.add(aliment);
        foodViewModelList.add(aliment2);
        foodViewModelList.add(aliment3);
        foodViewModelList.add(aliment4);
        return foodViewModelList;
    }
}