package edu.polytech.fridge.fridge.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.polytech.fridge.R;
import edu.polytech.fridge.databinding.ItemFindFoodItemviewBinding;
import edu.polytech.fridge.fridge.data.FridgeFindFoodsRecyclerViewInterface;
import edu.polytech.fridge.fridge.viewmodel.FindFoodViewModel;
import edu.polytech.fridge.fridge.view.FindFoodView;

/**
 * Adapter to put food on the RecyclerView aka the fridge content
 */
public class FindFoodAdapter extends RecyclerView.Adapter {
    private final List<FindFoodViewModel> models = new ArrayList<>();
    private final FridgeFindFoodsRecyclerViewInterface findFoodsRecyclerViewInterface;

    /**
     * Contains Food elements for fridge content
     *
     * @param viewModels Food items
     */
    public FindFoodAdapter(final List<FindFoodViewModel> viewModels, FridgeFindFoodsRecyclerViewInterface findFoodsRecyclerViewInterface) {
        if (viewModels != null) this.models.addAll(viewModels);
        this.findFoodsRecyclerViewInterface = findFoodsRecyclerViewInterface;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final ItemFindFoodItemviewBinding itemFindFoodItemviewBinding = ItemFindFoodItemviewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new FindFoodView(itemFindFoodItemviewBinding, findFoodsRecyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        ((FindFoodView) holder).bindData(models.get(position));
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

