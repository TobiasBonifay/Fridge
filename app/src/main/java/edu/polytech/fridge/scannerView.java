package edu.polytech.fridge;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ZygotePreload;
import android.os.Bundle;
import com.google.zxing.*;




public class scannerView extends AppCompatActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner_view);
    }
}