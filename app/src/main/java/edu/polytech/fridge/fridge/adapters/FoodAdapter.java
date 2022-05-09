package edu.polytech.fridge.fridge.adapters;

import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import edu.polytech.fridge.R;
import edu.polytech.fridge.databinding.ItemFridgeItemviewBinding;
import edu.polytech.fridge.factory.Ingredient;
import edu.polytech.fridge.fridge.data.Fridge;
import edu.polytech.fridge.fridge.view.FoodView;

/**
 * Adapter to put food on the RecyclerView aka the fridge content
 */
public class FoodAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ItemFridgeItemviewBinding itemFridgeItemviewBinding;
    private final List<Ingredient> models = new ArrayList<>();

    /**
     * Contains Food elements for fridge content
     *
     * @param viewModels Food items
     */
    public FoodAdapter(final List<Ingredient> viewModels) {
        if (viewModels != null) this.models.addAll(viewModels);
    }

    public void changeQuantity(int pos) {
        TextView qt = itemFridgeItemviewBinding.currentQuantity;
        ImageButton add = itemFridgeItemviewBinding.incrementQuantity;
        ImageButton remove = itemFridgeItemviewBinding.decrementQuantity;
        Ingredient ingredient = Fridge.getInstance().getFoodList().get(pos);

        Log.d("POSITION changeQuantity ", ingredient.getFoodName() + " & " + Integer.parseInt(qt.getText().toString()));
        AtomicInteger old = new AtomicInteger(ingredient.getCurrentQuantity());
        add.setOnClickListener(view -> Fridge.getInstance().getFoodList().get(pos).setCurrentQuantity(old.incrementAndGet()));
        remove.setOnClickListener(view -> Fridge.getInstance().getFoodList().get(pos).setCurrentQuantity(old.incrementAndGet()));

        Log.d("POSITION change2 ", old + " new " + ingredient.getCurrentQuantity());
        Log.d("POSITION change3 ", "" + Fridge.getInstance().getFoodList().get(pos).getCurrentQuantity());
        Log.d("POSITION change 4", "" + ingredient.getCurrentQuantity());
        // qt.setText(ingredient.getCurrentQuantity());

        notifyItemChanged(pos);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        itemFridgeItemviewBinding = ItemFridgeItemviewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        final RecyclerView.ViewHolder holder = new FoodView(itemFridgeItemviewBinding) {
            @Override
            public String toString() {
                return super.toString();
            }
        };
        itemFridgeItemviewBinding.incrementQuantity.setOnClickListener(view -> {
            int pos = holder.getAbsoluteAdapterPosition();
            changeQuantity(pos);
            Log.d("POSITION ", String.valueOf(pos));
        });
        itemFridgeItemviewBinding.decrementQuantity.setOnClickListener(view -> {
            int pos = holder.getAbsoluteAdapterPosition();
            changeQuantity(pos);
            Log.d("POSITION 2 ", String.valueOf(pos));

        });
        return holder;
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

