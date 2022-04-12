package edu.polytech.fridge;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import edu.polytech.fridge.databinding.ActivityMainBinding;
import edu.polytech.fridge.map.MapsActivity2;

public class MainActivity extends AppCompatActivity {
    GoogleMap map;
    Button btn;
    Intent intent;
    private ListView listView;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        listView=findViewById(androidx.appcompat.R.id.select_dialog_listview);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_fridge,
                R.id.navigation_recipe,
                R.id.navigation_notifications)
                .build();
        btn=findViewById(R.id.butt1);
        intent = new Intent(MainActivity.this, MapsActivity2.class);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });




    }


}