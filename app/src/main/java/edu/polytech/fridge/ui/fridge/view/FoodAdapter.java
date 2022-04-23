package edu.polytech.fridge.ui.fridge.view;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import edu.polytech.fridge.R;
import edu.polytech.fridge.databinding.ItemFridgeItemviewBinding;
import edu.polytech.fridge.ui.fridge.model.FoodViewModel;

/**
 * Adapter to put food on the RecyclerView aka the fridge content
 */
public class FoodAdapter extends RecyclerView.Adapter {
    private final List<FoodViewModel> models = new ArrayList<>();

    /**
     * Contains Food elements for fridge content
     * @param viewModels Food items
     */
    public FoodAdapter(final List<FoodViewModel> viewModels) {
        if (viewModels != null) this.models.addAll(viewModels);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final ItemFridgeItemviewBinding itemFridgeItemviewBinding = ItemFridgeItemviewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new FoodView(itemFridgeItemviewBinding);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        ((FoodView) holder).bindData(models.get(position));
        holder.itemView.startAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.anim_fridge_recyclerview));
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
        return R.layout.item_fridge_itemview;
    }
}

