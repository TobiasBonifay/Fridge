package edu.polytech.fridge.fridge.viewmodel;


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

    }