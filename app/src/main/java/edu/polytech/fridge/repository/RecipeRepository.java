package edu.polytech.fridge.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;


import edu.polytech.fridge.models.Recipe;

public final class RecipeRepository {

	private static final String COLLECTION_NAME = "Receipes";
	private static final String ID_FIELD = "id";
	private static final String INGREDIENTS_FIELD = "ingredients";
	private static final String NOM_FIELD = "nom";
	private static final String PREPARATION_FIELD = "preparation";

	private static volatile RecipeRepository instance;
	public static List<Recipe> recipes = new ArrayList<>();

	private RecipeRepository() {

	}

	public static RecipeRepository getInstance() {
		RecipeRepository result = instance;
		if (result != null) {
			return result;
		}

		synchronized (RecipeRepository.class) {
			if (instance == null) {
				instance = new RecipeRepository();
			}
			return instance;
		}
	}




	// Get Recipe Data from Firestore
	public List<Recipe> getRecipeData()  {

		recipes = new ArrayList<>();
		synchronized (recipes) {

			FirebaseFirestore.getInstance().collection("Receipes")
					.get()
					.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
						@Override
						public void onComplete(@NonNull Task<QuerySnapshot> task) {
							if (task.isSuccessful()) {
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
							} else {
								Log.w("Erreur de récupération firebase", "Error getting documents.", task.getException());
							}

						}
					});
		}


			//recipes.add(new Recipe(1, "couscous", "aaaa", "ppp"));
			System.out.println("######nombre de recettes récupérées: " + recipes.size());

			return recipes;
		//}
	}
}