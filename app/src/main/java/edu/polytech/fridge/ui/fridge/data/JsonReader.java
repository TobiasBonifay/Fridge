package edu.polytech.fridge.ui.fridge.data;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

import edu.polytech.fridge.ui.fridge.view.FoodViewModel;

public class JsonReader {

    public static void fetchContent(Context context) {
        String jsonFileString = Utils.getJsonFromAssets(context, "data.json");
        Log.i("data", jsonFileString);
        Gson gson = new Gson();
        Type listUserType = new TypeToken<List<FoodViewModel>>() { }.getType();
        List<FoodViewModel> foods = gson.fromJson(jsonFileString, listUserType);
        for (int i = 0; i < foods.size(); i++) {
            Log.i("dataJSONNNN", "> Item " + i + "\n" + foods.get(i));
        }
    }
}

