package edu.polytech.fridge.fridge.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RecipeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public RecipeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Liste de recettes");
    }

    public LiveData<String> getText() {

        //recipes = new ArrayList<>();
        mText.setValue("Chargement depuis Firebase...");

        //******
        return mText;
    }

    }