package edu.polytech.fridge.ui.fridge.view;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import edu.polytech.fridge.R;
import edu.polytech.fridge.databinding.FragmentFridgeBinding;
import edu.polytech.fridge.databinding.ItemSimpleItemviewBinding;

public class FoodAdapter extends RecyclerView.Adapter {
    private final List<FoodViewModel> models = new ArrayList<>();

    public FoodAdapter(final List<FoodViewModel> viewModels) {
        if (viewModels != null) {
            this.models.addAll(viewModels);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final FragmentFridgeBinding fragmentFridgeBinding = FragmentFridgeBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        final ItemSimpleItemviewBinding itemSimpleItemviewBinding = ItemSimpleItemviewBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new FoodViewHolder(itemSimpleItemviewBinding);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        ((FoodViewHolder) holder).bindData(models.get(position));
    }


    @Override
    public int getItemCount() {
        return models.size();
    }

    /*
    “… Unlike ListView adapters, types need not be contiguous. Consider using id resources to uniquely identify item view types.”
    */
    @Override
    public int getItemViewType(int position) {
        return R.layout.item_simple_itemview;
    }
}

