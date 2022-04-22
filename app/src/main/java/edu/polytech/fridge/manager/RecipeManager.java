package edu.polytech.fridge.manager;

import com.google.android.gms.tasks.Task;


import java.util.List;

import edu.polytech.fridge.models.Recipe;
import edu.polytech.fridge.repository.RecipeRepository;


public class RecipeManager {
	private static volatile RecipeManager instance;
	private RecipeRepository recipeRepository;
    private RecipeManager(){ recipeRepository = RecipeRepository.getInstance();}
	
		public static RecipeManager getInstance(){
		RecipeManager result = instance;
		if( result != null){
			return result;
		}
		
		synchronized(RecipeManager.class){
			if(instance == null){
				instance = new RecipeManager();
			}
			return instance;
		}
	}


//    public Task<Recipe> getRecipeData(){
//        // Get the Recipe from Firestore and cast it to a Recipe model Object
//        return recipeRepository.getRecipeData().continueWith(task -> task.getResult().toObject(Recipe.class)) ;
//    }

	public List<Recipe> getAllRecipesData(){
		// Get the Recipe from Firestore and cast it to a Recipe model Object
		return recipeRepository.getRecipeData();
	}
	
}