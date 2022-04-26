package edu.polytech.fridge.fridge.model;


import static edu.polytech.fridge.RecipeFragment.recipes;

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

import edu.polytech.fridge.models.Recipe;

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

    // Get Recipe Data from Firestore
    public synchronized void getRecipeData() {

        synchronized (recipes) {
            recipes = new ArrayList<>();
            FirebaseFirestore.getInstance().collection("Receipes")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    System.out.println("Recette récupérée : " + document.getString("nom"));
                                    Recipe item = document.toObject(Recipe.class);
                                    //add to list
                                    recipes.add(item);
                                    System.out.println("nombre de recettes: " + recipes.size());
                                    //recipes.notify();
                                    Log.d("récupération depuis firebase", document.getId() + " => " + document.getData());
                                }
                                Log.d("fin des recettes", "");
                            } else {
                                Log.w("Erreur de récupération firebase", "Error getting documents.", task.getException());
                            }

                        }
                    });
            System.out.println("######  nombre de recettes récupérées: " + recipes.size());
        }
    }
}