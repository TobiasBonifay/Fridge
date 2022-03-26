package edu.polytech.fridge.ui.fridge.view;

import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import edu.polytech.fridge.databinding.ItemSimpleItemviewBinding;

public class SimpleViewHolder extends RecyclerView.ViewHolder {
    private final TextView simpleTextView;

    public SimpleViewHolder(final ItemSimpleItemviewBinding binding) {
        super(binding.getRoot());
        simpleTextView = binding.simpleText;
        // simpleTextView = (TextView) itemView.findViewById(R.id.simple_text);
    }

    public void bindData(final SimpleViewModel viewModel) {
        simpleTextView.setText(viewModel.getSimpleText());
    }
}