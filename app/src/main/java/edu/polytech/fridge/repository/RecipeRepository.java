package edu.polytech.fridge.repository;

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

import edu.polytech.fridge.models.Recipe;

public final class RecipeRepository {

	private static final String COLLECTION_NAME = "Receipes";
	private static final String ID_FIELD = "id";
	private static final String INGREDIENTS_FIELD = "ingredients";
	private static final String NOM_FIELD = "nom";
	private static final String PREPARATION_FIELD = "preparation";

	private static volatile RecipeRepository instance;

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


	// Get the Collection Reference
	private CollectionReference getRecipesCollection() {
		return FirebaseFirestore.getInstance().collection(COLLECTION_NAME);
	}

	// Get Recipe Data from Firestore
	public List<Recipe> getRecipeData() {
		List<Recipe> recipes = new ArrayList<>();
		/*
		QuerySnapshot result = this.getRecipesCollection().get().getResult();
		List<DocumentSnapshot> documents = result.getDocuments();
		for (DocumentSnapshot document : documents) {
			int id = (int) document.get(ID_FIELD);
			String nom = document.getString(NOM_FIELD);
			String ingredients = document.getString(INGREDIENTS_FIELD);
			String preparation = document.getString(PREPARATION_FIELD);
			Recipe recipe = new Recipe(id,nom,ingredients, preparation);
			recipes.add(recipe);
			System.out.println("Recette récupérée : "+recipe);
		}*/
		//-----------------------------------------
		this.getRecipesCollection().get()
				.continueWithTask(new Continuation<QuerySnapshot, Task<List<QuerySnapshot>>>() {
					@Override
					public Task<List<QuerySnapshot>> then(@NonNull Task<QuerySnapshot> task) {
						List<Task<QuerySnapshot>> tasks = new ArrayList<Task<QuerySnapshot>>();
						for (DocumentSnapshot ds : task.getResult()) {
							tasks.add(ds.getReference().collection("thingstodo").get());
						}

						return Tasks.whenAllSuccess(tasks);
					}
				})
				.addOnCompleteListener(new OnCompleteListener<List<QuerySnapshot>>() {
					@Override
					public void onComplete(@NonNull Task<List<QuerySnapshot>> task) {
						// BTW, `getResult()` will throw an exception if the task fails unless you first check for `task.isSuccessful()`
						List<QuerySnapshot> list = task.getResult();
						for (QuerySnapshot qs : list) {
							for (DocumentSnapshot ds : qs) {
								System.out.println("Recette récupérée : "+ds.getString("nom"));
								Recipe item = ds.toObject(Recipe.class);
								//add to list including day
								recipes.add(item);

							}
						}
					}
				});
		return recipes;
	}
}