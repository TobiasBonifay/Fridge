package edu.polytech.fridge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.TextView;
import android.Manifest;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
public class ScanToAdd extends AppCompatActivity implements ZXingScannerView.ResultHandler
{
    ZXingScannerView scannerView;
    public static TextView scantext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView=new ZXingScannerView(this);
        setContentView(scannerView);

        Dexter.withContext(getApplicationContext())
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        scannerView.startCamera();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();
    }

    @Override
    public void handleResult(Result rawResult) {
        Vibrator vibrator =(Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(400L);
        //ScanToAdd.scantext.setText(rawResult.getText());
        //Toast.makeText(this,rawResult.getText(),Toast.LENGTH_LONG).show();
        if (rawResult.getBarcodeFormat() != BarcodeFormat.EAN_13 || rawResult.getBarcodeFormat() == BarcodeFormat.EAN_13 ) {

            Toast.makeText(this, rawResult.getText() + " is added succesfully to the fridge" , Toast.LENGTH_LONG).show();


        } else {

            Intent intent = new Intent(this, FindFoodsActivity.class);
            intent.putExtra("PRODUCT", rawResult.getText());
            startActivity(intent);
        }




        onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }

}