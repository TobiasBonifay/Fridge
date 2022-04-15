package edu.polytech.fridge.ui.fridge.view;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import edu.polytech.fridge.databinding.ItemSimpleItemviewBinding;

/**
 * Content of a food item
 */
public class FoodViewHolder extends RecyclerView.ViewHolder {
    private final TextView foodName_TV;
    private final ImageView foodImage_IMG;
    private final TextView expirationDate_TV;
    private final TextView currentQuantity_TV;
    private final ImageView incrementQuantity_IB;
    private final ImageView decrementQuantity_IB;
    private final ImageView donate_Btn;

    public FoodViewHolder(final ItemSimpleItemviewBinding binding) {
        super(binding.getRoot());
        // food characteristics
        foodName_TV = binding.foodName;
        foodImage_IMG = binding.foodImage;
        expirationDate_TV = binding.foodExpirationDate;
        // quantities
        incrementQuantity_IB = binding.decrementQuantity;
        currentQuantity_TV = binding.currentQuantity;
        decrementQuantity_IB = binding.incrementQuantity;
        // donate
        donate_Btn = binding.donate;
    }

    public void bindData(final FoodViewModel viewModel) {
        foodName_TV.setText(viewModel.getFoodName());
        foodImage_IMG.setImageResource(viewModel.getFoodImage());
        expirationDate_TV.setText(viewModel.getExpirationDate());
        currentQuantity_TV.setText(String.valueOf(viewModel.getCurrentQuantity()));
    }
}