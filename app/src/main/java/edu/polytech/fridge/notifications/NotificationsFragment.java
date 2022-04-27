package edu.polytech.fridge.notifications;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import edu.polytech.fridge.databinding.FragmentNotificationsBinding;
import edu.polytech.fridge.notifications.vmc.NotificationsController;
import edu.polytech.fridge.notifications.vmc.NotificationsView;

public class NotificationsFragment extends Fragment {
    private FragmentNotificationsBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

        }

        NotificationsView notificationsView = new NotificationsView(binding.getRoot());
        NotificationsController notificationsController = new NotificationsController(this, notificationsView);
        notificationsView.setController(notificationsController);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}