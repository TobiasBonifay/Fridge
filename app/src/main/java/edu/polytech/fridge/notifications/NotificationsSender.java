package edu.polytech.fridge.notifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.core.app.NotificationCompat;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;
import java.util.Random;
import java.util.logging.Logger;

import edu.polytech.fridge.MainActivity;
import edu.polytech.fridge.R;

public class NotificationsSender {
    private Context context;

    public NotificationsSender(Context context) {
        this.context = context;
    }

    /**
     * Create a thread which display a notification
     *
     * @param notificationsSender
     * @param imageUrl url of the image to display under the notification
     */
    public void newNotification(NotificationsSender notificationsSender, final String text, final String imageUrl) {
        this.context = notificationsSender.context;
        new Thread(() -> {
            final Bitmap img = fetchImage(imageUrl);
            showNotificationWithImage(text, img);
        }).start();
    }

    /**
     * Try to get the image as a Bitmap from a given link as parameter
     *
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
     *
     * @param img  Image in bitmap format
     * @param text Text (String) to display on the notification
     */
    private void showNotificationWithImage(final String text, final Bitmap img) {
        final String title = "FRIDGE";
        final int notificationId = new Random().nextInt(100);
        final String channelId = "notification.channel2";
        final int flags = PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT;

        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
        Intent intent = new Intent(context, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, flags);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId);

        builder.setSmallIcon(R.drawable.ic_refrigerator);
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        builder.setContentTitle(title);
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
}
