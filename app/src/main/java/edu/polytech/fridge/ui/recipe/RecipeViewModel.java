package edu.polytech.fridge.ui.recipe;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RecipeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public RecipeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is recipe fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}