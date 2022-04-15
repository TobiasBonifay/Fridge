package edu.polytech.fridge.ui.fridge;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import edu.polytech.fridge.MainActivity;
import edu.polytech.fridge.R;
import edu.polytech.fridge.databinding.FragmentFridgeBinding;
import edu.polytech.fridge.ui.fridge.view.FoodAdapter;
import edu.polytech.fridge.ui.fridge.view.FoodViewModel;

public class FridgeFragmentFoods extends Fragment {
    private FragmentFridgeBinding binding;
    private FoodAdapter foodAdapterForUserFridge;
    private RecyclerView recyclerViewToDisplayFridgeFood;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentFridgeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Fridge.generateFridgeTemplateWithFakeFoods();

        setUpFridgeContent();
        setUpSpinner();
        setUpAddFoodButton();

        return root;
    }

    private void setUpSpinner() {
        Spinner spinner = binding.spinnerFilter;
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(
                        getContext(),
                        R.array.filter_by_fridge,
                        android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void setUpAddFoodButton() {
        FloatingActionButton faButtonAddFood = binding.addFood;
        faButtonAddFood.setOnClickListener(view -> addFoodOnFridge());
    }

    private void setUpFridgeContent() {
        foodAdapterForUserFridge = new FoodAdapter(Fridge.getInstance().getFoodList());
        recyclerViewToDisplayFridgeFood = binding.simpleRecyclerview;
        recyclerViewToDisplayFridgeFood.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewToDisplayFridgeFood.setHasFixedSize(true);
        recyclerViewToDisplayFridgeFood.setAdapter(foodAdapterForUserFridge);
    }

    private void filterList(String text) {
        List<FoodViewModel> filteredList = new ArrayList<>();
        for (FoodViewModel food: Fridge.getInstance().getFoodList()) {
            if (food.getFoodName().toLowerCase(Locale.ROOT).contains(text.toLowerCase(Locale.ROOT))) {
                filteredList.add(food);
            }
        }
        if (filteredList.isEmpty()) {
            Toast.makeText(getContext(), "No food items", Toast.LENGTH_SHORT).show();
        } else {
            recyclerViewToDisplayFridgeFood.setAdapter(new FoodAdapter(filteredList));
        }
    }

    public void addFoodOnFridge() {
        FoodViewModel newFood = new FoodViewModel("Carrot", R.drawable.ic_carrot, "27/04/2022", 1);
        Fridge.getInstance().addFoodOnFridge(newFood, 1);
        foodAdapterForUserFridge.notifyItemInserted(Fridge.getInstance().getFoodList().size());
        recyclerViewToDisplayFridgeFood.setAdapter(new FoodAdapter(Fridge.getInstance().getFoodList()));

        Intent intent = new Intent(getActivity(), FridgeFindFoodsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}