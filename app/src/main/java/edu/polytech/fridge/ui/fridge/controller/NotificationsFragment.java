package edu.polytech.fridge.ui.fridge.controller;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;
import java.util.Random;
import java.util.logging.Logger;

import edu.polytech.fridge.R;
import edu.polytech.fridge.databinding.FragmentNotificationsBinding;
import edu.polytech.fridge.ui.fridge.model.NotificationsViewModel;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel = new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textNotifications;
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        final Button showNotificationButton = binding.showNotificationExample;
        showNotificationButton.setOnClickListener(view -> newNotification("https://www.onceuponachef.com/images/2011/11/potato-leek-soup-14.jpg"));

        return root;
    }

    /**
     * Create a thread which display a notification
     * @param imageUrl url of the image to display under the notification
     */
    private void newNotification(final String imageUrl) {
        new Thread(() -> {
            final Bitmap img = fetchImage(imageUrl);
            showNotificationWithImage("test", img);
        }).start();
    }

    /**
     * Try to get the image as a Bitmap from a given link as parameter
     * @param src link of the image to download
     * @return Bitmap image
     */
    private Bitmap fetchImage(final String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();
            return BitmapFactory.decodeStream(httpURLConnection.getInputStream());
        } catch (Exception e) {
            Logger.getLogger("notif", "streamerror");
            return null;
        }
    }

    /**
     * Show the notification with a given text, and given image
     * @param img Image in bitmap format
     * @param text Text (String) to display on the notification
     */
    private void showNotificationWithImage(final String text, final Bitmap img) {
        final Context appContext = requireContext().getApplicationContext();
        final int notificationId = new Random().nextInt(100);
        final String channelId = "notification.channel2";

        NotificationManager notificationManager = (NotificationManager) requireActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(appContext, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        final int flags = PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT;
        PendingIntent pendingIntent = PendingIntent.getActivity(appContext, 0, intent, flags);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(appContext, channelId);

        builder.setSmallIcon(R.drawable.ic_refrigerator);
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        builder.setContentTitle("FRIDGE");
        builder.setContentText(text);
        builder.setContentIntent(pendingIntent);
        if (img != null) builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(img));
        builder.setAutoCancel(true);
        builder.setPriority(NotificationCompat.PRIORITY_HIGH);

        if (notificationManager != null && notificationManager.getNotificationChannel(channelId) == null) {
            NotificationChannel notificationChannel = new NotificationChannel(channelId, "notification.channel2", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setDescription("Notif channel to notify user");
            notificationChannel.enableVibration(true);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(0xFFFF0000);
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