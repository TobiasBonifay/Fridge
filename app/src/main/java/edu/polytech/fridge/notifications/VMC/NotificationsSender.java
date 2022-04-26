package edu.polytech.fridge.notifications.VMC;

import android.app.Notification;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationsSender {
    private final Context context;
    public static int notificationId = 0;

    public NotificationSender(Context context) {
        this.context = context;
    }

    public void sendNotification(String title, String message, String channelId, int priority) {
        Bitmap bitmap= BitmapFactory.decodeResource(context.getResources(),R.drawable.farine_de_ble);
        Bitmap bitmap2= BitmapFactory.decodeResource(context.getResources(),R.drawable.calendarnotif);
        NotificationCompat.Builder notification = new NotificationCompat.Builder(context, channelId)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(priority)
                .setShowWhen(true);
        switch(channelId) {
            case CHANNEL_suggestion: notification.setSmallIcon(R.drawable.full_heart).setStyle(new NotificationCompat.BigPictureStyle()
                    .bigPicture(bitmap).setSummaryText(message)
            );break;
            case CHANNEL_rappelcourse: notification.setTimeoutAfter(20000).setSmallIcon(R.drawable.bell).setStyle(new NotificationCompat.BigPictureStyle()
                    .bigPicture(bitmap2).setSummaryText(message));break;

        }

        Notification notificationBuilt = notification.build();
        NotificationManagerCompat.from(context).notify(notificationId, notificationBuilt);
        NotificationsListener.onNotificationPosted(notificationBuilt);
    }

    public static void mockNotifications(NotificationsSender notificationSender) {
        String title = "Mock notification suggestion";
        String titlerappel = "Mock notification rappel";
        String message = "message ";
        int priority = 1;
        int priorityrappel = 2;

        for (int id = 0; id < 10; id++) {
            notificationSender.sendNotification(title, message + id, CHANNEL_suggestion, priority);
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
        notificationId+=1;
        for (int id = 0; id < 10; id++) {

            notificationSender.sendNotification(titlerappel, message + id, CHANNEL_rappelcourse, priority);}
    }
}
