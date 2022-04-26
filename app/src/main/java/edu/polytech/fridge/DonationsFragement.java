package edu.polytech.fridge;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.List;

import edu.polytech.fridge.fridge.data.Fridge;
import edu.polytech.fridge.fridge.model.FoodViewModel;
import edu.polytech.fridge.map.MapActivity;


public class DonationsFragement extends Fragment {
    ImageView  openCamera;
    Button  donation;
    EditText name, quantity;

    Button btn;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_donations_fragement, container, false);
//        imageView = view.findViewById(R.id.picture);
        openCamera = view.findViewById(R.id.openCamera2);
        donation = view.findViewById(R.id.donate);
        name = view.findViewById(R.id.textView);
        quantity = view.findViewById(R.id.textView2);
        btn = view.findViewById(R.id.button2);
        donation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<FoodViewModel> list =  Fridge.getInstance().getFoodList();
                for (FoodViewModel e : list){
                    if(e.getFoodName().equals(name.getText().toString())){
                        int q = e.getCurrentQuantity()-Integer.parseInt(quantity.getText().toString());
                        e.setCurrentQuantity(q);
                    }
                }
                startActivity(new Intent(getActivity(), MapActivity.class));
            }
        });
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions( getActivity(),
                    new String[]{Manifest.permission.CAMERA},  101);


        }
        openCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,101);


            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DonationsFragement.this.getActivity(),AddEvent.class);
                startActivity(i);
            }
        });


        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == 101){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            openCamera.setImageBitmap(bitmap);
        }
    }
}