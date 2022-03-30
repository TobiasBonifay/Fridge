package edu.polytech.fridge.map;

import android.os.Bundle;
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

import edu.polytech.fridge.R;
import edu.polytech.fridge.databinding.ActivityMainBinding;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {
    GoogleMap map;
    private ListView listView;

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        LatLng Nice =  new LatLng(43.681035, 7.224034);
        map.addMarker(new MarkerOptions().position(Nice).title("Nice"));
        map.moveCamera(CameraUpdateFactory.newLatLng(Nice));
    }
}
