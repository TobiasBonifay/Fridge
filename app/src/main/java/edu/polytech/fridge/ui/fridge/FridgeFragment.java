package edu.polytech.fridge.ui.fridge;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import edu.polytech.fridge.databinding.FragmentFridgeBinding;

public class FridgeFragment extends Fragment {

    private FragmentFridgeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FridgeViewModel fridgeViewModel = new ViewModelProvider(this).get(FridgeViewModel.class);

        binding = FragmentFridgeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        fridgeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        instantiateSimpleView();

        return root;
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