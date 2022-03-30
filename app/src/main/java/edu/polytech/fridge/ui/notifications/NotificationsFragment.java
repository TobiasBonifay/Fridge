package edu.polytech.fridge.ui.notifications;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
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

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;
import java.util.Random;
import java.util.logging.Logger;

import edu.polytech.fridge.MainActivity;
import edu.polytech.fridge.R;
import edu.polytech.fridge.databinding.FragmentNotificationsBinding;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel = new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textNotifications;
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        final Button showNotificationButton = binding.showNotificationExample;
        showNotificationButton.setOnClickListener(view -> showNotification("test","https://www.onceuponachef.com/images/2011/11/potato-leek-soup-14.jpg"));

        return root;
    }

    private void showNotificationWithImage(final Bitmap img, final String text) {
        final Context appContext = requireContext().getApplicationContext();
        final int notificationId = new Random().nextInt(100);
        final String channelId = "notification.channel2";

        NotificationManager notificationManager = (NotificationManager) requireActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(appContext, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(appContext, 0, intent, PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(appContext, channelId);

        builder.setSmallIcon(R.drawable.ic_refrigerator);
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        builder.setContentTitle("FRIDGE");
        builder.setContentText(text);
        builder.setContentIntent(pendingIntent);
        builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(img));
        builder.setAutoCancel(true);
        builder.setPriority(NotificationCompat.PRIORITY_HIGH);

        if (notificationManager != null && notificationManager.getNotificationChannel(channelId) == null) {
            NotificationChannel notificationChannel = new NotificationChannel(channelId, "notification.channel2", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setDescription("Notif channel to notify user");
            notificationChannel.enableVibration(true);
            notificationChannel.enableLights(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        Notification notification = builder.build();
        Objects.requireNonNull(notificationManager).notify(notificationId, notification);
    }


    @SuppressLint("StaticFieldLeak")
    private void showNotification(String text, String url) {
        new AsyncTask<String, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(String... strings) {
                InputStream inputStream;
                try {
                    URL url = new URL(strings[0]);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.connect();
                    inputStream = httpURLConnection.getInputStream();
                    return BitmapFactory.decodeStream(inputStream);
                } catch (Exception e) {
                    Logger.getLogger("notif", "streamerror");
                    return null;
                }
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);
                showNotificationWithImage(bitmap, "testing");
            }
        }.execute(url);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}