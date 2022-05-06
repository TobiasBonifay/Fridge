package edu.polytech.fridge.fridge.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import edu.polytech.fridge.R;
import edu.polytech.fridge.models.RecipeModel;

public class RecipeCustomAdapter extends BaseAdapter {

    private final List<RecipeModel> listData;
    private final LayoutInflater layoutInflater;
    private final Context context;
    private final boolean mode;

    public RecipeCustomAdapter(final Context aContext, final List<RecipeModel> listData, final boolean mode) {
        context = aContext;
        this.listData = listData;
        this.layoutInflater = LayoutInflater.from(aContext);
        this.mode = mode;

    }

    @Override
    public int getCount() {
        return this.listData.size();
    }

    @Override
    public Object getItem(final int position) {
        return this.listData.get(position);
    }

    @Override
    public long getItemId(final int position) {
        return position;
    }

    public View getView(final int position, View convertView, final ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            if (this.mode) {//mode âgé activé
                convertView = this.layoutInflater.inflate(R.layout.list_recipe_item_layout_lite, null);
            } else {//mode normal
                convertView = this.layoutInflater.inflate(R.layout.list_recipe_item_layout, null);
                holder.ingredientsView = convertView.findViewById(R.id.tvIngredients);
                holder.preparationView = convertView.findViewById(R.id.tvPreparation);
            }
            holder.imageView = convertView.findViewById(R.id.recipeImageView);
            holder.titreView = convertView.findViewById(R.id.tvTitre);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final RecipeModel recipeModel = listData.get(position);
        holder.titreView.setText(recipeModel.getNom());
        if (!mode) {
            holder.ingredientsView.setText("Ingrédients: " + recipeModel.getIngredients());
            holder.preparationView.setText("Préparation: " + recipeModel.getPreparation());
        }
        //Chargement de l'image
        //recipe.setImageUrl("https://cdn2.iconfinder.com/data/icons/bakery-related-line/64/bakery_pastry_cooking-06-512.png");
        if (recipeModel.getImageUrl() != null) {
            Picasso.with(context.getApplicationContext()).load(recipeModel.getImageUrl()).into(holder.imageView);
        } else {
            recipeModel.setImageUrl("https://cdn2.iconfinder.com/data/icons/bakery-related-line/64/bakery_pastry_cooking-06-512.png");
        }
        //2eme methode
        //imageView
//        int imageId = this.getMipmapResIdByName(recipe.getImageUrl());
//        holder.imageView.setImageResource(imageId);

        return convertView;
    }

    // Find Image ID corresponding to the name of the image (in the directory mipmap).
    public int getMipmapResIdByName(final String resName) {
        final String pkgName = this.context.getPackageName();
        // Return 0 if not found.
        final int resID = this.context.getResources().getIdentifier(resName, "mipmap", pkgName);
        Log.i("CustomListView", "Res Name: " + resName + "==> Res ID = " + resID);
        return resID;
    }

    static class ViewHolder {
        ImageView imageView;
        TextView titreView;
        TextView ingredientsView;
        TextView preparationView;
    }

}
