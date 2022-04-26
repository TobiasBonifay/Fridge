package edu.polytech.fridge.fridge.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import edu.polytech.fridge.databinding.ItemFindFoodItemviewBinding;
import edu.polytech.fridge.fridge.data.FridgeFindFoodsRecyclerViewInterface;
import edu.polytech.fridge.fridge.model.FindFoodViewModel;

/**
 * Content of a food item
 */
public class FindFoodView extends RecyclerView.ViewHolder {
    private final TextView foodName_TV;
    private final ImageView foodImage_IMG;

    public FindFoodView(final ItemFindFoodItemviewBinding binding, final FridgeFindFoodsRecyclerViewInterface fridgeFindFoodsRecyclerViewInterface) {
        super(binding.getRoot());
        View v = binding.getRoot();
        // food characteristics
        foodName_TV = binding.foodName;
        foodImage_IMG = binding.foodImage;
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fridgeFindFoodsRecyclerViewInterface != null) {
                    int pos = getBindingAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        fridgeFindFoodsRecyclerViewInterface.OnItemClick(pos);
                    }
                }
            }
        });
    }

    public void bindData(final FindFoodViewModel viewModel) {
        foodName_TV.setText(viewModel.getFoodName());
        foodImage_IMG.setImageResource(viewModel.getFoodImage());
    }
}