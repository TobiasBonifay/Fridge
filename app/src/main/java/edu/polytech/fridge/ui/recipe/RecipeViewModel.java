package edu.polytech.fridge.ui.recipe;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import edu.polytech.fridge.manager.RecipeManager;
import edu.polytech.fridge.models.Recipe;

public class RecipeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public RecipeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is recipe fragment");
    }

    public LiveData<String> getText() {
        String listRecipes = "Liste des recettes\n";
        for (Recipe recipe : RecipeManager.getInstance().getAllRecipesData()){
            listRecipes+= recipe+"\n";
         }
        mText.setValue(listRecipes);
        return mText;
    }
}