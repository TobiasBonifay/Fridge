package edu.polytech.fridge;

import static edu.polytech.fridge.gps.IGPSActivity.REQUEST_CODE;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;



public class NavigationFragment extends Fragment {
    public NavigationFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_navigation, container, false);
        rootView.findViewById(R.id.buttonCheckPermission).setOnClickListener(view -> {
            if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            }
        });
        rootView.findViewById(R.id.buttonSwitchOnOffGPS).setOnClickListener(view -> {
            Toast toast = Toast.makeText(getContext(), "You can Change Now", Toast.LENGTH_LONG);
            toast.show();
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        });
        rootView.findViewById(R.id.buttonSettings).setOnClickListener(view -> {
            Toast toast = Toast.makeText(getContext(), "Custom setting to this app", Toast.LENGTH_LONG);
            toast.show();
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.fromParts("package", requireActivity().getPackageName(), null));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });
        return rootView;
    }
}
