package edu.polytech.fridge;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import edu.polytech.fridge.R;
import edu.polytech.fridge.databinding.FragmentRecipeBinding;
import edu.polytech.fridge.fridge.adapters.RecipeCustomAdapter;
import edu.polytech.fridge.fridge.viewmodel.RecipeViewModel;
import edu.polytech.fridge.models.Recipe;

public class RecipeFragment extends Fragment {

    private FragmentRecipeBinding binding;
    public static List<Recipe> recipes = new ArrayList<>();
    static boolean mode= false;
    RecipeViewModel recipeViewModel;
    public static boolean firstTime = true;
    public static boolean firstFirebase = true;
    public RecipeFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        recipeViewModel =
                new ViewModelProvider(this).get(RecipeViewModel.class);

        binding = FragmentRecipeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textDashboard;
        /** à garder pour le rafraichissement*/
        recipeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        System.out.println("**** onCreateView Fragment: size of recipes="+recipes.size());
        return root;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        /* Populate List View Recipes*/
        //List<Recipe> fake_details = getFakeData(); //Fake Data
        if(recipes.size()==0){
            getRecipeData();
        }
        final Switch switchMode = getView().findViewById(R.id.switchMode);
        final ListView listView = getView().findViewById(R.id.listViewRecipes);
        //real of fake data
        listView.setAdapter(new RecipeCustomAdapter(getActivity(), recipes,mode));
        //listView.setAdapter(new RecipeCustomAdapter(getActivity(), fake_details,mode));

        // When the user clicks on the ListItem
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                Recipe recipe = (Recipe) o;
                showRecipeDetails(recipe);
                //Toast.makeText(getActivity(), "Préparation de la recette:\n :" + " " + recipe.getPreparation(), Toast.LENGTH_LONG).show();
            }
        });
        switchMode.setChecked(mode);
        if(firstTime) {
            firstTime = false;
            refresh();
        }
        switchMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                listView.setAdapter(new RecipeCustomAdapter(getActivity(), recipes,false));
                System.out.println("**** setOnCheckedChangeListener Fragment: size of recipes="+recipes.size());

//                Log.e("Switch: setOnClickListener: ",switchMode.isChecked()+"");
                mode = !mode;
                RecipeFragment f1 = new RecipeFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.nav_host_fragment_activity_main, f1); // f1_container is your FrameLayout container
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.addToBackStack(null);
                ft.setReorderingAllowed(true);
                ft.commit();
            }
        });

    }

    private void refresh() {
        RecipeFragment f1 = new RecipeFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.nav_host_fragment_activity_main, f1); // f1_container is your FrameLayout container
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack(null);
        ft.setReorderingAllowed(true);
        ft.commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    private void showRecipeDetails(Recipe recipe){
        //We need to get the instance of the LayoutInflater, use the context of this activity
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //Inflate the view from a predefined XML layout (no need for root id, using entire layout)
        View layout = inflater.inflate(R.layout.recipe_details,null);
        //load results
        ImageView imageView = (ImageView) layout.findViewById(R.id.recipeIcon);
        //static image
        //recipe.setImageUrl("https://image.shutterstock.com/image-photo/crepe-banana-chocolate-260nw-359513414.jpg");
        Picasso.get().load(recipe.getImageUrl()).into(imageView);

        ((TextView)layout.findViewById(R.id.recipeTitle)).setText(recipe.getNom());
        ((TextView)layout.findViewById(R.id.ingredients)).setText("Ingrédients: "+recipe.getIngredients());
        ((TextView)layout.findViewById(R.id.Préparation)).setText("Préparation: "+recipe.getPreparation());
        //Get the devices screen density to calculate correct pixel sizes
        float density=getActivity().getResources().getDisplayMetrics().density;
        // create a focusable PopupWindow with the given layout and correct size
        final PopupWindow pw = new PopupWindow(layout, (int)density*450, (int)density*700, true);
        //Button to close the pop-up
        ((Button) layout.findViewById(R.id.close)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pw.dismiss();
            }
        });
        //Set up touch closing outside of pop-up
        pw.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        pw.setTouchInterceptor(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    pw.dismiss();
                    return true;
                }
                return false;
            }
        });
        pw.setOutsideTouchable(true);
        // display the pop-up in the center
        pw.showAtLocation(layout, Gravity.CENTER, 0, 0);
    }
    public void getRecipeData() {
        if(firstFirebase ){
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
                                    return;
                                } else {
                                    Log.w("Erreur de récupération firebase", "Error getting documents.", task.getException());
                                }

                            }
                        });
                System.out.println("######  nombre de recettes récupérées: " + recipes.size());
            };
        }
        firstFirebase = false;
    }
}