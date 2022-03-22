package edu.polytech.fridge.ui.notifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.Objects;
import java.util.Random;

import edu.polytech.fridge.MainActivity;
import edu.polytech.fridge.R;
import edu.polytech.fridge.databinding.FragmentNotificationsBinding;
import edu.polytech.fridge.ui.fridge.FridgeFragment;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textNotifications;
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        final Button showNotificationButton = binding.showNotificationExample;
        showNotificationButton.setOnClickListener(view -> showNotification());

        return root;
    }

    private void showNotification() {
        int notificationId = new Random().nextInt(100);
        String channelId = "notification.channel";

        NotificationManager notificationManager = (NotificationManager) requireActivity()
                .getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent = new Intent(
                requireContext().getApplicationContext(),
                MainActivity.class
        );
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent pendingIntent = PendingIntent.getActivity(
                requireContext().getApplicationContext(),
                0,
                intent,
                PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT
                // setting the mutability flag
        );

        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                requireContext().getApplicationContext(), channelId
        );

        builder.setSmallIcon(R.drawable.ic_refrigerator);
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        builder.setContentTitle("FRIDGE");
        builder.setContentText("Warning");
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        builder.setPriority(NotificationCompat.PRIORITY_HIGH);

        if (notificationManager != null && notificationManager.getNotificationChannel(channelId) == null) {
            NotificationChannel notificationChannel = new NotificationChannel(
                    channelId,
                    "notification.channel",
                    NotificationManager.IMPORTANCE_HIGH);

            notificationChannel.setDescription("Notif channel to notify user");
            notificationChannel.enableVibration(true);
            notificationChannel.enableLights(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        Notification notification = builder.build();
        Objects.requireNonNull(notificationManager).notify(notificationId, notification);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}