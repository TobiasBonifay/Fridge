package edu.polytech.fridge.map;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import edu.polytech.fridge.R;
import edu.polytech.fridge.databinding.ActivityAddFoodItemBinding;
import edu.polytech.fridge.databinding.ActivityMapsBinding;

public class MapActivity extends AppCompatActivity implements LocationListener, GoogleMap.OnMarkerClickListener,
        OnMapReadyCallback {
    private ActivityMapsBinding binding;
    private LocationManager lm;
    private static final int REQUEST_CODE = 1234;
    private SupportMapFragment mapFragment;
    private GoogleMap ggMap;
    private LatLng ggPosition;
    private Geocoder coder;
    private boolean aBoolean = true;
    SearchView searchView;
    private boolean sumbitText = false;
    Button association;
    private double lat, lng;
    public static String name;


    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        coder = new Geocoder(this, Locale.getDefault());

        searchView = findViewById(R.id.idSearchView);

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        checkPermission();
        mapFragment.getMapAsync(this);
        /*
        association= view.findViewById(R.id.donation);
        association.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder googlePlaceUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
                googlePlaceUrl.append("location="+lat+","+lng);
                googlePlaceUrl.append("&radius="+8000);
                googlePlaceUrl.append("&type=parking");
                googlePlaceUrl.append("&sensor=true");
                googlePlaceUrl.append("&key="+"AIzaSyBtJo4kSMTPBDmf-d4Vt2BFRjn6NKJqv7g");
                Toast.makeText(MapActivity.this, "Showing Nearby Hospitals", Toast.LENGTH_SHORT).show();
                String url = googlePlaceUrl.toString();
                Object dataFetch[]=new Object[2];
                dataFetch[0]=ggMap;
                dataFetch[1]=url;
                FetchData fetchData = new FetchData();
                fetchData.execute(dataFetch);

            }
        });

         */
        View locationButton = ((View) mapFragment.getView().findViewById(Integer.parseInt("1")).getParent()).findViewById(Integer.parseInt("2"));
        RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) locationButton.getLayoutParams();
        rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
        rlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        rlp.setMargins(0, 0, 30, 30);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                String location = searchView.getQuery().toString();
                sumbitText = true;
                List<Address> addressList = null;
                if (location != null || location.equals("")) {
                    Geocoder geocoder = new Geocoder(MapActivity.this);
                    try {
                        addressList = geocoder.getFromLocationName(location, 1);
                        System.out.println(addressList);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (addressList != null && addressList.size() > 0) {

                        Address address = addressList.get(0);
                        LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                        ggMap.addMarker(new MarkerOptions().position(latLng).title(location));
                        ggMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                    }
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


    }



    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap mGoogleMap) {
        this.ggMap = mGoogleMap;
        ggMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        ggMap.setMyLocationEnabled(true);
        ggMap.addMarker(new MarkerOptions().position(new LatLng(43.61705334494936, 7.080146423829886)).icon(bitmapDesciptorFromVector(getApplicationContext(),R.drawable.ic_donate_donation_svgrepo_com)));
        ggMap.addMarker(new MarkerOptions().position(new LatLng(43.61273253728841, 7.080371161290283)).icon(bitmapDesciptorFromVector(getApplicationContext(),R.drawable.ic_donate_donation_svgrepo_com)));
        ggMap.addMarker(new MarkerOptions().position(new LatLng(43.623323892830854, 7.075558231733037)).icon(bitmapDesciptorFromVector(getApplicationContext(),R.drawable.ic_donate_donation_svgrepo_com)));
        ggMap.addMarker(new MarkerOptions().position(new LatLng(43.617164927719976, 7.047458647488262)).icon(bitmapDesciptorFromVector(getApplicationContext(),R.drawable.ic_donate_donation_svgrepo_com)));

    }

    @Override
    protected void onResume() {
        super.onResume();
        checkPermission();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == this.REQUEST_CODE) {
            checkPermission();
        }
    }

    private void checkPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
            }, this.REQUEST_CODE);
            return;
        }
        lm = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        if (lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            lm.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, 10000, 0, this);
        }
        if (lm.isProviderEnabled(LocationManager.PASSIVE_PROVIDER)) {
            lm.requestLocationUpdates(
                    LocationManager.PASSIVE_PROVIDER, 10000, 0, this);
        }
        if (lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            lm.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER, 10000, 0, this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    public void onLocationChanged(@NonNull Location location) {
        DatabaseReference base =  FirebaseDatabase.getInstance().getReference("uploads");

        Bitmap bitmap=null;

        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        if (ggMap != null) {
            ggPosition = new LatLng(latitude, longitude);
            ggMap.setOnMarkerClickListener(this);
            if (sumbitText == false && aBoolean == true) {
                ggMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ggPosition, 15));
                Marker marker = ggMap.addMarker(new MarkerOptions().position(ggPosition).title("Donated food"));
                marker.showInfoWindow();
            }
            aBoolean = false;


        }
    }
    private BitmapDescriptor bitmapDesciptorFromVector(Context context,int vectorResId){
        Drawable vectorDrawable = ContextCompat.getDrawable(context,vectorResId);
        vectorDrawable.setBounds(0,0,vectorDrawable.getIntrinsicWidth(),vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap=Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),vectorDrawable.getIntrinsicHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }


    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        return false;
    }
    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }
}