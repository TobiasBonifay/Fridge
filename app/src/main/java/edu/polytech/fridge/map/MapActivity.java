package edu.polytech.fridge.map;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import edu.polytech.fridge.MainActivity;
import edu.polytech.fridge.R;
import edu.polytech.fridge.databinding.ActivityMainBinding;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {
    private Button button;
    GoogleMap map;
    private ListView listView;

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        button = findViewById(R.id.butt1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        LatLng Nice =  new LatLng(43.681035, 7.224034);
        map.addMarker(new MarkerOptions().position(Nice).title("Nice"));
        map.moveCamera(CameraUpdateFactory.newLatLng(Nice));
    }
}
