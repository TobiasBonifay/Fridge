package edu.polytech.fridge.ui.fridge.model;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import edu.polytech.fridge.models.Recipe;
import edu.polytech.fridge.repository.RecipeRepository;

public class RecipeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private RecipeRepository recipeRepository;

    public RecipeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Liste de recettes");
        recipeRepository = RecipeRepository.getInstance();
    }

    public LiveData<String> getText() {

        List<Recipe> recipes = new ArrayList<>();

        //*******
        FirebaseFirestore.getInstance().collection("Receipes")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            String listRecipes = "\n\n";
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                System.out.println("Recette récupérée : " + document.getString("nom"));
                                Recipe item = document.toObject(Recipe.class);
                                System.out.println("******" + item.toString());
                                //add to list
                                recipes.add(item);
                                System.out.println("nombre de recettes: " + recipes.size());
                                //recipes.notify();
                                Log.d("récupération depuis firebase", document.getId() + " => " + document.getData());
                            }
                            Log.d("fin des recettes","");
                            for (Recipe recipe : recipes){
                                listRecipes+= "Recette de "+recipe.getNom()+""+"\n"
                                +"Les ingrédients: "+recipe.getIngredients()+"\n"
                                +"La préparation: "+recipe.getPreparation()+"\n"
                                +"________________________________"+"\n";
                            }


                            mText.setValue(listRecipes);
                        } else {
                            Log.w("Erreur de récupération firebase", "Error getting documents.", task.getException());
                        }

                    }
                });



    //recipes.add(new Recipe(1, "couscous", "aaaa", "ppp"));
			System.out.println("######nombre de recettes récupérées: " + recipes.size());



    //******

        return mText;
    }
}