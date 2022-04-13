package edu.polytech.fridge.map;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import java.io.IOException;

import edu.polytech.fridge.MainActivity;
import edu.polytech.fridge.NavigationFragment;
import edu.polytech.fridge.R;

import edu.polytech.fridge.gps.GPSFragment;
import edu.polytech.fridge.gps.IGPSActivity;

public class MapsActivity extends FragmentActivity implements IGPSActivity, OnMapReadyCallback {
    private GPSFragment gpsFragment;
    private GoogleMap map;
    private NavigationFragment navigationFragment;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
        gpsFragment = (GPSFragment) getSupportFragmentManager().findFragmentById(R.id.gpsLocation);
        if (gpsFragment == null) {
            gpsFragment = new GPSFragment(this);
            FragmentTransaction gpsTransaction = getSupportFragmentManager().beginTransaction();
            gpsTransaction.replace(R.id.gpsLocation, gpsFragment);
            gpsTransaction.addToBackStack(null);
            gpsTransaction.commit();
        }
        navigationFragment = (NavigationFragment) getSupportFragmentManager().findFragmentById(R.id.navigation);
        if (navigationFragment == null) {
            navigationFragment = new NavigationFragment();
            FragmentTransaction gpsTransaction = getSupportFragmentManager().beginTransaction();
            gpsTransaction.replace(R.id.gpsLocation, navigationFragment);
            gpsTransaction.addToBackStack(null);
            gpsTransaction.commit();
        }
        button = findViewById(R.id.back);
        Intent intent = new Intent(MapsActivity.this, MainActivity.class);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast toast = Toast.makeText(getApplicationContext(), "FINE_LOCATION_Granted", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
            break;
        }
        gpsFragment = new GPSFragment(this);
        FragmentTransaction gpsTransaction = getSupportFragmentManager().beginTransaction();
        gpsTransaction.replace(R.id.gpsLocation, gpsFragment);
        gpsTransaction.addToBackStack(null);
        gpsTransaction.commit();
    }

    @Override
    public void moveCamera() {
        try {
            gpsFragment.setPlaceName("City: " + gpsFragment.getPlaceName());
        } catch (IOException e) {
            gpsFragment.setPlaceName("Unknown city");
        }
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(gpsFragment.getPosition(), 15f));
    }



}