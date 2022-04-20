package edu.polytech.fridge.ui.fridge.data;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

public class Utils {
    static String getJsonFromAssets(Context context, String fileName) {
        String jsonString;
        try {
            InputStream is = context.getAssets().open(fileName);
            int size = 0;
            try {
                size = is.available();
            } catch (IOException e) {
                e.printStackTrace();
            }
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jsonString = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return jsonString;
    }
}