package edu.polytech.fridge.ui.fridge;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import edu.polytech.fridge.databinding.FragmentFridgeBinding;
import edu.polytech.fridge.ui.fridge.view.SimpleAdapter;
import edu.polytech.fridge.ui.fridge.view.SimpleViewModel;

public class FridgeFragment extends Fragment {
    private FridgeViewModel fridgeViewModel;
    private FragmentFridgeBinding binding;
    private ImageButton imageButtonAddFood;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fridgeViewModel = new ViewModelProvider(this).get(FridgeViewModel.class);

        binding = FragmentFridgeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        imageButtonAddFood = binding.addFood;
        imageButtonAddFood.setOnClickListener(view -> addFoodOnFridge());
        instantiateSimpleView();

        return root;
    }

    private void addFoodOnFridge() {
        Toast.makeText(getContext(), "Add food", Toast.LENGTH_SHORT).show();
    }

    private void instantiateSimpleView() {
        SimpleAdapter adapter = new SimpleAdapter(generateSimpleList());
        RecyclerView recyclerView = binding.simpleRecyclerview;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private List<SimpleViewModel> generateSimpleList() {
        List<SimpleViewModel> simpleViewModelList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            simpleViewModelList.add(new SimpleViewModel(String.format(Locale.US, "This item %d", i)));
        }
        return simpleViewModelList;
    }
}