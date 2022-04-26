package edu.polytech.fridge.fridge.view;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import edu.polytech.fridge.databinding.ActivityAddFoodItemBinding;
import edu.polytech.fridge.fridge.data.FridgeFindFoodsRecyclerViewInterface;
import edu.polytech.fridge.fridge.model.AddFoodItemModel;

/**
 * Content of a food item
 */
public class AddFoodItemView extends RecyclerView.ViewHolder {
    private final ImageView food_image_add;
    private final TextView food_name_add;
    private Button cancel, addIngredient;

    public AddFoodItemView(final ActivityAddFoodItemBinding binding, final FridgeFindFoodsRecyclerViewInterface fridgeFindFoodsRecyclerViewInterface) {
        super(binding.getRoot());
        View v = binding.getRoot();
        // food characteristics
        food_name_add = binding.foodNameAdd;
        food_image_add = binding.foodImageAdd;
        cancel = binding.Cancel;
        addIngredient = binding.addIngredient;
    }

    public void bindData(final AddFoodItemModel viewModel) {
        food_name_add.setText(viewModel.getFoodName());
        food_image_add.setImageResource(viewModel.getFoodImage());
    }
}